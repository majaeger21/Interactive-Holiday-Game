import java.util.*;

import processing.core.PImage;
import processing.core.PApplet;

/**
 * This class contains many functions written in a procedural style.
 * You will reduce the size of this class over the next several weeks
 * by refactoring this codebase to follow an OOP style.
 */
public final class Functions
{
    private static final Random rand = new Random();

    private static final int COLOR_MASK = 0xffffff;
    private static final int KEYED_IMAGE_MIN = 5;
    private static final int KEYED_RED_IDX = 2;
    private static final int KEYED_GREEN_IDX = 3;
    private static final int KEYED_BLUE_IDX = 4;

    public static final int PROPERTY_KEY = 0;

    private static final List<String> PATH_KEYS = new ArrayList<>(Arrays.asList("bridge", "dirt", "dirt_horiz", "dirt_vert_left", "dirt_vert_right",
            "dirt_bot_left_corner", "dirt_bot_right_up", "dirt_vert_left_bot"));

    public static final String SAPLING_KEY = "sapling";
    public static final int SAPLING_HEALTH_LIMIT = 5;
    public static final int SAPLING_ACTION_ANIMATION_PERIOD = 1000; // have to be in sync since grows and gains health at same time
    private static final int SAPLING_NUM_PROPERTIES = 4;
    private static final int SAPLING_ID = 1;
    private static final int SAPLING_COL = 2;
    private static final int SAPLING_ROW = 3;
    private static final int SAPLING_HEALTH = 4;

    public static final String BGND_KEY = "background";
    public static final int BGND_NUM_PROPERTIES = 4;
    public static final int BGND_ID = 1;
    public static final int BGND_COL = 2;
    public static final int BGND_ROW = 3;

    public static final String FROZEN_WATER_KEY = "frozenWater";
    public static final String OBSTACLE_KEY = "obstacle";
    private static final int OBSTACLE_NUM_PROPERTIES = 5;
    private static final int OBSTACLE_ID = 1;
    private static final int OBSTACLE_COL = 2;
    private static final int OBSTACLE_ROW = 3;
    private static final int OBSTACLE_ANIMATION_PERIOD = 4;

    public static final String DUDE_KEY = "dude";
    private static final int DUDE_NUM_PROPERTIES = 7;
    private static final int DUDE_ID = 1;
    private static final int DUDE_COL = 2;
    private static final int DUDE_ROW = 3;
    private static final int DUDE_LIMIT = 4;
    private static final int DUDE_ACTION_PERIOD = 5;
    private static final int DUDE_ANIMATION_PERIOD = 6;


    public static final String SCOOTER_KEY = "scooter";
    private static final int SCOOTER_ID = 1;
    private static final int SCOOTER_COL = 2;
    private static final int SCOOTER_ROW = 3;
    private static final int SCOOTER_LIMIT = 4;
    private static final int SCOOTER_ACTION_PERIOD = 5;
    private static final int SCOOTER_ANIMATION_PERIOD = 6;

    public static final String HOUSE_KEY = "house";
    public static final String CSB_KEY = "csBuilding";
    private static final int HOUSE_NUM_PROPERTIES = 4;
    private static final int HOUSE_ID = 1;
    private static final int HOUSE_COL = 2;
    private static final int HOUSE_ROW = 3;

    public static final String TURKEY_KEY = "turkey";
    private static final int TURKEY_NUM_PROPERTIES = 6;
    private static final int TURKEY_ID = 1;
    private static final int TURKEY_COL = 2;
    private static final int TURKEY_ROW = 3;
    private static final int TURKEY_ANIMATION_PERIOD = 4;
    private static final int TURKEY_ACTION_PERIOD = 12;

    public static final String FAIRY_KEY = "fairy";
    private static final int FAIRY_NUM_PROPERTIES = 6;
    private static final int FAIRY_ID = 1;
    private static final int FAIRY_COL = 2;
    private static final int FAIRY_ROW = 3;
    private static final int FAIRY_ANIMATION_PERIOD = 4;
    private static final int FAIRY_ACTION_PERIOD = 5;

    public static final String STUMP_KEY = "stump";

    public static final String TREEFALL_KEY = "treeFall";
    public static final String TREE_KEY = "tree";
    private static final int TREE_NUM_PROPERTIES = 7;
    private static final int TREE_ID = 1;
    private static final int TREE_COL = 2;
    private static final int TREE_ROW = 3;
    private static final int TREE_ANIMATION_PERIOD = 4;
    private static final int TREE_ACTION_PERIOD = 5;
    private static final int TREE_HEALTH = 6;

