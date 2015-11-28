////-----------------------------------------------
//-- Designer 		: Gabe Harms, Neal Malkani
//-- Date 			: 09/21/2014
//-- Course 		: CMPEN 417
//-- Affiliation 	: The Pennsylvania State University
//-- Description 	: Homework 3 Part I
////------------------------------------------------

module Part1(w, CLK, res_n, z, y  );
	input 	   w; // slide switches
	input 		res_n; // slide switches
	input 		CLK;
	output 		z; // LEDs
	output [8:0] y;
	
	wire  	  X0;
	wire  	  X1;
	wire  	  X2;
	wire  	  X3;
	wire  	  X4;
	wire  	  X5;
	wire  	  X6;
	wire  	  X7;
	wire  	  X8;
	wire  	  X9;
	wire 		  temp;	
	

	assign X0 = (res_n == 1'b0) ? 1'b0: 1'b1;
	flipflop	 y0 ( X0, y[0], res_n, CLK);

	assign X1 = (~w)&(~y[0]) || (~w)&y[8] || (~w)&y[5] || (~w)&y[6] || (~w)&y[7];
	flipflop	 y1 ( X1, y[1], res_n, CLK);
	
	assign X2 = y[1]&(~w);
	flipflop	 y2 ( X2, y[2], res_n, CLK);
	
	assign X3 = y[2]&(~w);
	flipflop	 y3 ( X3, y[3], res_n, CLK);
	
	assign X4 = y[3]&(~w) || y[4]&(~w);
	flipflop	 y4 ( X4, y[4], res_n, CLK);
	
	assign X5 = (w)&(~y[0]) || (w)&y[4] ||(w)&y[3] || (w)&y[2] || (w)&y[1]  ;
	flipflop	 y5 ( X5, y[5], res_n, CLK);
	
	assign X6 = y[5]&(w);
	flipflop	 y6 ( X6, y[6], res_n, CLK);
	
	assign X7 = y[6]&(w);
	flipflop	 y7 ( X7, y[7], res_n, CLK);
	
	assign X8 = y[7]&(w) || y[8]&(w);
	flipflop	 y8 ( X8, y[8], res_n, CLK);
	
	assign z = y[8] || y[4];
	
endmodule


module flipflop (D, Q, rst_n, clk);
	input 	rst_n;
	input 	clk;
	input		D;
	output reg  Q;
	
	always @ (posedge clk)
	begin
		if(rst_n == 0)
			Q = 1'b0;
		else
			Q = D;
	end
endmodule
