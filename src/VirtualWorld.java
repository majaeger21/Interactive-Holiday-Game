import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;
import java.util.Optional;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import processing.core.*;

public final class VirtualWorld extends PApplet
{
    private static boolean studentSpawnBool = false;
    private static boolean csbSpawnBool = false;
    private Student student;
    private static final int TIMER_ACTION_PERIOD = 100;

    private static final int VIEW_WIDTH = 640;
    private static final int VIEW_HEIGHT = 480;
    private static final int TILE_WIDTH = 32;
    private static final int TILE_HEIGHT = 32;
    private static final int WORLD_WIDTH_SCALE = 2;
    private static final int WORLD_HEIGHT_SCALE = 2;

    private static final int VIEW_COLS = VIEW_WIDTH / TILE_WIDTH;
    private static final int VIEW_ROWS = VIEW_HEIGHT / TILE_HEIGHT;
    private static final int WORLD_COLS = VIEW_COLS * WORLD_WIDTH_SCALE;
    private static final int WORLD_ROWS = VIEW_ROWS * WORLD_HEIGHT_SCALE;

    private static final String IMAGE_LIST_FILE_NAME = "imagelist";
    private static final String DEFAULT_IMAGE_NAME = "background_default";
    private static final int DEFAULT_IMAGE_COLOR = 0x808080;

    private static String LOAD_FILE_NAME = "world.sav";

    private static final String FAST_FLAG = "-fast";
    private static final String FASTER_FLAG = "-faster";
    private static final String FASTEST_FLAG = "-fastest";
    private static final double FAST_SCALE = 0.5;
    private static final double FASTER_SCALE = 0.25;
    private static final double FASTEST_SCALE = 0.10;

    private static double timeScale = 1.0;

    private ImageStore imageStore;
    private WorldModel world;
    private WorldView view;
    private EventScheduler scheduler;
    private long nextTime;

    public void settings() {
        size(VIEW_WIDTH, VIEW_HEIGHT);
    }

    public boolean getstudentSpawnBool(){
        return studentSpawnBool;
    }

    /*
       Processing entry point for "sketch" setup.
    */
    public void setup() {
        this.imageStore = new ImageStore(
                createImageColored(TILE_WIDTH, TILE_HEIGHT,
                                   DEFAULT_IMAGE_COLOR));
        this.world = new WorldModel(WORLD_ROWS, WORLD_COLS,
                                    createDefaultBackground(imageStore));
        this.view = new WorldView(VIEW_ROWS, VIEW_COLS, this, world, TILE_WIDTH,
                                  TILE_HEIGHT);
        this.scheduler = new EventScheduler(timeScale);

        loadImages(IMAGE_LIST_FILE_NAME, imageStore, this);
        loadWorld(world, LOAD_FILE_NAME, imageStore);

        scheduleActions(world, scheduler, imageStore);

        nextTime = System.currentTimeMillis() + TIMER_ACTION_PERIOD;
    }

    public void draw() {
        long time = System.currentTimeMillis();
        if (time >= nextTime) {
            this.scheduler.updateOnTime( time);
            nextTime = time + TIMER_ACTION_PERIOD;
        }

        view.drawViewport();
    }