    public static final int TREE_ANIMATION_MAX = 600;
    public static final int TREE_ANIMATION_MIN = 50;
    public static final int TREE_ACTION_MAX = 1400;
    public static final int TREE_ACTION_MIN = 1000;
    public static final int TREE_HEALTH_MAX = 3;
    public static final int TREE_HEALTH_MIN = 1;



//    public static PImage getCurrentImage(Object entity) {
//        if (entity instanceof Background) {
//            return ((Background)entity).images.get(
//                    ((Background)entity).imageIndex);
//        }
//        else if (entity instanceof Entity) {
//            return ((Entity)entity).images.get(((Entity)entity).imageIndex);
//        }
//        else {
//            throw new UnsupportedOperationException(
//                    String.format("getCurrentImage not supported for %s",
//                            entity));
//        }
//    }

//    public static int getAnimationPeriod(Entity entity) {
//        switch (entity.kind) {
//            case DUDE_FULL:
//            case DUDE_NOT_FULL:
//            case OBSTACLE:
//            case FAIRY:
//            case SAPLING:
//            case TREE:
//                return entity.animationPeriod;
//            default:
//                throw new UnsupportedOperationException(
//                        String.format("getAnimationPeriod not supported for %s",
//                                entity.kind));
//        }
//    }

//    public static void nextImage(Entity entity) {
//        entity.imageIndex = (entity.imageIndex + 1) % entity.images.size();
//    }

//    public static void executeAction(Action action, EventScheduler scheduler) {
//        switch (action.kind) {
//            case ACTIVITY:
//                executeActivityAction(action, scheduler);
//                break;
//
//            case ANIMATION:
//                executeAnimationAction(action, scheduler);
//                break;
//        }
//    }
//
//    public static void executeAnimationAction(
//            Action action, EventScheduler scheduler)
//    {
//        action.entity.nextImage();
//
//        if (action.repeatCount != 1) {
//            scheduleEvent(scheduler, action.entity,
//                    createAnimationAction(action.entity,
//                            Math.max(action.repeatCount - 1,
//                                    0)),
//                    action.entity.getAnimationPeriod());
//        }
//    }

//    public static void executeActivityAction(
//            Action action, EventScheduler scheduler)
//    {
//        switch (action.entity.kind) {
//            case SAPLING:
//                executeSaplingActivity(action.entity, action.world,
//                        action.imageStore, scheduler);
//                break;
//
//            case TREE:
//                executeTreeActivity(action.entity, action.world,
//                        action.imageStore, scheduler);
//                break;
//
//            case FAIRY:
//                executeFairyActivity(action.entity, action.world,
//                        action.imageStore, scheduler);
//                break;
//
//            case DUDE_NOT_FULL:
//                executeDudeNotFullActivity(action.entity, action.world,
//                        action.imageStore, scheduler);
//                break;
//
//            case DUDE_FULL:
//                executeDudeFullActivity(action.entity, action.world,
//                        action.imageStore, scheduler);
//                break;
//
//            default:
//                throw new UnsupportedOperationException(String.format(
//                        "executeActivityAction not supported for %s",
//                        action.entity.kind));
//        }
//    }

//    public static void executeSaplingActivity(
//            Entity entity,
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//        entity.health++;
//        if (!transformPlant(entity, world, scheduler, imageStore))
//        {
//            scheduleEvent(scheduler, entity,
//                    createActivityAction(entity, world, imageStore),
//                    entity.actionPeriod);
//        }
//    }

//    public static void executeTreeActivity(
//            Entity entity,
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//
//        if (!transformPlant(entity, world, scheduler, imageStore)) {
//
//            scheduleEvent(scheduler, entity,
//                    createActivityAction(entity, world, imageStore),
//                    entity.actionPeriod);
//        }
//    }

//    public static void executeFairyActivity(
//            Entity entity,
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//        Optional<Entity> fairyTarget =
//                findNearest(world, entity.position, new ArrayList<>(Arrays.asList(EntityKind.STUMP)));
//
//        if (fairyTarget.isPresent()) {
//            Point tgtPos = fairyTarget.get().position;
//
//            if (moveToFairy(entity, world, fairyTarget.get(), scheduler)) {
//                Entity sapling = createSapling("sapling_" + entity.id, tgtPos,
//                        getImageList(imageStore, SAPLING_KEY));
//
//                addEntity(world, sapling);
//                scheduleActions(sapling, scheduler, world, imageStore);
//            }
//        }
//
//        scheduleEvent(scheduler, entity,
//                createActivityAction(entity, world, imageStore),
//                entity.actionPeriod);
//    }

//    public static void executeDudeNotFullActivity(
//            Entity entity,
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//        Optional<Entity> target =
//                findNearest(world, entity.position, new ArrayList<>(Arrays.asList(EntityKind.TREE, EntityKind.SAPLING)));
//
//        if (!target.isPresent() || !moveToNotFull(entity, world,
//                target.get(),
//                scheduler)
//                || !transformNotFull(entity, world, scheduler, imageStore))
//        {
//            scheduleEvent(scheduler, entity,
//                    createActivityAction(entity, world, imageStore),
//                    entity.actionPeriod);
//        }
//    }

//    public static void executeDudeFullActivity(
//            Entity entity,
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//        Optional<Entity> fullTarget =
//                findNearest(world, entity.position, new ArrayList<>(Arrays.asList(EntityKind.HOUSE)));
//
//        if (fullTarget.isPresent() && moveToFull(entity, world,
//                fullTarget.get(), scheduler))
//        {
//            transformFull(entity, world, scheduler, imageStore);
//        }
//        else {
//            scheduleEvent(scheduler, entity,
//                    createActivityAction(entity, world, imageStore),
//                    entity.actionPeriod);
//        }
//    }


//    public static void scheduleActions(
//            Entity entity,
//            EventScheduler scheduler,
//            WorldModel world,
//            ImageStore imageStore)
//    {
//        switch (entity.kind) {
//            case DUDE_FULL:
//                scheduleEvent(scheduler, entity,
//                        createActivityAction(entity, world, imageStore),
//                        entity.actionPeriod);
//                scheduleEvent(scheduler, entity,
//                        createAnimationAction(entity, 0),
//                        entity.getAnimationPeriod());
//                break;
//
//            case DUDE_NOT_FULL:
//                scheduleEvent(scheduler, entity,
//                        createActivityAction(entity, world, imageStore),
//                        entity.actionPeriod);
//                scheduleEvent(scheduler, entity,
//                        createAnimationAction(entity, 0),
//                        entity.getAnimationPeriod());
//                break;
//
//            case OBSTACLE:
//                scheduleEvent(scheduler, entity,
//                        createAnimationAction(entity, 0),
//                        entity.getAnimationPeriod());
//                break;
//
//            case FAIRY:
//                scheduleEvent(scheduler, entity,
//                        createActivityAction(entity, world, imageStore),
//                        entity.actionPeriod);
//                scheduleEvent(scheduler, entity,
//                        createAnimationAction(entity, 0),
//                        entity.getAnimationPeriod());
//                break;
//
//            case SAPLING:
//                scheduleEvent(scheduler, entity,
//                        createActivityAction(entity, world, imageStore),
//                        entity.actionPeriod);
//                scheduleEvent(scheduler, entity,
//                        createAnimationAction(entity, 0),
//                        entity.getAnimationPeriod());
//                break;
//
//            case TREE:
//                scheduleEvent(scheduler, entity,
//                        createActivityAction(entity, world, imageStore),
//                        entity.actionPeriod);
//                scheduleEvent(scheduler, entity,
//                        createAnimationAction(entity, 0),
//                        entity.getAnimationPeriod());
//                break;
//
//            default:
//        }
//    }

//    public static boolean transformNotFull(
//            Entity entity,
//            WorldModel world,
//            EventScheduler scheduler,
//            ImageStore imageStore)
//    {
//        if (entity.resourceCount >= entity.resourceLimit) {
//            Entity miner = createDudeFull(entity.id,
//                    entity.position, entity.actionPeriod,
//                    entity.animationPeriod,
//                    entity.resourceLimit,
//                    entity.images);
//
//            removeEntity(world, entity);
//            unscheduleAllEvents(scheduler, entity);
//
//            addEntity(world, miner);
//            scheduleActions(miner, scheduler, world, imageStore);
//
//            return true;
//        }
//
//        return false;
//    }

//    public static void transformFull(
//            Entity entity,
//            WorldModel world,
//            EventScheduler scheduler,
//            ImageStore imageStore)
//    {
//        Entity miner = createDudeNotFull(entity.id,
//                entity.position, entity.actionPeriod,
//                entity.animationPeriod,
//                entity.resourceLimit,
//                entity.images);
//
//        removeEntity(world, entity);
//        unscheduleAllEvents(scheduler, entity);
//
//        addEntity(world, miner);
//        scheduleActions(miner, scheduler, world, imageStore);
//    }


//    public static boolean transformPlant( Entity entity,
//                                          WorldModel world,
//                                          EventScheduler scheduler,
//                                          ImageStore imageStore)
//    {
//        if (entity.kind == EntityKind.TREE)
//        {
//            return transformTree(entity, world, scheduler, imageStore);
//        }
//        else if (entity.kind == EntityKind.SAPLING)
//        {
//            return transformSapling(entity, world, scheduler, imageStore);
//        }
//        else
//        {
//            throw new UnsupportedOperationException(
//                    String.format("transformPlant not supported for %s", entity));
//        }
//    }

//    public static boolean transformTree(
//            Entity entity,
//            WorldModel world,
//            EventScheduler scheduler,
//            ImageStore imageStore)
//    {
//        if (entity.health <= 0) {
//            Entity stump = createStump(entity.id,
//                    entity.position,
//                    imageStore.getImageList( STUMP_KEY));
//
//            removeEntity(world, entity);
//            scheduler.unscheduleAllEvents( entity);
//
//            stump.addEntity(world);
//
//            return true;
//        }
//
//        return false;
//    }

//    public static boolean transformSapling(
//            Entity entity,
//            WorldModel world,
//            EventScheduler scheduler,
//            ImageStore imageStore)
//    {
//        if (entity.health <= 0) {
//            Entity stump = createStump(entity.id,
//                    entity.position,
//                    getImageList(imageStore, STUMP_KEY));
//
//            removeEntity(world, entity);
//            unscheduleAllEvents(scheduler, entity);
//
//            addEntity(world, stump);
//
//            return true;
//        }
//        else if (entity.health >= entity.healthLimit)
//        {
//            Entity tree = createTree("tree_" + entity.id,
//                    entity.position,
//                    getNumFromRange(TREE_ACTION_MAX, TREE_ACTION_MIN),
//                    getNumFromRange(TREE_ANIMATION_MAX, TREE_ANIMATION_MIN),
//                    getNumFromRange(TREE_HEALTH_MAX, TREE_HEALTH_MIN),
//                    getImageList(imageStore, TREE_KEY));
//
//            removeEntity(world, entity);
//            unscheduleAllEvents(scheduler, entity);
//
//            addEntity(world, tree);
//            scheduleActions(tree, scheduler, world, imageStore);
//
//            return true;
//        }
//
//        return false;
//    }

//    public static boolean moveToFairy(
//            Entity fairy,
//            WorldModel world,
//            Entity target,
//            EventScheduler scheduler)
//    {
//        if (adjacent(fairy.position, target.position)) {
//            removeEntity(world, target);
//            unscheduleAllEvents(scheduler, target);
//            return true;
//        }
//        else {
//            Point nextPos = nextPositionFairy(fairy, world, target.position);
//
//            if (!fairy.position.equals(nextPos)) {
//                Optional<Entity> occupant = getOccupant(world, nextPos);
//                if (occupant.isPresent()) {
//                    unscheduleAllEvents(scheduler, occupant.get());
//                }
//
//                moveEntity(world, fairy, nextPos);
//            }
//            return false;
//        }
//    }

//    public static boolean moveToNotFull(
//            Entity dude,
//            WorldModel world,
//            Entity target,
//            EventScheduler scheduler)
//    {
//        if (adjacent(dude.position, target.position)) {
//            dude.resourceCount += 1;
//            target.health--;
//            return true;
//        }
//        else {
//            Point nextPos = nextPositionDude(dude, world, target.position);
//
//            if (!dude.position.equals(nextPos)) {
//                Optional<Entity> occupant = getOccupant(world, nextPos);
//                if (occupant.isPresent()) {
//                    unscheduleAllEvents(scheduler, occupant.get());
//                }
//
//                moveEntity(world, dude, nextPos);
//            }
//            return false;
//        }
//    }

//    public static boolean moveToFull(
//            Entity dude,
//            WorldModel world,
//            Entity target,
//            EventScheduler scheduler)
//    {
//        if (adjacent(dude.position, target.position)) {
//            return true;
//        }
//        else {
//            Point nextPos = nextPositionDude(dude, world, target.position);
//
//            if (!dude.position.equals(nextPos)) {
//                Optional<Entity> occupant = getOccupant(world, nextPos);
//                if (occupant.isPresent()) {
//                    unscheduleAllEvents(scheduler, occupant.get());
//                }
//
//                moveEntity(world, dude, nextPos);
//            }
//            return false;
//        }
//    }

//    public static Point nextPositionFairy(
//            Entity entity, WorldModel world, Point destPos)
//    {
//        int horiz = Integer.signum(destPos.x - entity.position.x);
//        Point newPos = new Point(entity.position.x + horiz, entity.position.y);
//
//        if (horiz == 0 || newPos.isOccupied(world)) {
//            int vert = Integer.signum(destPos.y - entity.position.y);
//            newPos = new Point(entity.position.x, entity.position.y + vert);
//
//            if (vert == 0 || newPos.isOccupied(world)) {
//                newPos = entity.position;
//            }
//        }
//
//        return newPos;
//    }

//    public static Point nextPositionDude(
//            Entity entity, WorldModel world, Point destPos)
//    {
//        int horiz = Integer.signum(destPos.x - entity.position.x);
//        Point newPos = new Point(entity.position.x + horiz, entity.position.y);
//
//        if (horiz == 0 || newPos.isOccupied(world) && newPos.getOccupancyCell(world).kind != EntityKind.STUMP) {
//            int vert = Integer.signum(destPos.y - entity.position.y);
//            newPos = new Point(entity.position.x, entity.position.y + vert);
//
//            if (vert == 0 || newPos.isOccupied(world) &&  newPos.getOccupancyCell(world).kind != EntityKind.STUMP) {
//                newPos = entity.position;
//            }
//        }
//
//        return newPos;
//    }


//    public static boolean adjacent(Point p1, Point p2) {
//        return (p1.x == p2.x && Math.abs(p1.y - p2.y) == 1) || (p1.y == p2.y
//                && Math.abs(p1.x - p2.x) == 1);
//    }

//    public static int getNumFromRange(int max, int min)
//        {
//            Random rand = new Random();
//            return min + rand.nextInt(
//                    max
//                        - min);
//    }

//    public static void scheduleEvent(
//            EventScheduler scheduler,
//            Entity entity,
//            Action action,
//            long afterPeriod)
//    {
//        long time = System.currentTimeMillis() + (long)(afterPeriod
//                * scheduler.timeScale);
//        Event event = new Event(action, time, entity);
//
//        scheduler.eventQueue.add(event);
//
//        // update list of pending events for the given entity
//        List<Event> pending = scheduler.pendingEvents.getOrDefault(entity,
//                new LinkedList<>());
//        pending.add(event);
//        scheduler.pendingEvents.put(entity, pending);
//    }

//    public static void unscheduleAllEvents(
//            EventScheduler scheduler, Entity entity)
//    {
//        List<Event> pending = scheduler.pendingEvents.remove(entity);
//
//        if (pending != null) {
//            for (Event event : pending) {
//                scheduler.eventQueue.remove(event);
//            }
//        }
//    }

//    public static void removePendingEvent(
//            EventScheduler scheduler, Event event)
//    {
//        List<Event> pending = scheduler.pendingEvents.get(event.entity);
//
//        if (pending != null) {
//            pending.remove(event);
//        }
//    }

//    public static void updateOnTime(EventScheduler scheduler, long time) {
//        while (!scheduler.eventQueue.isEmpty()
//                && scheduler.eventQueue.peek().time < time) {
//            Event next = scheduler.eventQueue.poll();
//
//            next.removePendingEvent(scheduler);
//
//            next.action.executeAction(scheduler);
//        }
//    }

//    public static List<PImage> getImageList(ImageStore imageStore, String key) {
//        return imageStore.images.getOrDefault(key, imageStore.defaultImages);
//    }

//    public static void loadImages(
//            Scanner in, ImageStore imageStore, PApplet screen)
//    {
//        int lineNumber = 0;
//        while (in.hasNextLine()) {
//            try {
//                processImageLine(imageStore.images, in.nextLine(), screen);
//            }
//            catch (NumberFormatException e) {
//                System.out.println(
//                        String.format("Image format error on line %d",
//                                lineNumber));
//            }
//            lineNumber++;
//        }
//    }

