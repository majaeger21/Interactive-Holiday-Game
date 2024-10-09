import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Fairy extends Moveable implements Executeable{
    public Fairy(String id, Point position, List<PImage> images, int health, int actionPeriod, int animationPeriod) {
        super(id, position, images,health, actionPeriod, animationPeriod);
    }

    private PathingStrategy strategy = new AStarPathingStrategy();

    @Override
    boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
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

    @Override
    Point nextPosition(WorldModel world, Point destPos) {
        List<Point> points = strategy.computePath(getPosition(), destPos,
                p -> world.withinBounds(p) && !world.isOccupied(p),
                Point::adjacent,
                PathingStrategy.CARDINAL_NEIGHBORS);
        if (points.size() != 0)
            return points.get(0);
        return this.getPosition();
//        int horiz = Integer.signum(destPos.getX() - this.position.getX());
//        Point newPos = new Point(this.position.getX() + horiz, this.position.getY());
//
//        if (horiz == 0 || world.isOccupied(newPos)) {
//            int vert = Integer.signum(destPos.getY() - this.position.getY());
//            newPos = new Point(this.position.getX(), this.position.getY() + vert);
//
//            if (vert == 0 || world.isOccupied(newPos)) {
//                newPos = this.position;
//            }
//        }
//
//        return newPos;
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
        Optional<Entity> fairyTarget =
                world.findNearest(this.position, new ArrayList<>(Arrays.asList(Stump.class)));

        if (fairyTarget.isPresent()) {
            Point tgtPos = fairyTarget.get().position;

            if (this.moveTo(world, fairyTarget.get(), scheduler)) {
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
