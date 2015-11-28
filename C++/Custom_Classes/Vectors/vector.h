// vector.h - vector class declaration
//written by gabe harms


#pragma once
#include <iostream>
#define INITIAL_CAPACITY 20
#define CAPACITY_BOOST 10
using namespace std;


template<typename DATA_TYPE>
class Vector
{

public:
	

	//Init/Default-constructor
	//When initialCapacity is not specified upon initialization
	//the compiler 
	Vector(int initialCapacity = INITIAL_CAPACITY)
	{
		Size = 0;
		Capacity = initialCapacity;
		Data = new DATA_TYPE[Capacity];
	}

	//Copy-Constructor, perfroms deep copy
	Vector(const Vector& source)
	{
		//Initially the Data pointer is set to some garbage address,
		//which will cuase delete staement in the assignment operator 
		//to crash. So we set the Data pointer to NULL to prevent the 
		//delete statement in the = operator from crashing
		Data = NULL
		*this = source
	}

	//Destructor
	~Vector()
	{
	}
	
	//Assignment operator perfroms deeep copy
	Vector& operator = (const Vector& source)
	{
		//1. Dispose the old memory
		delete[] Data;

		int sourceCapacity = source.Capacity();
		//2. Allocate Data to match the size of source Capacity
		Data = new DATA_TYPE[sourceCapacity];

		//3. Copy all array elemnts from source data into this Data
		for (int i = 0; i < Size; i++)
				Data[i] = source[i];

		//4. Copy Size and Cpacity
		Capacity = sourceCapacity;
		Size = source.Size();
	}


	//Index operator
	DATA_TYPE& operator[] (int index) const
	{
		return Data[index];
	}

	//Adds a new element at the end of the Vector
	void Add(DATA_TYPE value)
	{

		Insert(value, Size);

		
	}

	//Inserts the value into this Vector at specified index
	//oves the tail to the right to accomodate the new element
	void Insert(const DATA_TYPE& value, int index)
	{
		
		//If the Capacity is not large enough
		if (Size >= Capacity)
		{
			//1. Increase Capacity
			Capacity += CAPACITY_BOOST;
			
			//2. Allocate larger C-array
			DATA_TYPE* newData = new DATA_TYPE[Capacity];

			//3. Copy elemnts from Data to newData
			for (int i = 0; i < Size; i++)
				newData[i] = Data[i];

			//4. Delete old Data
			delete[] Data;

			// 5. Replace the pointer to the deleted old Data with
			//the point newData
			Data = newData;

		}

		//Move the tail
			for (int i = Size-1; i > index; i--)
			{
				Data[i+1] = Data[i];
			}

			Data[index] = value;
			Size++;
	}

	//Removes an element at the specified index by moving the tail to the left
	void Remove(int index)
	{

	}

	//Get-accesors to provide read-only access to Size and Capacity
	int GetSize() const
	{
		return Size;
	}
	int GetCapacity() const
	{
		return Capacity;
	}

private:

	//Pointer representing the encapsulated C - array
	DATA_TYPE* Data;

	// Size is the count of the element in the C-array actually used
	int Size;
	//Capacity is the toatl count of allocated elements in teh data Carray
	int Capacity;
};

template<typename DATA_TYPE>
ostream& operator << (ostream& out,  Vector<DATA_TYPE>& myVector)
{
	//write a for loop to print all vector elements
	return out;
}