    public static void processImageLine(
            Map<String, List<PImage>> images, String line, PApplet screen)
    {
        String[] attrs = line.split("\\s");
        if (attrs.length >= 2) {
            String key = attrs[0];
            PImage img = screen.loadImage(attrs[1]);
            if (img != null && img.width != -1) {
                List<PImage> imgs = getImages(images, key);
                imgs.add(img);

                if (attrs.length >= KEYED_IMAGE_MIN) {
                    int r = Integer.parseInt(attrs[KEYED_RED_IDX]);
                    int g = Integer.parseInt(attrs[KEYED_GREEN_IDX]);
                    int b = Integer.parseInt(attrs[KEYED_BLUE_IDX]);
                    setAlpha(img, screen.color(r, g, b), 0);
                }
            }
        }
    }

    public static List<PImage> getImages(
            Map<String, List<PImage>> images, String key)
    {
        List<PImage> imgs = images.get(key);
        if (imgs == null) {
            imgs = new LinkedList<>();
            images.put(key, imgs);
        }
        return imgs;
    }

    /*
      Called with color for which alpha should be set and alpha value.
      setAlpha(img, color(255, 255, 255), 0));
    */
    public static void setAlpha(PImage img, int maskColor, int alpha) {
        int alphaValue = alpha << 24;
        int nonAlpha = maskColor & COLOR_MASK;
        img.format = PApplet.ARGB;
        img.loadPixels();
        for (int i = 0; i < img.pixels.length; i++) {
            if ((img.pixels[i] & COLOR_MASK) == nonAlpha) {
                img.pixels[i] = alphaValue | nonAlpha;
            }
        }
        img.updatePixels();
    }

//    public static void shift(Viewport viewport, int col, int row) {
//        viewport.col = col;
//        viewport.row = row;
//    }

//    public static boolean contains(Viewport viewport, Point p) {
//        return p.y >= viewport.row && p.y < viewport.row + viewport.numRows
//                && p.x >= viewport.col && p.x < viewport.col + viewport.numCols;
//    }

//    public static void load(
//            Scanner in, WorldModel world, ImageStore imageStore)
//    {
//        int lineNumber = 0;
//        while (in.hasNextLine()) {
//            try {
//                if (!world.processLine(in.nextLine(), imageStore)) {
//                    System.err.println(String.format("invalid entry on line %d",
//                            lineNumber));
//                }
//            }
//            catch (NumberFormatException e) {
//                System.err.println(
//                        String.format("invalid entry on line %d", lineNumber));
//            }
//            catch (IllegalArgumentException e) {
//                System.err.println(
//                        String.format("issue on line %d: %s", lineNumber,
//                                e.getMessage()));
//            }
//            lineNumber++;
//        }
//    }

//    public static boolean processLine(
//            String line, WorldModel world, ImageStore imageStore)
//    {
//        String[] properties = line.split("\\s");
//        if (properties.length > 0) {
//            switch (properties[PROPERTY_KEY]) {
//                case BGND_KEY:
//                    return parseBackground(properties, world, imageStore);
//                case DUDE_KEY:
//                    return parseDude(properties, world, imageStore);
//                case OBSTACLE_KEY:
//                    return parseObstacle(properties, world, imageStore);
//                case FAIRY_KEY:
//                    return parseFairy(properties, world, imageStore);
//                case HOUSE_KEY:
//                    return parseHouse(properties, world, imageStore);
//                case TREE_KEY:
//                    return parseTree(properties, world, imageStore);
//                case SAPLING_KEY:
//                    return parseSapling(properties, world, imageStore);
//            }
//        }
//
//        return false;
//    }

//    public static boolean parseBackground(
//            String[] properties, WorldModel world, ImageStore imageStore)
//    {
//        if (properties.length == BGND_NUM_PROPERTIES) {
//            Point pt = new Point(Integer.parseInt(properties[BGND_COL]),
//                    Integer.parseInt(properties[BGND_ROW]));
//            String id = properties[BGND_ID];
//            new Background(id, imageStore.getImageList( id)).setBackground(world, pt
//                    );
//        }
//
//        return properties.length == BGND_NUM_PROPERTIES;
//    }

