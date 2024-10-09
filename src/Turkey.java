import processing.core.PImage;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Turkey extends Moveable implements Executeable{
    private PathingStrategy strategy = new AStarPathingStrategy();
    private int countMoves = 0;


    public Turkey(String id, Point position, List<PImage> images, int health, int actionPeriod, int animationPeriod) {
        super(id, position, images, health, actionPeriod, animationPeriod);
    }

    @Override
    boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (countMoves == 5){
            countMoves = 0;
            if (this.position.adjacent(target.position)) {
                target.removeEntity(world);
                scheduler.unscheduleAllEvents( target);
                return true;
            }
            else {
                Point nextPos = this.nextPosition( world, target.position);

                if (!this.position.equals(nextPos)) {
                    Optional<Entity> occupant = world.getOccupant( nextPos);
                    occupant.ifPresent(scheduler::unscheduleAllEvents);

                    world.moveEntity(this, nextPos);
                }
                return false;
            }
        }
        else {
            countMoves++;
        }
        return false;
    }

    @Override
    Point nextPosition(WorldModel world, Point destPos) {
        List<Point> points = strategy.computePath(getPosition(), destPos,
                p -> world.withinBounds(p) && !world.isOccupied(p),
                Point::adjacent,
                PathingStrategy.CARDINAL_NEIGHBORS);
        if (points.size() != 0)
            return points.get(0);
        return this.getPosition();
    }

    @Override
    void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent( this,
                new Activity( this, world, imageStore),
                this.getActionPeriod());
        scheduler.scheduleEvent( this,
                new Animation( this,0),
                this.getAnimationPeriod());
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> turkeyTarget =
                world.findNearest(this.position, new ArrayList<>(List.of(Stump.class)));
        Optional<Entity> turkeyTarget2 =
                world.findNearest(this.position, new ArrayList<>(List.of(Student.class)));

        if (turkeyTarget2.isPresent()){
            Point tgtPos = turkeyTarget2.get().position;

            if (this.moveTo(world, turkeyTarget2.get(), scheduler)) {
                Sapling sapling = new Sapling("sapling_" + this.getid(), tgtPos,
                        imageStore.getImageList( Functions.SAPLING_KEY), 0, 0, 0, 0);

                world.addEntity(sapling);
                sapling.scheduleActions(scheduler, world, imageStore);
            }
        } else if (turkeyTarget.isPresent()) {
            Point tgtPos = turkeyTarget.get().position;

            if (this.moveTo(world, turkeyTarget.get(), scheduler)) {
                Sapling sapling = new Sapling("sapling_" + this.getid(), tgtPos,
                        imageStore.getImageList( Functions.SAPLING_KEY), 0, 0, 0, 0);

                world.addEntity(sapling);
                sapling.scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent( this,
                new Activity( this, world, imageStore),
                this.getActionPeriod());
    }
}
