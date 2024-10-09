/**
 * An action that can be taken by an entity
 */
public interface Action
{
    void executeAction(EventScheduler scheduler);

//    private ActionKind kind;
//    private Entity entity;
//    private WorldModel world;
//    private ImageStore imageStore;
//    private int repeatCount;
//
//    public Action(
//            ActionKind kind,
//            Entity entity,
//            WorldModel world,
//            ImageStore imageStore,
//            int repeatCount)
//    {
//        this.kind = kind;
//        this.entity = entity;
//        this.world = world;
//        this.imageStore = imageStore;
//        this.repeatCount = repeatCount;
//    }
//
//    public void executeAction(EventScheduler scheduler) {
//        switch (this.kind) {
//            case ACTIVITY:
//                executeActivityAction(scheduler);
//                break;
//
//            case ANIMATION:
//                executeAnimationAction(scheduler);
//                break;
//        }
//    }
//
//    public void executeAnimationAction(EventScheduler scheduler)
//    {
//        this.entity.nextImage();
//
//        if (this.repeatCount != 1) {
//            scheduler.scheduleEvent( this.entity,
//                    Action.createAnimationAction(this.entity,
//                            Math.max(this.repeatCount - 1,
//                                    0)),
//                    this.entity.getAnimationPeriod());
//        }
//    }
//
//    public void executeActivityAction(EventScheduler scheduler)
//    {
//
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
//        }
//    }
//    public static Action createActivityAction(
//            Entity entity, WorldModel world, ImageStore imageStore)
//    {
//        return new Action(ActionKind.ACTIVITY, entity, world, imageStore, 0);
//    }
//
//    public static Action createAnimationAction(Entity entity, int repeatCount) {
//        return new Action(ActionKind.ANIMATION, entity, null, null,
//                repeatCount);
//    }



}
