////-----------------------------------------------
//-- Designer 		: Gabe Harms, Neal Malkani
//-- Date 			: 09/03/2014
//-- Course 		: CMPEN 417
//-- Affiliation 	: The Pennsylvania State University
//-- Description 	: Switches 
////------------------------------------------------

// Simple module that connects the SW switches to the LEDR lights
module HW1_Part1 (SW, LEDR);

	input [9:0] SW; // slide switches
	output [9:0] LEDR; // red LEDs
	
	assign LEDR = SW;
	
endmodule















