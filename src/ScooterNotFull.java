import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ScooterNotFull extends Moveable implements Transformable, Executeable{
    private int resourceLimit;
    public ScooterNotFull(String id, Point position, List<PImage> images, int health, int actionPeriod, int animationPeriod, int resourceLimit) {
        super(id, position, images, health, actionPeriod, animationPeriod);
        this.resourceLimit = resourceLimit;
    }

    @Override
    boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (this.position.adjacent(target.position)) {
            this.setResourceCount();
            target.setHealth();
            return true;
        }
        else {
            Point nextPos = this.nextPosition( world, target.position);

            if (!this.position.equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant( nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents( occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }

    @Override
    Point nextPosition(WorldModel world, Point destPos) {
        int horiz = Integer.signum(destPos.getX() - this.position.getX());
        Point newPos = new Point(this.position.getX() + horiz, this.position.getY());

        if (horiz == 0 || world.isOccupied(newPos) && newPos.getOccupancyCell(world).getClass() != Stump.class) {
            int vert = Integer.signum(destPos.getY() - this.position.getY());
            newPos = new Point(this.position.getX(), this.position.getY() + vert);

            if (vert == 0 || world.isOccupied(newPos) &&  newPos.getOccupancyCell(world).getClass() != Stump.class) {
                newPos = this.position;
            }
        }

        return newPos;
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
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.getResourceCount() >= this.resourceLimit) {
            ScooterFull miner = new ScooterFull(this.getid(),
                    this.position,
                    this.getimages(),
                    0,
                    this.getActionPeriod(),
                    this.getAnimationPeriod(),
                    this.resourceLimit);


            this.removeEntity(world);
            scheduler.unscheduleAllEvents( this);

            world.addEntity(miner);
            miner.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> target =
                world.findNearest(this.position, new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));

        if (!target.isPresent() || !this.moveTo(world,
                target.get(),
                scheduler)
                || !this.transform(world, scheduler, imageStore))
        {
            scheduler.scheduleEvent( this,
                    new Activity( this, world, imageStore),
                    this.getActionPeriod());
        }
    }
}
