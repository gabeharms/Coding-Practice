////-----------------------------------------------
//-- Designer 		: Gabe Harms, Neal Malkani
//-- Date 			: 09/03/2014
//-- Course 		: CMPEN 417
//-- Affiliation 	: The Pennsylvania State University
//-- Description 	: HW1 Part 5
////------------------------------------------------

module HW1_Part5 (SW, LEDR, HEX0, HEX1, HEX2);
	input [9:0] SW; // slide switches
	output [9:0] LEDR; // red lights
	output [0:6] HEX0; // 7-seg display
	output [0:6] HEX1; // 7-seg display	
	output [0:6] HEX2; // 7-seg display
	wire [1:0] M0;
	wire [1:0] M1;
	wire [1:0] M2;
	
	assign LEDR = SW;
	
	mux_2bit_3to1 U0 (SW[9:8], SW[5:4], SW[3:2], SW[1:0], M0);
	mux_2bit_3to1 U1 (SW[9:8], SW[1:0], SW[5:4], SW[3:2], M1);
	mux_2bit_3to1 U2 (SW[9:8], SW[3:2], SW[1:0], SW[5:4], M2);
	char_7seg H0 (M0[0], M0[1], HEX0);
	char_7seg H1 (M1[0], M1[1], HEX1);
	char_7seg H2 (M2[0], M2[1], HEX2);
endmodule


// implements a 2-bit wide 3-to-1 multiplexer
module mux_2bit_3to1 (S, U, V, W, M);
	input [1:0] W; 
	input [1:0] U; // slide switches
	input [1:0] V;// slide switches
	input [1:0]	S;
	output[1:0] M; // red LEDs
	
	wire [1:0]  X; 
	
	assign X[0] = (~S[0] & U[0]) | (S[0] & V[0]);
	assign X[1] = (~S[0] & U[1]) | (S[0] & V[1]);
	
	assign M[0] = (~S[1] & X[0]) | (S[1] & W[0]);
	assign M[1] = (~S[1] & X[1]) | (S[1] & W[1]);
endmodule


// implements a 7-segment decoder for d, E, 1 and ‘blank’
module char_7seg (c0, c1, HEX);
	input 	    c0; // slide switches
	input 	    c1;
	
	output [0:6] HEX; // output 7-seg code
	
	assign HEX[6] =  (c1 & ~c0) | (c1 & c0);
	assign HEX[5] =  (~c1 & ~c0) | (c1 & ~c0) | (c1 & c0);
	assign HEX[4] =  (c1 & ~c0) | (c1 & c0);
	assign HEX[3] =  (c1 & ~c0) | (c1 & c0);
	assign HEX[2] =  (~c1 & c0) | (c1 & c0);
	assign HEX[1] =  (~c1 & c0) | (c1 & c0);
	assign HEX[0] =  (~c1 & ~c0) | (c1 & ~c0) | (c1 & c0);
endmodule
