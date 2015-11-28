////-----------------------------------------------
//-- Designer 		: Gabe Harms, Neal Malkani
//-- Date 			: 09/03/2014
//-- Course 		: CMPEN 417
//-- Affiliation 	: The Pennsylvania State University
//-- Description 	: HW1 Part 6
////------------------------------------------------

module HW1_Part6 (SW, LEDR, HEX0, HEX1, HEX2, HEX3, HEX4, HEX5);
	input [9:0] SW; // slide switches
	output [9:0] LEDR; // red lights
	output [0:6] HEX0; // 7-seg display
	output [0:6] HEX1; // 7-seg display	
	output [0:6] HEX2; // 7-seg display
	output [0:6] HEX3; // 7-seg display
	output [0:6] HEX4; // 7-seg display	
	output [0:6] HEX5; // 7-seg display
	wire [1:0] M0;
	wire [1:0] M1;
	wire [1:0] M2;
	wire [1:0] M3;
	wire [1:0] M4;
	wire [1:0] M5;
	wire [1:0] blank;
	
	assign blank = 2'b11;
	assign LEDR = SW;
	
	mux_2bit_6to1 U0 (SW[9:7], SW[5:4], blank, blank, blank, SW[1:0], SW[3:2], M0);
	mux_2bit_6to1 U1 (SW[9:7], SW[3:2], SW[5:4], blank, blank, blank, SW[1:0], M1);
	mux_2bit_6to1 U2 (SW[9:7], SW[1:0], SW[3:2], SW[5:4], blank, blank, blank, M2);
	mux_2bit_6to1 U3 (SW[9:7], blank, SW[1:0], SW[3:2], SW[5:4], blank, blank, M3);
	mux_2bit_6to1 U4 (SW[9:7], blank, blank, SW[1:0], SW[3:2], SW[5:4], blank, M4);
	mux_2bit_6to1 U5 (SW[9:7], blank, blank, blank, SW[1:0], SW[3:2], SW[5:4], M5);
	char_7seg H0 (M0[0], M0[1], HEX0);
	char_7seg H1 (M1[0], M1[1], HEX1);
	char_7seg H2 (M2[0], M2[1], HEX2);
	char_7seg H3 (M3[0], M3[1], HEX3);
	char_7seg H4 (M4[0], M4[1], HEX4);
	char_7seg H5 (M5[0], M5[1], HEX5);
endmodule


// implements a 2-bit wide 6-to-1 multiplexer
module mux_2bit_6to1 (S, U, V, W, T, R, Q, M0);
	input [1:0] U; // slide switches
	input [1:0] V; // slide switches
	input [1:0] W; // slide switches
	input [1:0] T; // slide switches
	input [1:0] R; // slide switches
	input [1:0] Q; // slide switches
	
	input [2:0]	S;
	output[1:0] M0; // red LEDs
	
	wire[1:0] M1;
	wire[1:0] M2;
	wire[1:0] M3;
	wire[1:0] M4;
	
	//call 2 3to1 and then 2to1
	mux_2bit_2to1 U0 (S[0], U, V, M1);
	mux_2bit_2to1 U1 (S[0], W, T, M2);
	mux_2bit_2to1 U2 (S[0], R, Q, M3);
	mux_2bit_2to1 U3 (S[1], M1, M2, M4);
	mux_2bit_2to1 U4 (S[2], M4, M3, M0);
	
endmodule


// implements a 2-bit wide 2-to-1 multiplexer
module mux_2bit_2to1 (S, U, V, M);
	input [1:0] U; // slide switches
	input [1:0] V; // slide switches
	input [1:0]	S;
	output[1:0] M; // red LEDs
	
	wire [1:0]  X; 
	
	assign M[0] = (~S[0] & U[0]) | (S[0] & V[0]);
	assign M[1] = (~S[0] & U[1]) | (S[0] & V[1]);
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
