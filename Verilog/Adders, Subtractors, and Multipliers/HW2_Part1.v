////-----------------------------------------------
//-- Designer 		: Gabe Harms, Neal Malkani
//-- Date 			: 09/16/2014
//-- Course 		: CMPEN 417
//-- Affiliation 	: The Pennsylvania State University
//-- Description 	: Homework 2 Part 1
////------------------------------------------------


// Simple module that connects the SW switches to the LEDR lights
module HW2_Part1 (A, S, overflow, rst_n, clk);

	input [7:0] A;
	input rst_n;
	input clk;
	
	output[7:0] S;
	output overflow;
	
	wire [7:0] sum;
	wire [7:0] temp;	
	wire [7:0] Loutput;
	 
		flipflop L0 (A, Loutput, rst_n, clk );
		
		assign {carry, sum} = A + temp;
		
		flipflop F0 (carry, overflow, rst_n, clk);
		flipflop L1 (sum, temp, rst_n, clk);
		
		assign S =temp;
		
	
endmodule

module latchh (R, Q, rst_n, wr_e);
	input rst_n;
	input wr_e;
	input [7:0] R;
	output reg [7:0] Q;
	
	always @ (wr_e, rst_n)
	begin
		if(rst_n == 0)
			Q = 8'b00000000;
		else if (wr_e == 1'b1)
			Q = R;
	end
endmodule

module flipflop (D, Q, rst_n, clk);
	input rst_n;
	input clk;
	input [7:0] D;
	output reg [7:0] Q;
	
	always @ (posedge clk, negedge rst_n)
	begin
		if(rst_n == 0)
			Q = 8'b00000000;
		else
			Q = D;
	end
endmodule
