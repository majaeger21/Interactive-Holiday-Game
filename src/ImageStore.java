import java.util.*;

import processing.core.PApplet;
import processing.core.PImage;

public final class ImageStore
{
    private Map<String, List<PImage>> images;
    private List<PImage> defaultImages;

    public ImageStore(PImage defaultImage) {
        this.images = new HashMap<>();
        defaultImages = new LinkedList<>();
        defaultImages.add(defaultImage);
    }

    public List<PImage> getImageList(String key) {
        return this.images.getOrDefault(key, this.defaultImages);
    }

    public void loadImages(
            Scanner in, PApplet screen)
    {
        int lineNumber = 0;
        while (in.hasNextLine()) {
            try {
                Functions.processImageLine(this.images, in.nextLine(), screen);
            }
            catch (NumberFormatException e) {
                System.out.println(
                        String.format("Image format error on line %d",
                                lineNumber));
            }
            lineNumber++;
        }
    }

//    public boolean parseBackground(
//            String[] properties, WorldModel world)
//    {
//        if (properties.length == Functions.BGND_NUM_PROPERTIES) {
//            Point pt = new Point(Integer.parseInt(properties[Functions.BGND_COL]),
//                    Integer.parseInt(properties[Functions.BGND_ROW]));
//            String id = properties[Functions.BGND_ID];
//            new Background(id, this.getImageList( id)).setBackground(world, pt
//            );
//        }
//
//        return properties.length == Functions.BGND_NUM_PROPERTIES;
//    }



}
