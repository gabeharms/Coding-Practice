package com.gabeharms.sorting;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Unit test for QuickSort.
 */
public class QuickSortTest
  extends SortingAlgorithmTest
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public QuickSortTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( QuickSortTest.class );
  }

  /**
   * @method sort
   * @context Already sorted list
   */
  public void testAlreadySortedList()
  {
    QuickSort<Integer> sorter = new QuickSort<Integer>(sortedList());

    assertEquals(sortedList(), sorter.sort());
  }

  /**
   * @method sort
   * @context Partially sorted list
   */
  public void testPartiallySortedList()
  {
    QuickSort<Integer> sorter = new QuickSort<Integer>(partiallySortedList());

    assertEquals(sortedList(), sorter.sort());
  }

  /**
   * @method sort
   * @context Unsorted list
   */
  public void testUnsortedList()
  {
    QuickSort<Integer> sorter = new QuickSort<Integer>(unsortedList());

    assertEquals(sortedList(), sorter.sort());
  }

  /**
   * @method sort
   * @context Large Unsorted list
   */
  public void testLargeUnsortedList()
  {
    QuickSort<Integer> sorter = new QuickSort<Integer>(largeUnsortedList());

    assertEquals(largeSortedList(), sorter.sort());
  }
}