    public static boolean parseSapling(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == SAPLING_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[SAPLING_COL]),
                    Integer.parseInt(properties[SAPLING_ROW]));
            String id = properties[SAPLING_ID];
            int health = Integer.parseInt(properties[SAPLING_HEALTH]);
            Sapling entity = new Sapling(id, pt, imageStore.getImageList( SAPLING_KEY), health,
                    SAPLING_ACTION_ANIMATION_PERIOD, SAPLING_ACTION_ANIMATION_PERIOD, SAPLING_HEALTH_LIMIT);
            world.tryAddEntity( entity);
        }

        return properties.length == SAPLING_NUM_PROPERTIES;
    }

    public static boolean parseDude(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == DUDE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[DUDE_COL]),
                    Integer.parseInt(properties[DUDE_ROW]));
            DudeNotFull entity = new DudeNotFull(properties[DUDE_ID],
                    pt,
                    imageStore.getImageList( DUDE_KEY),
                    0,
                    Integer.parseInt(properties[DUDE_ACTION_PERIOD]),
                    Integer.parseInt(properties[DUDE_ANIMATION_PERIOD]),
                    Integer.parseInt(properties[DUDE_LIMIT]));
            world.tryAddEntity( entity);
        }

        return properties.length == DUDE_NUM_PROPERTIES;
    }

    public static boolean parseScooter(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == DUDE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[SCOOTER_COL]),
                    Integer.parseInt(properties[SCOOTER_ROW]));
            DudeNotFull entity = new DudeNotFull(properties[SCOOTER_ID],
                    pt,
                    imageStore.getImageList(SCOOTER_KEY),
                    0,
                    Integer.parseInt(properties[SCOOTER_ACTION_PERIOD]),
                    Integer.parseInt(properties[SCOOTER_ANIMATION_PERIOD]),
                    Integer.parseInt(properties[SCOOTER_LIMIT]));
            world.tryAddEntity(entity);
        }

        return properties.length == DUDE_NUM_PROPERTIES;
    }

    public static boolean parseFairy(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == FAIRY_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[FAIRY_COL]),
                    Integer.parseInt(properties[FAIRY_ROW]));
            Fairy entity = new Fairy(properties[FAIRY_ID],
                    pt,
                    imageStore.getImageList( FAIRY_KEY),
                    0,
                    Integer.parseInt(properties[FAIRY_ACTION_PERIOD]),
                    Integer.parseInt(properties[FAIRY_ANIMATION_PERIOD]));
            world.tryAddEntity( entity);
        }

        return properties.length == FAIRY_NUM_PROPERTIES;
    }

    public static boolean parseTurkey(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == TURKEY_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[TURKEY_COL]),
                    Integer.parseInt(properties[TURKEY_ROW]));
            Fairy entity = new Fairy(properties[TURKEY_ID],
                    pt,
                    imageStore.getImageList( TURKEY_KEY),
                    0,
                    Integer.parseInt(properties[TURKEY_ACTION_PERIOD]),
                    Integer.parseInt(properties[TURKEY_ANIMATION_PERIOD]));
            world.tryAddEntity( entity);
        }

        return properties.length == FAIRY_NUM_PROPERTIES;
    }

    public static boolean parseTree(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == TREE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[TREE_COL]),
                    Integer.parseInt(properties[TREE_ROW]));
            Tree entity = new Tree(properties[TREE_ID],
                    pt,
                    imageStore.getImageList( TREE_KEY),
                    Integer.parseInt(properties[TREE_HEALTH]),
                    Integer.parseInt(properties[TREE_ACTION_PERIOD]),
                    Integer.parseInt(properties[TREE_ANIMATION_PERIOD]));
            world.tryAddEntity( entity);
        }

        return properties.length == TREE_NUM_PROPERTIES;
    }

    public static boolean parsetreeFall(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == TREE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[TREE_COL]),
                    Integer.parseInt(properties[TREE_ROW]));
            Tree entity = new Tree(properties[TREE_ID],
                    pt,
                    imageStore.getImageList( TREEFALL_KEY),
                    Integer.parseInt(properties[TREE_HEALTH]),
                    Integer.parseInt(properties[TREE_ACTION_PERIOD]),
                    Integer.parseInt(properties[TREE_ANIMATION_PERIOD]));
            world.tryAddEntity( entity);
        }

        return properties.length == TREE_NUM_PROPERTIES;
    }

    public static boolean parseObstacle(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == OBSTACLE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[OBSTACLE_COL]),
                    Integer.parseInt(properties[OBSTACLE_ROW]));
            Obstacle entity = new Obstacle(properties[OBSTACLE_ID], pt,
                    imageStore.getImageList(OBSTACLE_KEY),
                    0,
                    0,
                    Integer.parseInt(properties[OBSTACLE_ANIMATION_PERIOD]));
            world.tryAddEntity( entity);
        }

        return properties.length == OBSTACLE_NUM_PROPERTIES;
    }

    public static boolean parsefrozenWater(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == OBSTACLE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[OBSTACLE_COL]),
                    Integer.parseInt(properties[OBSTACLE_ROW]));
            Obstacle entity = new Obstacle(properties[OBSTACLE_ID], pt,
                    imageStore.getImageList(FROZEN_WATER_KEY),
                    0,
                    0,
                    Integer.parseInt(properties[OBSTACLE_ANIMATION_PERIOD]));
            world.tryAddEntity( entity);
        }

        return properties.length == OBSTACLE_NUM_PROPERTIES;
    }

    public static boolean parseHouse(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == HOUSE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[HOUSE_COL]),
                    Integer.parseInt(properties[HOUSE_ROW]));
            House entity = new House(properties[HOUSE_ID],
                    pt,
                    imageStore.getImageList(
                            HOUSE_KEY),
                    0);
            world.tryAddEntity( entity);
        }

        return properties.length == HOUSE_NUM_PROPERTIES;
    }
    public static boolean parseCSB(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == HOUSE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[HOUSE_COL]),
                    Integer.parseInt(properties[HOUSE_ROW]));
            House entity = new House(properties[HOUSE_ID],
                    pt,
                    imageStore.getImageList(
                            CSB_KEY),
                    0);
            world.tryAddEntity( entity);
        }

        return properties.length == HOUSE_NUM_PROPERTIES;
    }

