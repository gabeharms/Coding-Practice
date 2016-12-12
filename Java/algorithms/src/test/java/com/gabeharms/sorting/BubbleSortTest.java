package com.gabeharms.sorting;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Unit test for BubbleSort.
 */
public class BubbleSortTest
  extends SortingAlgorithmTest
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public BubbleSortTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( BubbleSortTest.class );
  }

  /**
   * @method sort
   * @context Already sorted list
   */
  public void testAlreadySortedList()
  {
    BubbleSort<Integer> sorter = new BubbleSort<Integer>(sortedList());

    assertEquals(sortedList(), sorter.sort());
  }

  /**
   * @method sort
   * @context Partially sorted list
   */
  public void testPartiallySortedList()
  {
    BubbleSort<Integer> sorter = new BubbleSort<Integer>(partiallySortedList());

    assertEquals(sortedList(), sorter.sort());
  }

  /**
   * @method sort
   * @context Unsorted list
   */
  public void testUnsortedList()
  {
    BubbleSort<Integer> sorter = new BubbleSort<Integer>(unsortedList());

    assertEquals(sortedList(), sorter.sort());
  }
}
