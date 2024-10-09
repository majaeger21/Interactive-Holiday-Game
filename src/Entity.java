import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */
public  class   Entity
{
//    private EntityKind kind;
    private String id;
    public Point position;
    private List<PImage> images;
    private int imageIndex;
    private int resourceCount;
    private int health;

    //    private int resourceLimit;
//    private int actionPeriod;
//    private int animationPeriod;
//    private int healthLimit;
//
    public int getHealth(){
        return this.health;
    }
    public void setHealth(){
        health--;
    }
    public void setHealth2(){
        health++;
    }

    public int getResourceCount() {
        return resourceCount;
    }
    public void setResourceCount(){
        resourceCount++;
    }
    //
//
//    public int getImageIndex(){
//        return this.imageIndex;
//    }

    public List<PImage> getimages(){
        return this.images;
    }
    public Point getPosition(){
        return this.position;
    }

    public String getid(){
        return this.id;
    }

//    public EntityKind getkind(){
//        return this.kind;
//    }

    public Entity(
//            EntityKind kind,
            String id,
            Point position,
            List<PImage> images,
//            int resourceLimit,
//            int resourceCount,
//            int actionPeriod,
//            int animationPeriod,
            int health)
//            int healthLimit)
    {
//        this.kind = kind;
        this.id = id;
        this.position = position;
        this.images = images;
        this.health = health;
//        this.imageIndex = 0;
//        this.resourceLimit = resourceLimit;
//        this.resourceCount = resourceCount;
//        this.actionPeriod = actionPeriod;
//        this.animationPeriod = animationPeriod;
//        this.health = health;
//        this.healthLimit = healthLimit;
    }
//    public int getAnimationPeriod() {
//        switch (this.kind) {
//            case DUDE_FULL:
//            case DUDE_NOT_FULL:
//            case OBSTACLE:
//            case FAIRY:
//            case SAPLING:
//            case TREE:
//                return this.animationPeriod;
//            default:
//                throw new UnsupportedOperationException(
//                        String.format("getAnimationPeriod not supported for %s",
//                                this.kind));
//        }
//    }
    public void nextImage() {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

//    public void executeSaplingActivity(
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//        this.health++;
//        if (!this.transformPlant(world, scheduler, imageStore))
//        {
//            scheduler.scheduleEvent(this,
//                    Action.createActivityAction(this, world, imageStore),
//                    this.actionPeriod);
//        }
//    }

//    public void executeTreeActivity(
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//
//        if (!this.transformPlant(world, scheduler, imageStore)) {
//
//            scheduler.scheduleEvent( this,
//                    Action.createActivityAction( this,world, imageStore),
//                    this.actionPeriod);
//        }
//    }

//    public void executeFairyActivity(
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//        Optional<Entity> fairyTarget =
//                world.findNearest(this.position, new ArrayList<>(Arrays.asList(EntityKind.STUMP)));
//
//        if (fairyTarget.isPresent()) {
//            Point tgtPos = fairyTarget.get().position;
//
//            if (this.moveToFairy(world, fairyTarget.get(), scheduler)) {
//                Entity sapling = createSapling("sapling_" + this.id, tgtPos,
//                        imageStore.getImageList( Functions.SAPLING_KEY));
//
//                world.addEntity(sapling);
//                sapling.scheduleActions(scheduler, world, imageStore);
//            }
//        }
//
//        scheduler.scheduleEvent( this,
//                Action.createActivityAction( this, world, imageStore),
//                this.actionPeriod);
//    }

//    public void executeDudeNotFullActivity(
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//        Optional<Entity> target =
//                world.findNearest(this.position, new ArrayList<>(Arrays.asList(EntityKind.TREE, EntityKind.SAPLING)));
//
//        if (!target.isPresent() || !this.moveToNotFull(world,
//                target.get(),
//                scheduler)
//                || !this.transformNotFull(world, scheduler, imageStore))
//        {
//            scheduler.scheduleEvent( this,
//                    Action.createActivityAction( this, world, imageStore),
//                    this.actionPeriod);
//        }
//    }

//    public void executeDudeFullActivity(
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//        Optional<Entity> fullTarget =
//               world.findNearest(this.position, new ArrayList<>(Arrays.asList(EntityKind.HOUSE)));
//
//        if (fullTarget.isPresent() && this.moveToFull(world,
//                fullTarget.get(), scheduler))
//        {
//            this.transformFull(world, scheduler, imageStore);
//        }
//        else {
//            scheduler.scheduleEvent( this,
//                    Action.createActivityAction( this, world, imageStore),
//                    this.actionPeriod);
//        }
//    }

//    public void scheduleActions(
//            EventScheduler scheduler,
//            WorldModel world,
//            ImageStore imageStore)
//    {
//        switch (this.kind) {
//            case DUDE_FULL:
//                scheduler.scheduleEvent(this,
//                        Action.createActivityAction( this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent( this,
//                        Action.createAnimationAction( this, 0),
//                        this.getAnimationPeriod());
//                break;
//
//            case DUDE_NOT_FULL:
//                scheduler.scheduleEvent( this,
//                        Action.createActivityAction( this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent( this,
//                        Action.createAnimationAction( this,0),
//                        this.getAnimationPeriod());
//                break;
//
//            case OBSTACLE:
//                scheduler.scheduleEvent( this,
//                        Action.createAnimationAction( this, 0),
//                        this.getAnimationPeriod());
//                break;
//
//            case FAIRY:
//                scheduler.scheduleEvent( this,
//                        Action.createActivityAction( this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent( this,
//                        Action.createAnimationAction( this,0),
//                        this.getAnimationPeriod());
//                break;
//
//            case SAPLING:
//                scheduler.scheduleEvent( this,
//                        Action.createActivityAction( this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent( this,
//                        Action.createAnimationAction( this,0),
//                        this.getAnimationPeriod());
//                break;
//
//            case TREE:
//                scheduler.scheduleEvent( this,
//                        Action.createActivityAction( this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent( this,
//                        Action.createAnimationAction( this,0),
//                        this.getAnimationPeriod());
//                break;
//
//            default:
//        }
//    }
//    public boolean transformNotFull(
//            WorldModel world,
//            EventScheduler scheduler,
//            ImageStore imageStore)
//    {
//        if (this.resourceCount >= this.resourceLimit) {
//            Entity miner = createDudeFull(this.id,
//                    this.position, this.actionPeriod,
//                    this.animationPeriod,
//                    this.resourceLimit,
//                    this.images);
//
//            this.removeEntity(world);
//            scheduler.unscheduleAllEvents( this);
//
//            world.addEntity(miner);
//            miner.scheduleActions(scheduler, world, imageStore);
//
//            return true;
//        }
//
//        return false;
//    }

//    public void transformFull(
//            WorldModel world,
//            EventScheduler scheduler,
//            ImageStore imageStore)
//    {
//        Entity miner = createDudeNotFull(this.id,
//                this.position, this.actionPeriod,
//                this.animationPeriod,
//                this.resourceLimit,
//                this.images);
//
//        this.removeEntity(world);
//        scheduler.unscheduleAllEvents( this);
//
//        world.addEntity(miner);
//        miner.scheduleActions(scheduler, world, imageStore);
//    }

//    public boolean transformPlant(WorldModel world,
//                                          EventScheduler scheduler,
//                                          ImageStore imageStore)
//    {
//        if (this.kind == EntityKind.TREE)
//        {
//            return this.transformTree( world, scheduler, imageStore);
//        }
//        else if (this.kind == EntityKind.SAPLING)
//        {
//            return this.transformSapling(world, scheduler, imageStore);
//        }
//        else
//        {
//            throw new UnsupportedOperationException(
//                    String.format("transformPlant not supported for %s", this));
//        }
//    }

//    public boolean transformSapling(
//            WorldModel world,
//            EventScheduler scheduler,
//            ImageStore imageStore)
//    {
//        if (this.health <= 0) {
//            Entity stump = createStump(this.id,
//                    this.position,
//                    imageStore.getImageList( Functions.STUMP_KEY));
//
//            this.removeEntity(world);
//            scheduler.unscheduleAllEvents( this);
//
//            world.addEntity(stump);
//
//            return true;
//        }
//        else if (this.health >= this.healthLimit)
//        {
//            Entity tree = createTree("tree_" + this.id,
//                    this.position,
//                    getNumFromRange(Functions.TREE_ACTION_MAX, Functions.TREE_ACTION_MIN),
//                    getNumFromRange(Functions.TREE_ANIMATION_MAX, Functions.TREE_ANIMATION_MIN),
//                    getNumFromRange(Functions.TREE_HEALTH_MAX, Functions.TREE_HEALTH_MIN),
//                    imageStore.getImageList( Functions.TREE_KEY));
//
//            this.removeEntity(world);
//            scheduler.unscheduleAllEvents( this);
//
//            world.addEntity(tree);
//            tree.scheduleActions(scheduler, world, imageStore);
//
//            return true;
//        }
//
//        return false;
//    }

//    public boolean moveToFairy(
//            WorldModel world,
//            Entity target,
//            EventScheduler scheduler)
//    {
//        if (this.position.adjacent(target.position)) {
//            target.removeEntity(world);
//            scheduler.unscheduleAllEvents( target);
//            return true;
//        }
//        else {
//            Point nextPos = this.nextPositionFairy( world, target.position);
//
//            if (!this.position.equals(nextPos)) {
//                Optional<Entity> occupant = world.getOccupant( nextPos);
//                if (occupant.isPresent()) {
//                    scheduler.unscheduleAllEvents( occupant.get());
//                }
//
//                world.moveEntity(this, nextPos);
//            }
//            return false;
//        }
//    }

//    public boolean moveToNotFull(
//            WorldModel world,
//            Entity target,
//            EventScheduler scheduler)
//    {
//        if (this.position.adjacent(target.position)) {
//            this.resourceCount += 1;
//            target.health--;
//            return true;
//        }
//        else {
//            Point nextPos = this.nextPositionDude( world, target.position);
//
//            if (!this.position.equals(nextPos)) {
//                Optional<Entity> occupant = world.getOccupant( nextPos);
//                if (occupant.isPresent()) {
//                    scheduler.unscheduleAllEvents( occupant.get());
//                }
//
//                world.moveEntity(this, nextPos);
//            }
//            return false;
//        }
//    }

//    public boolean moveToFull(
//            WorldModel world,
//            Entity target,
//            EventScheduler scheduler)
//    {
//        if (this.position.adjacent(target.position)) {
//            return true;
//        }
//        else {
//            Point nextPos = this.nextPositionDude( world, target.position);
//
//            if (!this.position.equals(nextPos)) {
//                Optional<Entity> occupant = world.getOccupant( nextPos);
//                if (occupant.isPresent()) {
//                    scheduler.unscheduleAllEvents( occupant.get());
//                }
//
//                world.moveEntity(this, nextPos);
//            }
//            return false;
//        }
//    }

//    public void addEntity(WorldModel world) {
//        if (world.withinBounds(this.position)) {
//            this.position.setOccupancyCell(world, this);
//            world.getEntities().add(this);
//        }
//    }

//    public boolean transformTree(
//            WorldModel world,
//            EventScheduler scheduler,
//            ImageStore imageStore)
//    {
//        if (this.health <= 0) {
//            Entity stump = createStump(this.id,
//                    this.position,
//                    imageStore.getImageList( Functions.STUMP_KEY));
//
//            this.removeEntity(world);
//            scheduler.unscheduleAllEvents( this);
//
//            world.addEntity(stump);
//
//            return true;
//        }
//
//        return false;
//    }

//    public void moveEntity(WorldModel world, Point pos) {
//        Point oldPos = this.position;
//        if (world.withinBounds(pos) && !pos.equals(oldPos)) {
//            oldPos.setOccupancyCell(world, null);
//            world.removeEntityAt( pos);
//            pos.setOccupancyCell(world, this);
//            this.position = pos;
//        }
//    }

    public void removeEntity(WorldModel world) {
        world.removeEntityAt( this.position);
    }


//    public Point nextPositionDude(WorldModel world, Point destPos)
//    {
//        int horiz = Integer.signum(destPos.getX() - this.position.getX());
//        Point newPos = new Point(this.position.getX() + horiz, this.position.getY());
//
//        if (horiz == 0 || world.isOccupied(newPos) && newPos.getOccupancyCell(world).kind != EntityKind.STUMP) {
//            int vert = Integer.signum(destPos.getY() - this.position.getY());
//            newPos = new Point(this.position.getX(), this.position.getY() + vert);
//
//            if (vert == 0 || world.isOccupied(newPos) &&  newPos.getOccupancyCell(world).kind != EntityKind.STUMP) {
//                newPos = this.position;
//            }
//        }
//
//        return newPos;
//    }


//    public Point nextPositionFairy(
//WorldModel world, Point destPos)
//    {
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
//    }
//
//    public static Entity createTree(
//            String id,
//            Point position,
//            int actionPeriod,
//            int animationPeriod,
//            int health,
//            List<PImage> images)
//    {
//        return new Entity(EntityKind.TREE, id, position, images, 0, 0,
//                actionPeriod, animationPeriod, health, 0);
//    }
//
//    public static Entity createStump(
//            String id,
//            Point position,
//            List<PImage> images)
//    {
//        return new Entity(EntityKind.STUMP, id, position, images, 0, 0,
//                0, 0, 0, 0);
//    }
//
//    // health starts at 0 and builds up until ready to convert to Tree
//    public static Entity createSapling(
//            String id,
//            Point position,
//            List<PImage> images)
//    {
//        return new Entity(EntityKind.SAPLING, id, position, images, 0, 0,
//                Functions.SAPLING_ACTION_ANIMATION_PERIOD, Functions.SAPLING_ACTION_ANIMATION_PERIOD, 0, Functions.SAPLING_HEALTH_LIMIT);
//    }
//
//    public static Entity createFairy(
//            String id,
//            Point position,
//            int actionPeriod,
//            int animationPeriod,
//            List<PImage> images)
//    {
//        return new Entity(EntityKind.FAIRY, id, position, images, 0, 0,
//                actionPeriod, animationPeriod, 0, 0);
//    }
//
//    // need resource count, though it always starts at 0
//    public static Entity createDudeNotFull(
//            String id,
//            Point position,
//            int actionPeriod,
//            int animationPeriod,
//            int resourceLimit,
//            List<PImage> images)
//    {
//        return new Entity(EntityKind.DUDE_NOT_FULL, id, position, images, resourceLimit, 0,
//                actionPeriod, animationPeriod, 0, 0);
//    }
//
//    public static Entity createDudeFull(
//            String id,
//            Point position,
//            int actionPeriod,
//            int animationPeriod,
//            int resourceLimit,
//            List<PImage> images) {
//        return new Entity(EntityKind.DUDE_FULL, id, position, images, resourceLimit, 0,
//                actionPeriod, animationPeriod, 0, 0);
//    }

    public static PImage getCurrentImage(Entity entity) {
//        if (entity instanceof Background) {
//            return ((Background)entity).getimages().get(
//                    ((Background)entity).getImageIndex());
//        }
//        else if (entity instanceof Entity) {
            return ((Entity)entity).images.get(((Entity)entity).imageIndex);
//        }
//        else {
//            throw new UnsupportedOperationException(
//                    String.format("getCurrentImage not supported for %s",
//                            entity));
//        }
    }

    public int getNumFromRange(int max, int min)
    {
        Random rand = new Random();
        return min + rand.nextInt(
                max
                        - min);
    }



}
