////-----------------------------------------------
//-- Designer 		: Gabe Harms, Neal Malkani
//-- Date 			: 09/21/2014
//-- Course 		: CMPEN 417
//-- Affiliation 	: The Pennsylvania State University
//-- Description 	: Homework 3 Part I
////------------------------------------------------

module PartII(w, CLK, res_n, z, y  );

	input 	   w; // slide switches
	input 		res_n; // slide switches
	input 		CLK;
	output 		z; // LEDs
	output [3:0] y;

	reg [3:0] y_Q, Y_D; // y_Q represents current state, Y_D represents next state
	parameter A = 4'b0000, B = 4'b0001, C = 4'b0010, D = 4'b0011, E = 4'b0100, F = 4'b0101, G = 4'b0110, H = 4'b0111, I = 4'b1000;
	
	always @(w, y_Q)
	begin: state_table
		case (y_Q)
			A: if (!w) Y_D = B;
				else Y_D = F;
			B: if (!w) Y_D = C;
				else Y_D = F;
			C: if (!w) Y_D = D;
				else Y_D = F;
			D: if (!w) Y_D = E;
				else Y_D = F;
			E: if (!w) Y_D = E;
				else Y_D = F;
			F: if (w) Y_D = G;
				else Y_D = B;
			G: if (w) Y_D = H;
				else Y_D = B;
			H: if (w) Y_D = I;
				else Y_D = B;
			I: if (w) Y_D = I;
				else Y_D = B;
			default: Y_D = 4'bxxxx;
		endcase
	end // state_table

	always @(posedge CLK)
	begin: state_FFs
			if (res_n == 1'b0)
				y_Q = 4'b0000;
			else
				y_Q = Y_D;
	end // state_FFS
	
	assign y = y_Q;
	
	assign z = (y == 4'b0100) ? 1'b1: 
				  (y == 4'b1000) ? 1'b1:
				  1'b0;




endmodule