//    public static void tryAddEntity(WorldModel world, Entity entity) {
//        if (isOccupied(world, entity.position)) {
//            // arguably the wrong type of exception, but we are not
//            // defining our own exceptions yet
//            throw new IllegalArgumentException("position occupied");
//        }
//
//        addEntity(world, entity);
//    }

//    public static boolean withinBounds(WorldModel world, Point pos) {
//        return pos.y >= 0 && pos.y < world.numRows && pos.x >= 0
//                && pos.x < world.numCols;
//    }

//    public static boolean isOccupied(WorldModel world, Point pos) {
//        return pos.withinBounds(world) && getOccupancyCell(world, pos) != null;
//    }

//    public static Optional<Entity> nearestEntity(
//            List<Entity> entities, Point pos)
//    {
//        if (entities.isEmpty()) {
//            return Optional.empty();
//        }
//        else {
//            Entity nearest = entities.get(0);
//            int nearestDistance = pos.distanceSquared(nearest.position);
//
//            for (Entity other : entities) {
//                int otherDistance = pos.distanceSquared(other.position);
//
//                if (otherDistance < nearestDistance) {
//                    nearest = other;
//                    nearestDistance = otherDistance;
//                }
//            }
//
//            return Optional.of(nearest);
//        }
//    }

//    public static int distanceSquared(Point p1, Point p2) {
//        int deltaX = p1.x - p2.x;
//        int deltaY = p1.y - p2.y;
//
//        return deltaX * deltaX + deltaY * deltaY;
//    }

