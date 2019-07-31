import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 */
public class MarsExplorerSimulatorTest {
    public MarsExplorerSimulatorTest() {

    }

    @Before
    public void setup() {
//        MarsExplorerSimulator.getBlockSet().clear();
//        MarsExplorerSimulator.getBlockSet().addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6).stream().map(i -> {
//            return new MarsExplorerSimulator.Position(i, 6);
//        }).collect(Collectors.toSet()));
//        MarsExplorerSimulator.getBlockSet().addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6).stream().map(i -> {
//            return new MarsExplorerSimulator.Position(6, i);
//        }).collect(Collectors.toSet()));
//        MarsExplorerSimulator.getBlockSet().addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6).stream().map(i -> {
//            return new MarsExplorerSimulator.Position(i, -1);
//        }).collect(Collectors.toSet()));
//        MarsExplorerSimulator.getBlockSet().addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6).stream().map(i -> {
//            return new MarsExplorerSimulator.Position(-1, i);
//        }).collect(Collectors.toSet()));
//        MarsExplorerSimulator.getBlockSet().addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6).stream().map(i -> {
//            return new MarsExplorerSimulator.Position(-1, -i);
//        }).collect(Collectors.toSet()));
//        MarsExplorerSimulator.getBlockSet().addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6).stream().map(i -> {
//            return new MarsExplorerSimulator.Position(-i, -1);
//        }).collect(Collectors.toSet()));

    }


