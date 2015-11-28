////-----------------------------------------------
//-- Designer 		: Gabe Harms, Neal Malkani
//-- Date 			: 09/21/2014
//-- Course 		: CMPEN 417
//-- Affiliation 	: The Pennsylvania State University
//-- Description 	: Homework 2 Part 4
////------------------------------------------------

module HW2_Part4(A, B, CLK, S, HEX0, HEX1, HEX2, HEX3 );
	input [4:0] A; // slide switches
	input [4:0] B; // slide switches
	input 		CLK;
	output [9:0] S; // LEDs
	
	output [6:0] HEX0;
	output [6:0] HEX1;
	output [6:0] HEX2;
	output [6:0] HEX3;
	
	wire [4:0] Q1;
	wire [4:0] Q2;
	wire [9:0] Q3;
	
	
	
	flipflop_5bit F0 (A, Q1, 1'b1, CLK);
	flipflop_5bit F1 (B, Q2, 1'b1, CLK);

	multiplier M0 (A, B, Q3 );
	
	flipflop_10bit F3 (Q3, S, 1'b1, CLK);
	
	hex_decoder H0 (S, HEX0, HEX1, HEX2, HEX3 );
	
endmodule


module adder #(parameter n = 5) (input [n-1:0] A, input [n-1:0] B, output [n-1:0] sum, output carry);
	
	assign {carry, sum} = A + B;
	
endmodule

module flipflop_8bit (D, Q, rst_n, clk);
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

module flipflop_5bit (D, Q, rst_n, clk);
	input rst_n;
	input clk;
	input [4:0] D;
	output reg [4:0] Q;
	
	always @ (posedge clk, negedge rst_n)
	begin
		if(rst_n == 0)
			Q = 5'b00000;
		else
			Q = D;
	end
endmodule

module flipflop_10bit (D, Q, rst_n, clk);
	input rst_n;
	input clk;
	input [9:0] D;
	output reg [9:0] Q;
	
	always @ (posedge clk, negedge rst_n)
	begin
		if(rst_n == 0)
			Q = 9'b000000000;
		else
			Q = D;
	end
endmodule

module multiplier (a, b, s);
	input [4:0] a; // slide switches
	input [4:0] b; // slide switches
	output [9:0] s; // LEDs
	integer i;
	
	wire [4:0] b0;
	wire [4:0] b1;
	wire [4:0] b2;
	wire [4:0] b3;
	wire [4:0] b4;
	
	wire [4:0] s0;
	wire [4:0] s1;
	wire [4:0] s2;
	wire [4:0] s3;
	wire [4:0] s4;
	
	wire  c0;
	wire  c1;
	wire  c2;
	wire  c3;
	wire  c4;
	
	
	assign b0 = { a[4]&b[0], a[3]&b[0], a[2]&b[0], a[1]&b[0], a[0]&b[0] };
	assign b1 = { a[4]&b[1], a[3]&b[1], a[2]&b[1], a[1]&b[1], a[0]&b[1] };
	assign b2 = { a[4]&b[2], a[3]&b[2], a[2]&b[2], a[1]&b[2], a[0]&b[2] };
	assign b3 = { a[4]&b[3], a[3]&b[3], a[2]&b[3], a[1]&b[3], a[0]&b[3] };
	assign b4 = { a[4]&b[4], a[3]&b[4], a[2]&b[4], a[1]&b[4], a[0]&b[4] };

	adder #(5) ADD1 ({1'b0,b0[4:1]}, b1, s0, c0);
	adder #(5) ADD2 ({c0, s0[4:1]}, b2, s1, c1);
	adder #(5) ADD3 ({c1, s1[4:1]}, b3, s2, c2);
	adder #(5) ADD4 ({c2, s2[4:1]}, b4, s3, c3);
	
	assign s[0] = b0[0];
	assign s[1] = s0[0];
	assign s[2] = s1[0];
	assign s[3] = s2[0];
	assign s[4] = s3[0];
	assign s[5] = s3[1];
	assign s[6] = s3[2];
	assign s[7] = s3[3];
	assign s[8] = s3[4];
	assign s[9] = c3;
	
endmodule

module hex_decoder(value, Display0, Display1, Display2, Display3);
	input [9:0] value;
	
	output [6:0] Display0;
	output [6:0] Display1;
	output [6:0] Display2;
	output [6:0] Display3;
	
	integer temp;
	
	always @ (*)
	begin
		temp = value;
	end
		assign_display  A0(temp%10, Display0);
		assign_display  A1((temp/10)%10, Display1);
		assign_display	 A3((temp/100)%10, Display2);
		assign_display  A4((temp/1000)%10, Display3);


endmodule

module assign_display(value, Display);
	input  [3:0] value;
	
	output [6:0] Display;


	assign Display = (value == 4'b0101) ? 7'b0010010: 
						  (value == 4'b0111) ? 7'b1111000:
						  (value == 4'b1001) ? 7'b0011000:
						  (value == 4'b1000) ? 7'b0000000:
						  (value == 4'b0110) ? 7'b0000010:
						  (value == 4'b0100) ? 7'b0011001:
						  (value == 4'b0011) ? 7'b0110000:
						  (value == 4'b0010) ? 7'b0100100:
						  (value == 4'b0001) ? 7'b1111001:
						  (value == 4'b0000) ? 7'b1000000:
						  7'b0000000; 
							
					
endmodule
