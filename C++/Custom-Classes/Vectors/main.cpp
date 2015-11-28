// main.cpp - vector classs testing main program entry point
// written by Gabe Harms


#include "vector.h"


void main()
{
	cout << "Vector class testing" << endl;

	//Write your test cases
	
	//Create another vector using dfault constructor
		Vector<int> myVector;
	
	//Create Vector using init constructor
		Vector<int> anotherVector(5);
		
	//test add function. test up to capacity and over capacity
	//check index operator by using for loop. Test with valid/invalid index
		anotherVector.Insert(10, 4);
		anotherVector.Insert(1, 0);
		anotherVector.Insert(2, 1);
		anotherVector.Insert(3, 2);
		anotherVector.Insert(5, 3);
		anotherVector.Insert(20, 4);
		
		for (int i = 0; i < 10; i++)
		{
			cout << anotherVector[i] << " " << endl;
			
		}
		cout << "\n\n\n\n";
		
		
		
		
		
		anotherVector.Remove(1);

		for (int i = 0; i < 10; i++)
		{
			cout << anotherVector[i] << " " << endl;
			
		}
		
		

	
	

	system("pause");

}