package com.gabeharms.search;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Unit test for BinarySearch.
 */
public class BinarySearchTest
  extends TestCase
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public BinarySearchTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( BinarySearchTest.class );
  }

  /**
   * @method search
   * @context Simple List
   */
  public void testSearchOnSimpleList()
  {
    SearchAlgorithm<Integer> searcher = new BinarySearch<Integer>(sortedSimpleList());

    assertEquals(0, searcher.search(0));
    assertEquals(1, searcher.search(1));
    assertEquals(2, searcher.search(2));
    assertEquals(3, searcher.search(3));
    assertEquals(4, searcher.search(4));
    assertEquals(5, searcher.search(5));
    assertEquals(-1, searcher.search(6));
  }

  /**
   * @method search
   * @context ModerateList List
   */
  public void testSearchOnModerateList()
  {
    SearchAlgorithm<Integer> searcher = new BinarySearch<Integer>(sortedModerateList());

    assertEquals(0, searcher.search(0));
    assertEquals(1, searcher.search(1));
    assertEquals(2, searcher.search(2));
    assertEquals(-1, searcher.search(3));
    assertEquals(-1, searcher.search(4));
    assertEquals(3, searcher.search(5));
    assertEquals(4, searcher.search(6));
    assertEquals(5, searcher.search(7));
  }

  /**
   * @method search
   * @context Simple List
   */
  public void testSearchOnComplexList()
  {
    SearchAlgorithm<Integer> searcher = new BinarySearch<Integer>(sortedComplexList());

    assertEquals(-1, searcher.search(0));
    assertEquals(0, searcher.search(1));
    assertEquals(-1, searcher.search(2));
    assertEquals(-1, searcher.search(3));
    assertEquals(1, searcher.search(4));
    assertEquals(2, searcher.search(5));
    assertEquals(-1, searcher.search(6));
    assertEquals(-1, searcher.search(7));
    assertEquals(-1, searcher.search(8));
    assertEquals(3, searcher.search(9));
    assertEquals(4, searcher.search(10));
    assertEquals(5, searcher.search(11));
    assertEquals(-1, searcher.search(12));
    assertEquals(-1, searcher.search(13));
    assertEquals(-1, searcher.search(14));
    assertEquals(-1, searcher.search(15));
    assertEquals(6, searcher.search(16));
    assertEquals(-1, searcher.search(17));
  }

  private List<Integer> sortedSimpleList()
  {
    Integer[] array = {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4), new Integer(5)};
    return new ArrayList<Integer>(Arrays.asList(array));
  }

  private List<Integer> sortedModerateList()
  {
    Integer[] array = {new Integer(0), new Integer(1), new Integer(2), new Integer(5), new Integer(6), new Integer(7)};
    return new ArrayList<Integer>(Arrays.asList(array));
  }

  private List<Integer> sortedComplexList()
  {
    Integer[] array = {new Integer(1), new Integer(4), new Integer(5), new Integer(9), new Integer(10), new Integer(11), new Integer(16)};
    return new ArrayList<Integer>(Arrays.asList(array));
  }
}
