/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 03-Jan-2024 22:59:11
 * @desc [DisjointSetTest, Test cases to test the Disjoint Set implementation and its operations find and union.]
 */

package com.rahilakhera.graph.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DisjointSetTest {

    @Test
    public void testDisjointTestOperations() {

        DisjointSet djs = new DisjointSet(8);

        assertEquals(1, djs.find(1));
        assertEquals(2, djs.find(2));

        /**
         * Before the union operation, both the elements are in the different sets.
         * They are their own parents.
         * After the uninon, parents of both the elements must be same.
         * The negative value in the parent shows, the element is the parent.
         * The number in negative value shows number of elements in the set.
         * So after the union of {1}, {2} -> {1,2}. There are two elements in the set
         * and as both were single elements before union operation, one of them will
         * become parent and it rank will be updated.
         */

        assertFalse(djs.inSameSet(1, 2));
        djs.union(1, 2);
        assertTrue(djs.inSameSet(1, 2));
        assertTrue(-2 == djs.getRank(1) || -2 == djs.getRank(2));

        assertFalse(djs.inSameSet(3, 4));
        djs.union(3, 4);
        assertTrue(djs.inSameSet(3, 4));
        assertTrue(-2 == djs.getRank(3) || -2 == djs.getRank(4));

        assertFalse(djs.inSameSet(5, 6));
        djs.union(5, 6);
        assertTrue(djs.inSameSet(5, 6));
        assertTrue(-2 == djs.getRank(5) || -2 == djs.getRank(6));

        assertFalse(djs.inSameSet(7, 8));
        djs.union(7, 8);
        assertTrue(djs.inSameSet(7, 8));
        assertTrue(-2 == djs.getRank(7) || -2 == djs.getRank(8));

        assertFalse(djs.inSameSet(2, 4));
        djs.union(2, 4);
        assertTrue(djs.inSameSet(2, 4));
        assertTrue(-4 == djs.getRank(1));

        assertFalse(djs.inSameSet(2, 5));
        djs.union(2, 5);
        assertTrue(djs.inSameSet(2, 5));
        assertTrue(-6 == djs.getRank(1));

        assertTrue(djs.inSameSet(1, 3));
        assertFalse(djs.union(1, 3));
        assertTrue(djs.inSameSet(2, 5));
        assertTrue(-6 == djs.getRank(1));

        assertFalse(djs.inSameSet(6, 8));
        assertTrue(djs.union(6, 8));
        assertTrue(djs.inSameSet(6, 8));
        assertTrue(-8 == djs.getRank(1));

        assertTrue(djs.inSameSet(5, 7));
        assertFalse(djs.union(5, 7));
        assertTrue(djs.inSameSet(5, 7));
        assertTrue(-8 == djs.getRank(1));

        assertTrue(-8 == djs.getRank(1));
    }
}
