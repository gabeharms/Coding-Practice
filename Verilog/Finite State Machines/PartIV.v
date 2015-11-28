////-----------------------------------------------
//-- Designer 		: Gabe Harms, Neal Malkani
//-- Date 			: 10/3/2014
//-- Course 		: CMPEN 417
//-- Affiliation 	: The Pennsylvania State University
//-- Description 	: Homework 3 Part IV
////------------------------------------------------

module PartIV(letter, res, clk_start, light, clk);

	input[2:0] letter;		//input letter from switches
	input  	  res;			//reset push button
	input  	  clk_start;	//clock start push button
	input  	  clk;			//50MHz clock from board
	output reg light;			//first LED light
	
	//Switch codes for letters A-H
	parameter A = 3'b000,
				 B = 3'b001,
				 C = 3'b010,
				 D = 3'b011,
				 E = 3'b100,
				 F = 3'b101,
				 G = 3'b110,
				 H = 3'b111;	
	
	//Used to divide 50MHz clock to 0.5sec
	parameter maxCnt = 25'd25000000;
				 
	//FSM states
	wire [2:0] hold = 3'b000,
				  short = 3'b001,
				  long = 3'b010,
				  off = 3'b100;
	
	//Variables
	reg [3:0] seq; 		// morse codes
	reg [2:0] len; 		// length of morse code sequence
	reg [3:0] current; 	// current state
	reg [3:0] next;		// next state
	reg [3:0] morse;		// Input to the state machine
	reg [2:0] cur_len;	// Index of current bit in morse code sequence
	reg [25:0] cnt; 		// counts until 25000000 for 0.5sec
	


	//Morse Code representation
	always @(letter)
	begin
		case (letter[2:0])
				A: begin len = 3'b010; seq = 4'b0100; end
				B: begin	len = 3'b100; seq = 4'b1000; end
				C: begin len = 3'b100; seq = 4'b1010; end
				D: begin	len = 3'b011; seq = 4'b1000; end
				E: begin	len = 3'b001; seq = 4'b0000; end
				F: begin	len = 3'b100; seq = 4'b0010; end
				G: begin len = 3'b011; seq = 4'b1100; end
				H: begin	len = 3'b100; seq = 4'b0000; end
		endcase
	end

	// Divides 50MHz clock to 0.5 second intervals
	always @(posedge clk)
		begin
			if (cnt >= maxCnt)
				begin
					cnt <= 0;
					current <= next;
					case(next)
						hold: begin	cur_len <= len; morse <= seq;	end
						off: begin cur_len = cur_len - 1; morse = morse << 1; end
					endcase
				end
			else
				cnt <= cnt + 1;
		end

	//FSM	
	always @(clk_start, res, current) //morse[3],													
		begin
			case (current) 
				hold: 	if (!clk_start) next = short;
							else next = hold;  
				short: 	if (!morse[3]) next = off;
							else next = long;
				long: 	if (!res) next = hold;
							else next = off;			
				off: 		if (cur_len == 0) next = hold;
							else next = short;					
			endcase
		end
			
	//Output Enable block
	always @(current)
		begin
			if(current == short || current == long)
				light = 1;
			else
				light = 0;
		end

endmodule
