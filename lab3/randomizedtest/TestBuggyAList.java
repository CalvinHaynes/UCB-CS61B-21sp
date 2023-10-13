package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void randomizedCall () {
        AListNoResizing<Integer> L = new AListNoResizing<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() > 0) {
                    int lastVal = L.getLast();
                    System.out.println(lastVal);
                }
            } else if (operationNumber == 3) {
                //removeLast
                if (L.size() > 0) {
                    int removeVal = L.removeLast();
                    System.out.println(removeVal);
                }
            }
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size1 = correct.size();
                int size2 = broken.size();
                System.out.println("size in AListNoResizing: " + size1);
                System.out.println("size in BuggyAList: " + size2);
            } else if (operationNumber == 2) {
                // getLast
                if (correct.size() > 0) {
                    int lastVal1 = correct.getLast();
                    System.out.println("last element in AListNoResizing: " + lastVal1);
                }
                if (broken.size() > 0) {
                    int lastVal2 = broken.getLast();
                    System.out.println("last element in BuggyAList: " + lastVal2);
                }
            } else if (operationNumber == 3) {
                //removeLast
                if (correct.size() > 0) {
                    int removeVal1 = correct.removeLast();
                    System.out.println("removeLast in AListNoResizing: " + removeVal1);
                }
                if (broken.size() > 0) {
                    int removeVal2 = broken.removeLast();
                    System.out.println("removeLast in BuggyAList: " + removeVal2);
                }
            }
        }
    }

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        correct.addLast(5);
        correct.addLast(10);
        correct.addLast(15);

        broken.addLast(5);
        broken.addLast(10);
        broken.addLast(15);

        assertEquals(correct.size(), broken.size());

        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
    }
}
