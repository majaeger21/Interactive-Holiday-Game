public class Node {

    private int g; // distance from start
    private int h; // distance to goal
    private int f; // total distance f = g + h
    private Node prevNode;
    private Point pos;

    public Node(int g, int h, Node prevNode, Point pos) {
        this.g = g;
        this.h = h;
        this.f = g + h;
        this.prevNode = prevNode;
        this.pos = pos;
    }

    public int getG(){
        return g;
    }
    public void setG(int g){
        this.g = g;
    }

    public int getH(){
        return h;
    }
    public void setH(int h){
        this.h = h;
    }

    public int getF(){
        return f;
    }
    public void setF(){
        this.f = f;
    }

    public Node getPrevNode(){
        return prevNode;
    }
    public void setPrevNode(Node prevNode){
        this.prevNode = prevNode;
    }

    public Point getPos(){
        return pos;
    }
    public void setPos(){
        this.pos = pos;
    }

    public boolean containsPoint(Point p) {
        return this.pos == p;
    }

//    public static Node reverseList(Node node) {
//        Node prev = null;
//        Node current = node;
//        Node next;
//        while (current != null) {
//            next = current.next;
//            current.next = prev;
//            prev = current;
//            current = next;
//        }
//        node = prev;
//
//        return node;
//    }
//
//    public static String printNodeList(Node node)
//    {
//        String val = "";
//        while (node != null) {
//            val += Integer.toString(node.item);
//            node = node.next;
//        }
//        return val;
//    }
//
//    public static int nodeLength(Node node){
//        int len = 0;
//        while (node != null) {
//            len += 1;
//            node = node.next;
//        }
//        return len;
//    }
////    public static Node listtonode(LinkedList list){
////        String tot = "";
////        Node currNode = list.head;
////        while (currNode != null) {
////            tot += currNode.item + "";
////            currNode = currNode.next;
////        }
////        return tot;
////    }

}
