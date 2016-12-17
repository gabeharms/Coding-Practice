package com.gabeharms.sorting;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Unit test for RadixSort.
 */
public class RadixSortTest
  extends SortingAlgorithmTest
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public RadixSortTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( RadixSortTest.class );
  }

  /**
   * @method sort
   * @context Already sorted list
   */
  public void testAlreadySortedList()
  {
    RadixSort sorter = new RadixSort(sortedList());

    assertEquals(sortedList(), sorter.sort());
  }

  /**
   * @method sort
   * @context Partially sorted list
   */
  public void testPartiallySortedList()
  {
    RadixSort sorter = new RadixSort(partiallySortedList());

    assertEquals(sortedList(), sorter.sort());
  }

  /**
   * @method sort
   * @context Unsorted list
   */
  public void testUnsortedList()
  {
    RadixSort sorter = new RadixSort(unsortedList());

    assertEquals(sortedList(), sorter.sort());
  }

  /**
   * @method sort
   * @context Large Unsorted list
   */
  public void testLargeUnsortedList()
  {
    RadixSort sorter = new RadixSort(largeUnsortedList());

    assertEquals(largeSortedList(), sorter.sort());
  }
}