//    public static Optional<Entity> findNearest(
//            WorldModel world, Point pos, List<EntityKind> kinds)
//    {
//        List<Entity> ofType = new LinkedList<>();
//        for (EntityKind kind: kinds)
//        {
//            for (Entity entity : world.entities) {
//                if (entity.kind == kind) {
//                    ofType.add(entity);
//                }
//            }
//        }
//
//        return pos.nearestEntity(ofType);
//    }

    /*
       Assumes that there is no entity currently occupying the
       intended destination cell.
    */
//    public static void addEntity(WorldModel world, Entity entity) {
//        if (entity.position.withinBounds(world)) {
//            setOccupancyCell(world, entity.position, entity);
//            world.entities.add(entity);
//        }
//    }



//    public static void removeEntity(WorldModel world, Entity entity) {
//        removeEntityAt(world, entity.position);
//    }

//    public static void removeEntityAt(WorldModel world, Point pos) {
//        if (pos.withinBounds(world) && getOccupancyCell(world, pos) != null) {
//            Entity entity = getOccupancyCell(world, pos);
//
//            /* This moves the entity just outside of the grid for
//             * debugging purposes. */
//            entity.position = new Point(-1, -1);
//            world.entities.remove(entity);
//            pos.setOccupancyCell(world, null);
//        }
//    }

