package com.gabeharms.search;

import java.util.List;

abstract class SearchAlgorithm<T extends Comparable<T>>
{
  protected List<T> sortedList;
  abstract int search(T value);

  public SearchAlgorithm(List<T> sortedList)
  {
    this.sortedList = sortedList;
  }


  public static void main(String[] args) {

  }
}
