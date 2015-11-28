////-----------------------------------------------
//-- Designer 		: Gabe Harms, Neal Malkani
//-- Date 			: 09/03/2014
//-- Course 		: CMPEN 417
//-- Affiliation 	: The Pennsylvania State University
//-- Description 	: 7 segment decoder
////------------------------------------------------

// Simple module that connects the SW switches to the LEDR lights
module HW1_Part4 (c0, c1, HEX);

	input 	    c0; // slide switches
	input 	    c1;
	
	output [6:0] HEX; // red LEDs
	
	assign HEX[6] =  (c1 & ~c0) | (c1 & c0);
	assign HEX[5] =  (~c1 & ~c0) | (c1 & ~c0) | (c1 & c0);
	assign HEX[4] =  (c1 & ~c0) | (c1 & c0);
	assign HEX[3] =  (c1 & ~c0) | (c1 & c0);
	assign HEX[2] =  (~c1 & c0) | (c1 & c0);
	assign HEX[1] =  (~c1 & c0) | (c1 & c0);
	assign HEX[0] =  (~c1 & ~c0) | (c1 & ~c0) | (c1 & c0);
	
endmodule

