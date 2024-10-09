import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import processing.core.PImage;

public class Student extends Scheduleable implements Executeable, Transformable{


    public Student(String id, Point position, List<PImage> images, int health, int actionPeriod, int animationPeriod) {
        super(id, position, images, health, actionPeriod, animationPeriod);
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
        if (!this.transform(world, scheduler, imageStore)) {

            scheduler.scheduleEvent( this,
                    new Activity( this,world, imageStore),
                    this.getActionPeriod());
        }
    }

    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        return false;
    }

}