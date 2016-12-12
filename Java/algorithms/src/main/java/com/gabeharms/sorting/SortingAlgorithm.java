package com.gabeharms.sorting;

import java.util.List;

abstract class SortingAlgorithm<T extends Comparable<T>>
{
  protected List<T> list;
  abstract List<T> sort();

  public SortingAlgorithm(List<T> list)
  {
    this.list = list;
  }


  public static void main(String[] args) {

  }
}
