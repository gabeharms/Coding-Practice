package com.gabeharms.sorting;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Unit test for MergeSort.
 */
public class MergeSortTest
  extends SortingAlgorithmTest
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public MergeSortTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( MergeSortTest.class );
  }

  /**
   * @method sort
   * @context Already sorted list
   */
  public void testAlreadySortedList()
  {
    MergeSort<Integer> sorter = new MergeSort<Integer>(sortedList());

    assertEquals(sortedList(), sorter.sort());
  }

  /**
   * @method sort
   * @context Partially sorted list
   */
  public void testPartiallySortedList()
  {
    MergeSort<Integer> sorter = new MergeSort<Integer>(partiallySortedList());

    assertEquals(sortedList(), sorter.sort());
  }

  /**
   * @method sort
   * @context Unsorted list
   */
  public void testUnsortedList()
  {
    MergeSort<Integer> sorter = new MergeSort<Integer>(unsortedList());

    assertEquals(sortedList(), sorter.sort());
  }
}
