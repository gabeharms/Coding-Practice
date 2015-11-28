////-----------------------------------------------
//-- Designer 		: Gabe Harms, Neal Malkani
//-- Date 			: 09/21/2014
//-- Course 		: CMPEN 417
//-- Affiliation 	: The Pennsylvania State University
//-- Description 	: Homework 3 Part I
////------------------------------------------------

module PartIII(w, CLK, res_n, z );

	input 	   w; // slide switches
	input 		res_n; // slide switches
	input 		CLK;
	output 		z; // LEDs
	
	wire tmp1;
	wire tmp2;
	wire res;

	wire [3:0] x1;
	wire [3:0] x2;

	//wire [3:0] shift_reg2;
	
	//shift_reg SR1 (w, shift_reg1, res_n, CLK  );
	//shift_reg SR2 (w, shift_reg2, res_n, CLK  );
	
	//shift reg
	flipflop1 F1 ( w, x1[0], res_n, CLK );
	flipflop1 F2 ( x1[0], x1[1], res_n, CLK );
	flipflop1 F3 ( x1[1], x1[2], res_n, CLK );
	flipflop1 F4 ( x1[2], x1[3], res_n, CLK );
	//shift reg
	flipflop0 F5 ( w, x2[0], res_n, CLK );
	flipflop0 F6 ( x2[0], x2[1], res_n, CLK );
	flipflop0 F7 ( x2[1], x2[2], res_n, CLK );
	flipflop0 F8 ( x2[2], x2[3], res_n, CLK );


	
	assign tmp1 = (x1 == 4'b0000) ? 1'b1: 1'b0;
	assign tmp2 = (x2 == 4'b1111) ? 1'b1: 1'b0;

	assign z = (tmp1 | tmp2);

endmodule

module flipflop0 (D, Q, rst_n, clk);
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


module flipflop1 (D, Q, rst_n, clk);
	input 	rst_n;
	input 	clk;
	input		D;
	output reg  Q;
	
	always @ (posedge clk)
	begin
		if(rst_n == 0)
			Q = 1'b1;
		else
			Q = D;
	end
endmodule



module shift_reg ( Si, out, rst_n, clk);
	input 	rst_n;
	input 	clk;
	input 	Si;
	output [3:0]	out;
	
	reg [3:0] tmp;
	
	always @ (posedge clk)
	begin
		if(rst_n == 0)
		begin
			tmp = 4'b0000;
		end
		else
		begin
			tmp = tmp << 1;
			tmp[0] = Si;
		end
	end	
	
	assign So = tmp[3];
endmodule
