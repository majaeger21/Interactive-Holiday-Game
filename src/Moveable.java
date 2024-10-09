import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public abstract class Moveable extends Scheduleable{

    public Moveable(String id, Point position, List<PImage> images, int health, int actionPeriod, int animationPeriod) {
        super(id, position, images, health,actionPeriod, animationPeriod);
    }

    abstract boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);
    abstract Point nextPosition(WorldModel world, Point destPos);
}

