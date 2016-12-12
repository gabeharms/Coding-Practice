package com.gabeharms.sorting;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Unit test for SortAlgorithm subclasses to extend.
 */
public abstract class SortingAlgorithmTest
  extends TestCase
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public SortingAlgorithmTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( SortingAlgorithmTest.class );
  }


  protected List<Integer> sortedList()
  {
    Integer[] sortedArray = {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4), new Integer(5)};
    return new ArrayList<Integer>(Arrays.asList(sortedArray));
  }

  protected List<Integer> partiallySortedList()
  {
    Integer[] partiallySortedArray = {new Integer(0), new Integer(1), new Integer(2), new Integer(5), new Integer(4), new Integer(3)};
    return new ArrayList<Integer>(Arrays.asList(partiallySortedArray));
  }

  protected List<Integer> unsortedList()
  {
    Integer[] unsortedArray = {new Integer(0), new Integer(1), new Integer(2), new Integer(5), new Integer(4), new Integer(3)};
    return new ArrayList<Integer>(Arrays.asList(unsortedArray));
  }
}