//    public static Optional<PImage> getBackgroundImage(
//            WorldModel world, Point pos)
//    {
//        if (pos.withinBounds(world)) {
//            return Optional.of(getCurrentImage(getBackgroundCell(world, pos)));
//        }
//        else {
//            return Optional.empty();
//        }
//    }

//    public static void setBackground(
//            WorldModel world, Point pos, Background background)
//    {
//        if (pos.withinBounds(world)) {
//            setBackgroundCell(world, pos, background);
//        }
//    }

//    public static Optional<Entity> getOccupant(WorldModel world, Point pos) {
//        if (pos.isOccupied(world)) {
//            return Optional.of(pos.getOccupancyCell(world));
//        }
//        else {
//            return Optional.empty();
//        }
//    }

//    public static Entity getOccupancyCell(WorldModel world, Point pos) {
//        return world.occupancy[pos.y][pos.x];
//    }

//    public static void setOccupancyCell(
//            WorldModel world, Point pos, Entity entity)
//    {
//        world.occupancy[pos.y][pos.x] = entity;
//    }

//    public static Background getBackgroundCell(WorldModel world, Point pos) {
//        return world.background[pos.y][pos.x];
//    }

//    public static void setBackgroundCell(
//            WorldModel world, Point pos, Background background)
//    {
//        world.background[pos.y][pos.x] = background;
//    }

