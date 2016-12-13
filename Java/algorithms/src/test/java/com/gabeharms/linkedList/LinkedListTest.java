package com.gabeharms.linkedList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for LinkedList.
 */
public class LinkedListTest
  extends TestCase
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public LinkedListTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( LinkedListTest.class );
  }

  /**
   * @method size
   * @context empty list
   */
  public void testSizeEmptyList()
  {
    LinkedList<Integer> list = new LinkedList<Integer>();

    assertEquals(0, list.size());
  }


  /**
   * @method size
   * @context empty list with head of NullNode
   */
  public void testSizeEmptyListNullNode()
  {
    LinkedList<Integer> list = new LinkedList<Integer>(new HeadNode<Integer>());

    assertEquals(0, list.size());
  }


  /**
   * @method size
   * @context one real node in list
   */
  public void testSizeOneRealNodeInList()
  {
    HeadNode<Integer> head = new HeadNode<Integer>(null, new RealNode<Integer>());
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    assertEquals(1, list.size());
  }


  /**
   * @method size
   * @context one real node in list and a null node
   */
  public void testSizeOneRealNodeInListOneNullNode()
  {
    Node<Integer> node1 = new RealNode<Integer>(null, null, new NullNode<Integer>());
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    assertEquals(1, list.size());
  }


  /**
   * @method size
   * @context three real nodes in list
   */
  public void testSizeThreeRealNodeInList()
  {
    Node<Integer> node3 = new RealNode<Integer>(5, null, null);
    Node<Integer> node2 = new RealNode<Integer>(5, null, node3);
    Node<Integer> node1 = new RealNode<Integer>(5, null, node2);
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    assertEquals(3, list.size());
  }

  /**
   * @method size
   * @context three real nodes in list and one null node
   */
  public void testSizeThreeRealNodeInListOneNullNode()
  {
    Node<Integer> node3 = new RealNode<Integer>(5, null, new NullNode<Integer>());
    Node<Integer> node2 = new RealNode<Integer>(5, null, node3);
    Node<Integer> node1 = new RealNode<Integer>(5, null, node2);
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    assertEquals(3, list.size());
  }


  /**
   * @method get
   * @context when empty list, getting 0
   */
  public void testGetZeroEmptyList()
  {
    HeadNode<Integer> head = new HeadNode<Integer>(null, null);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    assertTrue(list.get(0) instanceof NullNode);
  }

  /**
   * @method get
   * @context when empty list, getting 50
   */
  public void testGetFiftyEmptyList()
  {
    HeadNode<Integer> head = new HeadNode<Integer>(null, null);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    assertTrue(list.get(50) instanceof NullNode);
  }


  /**
   * @method get
   * @context when one node in list, getting 0
   */
  public void testGetZeroOnSingleNodeList()
  {
    Node<Integer> node1 = new RealNode<Integer>(5, null, null);
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    assertEquals(node1, list.get(0));
  }


  /**
   * @method get
   * @context when one node in list, getting 1
   */
  public void testGetOneOnSingleNodeList()
  {
    Node<Integer> node1 = new RealNode<Integer>(5, null, null);
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    assertTrue(list.get(1) instanceof NullNode);
  }


  /**
   * @method get
   * @context when three node in list, getting 0
   */
  public void testGetZeroOnThreeNodeList()
  {
    Node<Integer> node3 = new RealNode<Integer>(5, null, null);
    Node<Integer> node2 = new RealNode<Integer>(5, null, node3);
    Node<Integer> node1 = new RealNode<Integer>(5, null, node2);
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    assertEquals(node1, list.get(0));
  }

  /**
   * @method get
   * @context when three node in list, getting 1
   */
  public void testGetOneOnThreeNodeList()

  {
    Node<Integer> node3 = new RealNode<Integer>(5, null, null);
    Node<Integer> node2 = new RealNode<Integer>(5, null, node3);
    Node<Integer> node1 = new RealNode<Integer>(5, null, node2);
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    assertEquals(node2, list.get(1));
  }

  /**
   * @method get
   * @context when three node in list, getting 2
   */
  public void testGetTwoOnThreeNodeList()

  {
    Node<Integer> node3 = new RealNode<Integer>(5, null, null);
    Node<Integer> node2 = new RealNode<Integer>(5, null, node3);
    Node<Integer> node1 = new RealNode<Integer>(5, null, node2);
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    assertEquals(node3, list.get(2));
  }

  /**
   * @method get
   * @context when three node in list, getting 3
   */
  public void testGetThreeOnThreeNodeList()

  {
    Node<Integer> node3 = new RealNode<Integer>(5, null, null);
    Node<Integer> node2 = new RealNode<Integer>(5, null, node3);
    Node<Integer> node1 = new RealNode<Integer>(5, null, node2);
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    assertTrue(list.get(3) instanceof NullNode);
  }


  /**
   * @method add
   * @context when empty list
   */
  public void testAddOnEmptyList()

  {
    Node<Integer> node1 = new RealNode<Integer>(5, null, null);
    HeadNode<Integer> head = new HeadNode<Integer>(null, null);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    list.add(node1);

    assertEquals(1, list.size());
    assertEquals(node1, list.get(0));
    assertTrue(list.get(1) instanceof NullNode);
  }


  /**
   * @method add
   * @context when list has one node
   */
  public void testAddOnOneNodeList()

  {
    Node<Integer> node2 = new RealNode<Integer>(5, null, null);
    Node<Integer> node1 = new RealNode<Integer>(5, null, null);
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    list.add(node2);

    assertEquals(2, list.size());
    assertEquals(node1, list.get(0));
    assertEquals(node2, list.get(1));
    assertTrue(list.get(2) instanceof NullNode);
    assertEquals(node2, list.get(0).getNext());
    assertEquals(node1, list.get(1).getPrevious());
  }

  /**
   * @method add
   * @context when list has three nodes
   */
  public void testAddOnThreeNodeList()

  {
    Node<Integer> node4 = new RealNode<Integer>(5, null, null);
    Node<Integer> node3 = new RealNode<Integer>(5, null, null);
    Node<Integer> node2 = new RealNode<Integer>(5, null, node3);
    Node<Integer> node1 = new RealNode<Integer>(5, null, node2);
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    list.add(node4);

    assertEquals(4, list.size());
    assertEquals(node1, list.get(0));
    assertEquals(node2, list.get(1));
    assertEquals(node3, list.get(2));
    assertEquals(node4, list.get(3));
    assertTrue(list.get(4) instanceof NullNode);
    assertEquals(node4, list.get(2).getNext());
    assertEquals(node3, list.get(3).getPrevious());
  }


  /**
   * @method remove
   * @context when empty list, remove node 0
   */
  public void testRemoveZeroOnEmptyList()
  {
    HeadNode<Integer> head = new HeadNode<Integer>(null, null);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    Node<Integer> removedNode = list.remove(0);

    assertEquals(0, list.size());
    assertTrue(removedNode instanceof NullNode);
    assertTrue(list.get(0) instanceof NullNode);
  }

  /**
   * @method remove
   * @context when empty list, remove node 1
   */
  public void testRemoveOneOnEmptyList()
  {
    HeadNode<Integer> head = new HeadNode<Integer>(null, null);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    Node<Integer> removedNode = list.remove(1);

    assertEquals(0, list.size());
    assertTrue(removedNode instanceof NullNode);
    assertTrue(list.get(0) instanceof NullNode);
  }

  /**
   * @method remove
   * @context when one node list, remove node 0
   */
  public void testRemoveZeroOnSingleNodeList()
  {
    Node<Integer> node1 = new RealNode<Integer>(5, null, null);
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    node1.setPrevious(head);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    Node<Integer> removedNode = list.remove(0);

    assertEquals(0, list.size());
    assertEquals(node1, removedNode);
    assertTrue(list.get(0) instanceof NullNode);
  }

  /**
   * @method remove
   * @context when one node list, remove node 1
   */
  public void testRemoveOneOnSingleNodeList()
  {
    Node<Integer> node1 = new RealNode<Integer>(5, null, null);
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    node1.setPrevious(head);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    Node<Integer> removedNode = list.remove(1);

    assertEquals(1, list.size());
    assertTrue(removedNode instanceof NullNode);
    assertEquals(node1, list.get(0));
    assertTrue(list.get(0).getNext() instanceof NullNode);
    assertEquals(head, list.get(0).getPrevious());
  }

  /**
   * @method remove
   * @context when three node list, remove node 0
   */
  public void testRemoveZeroOnThreeNodeList()
  {
    Node<Integer> node3 = new RealNode<Integer>(5, null, null);
    Node<Integer> node2= new RealNode<Integer>(5, null, node3);
    Node<Integer> node1 = new RealNode<Integer>(5, null, node2);
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    node1.setPrevious(head);
    node2.setPrevious(node1);
    node3.setPrevious(node2);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    Node<Integer> removedNode = list.remove(0);

    assertEquals(2, list.size());
    assertEquals(node1, removedNode);
    assertEquals(node2, list.get(0));
    assertEquals(head, list.get(0).getPrevious());
    assertEquals(node3, list.get(0).getNext());
    assertEquals(node3, list.get(1));
    assertEquals(node2, list.get(1).getPrevious());
    assertTrue(list.get(1).getNext() instanceof NullNode);
  }

  /**
   * @method remove
   * @context when three node list, remove node 1
   */
  public void testRemoveOneOnThreeNodeList()
  {
    Node<Integer> node3 = new RealNode<Integer>(5, null, null);
    Node<Integer> node2= new RealNode<Integer>(5, null, node3);
    Node<Integer> node1 = new RealNode<Integer>(5, null, node2);
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    node1.setPrevious(head);
    node2.setPrevious(node1);
    node3.setPrevious(node2);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    Node<Integer> removedNode = list.remove(1);

    assertEquals(2, list.size());
    assertEquals(node2, removedNode);
    assertEquals(node1, list.get(0));
    assertEquals(head, list.get(0).getPrevious());
    assertEquals(node3, list.get(0).getNext());
    assertEquals(node3, list.get(1));
    assertEquals(node1, list.get(1).getPrevious());
    assertTrue(list.get(1).getNext() instanceof NullNode);
  }

  /**
   * @method remove
   * @context when three node list, remove node 2
   */
  public void testRemoveTwoOnThreeNodeList()
  {
    Node<Integer> node3 = new RealNode<Integer>(5, null, null);
    Node<Integer> node2= new RealNode<Integer>(5, null, node3);
    Node<Integer> node1 = new RealNode<Integer>(5, null, node2);
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    node1.setPrevious(head);
    node2.setPrevious(node1);
    node3.setPrevious(node2);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    Node<Integer> removedNode = list.remove(2);

    assertEquals(2, list.size());
    assertEquals(node3, removedNode);
    assertEquals(node1, list.get(0));
    assertEquals(head, list.get(0).getPrevious());
    assertEquals(node2, list.get(0).getNext());
    assertEquals(node2, list.get(1));
    assertEquals(node1, list.get(1).getPrevious());
    assertTrue(list.get(1).getNext() instanceof NullNode);
  }

  /**
   * @method remove
   * @context when three node list, remove node 3
   */
  public void testRemoveThreeOnThreeNodeList()
  {
    Node<Integer> node3 = new RealNode<Integer>(5, null, null);
    Node<Integer> node2= new RealNode<Integer>(5, null, node3);
    Node<Integer> node1 = new RealNode<Integer>(5, null, node2);
    HeadNode<Integer> head = new HeadNode<Integer>(null, node1);
    node1.setPrevious(head);
    node2.setPrevious(node1);
    node3.setPrevious(node2);
    LinkedList<Integer> list = new LinkedList<Integer>(head);

    Node<Integer> removedNode = list.remove(3);

    assertEquals(3, list.size());
    assertTrue(removedNode instanceof NullNode);
    assertEquals(node1, list.get(0));
    assertEquals(head, list.get(0).getPrevious());
    assertEquals(node2, list.get(0).getNext());
    assertEquals(node2, list.get(1));
    assertEquals(node1, list.get(1).getPrevious());
    assertEquals(node3, list.get(1).getNext());
    assertEquals(node3, list.get(2));
    assertEquals(node2, list.get(2).getPrevious());
    assertTrue(list.get(2).getNext() instanceof NullNode);
  }
}
