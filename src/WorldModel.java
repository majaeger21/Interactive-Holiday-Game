import processing.core.PImage;

import java.util.*;

/**
 * Represents the 2D World in which this simulation is running.
 * Keeps track of the size of the world, the background image for each
 * location in the world, and the entities that populate the world.
 */
public final class WorldModel
{
    private int numRows;
    private int numCols;
    public Background background[][];
    public Entity occupancy[][];
    private Set<Entity> entities;

    public int getNumRows(){
        return this.numRows;
    }
    public int getNumCols(){
        return this.numCols;
    }
    public Set<Entity> getEntities(){
        return this.entities;
    }

    public WorldModel(int numRows, int numCols, Background defaultBackground) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.background = new Background[numRows][numCols];
        this.occupancy = new Entity[numRows][numCols];
        this.entities = new HashSet<>();

        for (int row = 0; row < numRows; row++) {
            Arrays.fill(this.background[row], defaultBackground);
        }
    }

    public boolean processLine(
            String line, ImageStore imageStore)
    {
        String[] properties = line.split("\\s");
        if (properties.length > 0) {
            switch (properties[Functions.PROPERTY_KEY]) {
                case Functions.BGND_KEY:
                    return this.parseBackground(properties, imageStore);
                case Functions.DUDE_KEY:
                    return Functions.parseDude(properties, this, imageStore);
                case Functions.SCOOTER_KEY:
                    return Functions.parseScooter(properties, this, imageStore);
                case Functions.OBSTACLE_KEY:
                    return Functions.parseObstacle(properties, this, imageStore);
                case Functions.FROZEN_WATER_KEY:
                    return Functions.parsefrozenWater(properties, this, imageStore);
                case Functions.FAIRY_KEY:
                    return Functions.parseFairy(properties, this, imageStore);
                case Functions.TURKEY_KEY:
                    return Functions.parseTurkey(properties, this, imageStore);
                case Functions.HOUSE_KEY:
                    return Functions.parseHouse(properties, this, imageStore);
                case Functions.CSB_KEY:
                    return Functions.parseCSB(properties, this, imageStore);
                case Functions.TREE_KEY:
                    return Functions.parseTree(properties, this, imageStore);
                case Functions.TREEFALL_KEY:
                    return Functions.parsetreeFall(properties, this, imageStore);
                case Functions.SAPLING_KEY:
                    return Functions.parseSapling(properties, this, imageStore);
            }
        }
        return false;
    }

    public void load(
            Scanner in, ImageStore imageStore)
    {
        int lineNumber = 0;
        while (in.hasNextLine()) {
            try {
                if (!this.processLine(in.nextLine(), imageStore)) {
                    System.err.println(String.format("invalid entry on line %d",
                            lineNumber));
                }
            }
            catch (NumberFormatException e) {
                System.err.println(
                        String.format("invalid entry on line %d", lineNumber));
            }
            catch (IllegalArgumentException e) {
                System.err.println(
                        String.format("issue on line %d: %s", lineNumber,
                                e.getMessage()));
            }
            lineNumber++;
        }
    }

    public void tryAddEntity( Entity entity) {
        if (this.isOccupied(entity.getPosition())) {
            // arguably the wrong type of exception, but we are not
            // defining our own exceptions yet
            throw new IllegalArgumentException("position occupied");
        }

        this.addEntity(entity);
    }
    public void removeEntityAt( Point pos) {
        if (this.withinBounds(pos) && pos.getOccupancyCell(this) != null) {
            Entity entity = pos.getOccupancyCell(this);

            /* This moves the entity just outside of the grid for
             * debugging purposes. */
            entity.position = new Point(-1, -1);
            this.entities.remove(entity);
            pos.setOccupancyCell(this, null);
        }
    }

    public Optional<PImage> getBackgroundImage(Point pos)
    {
        if (this.withinBounds(pos)) {
            return Optional.of(Background.getCurrentImage(this.getBackgroundCell( pos)));
        }
        else {
            return Optional.empty();
        }
    }

    public  Background getBackgroundCell( Point pos) {
        return this.background[pos.getY()][pos.getX()];
    }

    public boolean withinBounds( Point pos) {
        return pos.getY() >= 0 && pos.getY() < this.numRows && pos.getX() >= 0
                && pos.getX() < this.numCols;
    }

    public Optional<Entity> getOccupant( Point pos) {
        if (this.isOccupied(pos)) {
            return Optional.of(pos.getOccupancyCell(this));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Entity> findNearest(
    Point pos, List<Class> kinds) {
        List<Entity> ofType = new LinkedList<>();
        for (Class kind : kinds) {
            for (Entity entity : this.entities) {
                if (entity.getClass() == kind) {
                    ofType.add(entity);
                }
            }
        }
                return pos.nearestEntity(ofType);

    }

        public void addEntity(Entity entity) {
        if (this.withinBounds(entity.getPosition())) {
            entity.getPosition().setOccupancyCell(this,entity);
            this.entities.add(entity);
        }
    }

        public boolean isOccupied(Point pos) {
            return this.withinBounds(pos) && pos.getOccupancyCell(this) != null;
    }

        public void moveEntity( Entity entity, Point pos) {
            Point oldPos = entity.position;
            if (this.withinBounds(pos) && !pos.equals(oldPos)) {
                oldPos.setOccupancyCell(this, null);
                this.removeEntityAt( pos);
                pos.setOccupancyCell(this, entity);
                entity.position = pos;
        }
    }

    public void setBackgroundCell(Point pos,
                                  Background background) {
        this.background[pos.getY()][pos.getX()] = background;
    }

    public void setBackground(Point pos, Background background) {
        if (this.withinBounds(pos)) {
            background.setBackgroundCell(this, pos);
        }
    }

    public boolean parseBackground(String[] properties, ImageStore imageStore) {
        if (properties.length == 4) {
            Point pt = new Point(Integer.parseInt(properties[2]), Integer.parseInt(properties[3]));
            String id = properties[1];
            this.setBackground(pt, new Background(id, imageStore.getImageList(id)));
        }

        return properties.length == 4;
    }


}