//    public static Point viewportToWorld(Viewport viewport, int col, int row) {
//        return new Point(col + viewport.col, row + viewport.row);
//    }

//    public static Point worldToViewport(Viewport viewport, int col, int row) {
//        return new Point(col - viewport.col, row - viewport.row);
//    }

    public static int clamp(int value, int low, int high) {
        return Math.min(high, Math.max(value, low));
    }

//    public static void shiftView(WorldView view, int colDelta, int rowDelta) {
//        int newCol = clamp(view.viewport.col + colDelta, 0,
//                view.world.numCols - view.viewport.numCols);
//        int newRow = clamp(view.viewport.row + rowDelta, 0,
//                view.world.numRows - view.viewport.numRows);
//
//        view.viewport.shift( newCol, newRow);
//    }

//    public static void drawBackground(WorldView view) {
//        for (int row = 0; row < view.viewport.numRows; row++) {
//            for (int col = 0; col < view.viewport.numCols; col++) {
//                Point worldPoint = view.viewport.viewportToWorld( col, row);
//                Optional<PImage> image =
//                        view.world.getBackgroundImage( worldPoint);
//                if (image.isPresent()) {
//                    view.screen.image(image.get(), col * view.tileWidth,
//                            row * view.tileHeight);
//                }
//            }
//        }
//    }

//    public static void drawEntities(WorldView view) {
//        for (Entity entity : view.world.entities) {
//            Point pos = entity.position;
//
//            if (view.viewport.contains( pos)) {
//                Point viewPoint = view.viewport.worldToViewport( pos.x, pos.y);
//                view.screen.image(getCurrentImage(entity),
//                        viewPoint.x * view.tileWidth,
//                        viewPoint.y * view.tileHeight);
//            }
//        }
//    }

//    public static void drawViewport(WorldView view) {
//        view.drawBackground();
//        view.drawEntities();
//    }

//    public static Action createAnimationAction(Entity entity, int repeatCount) {
//        return new Action(ActionKind.ANIMATION, entity, null, null,
//                repeatCount);
//    }

//    public static Action createActivityAction(
//            Entity entity, WorldModel world, ImageStore imageStore)
//    {
//        return new Action(ActionKind.ACTIVITY, entity, world, imageStore, 0);
//    }

//    public static Entity createHouse(
//            String id, Point position, List<PImage> images)
//    {
//        return new Entity(EntityKind.HOUSE, id, position, images, 0, 0, 0,
//                0, 0, 0);
//    }

//    public static Entity createObstacle(
//            String id, Point position, int animationPeriod, List<PImage> images)
//    {
//        return new Entity(EntityKind.OBSTACLE, id, position, images, 0, 0, 0,
//                animationPeriod, 0, 0);
//    }

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

//    public static Entity createStump(
//            String id,
//            Point position,
//            List<PImage> images)
//    {
//        return new Entity(EntityKind.STUMP, id, position, images, 0, 0,
//                0, 0, 0, 0);
//    }

//    // health starts at 0 and builds up until ready to convert to Tree
//    public static Entity createSapling(
//            String id,
//            Point position,
//            List<PImage> images)
//    {
//        return new Entity(EntityKind.SAPLING, id, position, images, 0, 0,
//                SAPLING_ACTION_ANIMATION_PERIOD, SAPLING_ACTION_ANIMATION_PERIOD, 0, SAPLING_HEALTH_LIMIT);
//    }

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

    // don't technically need resource count ... full
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
}
