//main.cpp - written by Gabe Harms



#include <iostream>
#include "String.h"
#define HELLO_WORD_LENGTH 5

using namespace std;



void main()
{

	String s(25);


	String test = "hello";
	
	//Test .GetLength
	int length = test.GetLength();
	if (length != HELLO_WORD_LENGTH)
		cout << "Error: Test .GetLength(); " << test << " is not " << length << " characters long" <<  endl;

	//Test = operator
	String compare = "whatup";
	compare = test;
	if (compare != test)
		cout << "Error: Test = operator: " << compare << " should equal " << test;


	//Test == operator
	compare = "no";
	if (test == compare)
		cout << "Error: Test == operator: " << compare << " does not equal " << test << endl;
	
	//Test != operator
	compare = test;
	if (test != compare)
		cout << "Error: Test != operator: " << compare << " should equal " << test << endl;
		
	//Test > operator
	compare = "longword";
	if (test > compare)
		cout << "Error: Test > operator: " << compare << " should be longer than  " << test << endl;
	
	//Test < operator
	compare = "no";
	if (test < compare)
		cout << "Error: Test < operator: " << compare << " should be less than " << test << endl;
	

	


	system("pause");

}
