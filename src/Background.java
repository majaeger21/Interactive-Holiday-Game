import java.util.List;

import processing.core.PImage;

/**
 * Represents a background for the 2D world.
 */
public final class Background {
    private String id;
    private List<PImage> images;
    private int imageIndex;

    public int getImageIndex() {
        return this.imageIndex;
    }

    public List<PImage> getimages() {
        return this.images;
    }

    public Background(String id, List<PImage> images) {
        this.id = id;
        this.images = images;
    }

    public String getId(){
        return id;
    }

//    public void setBackground(
//            WorldModel world, Point pos) {
//        if (world.withinBounds(pos)) {
//            this.setBackgroundCell(world, pos);
//        }
//    }

    public void setBackgroundCell(
            WorldModel world, Point pos) {
        world.background[pos.getY()][pos.getX()] = this;
    }

    public static PImage getCurrentImage(Background entity) {
//        if (entity instanceof Background) {
            return ( entity).images.get(
                    (entity).imageIndex);
//        }
//        else if (entity instanceof Entity) {
//            return ((Entity)entity).getimages().get(((Entity)entity).getImageIndex());
//        }
//        else {
//            throw new UnsupportedOperationException(
//                    String.format("getCurrentImage not supported for %s",
//                            entity));
//        }
//    }
    }
}

