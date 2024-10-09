import processing.core.PImage;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * A simple class representing a location in 2D space.
 */
public final class Point
{
    private final int x;
    private final int y;

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean equals(Object other) {
        return other instanceof Point && ((Point)other).x == this.x
                && ((Point)other).y == this.y;
    }

    public int hashCode() {
        int result = 17;
        result = result * 31 + x;
        result = result * 31 + y;
        return result;
    }

    public boolean adjacent(Point p2) {
        return (this.x == p2.x && Math.abs(this.y - p2.y) == 1) || (this.y == p2.y
                && Math.abs(this.x - p2.x) == 1);
    }


    public int distanceSquared(Point p2) {
        int deltaX = this.x - p2.x;
        int deltaY = this.y - p2.y;

        return deltaX * deltaX + deltaY * deltaY;
    }

    public void setOccupancyCell(
            WorldModel world, Entity entity)
    {
        world.occupancy[this.y][this.x] = entity;
    }
    public Entity getOccupancyCell(WorldModel world) {
        return world.occupancy[this.y][this.x];
    }

//    public Entity createHouse(
//            String id, List<PImage> images)
//    {
//        return new Entity(EntityKind.HOUSE, id, this, images, 0, 0, 0,
//                0, 0, 0);
//    }
//
//    public Entity createObstacle(
//            String id, int animationPeriod, List<PImage> images)
//    {
//        return new Entity(EntityKind.OBSTACLE, id, this, images, 0, 0, 0,
//                animationPeriod, 0, 0);
//    }

    public Optional<Entity> nearestEntity(
            List<Entity> entities)
    {
        if (entities.isEmpty()) {
            return Optional.empty();
        }
        else {
            Entity nearest = entities.get(0);
            int nearestDistance = this.distanceSquared(nearest.getPosition());

            for (Entity other : entities) {
                int otherDistance = this.distanceSquared(other.getPosition());

                if (otherDistance < nearestDistance) {
                    nearest = other;
                    nearestDistance = otherDistance;
                }
            }

            return Optional.of(nearest);
        }
    }




}
