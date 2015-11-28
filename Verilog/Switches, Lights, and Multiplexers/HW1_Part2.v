////-----------------------------------------------
//-- Designer 		: Gabe Harms, Neal Malkani
//-- Date 			: 09/03/2014
//-- Course 		: CMPEN 417
//-- Affiliation 	: The Pennsylvania State University
//-- Description 	: 4 bit, 2to1 Mux
////------------------------------------------------

// Simple module that connects the SW switches to the LEDR lights
module HW1_Part2 (X, Y, s, M);

	input [3:0] X; // slide switches
	input [3:0] Y;
	input 		s;
	output[3:0] M; // red LEDs
	
	
	assign M[0] = (~s & X[0]) | (s & Y[0]);
	assign M[1] = (~s & X[1]) | (s & Y[1]);	
	assign M[2] = (~s & X[2]) | (s & Y[2]);	
	assign M[3] = (~s & X[3]) | (s & Y[3]);	

endmodule

