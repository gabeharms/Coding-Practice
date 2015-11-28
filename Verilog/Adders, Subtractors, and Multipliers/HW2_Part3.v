////-----------------------------------------------
//-- Designer 		: Gabe Harms, Neal Malkani
//-- Date 			: 09/16/2014
//-- Course 		: CMPEN 417
//-- Affiliation 	: The Pennsylvania State University
//-- Description 	: Homework 2 Part 3
////------------------------------------------------

module HW2_Part3(A, B, S, carry, Display6, Display4, Display1, Display0);
	input [3:0] A; // slide switches
	input [3:0] B; // slide switches
	output [7:0] S; // LEDs
	output [7:0] Display6;
	output [7:0] Display4;
	output [7:0] Display1;
	output [7:0] Display0;
	integer i;
	reg [7:0] b0;
	reg [7:0] b1;
	reg [7:0] b2;
	reg [7:0] b3;
	output carry;
	
	always @ (*)
	begin
		for(i = 7; i >= 0; i = i - 1)
		begin
			if(i > 3)
				b0[i] = 1'b0;
			else
				b0[i] = A[i] & B[0];
		end
		
		for (i = 7; i >= 0; i = i - 1)
		begin
			if(i > 4 || i < 1)
				b1[i] = 1'b0;
			else
				b1[i] = A[i-1] & B[1];
		end
		
		for (i = 7; i >= 0; i = i - 1)
		begin
			if(i > 5 || i < 2)
				b2[i] = 1'b0;
			else
				b2[i] = A[i-2] & B[2];
		end
		
		for (i = 7; i >= 0; i = i - 1)
		begin
			if(i > 6 || i < 3)
				b3[i] = 1'b0;
			else
				b3[i] = A[i-3] & B[3];
		end
	end
	
	
	assign {carry, S} = b0 + b1 + b2 + b3;
	
	assign_display  A0(A, Display6);
	assign_display  A1(B, Display4);
	
	assign_display  A2((S/10)%10, Display1);
	assign_display  A3(S%10, Display0);
	
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
						  (value == 4'b1010) ? 7'b0001000:
						  (value == 4'b1011) ? 7'b0000000:
						  (value == 4'b1100) ? 7'b1000110:
						  (value == 4'b1101) ? 7'b1000000:
						  (value == 4'b1110) ? 7'b0000110:
						  (value == 4'b1111) ? 7'b0001110:
						  7'b0000000; 
endmodule
