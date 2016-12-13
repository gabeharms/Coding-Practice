package com.gabeharms.linkedList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Node.
 */
public class NullNodeTest
  extends TestCase
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public NullNodeTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( NullNodeTest.class );
  }

  /**
   * @method getValue
   * @context when null value
   */
  public void testNullGetValue()
  {
    Node<Integer> node = new NullNode<Integer>(null, null, null);

    assertEquals(null, node.getValue());
  }

  /**
   * @method getValue
   * @context when non-null value
   */
  public void testNonNullGetValue()
  {
    Integer value = new Integer(5);
    Node<Integer> node = new NullNode<Integer>(value, null, null);

    assertEquals(null, node.getValue());
  }

  /**
   * @method getPrevious
   * @context when null value
   */
  public void testNullGetPrevious()
  {
    Node<Integer> node = new NullNode<Integer>(null, null, null);

    assertTrue(node.getPrevious() instanceof NullNode);
  }

  /**
   * @method getPrevious
   * @context when non-null value
   */
  public void testNonNullGetPrevious()
  {
    Node<Integer> previous = new NullNode<Integer>(new Integer(5), null, null);
    Node<Integer> node = new NullNode<Integer>(null, previous, null);

    assertTrue(node.getPrevious() instanceof NullNode);
  }

  /**
   * @method getNext
   * @context when null value
   */
  public void testNullGetNext()
  {
    Node<Integer> node = new NullNode<Integer>(null, null, null);

    assertTrue(node.getNext() instanceof NullNode);
  }

  /**
   * @method getNext
   * @context when non-null value
   */
  public void testNonNullGetNext()
  {
    Node<Integer> next = new NullNode<Integer>(new Integer(5), null, null);
    Node<Integer> node = new NullNode<Integer>(null, null, next);

    assertTrue(node.getNext() instanceof NullNode);
  }


  /**
   * @method setValue
   * @context when non-null value
   */
  public void testNonNullSetValue()
  {
    Integer value = new Integer(5);
    Node<Integer> node = new NullNode<Integer>(null, null, null);
    node.setValue(value);

    assertEquals(null, node.getValue());
  }

  /**
   * @method setPrevious
   * @context when non-null value
   */
  public void testNonNullSetPrevious()
  {
    Node<Integer> previous = new NullNode<Integer>(new Integer(5), null, null);
    Node<Integer> node = new NullNode<Integer>(null, null, null);
    node.setPrevious(previous);

    assertTrue(node.getPrevious() instanceof NullNode);
  }

  /**
   * @method setNext
   * @context when non-null value
   */
  public void testNonNullSetNext()
  {
    Node<Integer> next = new NullNode<Integer>(new Integer(5), null, null);
    Node<Integer> node = new NullNode<Integer>(null, null, null);
    node.setNext(next);

    assertTrue(node.getNext() instanceof NullNode);
  }

  /**
   * @method hasNext
   * @context at all times
   */
  public void testNullHasNext()
  {
    Node<Integer> node = new NullNode<Integer>(null, null, null);

    assertFalse(node.hasNext());
  }

  /**
   * @method hasPrevious
   * @context at all times
   */
  public void testHasPrevious()
  {
    Node<Integer> node = new NullNode<Integer>(null, null, null);

    assertFalse(node.hasPrevious());
  }

  /**
   * @method isNull
   * @context at all times
   */
  public void testisNull()
  {
    Node<Integer> node = new NullNode<Integer>(null, null, null);

    assertTrue(node.isNull());
  }
}
