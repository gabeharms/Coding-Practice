// String.h - String class declaration
// Written by Max I. Fomitchev-Zamilov

#pragma once

#include <iostream>
using namespace std;

#define NOT_FOUND -1

// C++ String class that encapsulates an ASCII C-string
class String
{
public:
	// Default constructor
	String();

	// MUST HAVE: Copy-constructor that performs deep copy
	String(const String& source);

	// Init-constructor to initialize this String with a C-string
	String(const char* text);

	// Init constructor, allocates this String to hold the size characters
	String(int size);

	// Destructor
	~String();

	// Assignment operator to perform deep copy
	String& operator = (const String& source);

	// Assignment operator to assign a C-string to this String
	String& operator = (const char* text);

	// Returns a reference to a single character from this String
	char& operator [] (int index) const;

	// Comparison operators
	bool operator == (const String& compareTo) const;

	bool operator != (const String& compareTo) const;
	bool operator > (const String& compareTo) const;
	bool operator < (const String& compareTo) const;

	// Concatenation operator
	String operator + (const String& aString) const;

	// Returns an index of aChar in this String beginning the search
	// at startPosition; when aChar is not found the function returns NOT_FOUND
	// throws an exception when startPosition is out of bounds
	int Find(char aChar, int startPosition = 0) const;

	// Returns a new string that corresponds to a substring of this String
	// beginning at startPosition and length chars long;
	// if length = 0 (not specified) then the substring spans from
	// startPosition until the end of this String
	// throws an exception when startPosition is out of bounds
	String Substring(int startPosition, int length = 0) const;

	// Returns the count of characters in the String; NULL-terminator is not counted
	int GetLength() const;

	// Returns the count of characters in a C-string text; NULL-terminator is not counted
	// static means that the mem ber function neither reads nor
	// writes any of the class' data members
	// String::GetLength("blah");
	static int GetLength(const char* text);

	char* GetText() const;


private:
	// Throws an String-type exception when index is out of bounds
	void CheckBounds(int index, const char* source) const;
	
	// The encapsulated C-string
	char* Text;
};

// Stream output operator to print String to output stream
ostream& operator << (ostream& out, const String& text);

