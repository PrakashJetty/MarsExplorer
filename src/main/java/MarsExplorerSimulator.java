import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class MarsExplorerSimulator {

    private static final Scanner scanner = new Scanner(System.in);

    public static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ')';
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Position)) return false;
            Position position = (Position) o;
            return x == position.x &&
                    y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public MarsExplorerSimulator() {
    }

    private static Queue<Position> pathMap = new LinkedList<>();
    private static Set<Position> blockSet = new HashSet<>();

    public static Map<String, String> traverseCmd(String cmd, int x, int y) {

        Position position = new Position(x, y);

        switch (cmd) {
            case "BLOCK":
                blockSet.add(position);
                break;
            case "PLACE":
                blockSet.clear();
                pathMap.add(position);
                break;
            case "REPORT":
                System.out.println(pathMap.peek().toString());
                scanner.close();
                System.exit(0);
            case "EXPLORE":
                Stack<Position> cresult = new Stack<>();
                cresult.add(pathMap.peek());
                return explore(new Position(x, y), cresult, new HashSet<>(), 1);
        }
        return new HashMap<>();
    }

    private static Map<String, String> explore(Position position, Stack<Position> cresult, HashSet<Object> objects, int i) {
        System.out.println("starting to explore from current coordinates :: " + pathMap.peek().toString() + " to target coordinates :: " + position.toString());
        if (position.equals(cresult.peek())) {
            System.out.println("len is 0");
            return new HashMap<>();
        } else {
            boolean turnOn = true;
            Position targetPosition = position;
            Position sourcePosition = cresult.peek();
            Queue<Position> vertexToexplore = new LinkedList<>();
            Map<String, Integer> pathLength = new HashMap<>();
            Map<String, String> path = new HashMap<>();
            Set<String> existingVertex = new HashSet<>();
            boolean begingExplore = false;
            int counter = 0;
            while (turnOn) {
                counter++;
                System.out.println("current vertex :: " + sourcePosition.toString());
                int x = sourcePosition.getX();
                int y = sourcePosition.getY();
                if (!begingExplore && vertexToexplore.peek() == null) {
                    //starting to explore
                    begingExplore = true;
                    existingVertex.add(sourcePosition.toString());
                    pathLength.put(sourcePosition.toString(), 0);
                    exploreVertex(x, y, vertexToexplore, path, existingVertex);

                } else if (begingExplore && vertexToexplore.peek() == null) {
                    System.out.println("Done");
                    turnOn = false;
                } else {
                    //get peek
                    sourcePosition = vertexToexplore.remove();
                    x = sourcePosition.getX();
                    y = sourcePosition.getY();
                    // check whether the pos is in set or not if contains continue
                    if (!existingVertex.contains(sourcePosition.toString()) && !blockSet.contains(sourcePosition)) {
                        //add in existingVertex
                        existingVertex.add(sourcePosition.toString());
                        //add to pathlength
                        String s = path.get(sourcePosition.toString());
                        pathLength.put(sourcePosition.toString(), pathLength.get(s) + 1);
                        exploreVertex(x, y, vertexToexplore, path, existingVertex);
                    }
                }
            }
            printEndPath(path, targetPosition);
            return path;
        }

    }

    private static void printEndPath(Map<String, String> path, Position targetPosition) {
        String target = targetPosition.toString();
        System.out.print(target + " ");
        String parentPath = path.get(target);
        System.out.print(parentPath + " ");
        int counter = 0;
        while (true) {
            counter++;
            if (null != path.get(parentPath)) {
                parentPath = path.get(parentPath);
                System.out.print(parentPath + " ");
            } else {
                break;
            }
        }

    }

    private static void exploreVertex(int x, int y, Queue<Position> vertexToexplore, Map<String, String> path, Set<String> existingVertex) {
        if (x + 1 <= 5 && y <= 5) {
            //explore right vertex.
            Map<String, String> exploreMap = new HashMap<>();
            Position positionRight = new Position(x + 1, y);
            Position position = new Position(x, y);
            exploreMap.put(positionRight.toString(), position.toString());
            if (!existingVertex.contains(positionRight.toString()) && !blockSet.contains(positionRight) && !blockSet.contains(position))
                path.put(positionRight.toString(), position.toString());
            vertexToexplore.add(positionRight);
            System.out.println("explored vertex :: " + positionRight.toString());
        }
        if (x - 1 >= 0 && y <= 5) {
            //explore Left vertex.
            Map<String, String> exploreMap = new HashMap<>();
            Position positionLeft = new Position(x - 1, y);
            Position position = new Position(x, y);
            exploreMap.put(positionLeft.toString(), position.toString());
            if (!existingVertex.contains(positionLeft.toString()) && !blockSet.contains(positionLeft) && !blockSet.contains(position))
                path.put(positionLeft.toString(), position.toString());
            vertexToexplore.add(positionLeft);
            System.out.println("explored vertex :: " + positionLeft.toString());
        }
        if (x <= 5 && y + 1 <= 5) {
            //explore UP vertex.
            Map<String, String> exploreMap = new HashMap<>();
            Position positionUp = new Position(x, y + 1);
            Position position = new Position(x, y);
            exploreMap.put(positionUp.toString(), position.toString());
            if (!existingVertex.contains(positionUp.toString()) && !blockSet.contains(positionUp) && !blockSet.contains(position))
                path.put(positionUp.toString(), position.toString());
            vertexToexplore.add(positionUp);
            System.out.println("explored vertex :: " + positionUp.toString());
        }
        if (x <= 5 && y - 1 >= 0) {
            //explore Down vertex.
            Map<String, String> exploreMap = new HashMap<>();
            Position positionDown = new Position(x, y - 1);
            Position position = new Position(x, y);
            exploreMap.put(positionDown.toString(), position.toString());
            if (!existingVertex.contains(positionDown.toString()) && !blockSet.contains(positionDown) && !blockSet.contains(position))
                path.put(positionDown.toString(), position.toString());
            vertexToexplore.add(positionDown);
            System.out.println("explored vertex :: " + positionDown.toString());
        }
    }

    public static void main(String[] args) throws IOException {

        do {
            String[] cmdItems = scanner.nextLine().split(" ");
            String[] cords = !cmdItems[0].equals("REPORT") ? cmdItems[1].split(",") : new String[]{"0", "0"};
            traverseCmd(cmdItems[0], Integer.parseInt(cords[0]), Integer.parseInt(cords[1]));
        } while (true);
    }
}
