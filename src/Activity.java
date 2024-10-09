import java.lang.reflect.Executable;

public class Activity implements Action{
    private Executeable entity;
    private WorldModel world;
    private ImageStore imageStore;

    public Activity (Executeable entity, WorldModel world, ImageStore imageStore){
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
    }

    public void executeAction(EventScheduler scheduler)
    {
        this.entity.executeActivity(this.world, this.imageStore, scheduler);
//        switch (this.entity.getkind()) {
//            case SAPLING:
//                this.entity.executeSaplingActivity(this.world,
//                        this.imageStore, scheduler);
//                break;
//
//            case TREE:
//                this.entity.executeTreeActivity(this.world,
//                        this.imageStore, scheduler);
//                break;
//
//            case FAIRY:
//                this.entity.executeFairyActivity(this.world,
//                        this.imageStore, scheduler);
//                break;
//
//            case DUDE_NOT_FULL:
//                this.entity.executeDudeNotFullActivity(this.world,
//                        this.imageStore, scheduler);
//                break;
//
//            case DUDE_FULL:
//                this.entity.executeDudeFullActivity(this.world,
//                        this.imageStore, scheduler);
//                break;
//
//            default:
//                throw new UnsupportedOperationException(String.format(
//                        "executeActivityAction not supported for %s",
//                        this.entity.getkind()));
    }
}
//    public static Action createActivityAction(
//            Entity entity, WorldModel world, ImageStore imageStore)
//    {
//        return new Activity( entity, world, imageStore);
//    }
