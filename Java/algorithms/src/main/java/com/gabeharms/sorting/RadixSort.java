package com.gabeharms.sorting;

import java.util.List;

public class RadixSort<T extends Comparable<T>> extends SortingAlgorithm<T>
{

  public RadixSort(List<T> list)
  {
    super(list);
  }

  public List<T> sort() {

    return this.list;
  }

  public static void main( String[] args )
  {
    System.out.println( "Hello World!" );
  }
}
