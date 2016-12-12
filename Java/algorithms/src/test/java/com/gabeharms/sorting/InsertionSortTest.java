package com.gabeharms.sorting;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Unit test for InsertionSort.
 */
public class InsertionSortTest
  extends SortingAlgorithmTest
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public InsertionSortTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( InsertionSortTest.class );
  }

  /**
   * @method sort
   * @context Already sorted list
   */
  public void testAlreadySortedList()
  {
    InsertionSort<Integer> sorter = new InsertionSort<Integer>(sortedList());

    assertEquals(sortedList(), sorter.sort());
  }

  /**
   * @method sort
   * @context Partially sorted list
   */
  public void testPartiallySortedList()
  {
    InsertionSort<Integer> sorter = new InsertionSort<Integer>(partiallySortedList());

    assertEquals(sortedList(), sorter.sort());
  }

  /**
   * @method sort
   * @context Unsorted list
   */
  public void testUnsortedList()
  {
    InsertionSort<Integer> sorter = new InsertionSort<Integer>(unsortedList());

    assertEquals(sortedList(), sorter.sort());
  }
}
