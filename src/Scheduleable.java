import processing.core.PImage;

import java.util.List;

public abstract class Scheduleable extends Entity {

    private int actionPeriod;
    private int animationPeriod;

    public Scheduleable(String id, Point position, List<PImage> images,int health, int actionPeriod, int animationPeriod) {
        super(id, position, images, health);
        this.actionPeriod = actionPeriod;
        this.animationPeriod = animationPeriod;
    }

    public int getActionPeriod() {
        return actionPeriod;
    }
    public int getAnimationPeriod(){
        return animationPeriod;
    }

    abstract void scheduleActions(EventScheduler scheduler,
                                  WorldModel world,
                                  ImageStore imageStore);
}