    // Just for debugging and for P5
    // Be sure to refactor this method as appropriate
    public void mousePressed() {
        Point loc = mouseToPoint();

        for (int i = loc.getX(); i < loc.getX() + 8; i++) {
            for (int j = loc.getY(); j < loc.getY() + 8; j++) {
                if (world.withinBounds(new Point(i, j))) {
                    Background background = world.getBackgroundCell(new Point(i, j));
                    if(Objects.equals(background.getId(), "grass")){
                        world.setBackgroundCell(new Point(i, j), new Background("grassBrown", this.imageStore.getImageList("grassBrown")));
                    } else if (Objects.equals(background.getId(), "flowers")) {
                        world.setBackgroundCell(new Point(i, j), new Background("pumpkin", this.imageStore.getImageList("pumpkin")));

                    } else if (Objects.equals(background.getId(), "dirt_bot_left_corner")) {
                        world.setBackgroundCell(new Point(i, j),
                                new Background("fall_dirt_bot_left_corner", this.imageStore.getImageList("fall_dirt_bot_left_corner")));


                    }else if (Objects.equals(background.getId(), "dirt_horiz")) {
                        world.setBackgroundCell(new Point(i, j),
                                new Background("fall_dirt_bot", this.imageStore.getImageList("fall_dirt_bot")));
                    }
                    else if (Objects.equals(background.getId(), "dirt_bot_right_up")) {
                        world.setBackgroundCell(new Point(i, j),
                                new Background("fall_dirt_bot_right_up", this.imageStore.getImageList("fall_dirt_bot_right_up")));
                    }
                    else if (Objects.equals(background.getId(), "dirt_vert_left")) {
                        world.setBackgroundCell(new Point(i, j),
                                new Background("fall_dirt_vert_left", this.imageStore.getImageList("fall_dirt_vert_left")));
                    }
                    else if (Objects.equals(background.getId(), "dirt_vert_left_bot")) {
                        world.setBackgroundCell(new Point(i, j),
                                new Background("fall_dirt_vert_left_bot", this.imageStore.getImageList("fall_dirt_vert_left_bot")));
                    }
                    else if (Objects.equals(background.getId(), "dirt_vert_right")) {
                        world.setBackgroundCell(new Point(i, j),
                                new Background("fall_dirt_vert_right", this.imageStore.getImageList("fall_dirt_vert_right")));
                    }
                    Optional<Entity> pnt = world.getOccupant(new Point(i, j));
                    if (pnt.isPresent()) {
                        Entity entity = pnt.get();
                        Point pos = entity.getPosition();
                        if (entity.getClass().equals(Tree.class))
                        {
                            FallTree tree_Fall = new FallTree("treeFall", pos, this.imageStore.getImageList("treeFall"), 0, 0, 0);
                            world.removeEntityAt(pos);
                            world.addEntity(tree_Fall);
                            tree_Fall.scheduleActions(this.scheduler, this.world, this.imageStore);
                        }
                        if (entity.getClass().equals(Obstacle.class)){
                            FrozenWater frozenWater = new FrozenWater("frozenWater", pos, this.imageStore.getImageList("frozenWater"), 0, 0, 0);
                            this.world.removeEntityAt(pos);
                            this.world.addEntity(frozenWater);
                            frozenWater.scheduleActions(this.scheduler, this.world, this.imageStore);
                        }
                        if (entity.getClass().equals(House.class)){
                            CSB csb = new CSB("csBuilding", pos, this.imageStore.getImageList("csBuilding"), 0);
                            this.world.removeEntityAt(pos);
                            world.addEntity(csb);
                            csbSpawnBool = true;
                        }
                    }
                }
            }
        }
        if (csbSpawnBool && (loc.getY() == 3 && loc.getX() == 3) && !studentSpawnBool){
            student = new Student("student", loc, this.imageStore.getImageList("student"), 0, 0, 0);
            this.world.addEntity(student);
            student.scheduleActions(this.scheduler, this.world, this.imageStore);
            studentSpawnBool = true;
        }

        Optional<Entity> movingTurkey = world.findNearest(loc, Collections.singletonList(Fairy.class));
        if (movingTurkey.isPresent()){
            Point pos = movingTurkey.get().getPosition();
            Turkey turkey = new Turkey("turkey", pos, this.imageStore.getImageList("turkey"), 0, 0, 0);
            this.world.removeEntityAt(pos);
            this.world.addEntity(turkey);
            turkey.scheduleActions(this.scheduler, this.world, this.imageStore);
        }

        Optional<Entity> scooter2 = world.findNearest(loc, Collections.singletonList(DudeNotFull.class));
        if (scooter2.isPresent()){
            Point pos = scooter2.get().getPosition();
            ScooterNotFull scoot = new ScooterNotFull("scooter", pos, this.imageStore.getImageList("scooter"), 0, 0, 0, 0);
            this.world.removeEntityAt(pos);
            this.world.addEntity(scoot);
            scoot.scheduleActions(this.scheduler, this.world, this.imageStore);
        }

        Optional<Entity> scooter = world.findNearest(loc, Collections.singletonList(DudeFull.class));
        if (scooter.isPresent()){
            Point pos = scooter.get().getPosition();
            ScooterFull scoot = new ScooterFull("scooter", pos, this.imageStore.getImageList("scooter"), 0, 0, 0, 0);
            this.world.removeEntityAt(pos);
            this.world.addEntity(scoot);
            scoot.scheduleActions(this.scheduler, this.world, this.imageStore);
        }


    }

    private Point mouseToPoint()
    {
        return view.getViewport().viewportToWorld( mouseX/TILE_WIDTH, mouseY/TILE_HEIGHT);
    }


    public void keyPressed() {
        int dx = 0;
        int dy = 0;
            switch (key) {
                case 'w':
                    dy = -1;
                    break;
                case 's':
                    dy = 1;
                    break;
                case 'a':
                    dx = -1;
                    break;
                case 'd':
                    dx = 1;
                    break;
            }
        if (studentSpawnBool){
            Point pnt = new Point(student.getPosition().getX() + dx, student.getPosition().getY() + dy);
            if (!world.isOccupied(pnt) && ((pnt.getX() > 0 && pnt.getX() < WORLD_COLS - 1) && (pnt.getY() > 0 && pnt.getY() < WORLD_ROWS - 1))){
                world.moveEntity(student, pnt);
            }
        }
        view.shiftView(dx, dy);
}

    public static Background createDefaultBackground(ImageStore imageStore) {
        return new Background(DEFAULT_IMAGE_NAME,
                imageStore.getImageList(
                                                     DEFAULT_IMAGE_NAME));
    }

    public static PImage createImageColored(int width, int height, int color) {
        PImage img = new PImage(width, height, RGB);
        img.loadPixels();
        for (int i = 0; i < img.pixels.length; i++) {
            img.pixels[i] = color;
        }
        img.updatePixels();
        return img;
    }

    static void loadImages(
            String filename, ImageStore imageStore, PApplet screen)
    {
        try {
            Scanner in = new Scanner(new File(filename));
            imageStore.loadImages(in, screen);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void loadWorld(
            WorldModel world, String filename, ImageStore imageStore)
    {
        try {
            Scanner in = new Scanner(new File(filename));
            world.load(in, imageStore);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void scheduleActions(
            WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Scheduleable){
                ((Scheduleable)entity).scheduleActions(scheduler, world, imageStore);
            }
        }
    }

    public static void parseCommandLine(String[] args) {
        if (args.length > 1)
        {
            if (args[0].equals("file"))
            {

            }
        }
        for (String arg : args) {
            switch (arg) {
                case FAST_FLAG:
                    timeScale = Math.min(FAST_SCALE, timeScale);
                    break;
                case FASTER_FLAG:
                    timeScale = Math.min(FASTER_SCALE, timeScale);
                    break;
                case FASTEST_FLAG:
                    timeScale = Math.min(FASTEST_SCALE, timeScale);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        parseCommandLine(args);
        PApplet.main(VirtualWorld.class);
    }


}
