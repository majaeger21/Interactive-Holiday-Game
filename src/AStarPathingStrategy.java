import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

class AStarPathingStrategy
        implements PathingStrategy
{


    public List<Point> computePath(Point start, Point end,
                                         Predicate<Point> canPassThrough,
                                         BiPredicate<Point, Point> withinReach,
                                         Function<Point, Stream<Point>> potentialNeighbors)
    {

        Comparator<Node> byF = Comparator.comparing(Node::getF);  // method to sort queue by F
        Queue<Node> openList = new PriorityQueue<>(byF);
        Map<Point, Node> openHash = new HashMap<>();
        List<Point> closedList = new ArrayList<>();
        List<Point> path = new ArrayList<>();
        Node startNode = new Node(0, manhattan(start, end), null, start);
        openList.add(startNode);

        while (!openList.isEmpty()){
            Node curr = openList.remove();
            if (withinReach.test(curr.getPos(), end)){
                makePath(path, curr);
                break;
            }
            closedList.add(curr.getPos());
            potentialNeighbors.apply(curr.getPos())
                    .filter(canPassThrough)  // get all the points that you are able to pass through (so excluding obstacles)
                    .filter(p -> !closedList.contains(p))   // p are all the points that we can pass through
                    .forEach(n -> {  // n are all the remaining points that are not in the closed list
                        if (!openHash.containsKey(n)){  // if openHash does not contain point, add node and point
                            Node temp = new Node(curr.getG() + 1, manhattan(n, end), curr, n);
                            openHash.put(n, temp);
                            openList.add(temp);
                        }
                        else {
                            if (openHash.get(n).getG() > curr.getG() + 1) {
                                Node temp = new Node(curr.getG() + 1, manhattan(n, end), curr, n);
                                openHash.replace(n, temp);
                            }
                        }
                    });
        }
//        for (Point p: path){
//            System.out.println(p.toString());
//        }
        return path;
    }

    private int manhattan(Point srt, Point end){
        return Math.abs(srt.getX() - end.getX()) + Math.abs(srt.getY() - end.getY());
    }

    private void makePath(List<Point> path, Node node){
        if (node.getPrevNode() == null){
            return;
        }
        path.add(0, node.getPos());
        if (node.getPrevNode() != null){
            makePath(path, node.getPrevNode());
        }
    }
}
