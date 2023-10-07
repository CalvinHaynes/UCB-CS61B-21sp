package IntList;

import static org.junit.Assert.*;

import jh61b.junit.In;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimeSimple2() {
        IntList lst = IntList.of(1, 3, 5, 7, 9, 13, 99, 102, 88);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1 -> 9 -> 25 -> 49 -> 9 -> 169 -> 99 -> 102 -> 88", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimeSimple3() {
        IntList lst = IntList.of(1, 9, 99, 102, 88);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1 -> 9 -> 99 -> 102 -> 88", lst.toString());
        assertFalse(changed);
    }
}