    private boolean testPathEquality(Map<String, String> aresult, Stack<MarsExplorerSimulator.Position> eresult) {
        if (aresult.isEmpty() && eresult.isEmpty()) {
            return true;
        } else if (!aresult.isEmpty() && !eresult.isEmpty()) {
            Queue<String> fpath = new LinkedBlockingQueue<>();
            String target = eresult.peek().toString();
            System.out.print(target + " ");
            fpath.add(target);
            String parentPath = aresult.get(target);
            System.out.print(parentPath + " ");

            fpath.add(parentPath);
            int counter = 0;
            while (true) {
                counter++;
                if (null != aresult.get(parentPath)) {
                    parentPath = aresult.get(parentPath);
                    fpath.add(parentPath);
                    System.out.print(parentPath + " ");
                } else {
                    break;
                }
            }
            if (fpath.size() == eresult.size()) {
                for (int i = 0; i <= eresult.size(); ++i) {
                    if (!fpath.remove().equals(eresult.pop().toString()))
                        return false;
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }


    @Test
    public void testExplorePath1() {
        MarsExplorerSimulator.traverseCmd("PLACE", 0, 0);
        MarsExplorerSimulator.traverseCmd("BLOCK", 0, 2);
        Map<String, String> aresult = MarsExplorerSimulator.traverseCmd("EXPLORE", 0, 3);
        Stack<MarsExplorerSimulator.Position> eresult = new Stack<>();
        eresult.add(new MarsExplorerSimulator.Position(0, 0));
        eresult.add(new MarsExplorerSimulator.Position(0, 1));
        eresult.add(new MarsExplorerSimulator.Position(1, 1));
        eresult.add(new MarsExplorerSimulator.Position(1, 2));
        eresult.add(new MarsExplorerSimulator.Position(1, 3));
        eresult.add(new MarsExplorerSimulator.Position(0, 3));

        Assert.assertTrue(testPathEquality(aresult, eresult));
    }

    @Test
    public void testExplorePath21() {
        MarsExplorerSimulator.traverseCmd("PLACE", 0, 0);
        MarsExplorerSimulator.traverseCmd("BLOCK", 1, 2);
        MarsExplorerSimulator.traverseCmd("BLOCK", 1, 3);
        MarsExplorerSimulator.traverseCmd("BLOCK", 2, 1);
        MarsExplorerSimulator.traverseCmd("BLOCK", 3, 1);
        Map<String, String> aresult = MarsExplorerSimulator.traverseCmd("EXPLORE", 2, 2);

        Stack<MarsExplorerSimulator.Position> eresult = new Stack<>();
        eresult.add(new MarsExplorerSimulator.Position(0, 0));
        eresult.add(new MarsExplorerSimulator.Position(0, 1));
        eresult.add(new MarsExplorerSimulator.Position(0, 2));
        eresult.add(new MarsExplorerSimulator.Position(0, 3));
        eresult.add(new MarsExplorerSimulator.Position(0, 4));
        eresult.add(new MarsExplorerSimulator.Position(1, 4));
        eresult.add(new MarsExplorerSimulator.Position(2, 4));
        eresult.add(new MarsExplorerSimulator.Position(2, 3));
        eresult.add(new MarsExplorerSimulator.Position(2, 2));

        Assert.assertTrue(testPathEquality(aresult, eresult));
    }

    @Test
    public void testExplorePath22() {
        MarsExplorerSimulator.traverseCmd("PLACE", 0, 0);
        MarsExplorerSimulator.traverseCmd("BLOCK", 1, 2);
        MarsExplorerSimulator.traverseCmd("BLOCK", 2, 1);
        Map<String, String> aresult = MarsExplorerSimulator.traverseCmd("EXPLORE", 2, 2);

        Stack<MarsExplorerSimulator.Position> eresult = new Stack<>();
        eresult.add(new MarsExplorerSimulator.Position(0, 0));
        eresult.add(new MarsExplorerSimulator.Position(0, 1));
        eresult.add(new MarsExplorerSimulator.Position(0, 2));
        eresult.add(new MarsExplorerSimulator.Position(0, 3));
        eresult.add(new MarsExplorerSimulator.Position(1, 3));
        eresult.add(new MarsExplorerSimulator.Position(2, 3));
        eresult.add(new MarsExplorerSimulator.Position(2, 2));

        Assert.assertTrue(testPathEquality(aresult, eresult));
    }


    @Test
    public void testExplorePath3() {
        MarsExplorerSimulator.traverseCmd("PLACE", 0, 0);
        MarsExplorerSimulator.traverseCmd("BLOCK", 1, 2);
        Map<String, String> aresult = MarsExplorerSimulator.traverseCmd("EXPLORE", 2, 2);
        Stack<MarsExplorerSimulator.Position> eresult = new Stack<>();
        eresult.add(new MarsExplorerSimulator.Position(0, 0));
        eresult.add(new MarsExplorerSimulator.Position(0, 1));
        eresult.add(new MarsExplorerSimulator.Position(1, 1));
        eresult.add(new MarsExplorerSimulator.Position(2, 1));
        eresult.add(new MarsExplorerSimulator.Position(2, 2));


        Assert.assertTrue(testPathEquality(aresult, eresult));

    }

    @Test
    public void testExplorePath4() {
        MarsExplorerSimulator.traverseCmd("PLACE", 0, 0);
        MarsExplorerSimulator.traverseCmd("BLOCK", 2, 1);
        Map<String, String> aresult = MarsExplorerSimulator.traverseCmd("EXPLORE", 2, 2);

        Stack<MarsExplorerSimulator.Position> eresult = new Stack<>();
        eresult.add(new MarsExplorerSimulator.Position(0, 0));
        eresult.add(new MarsExplorerSimulator.Position(0, 1));
        eresult.add(new MarsExplorerSimulator.Position(0, 2));
        eresult.add(new MarsExplorerSimulator.Position(1, 2));
        eresult.add(new MarsExplorerSimulator.Position(2, 2));


        Assert.assertTrue(testPathEquality(aresult, eresult));
    }


    @Test
    public void testExplorePath7() {
        MarsExplorerSimulator.traverseCmd("PLACE", 0, 0);
        MarsExplorerSimulator.traverseCmd("BLOCK", 1, 2);
        MarsExplorerSimulator.traverseCmd("BLOCK", 2, 1);
        MarsExplorerSimulator.traverseCmd("BLOCK", 2, 3);
        Map<String, String> aresult = MarsExplorerSimulator.traverseCmd("EXPLORE", 2, 2);

        Stack<MarsExplorerSimulator.Position> eresult = new Stack<>();
        eresult.add(new MarsExplorerSimulator.Position(0, 0));
        eresult.add(new MarsExplorerSimulator.Position(1, 0));
        eresult.add(new MarsExplorerSimulator.Position(2, 0));
        eresult.add(new MarsExplorerSimulator.Position(3, 0));
        eresult.add(new MarsExplorerSimulator.Position(3, 1));
        eresult.add(new MarsExplorerSimulator.Position(3, 2));
        eresult.add(new MarsExplorerSimulator.Position(2, 2));

        Assert.assertTrue(testPathEquality(aresult, eresult));
    }

    @Test
    public void testExplorePath8() {
        MarsExplorerSimulator.traverseCmd("PLACE", 0, 0);
        MarsExplorerSimulator.traverseCmd("BLOCK", 4, 3);
        MarsExplorerSimulator.traverseCmd("BLOCK", 4, 1);
        MarsExplorerSimulator.traverseCmd("BLOCK", 2, 3);
        Map<String, String> aresult = MarsExplorerSimulator.traverseCmd("EXPLORE", 4, 4);

        Stack<MarsExplorerSimulator.Position> eresult = new Stack<>();
        eresult.add(new MarsExplorerSimulator.Position(0, 0));
        eresult.add(new MarsExplorerSimulator.Position(0, 1));
        eresult.add(new MarsExplorerSimulator.Position(0, 2));
        eresult.add(new MarsExplorerSimulator.Position(0, 3));
        eresult.add(new MarsExplorerSimulator.Position(0, 4));
        eresult.add(new MarsExplorerSimulator.Position(1, 4));
        eresult.add(new MarsExplorerSimulator.Position(2, 4));
        eresult.add(new MarsExplorerSimulator.Position(3, 4));
        eresult.add(new MarsExplorerSimulator.Position(4, 4));

        Assert.assertTrue(testPathEquality(aresult, eresult));
    }

    
    @Test
    public void testExplorePath10() {
        MarsExplorerSimulator.traverseCmd("PLACE", 0, 0);
        MarsExplorerSimulator.traverseCmd("BLOCK", 4, 1);
        MarsExplorerSimulator.traverseCmd("BLOCK", 2, 3);
        Map<String, String> aresult = MarsExplorerSimulator.traverseCmd("EXPLORE", 5, 6);

//        Stack<MarsExplorerSimulator.Position> eresult = new Stack<>();
//        eresult.add(new MarsExplorerSimulator.Position(4, 3));
//        eresult.add(new MarsExplorerSimulator.Position(3, 3));
//        eresult.add(new MarsExplorerSimulator.Position(3, 2));
//        eresult.add(new MarsExplorerSimulator.Position(3, 1));
//        eresult.add(new MarsExplorerSimulator.Position(3, 0));
//        eresult.add(new MarsExplorerSimulator.Position(2, 0));
//        eresult.add(new MarsExplorerSimulator.Position(1, 0));
//        eresult.add(new MarsExplorerSimulator.Position(0, 0));
//
//        Assert.assertTrue(testPathEquality(aresult, eresult));
    }

    @Test
    public void testExplorePath11() {
        MarsExplorerSimulator.traverseCmd("PLACE", 0, 0);
        MarsExplorerSimulator.traverseCmd("BLOCK", 4, 1);
        MarsExplorerSimulator.traverseCmd("BLOCK", 2, 3);
        Map<String, String> aresult = MarsExplorerSimulator.traverseCmd("EXPLORE", 6, 6);

//        Stack<MarsExplorerSimulator.Position> eresult = new Stack<>();
//        eresult.add(new MarsExplorerSimulator.Position(4, 3));
//        eresult.add(new MarsExplorerSimulator.Position(3, 3));
//        eresult.add(new MarsExplorerSimulator.Position(3, 2));
//        eresult.add(new MarsExplorerSimulator.Position(3, 1));
//        eresult.add(new MarsExplorerSimulator.Position(3, 0));
//        eresult.add(new MarsExplorerSimulator.Position(2, 0));
//        eresult.add(new MarsExplorerSimulator.Position(1, 0));
//        eresult.add(new MarsExplorerSimulator.Position(0, 0));
//
//        Assert.assertTrue(testPathEquality(aresult, eresult));
    }

    @Test
    public void testExplorePath12() {
        MarsExplorerSimulator.traverseCmd("PLACE", 0, 0);

        Map<String, String> aresult = MarsExplorerSimulator.traverseCmd("EXPLORE", -1, -1);

//        Stack<MarsExplorerSimulator.Position> eresult = new Stack<>();
//        eresult.add(new MarsExplorerSimulator.Position(4, 3));
//        eresult.add(new MarsExplorerSimulator.Position(3, 3));
//        eresult.add(new MarsExplorerSimulator.Position(3, 2));
//        eresult.add(new MarsExplorerSimulator.Position(3, 1));
//        eresult.add(new MarsExplorerSimulator.Position(3, 0));
//        eresult.add(new MarsExplorerSimulator.Position(2, 0));
//        eresult.add(new MarsExplorerSimulator.Position(1, 0));
//        eresult.add(new MarsExplorerSimulator.Position(0, 0));
//
//        Assert.assertTrue(testPathEquality(aresult, eresult));
    }

    @Test
    public void testExplorePath13() {
        MarsExplorerSimulator.traverseCmd("PLACE", 0, 0);
        MarsExplorerSimulator.traverseCmd("BLOCK", 1, 5);
        MarsExplorerSimulator.traverseCmd("BLOCK", 2, 3);
        Map<String, String> aresult = MarsExplorerSimulator.traverseCmd("EXPLORE", 2, 5);

        Stack<MarsExplorerSimulator.Position> eresult = new Stack<>();
        eresult.add(new MarsExplorerSimulator.Position(0, 0));
        eresult.add(new MarsExplorerSimulator.Position(0, 1));
        eresult.add(new MarsExplorerSimulator.Position(0, 2));
        eresult.add(new MarsExplorerSimulator.Position(0, 3));
        eresult.add(new MarsExplorerSimulator.Position(0, 4));
        eresult.add(new MarsExplorerSimulator.Position(1, 4));
        eresult.add(new MarsExplorerSimulator.Position(2, 4));
        eresult.add(new MarsExplorerSimulator.Position(2, 5));

        Assert.assertTrue(testPathEquality(aresult, eresult));
    }

    @Test
    public void testExplorePath14() {
        MarsExplorerSimulator.traverseCmd("PLACE", 0, 0);
        MarsExplorerSimulator.traverseCmd("BLOCK", 1, 4);
        MarsExplorerSimulator.traverseCmd("BLOCK", 2, 3);
        MarsExplorerSimulator.traverseCmd("BLOCK", 3, 4);
        Map<String, String> aresult = MarsExplorerSimulator.traverseCmd("EXPLORE", 2, 4);

        Stack<MarsExplorerSimulator.Position> eresult = new Stack<>();
        eresult.add(new MarsExplorerSimulator.Position(0, 0));
        eresult.add(new MarsExplorerSimulator.Position(0, 1));
        eresult.add(new MarsExplorerSimulator.Position(0, 2));
        eresult.add(new MarsExplorerSimulator.Position(0, 3));
        eresult.add(new MarsExplorerSimulator.Position(0, 4));
        eresult.add(new MarsExplorerSimulator.Position(0, 5));

        eresult.add(new MarsExplorerSimulator.Position(1, 5));
        eresult.add(new MarsExplorerSimulator.Position(2, 5));
        eresult.add(new MarsExplorerSimulator.Position(2, 4));

        Assert.assertTrue(testPathEquality(aresult, eresult));
    }
}
