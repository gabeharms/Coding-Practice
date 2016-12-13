package com.gabeharms.sorting;

import java.util.List;
import java.util.ArrayList;

public class MergeSort<T extends Comparable<T>> extends SortingAlgorithm<T>
{
  public MergeSort(List<T> list)
  {
    super(list);
  }

  public List<T> sort() {
    list = mergeSort(list);
    return this.list;
  }

  private List<T> mergeSort(List<T> sortedList)
  {
    if (sortedList.size() < 2)
    {
      return sortedList;
    }
    else
    {
      int splitIndex = findSplitIndex(sortedList);
      List<T> subsetA = getSubset(sortedList, 0, splitIndex);
      List<T> subsetB = getSubset(sortedList, splitIndex, sortedList.size());
      return combine(mergeSort(subsetA), mergeSort(subsetB));
    }
  }

  private int findSplitIndex(List<T> list)
  {
    return list.size() / 2;
  }

  private List<T> getSubset(List<T> list, int startIndex, int endIndex)
  {
    return new ArrayList<T>(list.subList(startIndex, endIndex));
  }

  private List<T> combine(List<T> subsetA, List<T> subsetB)
  {
    List<T> mergedSet = new ArrayList<T>();

    while (isListNotEmpty(subsetA) && isListNotEmpty(subsetB))
    {
      pluckLeastFrom(mergedSet, subsetA, subsetB);
    }

    while (isListNotEmpty(subsetA))
    {
      pluckFirstFromTo(subsetA, mergedSet);
    }

    while (isListNotEmpty(subsetB))
    {
      pluckFirstFromTo(subsetB, mergedSet);
    }

    return mergedSet;
  }

  private boolean isListNotEmpty(List<T> list)
  {
    return list.size() != 0;
  }

  private void pluckLeastFrom(List<T> destination, List<T> subsetA, List<T> subsetB)
  {
    if (hasLeastFirstValueThan(subsetA, subsetB))
    {
      pluckFirstFromTo(subsetA, destination);
    }
    else
    {
      pluckFirstFromTo(subsetB, destination);
    }
  }

  private boolean hasLeastFirstValueThan(List<T> subsetA, List<T> subsetB)
  {
    return getFirst(subsetA).compareTo(getFirst(subsetB)) <= 0;
  }

  private T getFirst(List<T> list)
  {
    return list.get(0);
  }

  private T removeFirst(List<T> list)
  {
    return list.remove(0);
  }

  private void pluckFirstFromTo(List<T> from, List<T> to)
  {
    T value = removeFirst(from);
    to.add(value);
  }

  public static void main( String[] args )
  {
    System.out.println( "Hello World!" );
  }
}
