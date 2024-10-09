import processing.core.PImage;

import java.util.List;

public class Sapling extends Scheduleable implements Transformable, Executeable{
    private int healthLimit;
    public Sapling(String id, Point position, List<PImage> images, int health, int actionPeriod, int animationPeriod, int healthLimit) {
        super(id, position, images, health, actionPeriod, animationPeriod);
        this.healthLimit = healthLimit;
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
        else if (this.getHealth() >= this.healthLimit)
        {
            Tree tree = new Tree("tree_" + this.getid(),
                    this.position,
                    imageStore.getImageList( Functions.TREE_KEY),
                    getNumFromRange(Functions.TREE_HEALTH_MAX, Functions.TREE_HEALTH_MIN),
                    getNumFromRange(Functions.TREE_ACTION_MAX,Functions.TREE_ACTION_MIN),
                    getNumFromRange(Functions.TREE_ANIMATION_MAX, Functions.TREE_ANIMATION_MIN));

            this.removeEntity(world);
            scheduler.unscheduleAllEvents( this);

            world.addEntity(tree);
            tree.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        this.setHealth2();
        if (!this.transform(world, scheduler, imageStore))
        {
            scheduler.scheduleEvent(this,
                    new Activity(this, world, imageStore),
                    this.getActionPeriod());
        }
    }
}
