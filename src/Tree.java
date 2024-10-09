import processing.core.PImage;

import java.util.List;

public class Tree extends Scheduleable implements Transformable, Executeable{
    public Tree(String id, Point position, List<PImage> images, int health, int actionPeriod, int animationPeriod) {
        super(id, position, images, health,actionPeriod, animationPeriod);
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
        if (this.getHealth() <= 0) {
            Stump stump = new Stump(this.getid(),
                    this.position,
                    imageStore.getImageList( Functions.STUMP_KEY), 0);

            this.removeEntity(world);
            scheduler.unscheduleAllEvents( this);

            world.addEntity(stump);

            return true;
        }

        return false;
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

        if (!this.transform(world, scheduler, imageStore)) {

            scheduler.scheduleEvent( this,
                    new Activity( this,world, imageStore),
                    this.getActionPeriod());
        }
    }
}
