////-----------------------------------------------
//-- Designer 		: Gabe Harms, Neal Malkani
//-- Date 			: 09/03/2014
//-- Course 		: CMPEN 417
//-- Affiliation 	: The Pennsylvania State University
//-- Description 	: 2 bit, 3to1 Mux
////------------------------------------------------

// Simple module that connects the SW switches to the LEDR lights
module HW1_Part3 (U, V, W, S, M);

	input [1:0] U; // slide switches
	input [1:0] V;
	input [1:0] W; // slide switches
	input [1:0]	S;
	output[1:0] M; // red LEDs
	
	wire [1:0]  X; 
	
	assign X[0] = (~S[0] & U[0]) | (S[0] & V[0]);
	assign X[1] = (~S[0] & U[1]) | (S[0] & V[1]);
	
	assign M[0] = (~S[1] & X[0]) | (S[1] & W[0]);
	assign M[1] = (~S[1] & X[1]) | (S[1] & W[1]);


endmodule

