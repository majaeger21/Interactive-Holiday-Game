import processing.core.PImage;

import java.util.List;

public class Obstacle extends Scheduleable{
    public Obstacle(String id, Point position, List<PImage> images, int health, int actionPeriod, int animationPeriod) {
        super(id, position, images, health,actionPeriod, animationPeriod);
    }

    @Override
    void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
                        scheduler.scheduleEvent( this,
                        new Animation( this, 0),
                        this.getAnimationPeriod());
    }
}
