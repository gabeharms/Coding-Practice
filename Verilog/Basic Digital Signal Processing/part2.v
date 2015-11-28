////-----------------------------------------------
//-- Designer 		: Gabe Harms, Neal Malkani
//-- Date 		: 10/12/2014
//-- Course 		: CMPEN 417
//-- Affiliation 	: The Pennsylvania State University
//-- Description 	: Homework 4 Part 2
////------------------------------------------------

module part2 (CLOCK_50, CLOCK2_50, KEY, FPGA_I2C_SCLK, FPGA_I2C_SDAT, AUD_XCK, 
		        AUD_DACLRCK, AUD_ADCLRCK, AUD_BCLK, AUD_ADCDAT, AUD_DACDAT);

	input CLOCK_50, CLOCK2_50;
	input [0:0] KEY;
	// I2C Audio/Video config interface
	output FPGA_I2C_SCLK;
	inout FPGA_I2C_SDAT;
	// Audio CODEC
	output AUD_XCK;
	input AUD_DACLRCK, AUD_ADCLRCK, AUD_BCLK;
	input AUD_ADCDAT;
	output AUD_DACDAT;
	
	// Local wires.
	wire read_ready, write_ready, read, write;
	wire [23:0] readdata_left, readdata_right;
	wire [23:0] writedata_left, writedata_right;
	wire reset = ~KEY[0];
	
	// Our signals
	// right side
	wire [23:0] rightff_out [0:7];
	wire [23:0] right8_out [0:7];
	
	// left side
	wire [23:0] leftff_out [0:7];
	wire [23:0] left8_out [0:7];
	
	parameter eight = 4'b1000;

	/////////////////////////////////
	// Your code goes here 
	/////////////////////////////////
	
	assign read = read_ready;
	assign write = write_ready;
	
	flipflop rightff1 (readdata_right, rightff_out[0], reset, CLOCK_50);
	flipflop rightff2 (rightff_out[0], rightff_out[1], reset, CLOCK_50);
	flipflop rightff3 (rightff_out[1], rightff_out[2], reset, CLOCK_50);
	flipflop rightff4 (rightff_out[2], rightff_out[3], reset, CLOCK_50);
	flipflop rightff5 (rightff_out[3], rightff_out[4], reset, CLOCK_50);
	flipflop rightff6 (rightff_out[4], rightff_out[5], reset, CLOCK_50);
	flipflop rightff7 (rightff_out[5], rightff_out[6], reset, CLOCK_50);
	flipflop rightff8 (rightff_out[6], rightff_out[7], reset, CLOCK_50);
	
	flipflop leftff1 (readdata_left, leftff_out[0], reset, CLOCK_50);
	flipflop leftff2 (leftff_out[0], leftff_out[1], reset, CLOCK_50);
	flipflop leftff3 (leftff_out[1], leftff_out[2], reset, CLOCK_50);
	flipflop leftff4 (leftff_out[2], leftff_out[3], reset, CLOCK_50);
	flipflop leftff5 (leftff_out[3], leftff_out[4], reset, CLOCK_50);
	flipflop leftff6 (leftff_out[4], leftff_out[5], reset, CLOCK_50);
	flipflop leftff7 (leftff_out[5], leftff_out[6], reset, CLOCK_50);
	flipflop leftff8 (leftff_out[6], leftff_out[7], reset, CLOCK_50);
	
	assign right8_out[0] = rightff_out[0] / eight;
	assign right8_out[1] = rightff_out[1] / eight;
	assign right8_out[2] = rightff_out[2] / eight;
	assign right8_out[3] = rightff_out[3] / eight;
	assign right8_out[4] = rightff_out[4] / eight;
	assign right8_out[5] = rightff_out[5] / eight;
	assign right8_out[6] = rightff_out[6] / eight;
	assign right8_out[7] = rightff_out[7] / eight;
	
	assign left8_out[0] = leftff_out[0] / eight;
	assign left8_out[1] = leftff_out[1] / eight;
	assign left8_out[2] = leftff_out[2] / eight;
	assign left8_out[3] = leftff_out[3] / eight;
	assign left8_out[4] = leftff_out[4] / eight;
	assign left8_out[5] = leftff_out[5] / eight;
	assign left8_out[6] = leftff_out[6] / eight;
	assign left8_out[7] = leftff_out[7] / eight;

	assign writedata_right = right8_out[0] + right8_out[1] + right8_out[2] + 
					right8_out[3] + right8_out[4] + right8_out[5] + right8_out[6] + right8_out[7];	
	assign writedata_left = left8_out[0] + left8_out[1] + left8_out[2] + 
					left8_out[3] + left8_out[4] + left8_out[5] + left8_out[6] + left8_out[7];

	
/////////////////////////////////////////////////////////////////////////////////
// Audio CODEC interface. 
//
// The interface consists of the following wires:
// read_ready, write_ready - CODEC ready for read/write operation 
// readdata_left, readdata_right - left and right channel data from the CODEC
// read - send data from the CODEC (both channels)
// writedata_left, writedata_right - left and right channel data to the CODEC
// write - send data to the CODEC (both channels)
// AUD_* - should connect to top-level entity I/O of the same name.
//         These signals go directly to the Audio CODEC
// I2C_* - should connect to top-level entity I/O of the same name.
//         These signals go directly to the Audio/Video Config module
/////////////////////////////////////////////////////////////////////////////////
	clock_generator my_clock_gen(
		// inputs
		CLOCK2_50,
		reset,

		// outputs
		AUD_XCK
	);

	audio_and_video_config cfg(
		// Inputs
		CLOCK_50,
		reset,

		// Bidirectionals
		FPGA_I2C_SDAT,
		FPGA_I2C_SCLK
	);

	audio_codec codec(
		// Inputs
		CLOCK_50,
		reset,

		read,	write,
		writedata_left, writedata_right,

		AUD_ADCDAT,

		// Bidirectionals
		AUD_BCLK,
		AUD_ADCLRCK,
		AUD_DACLRCK,

		// Outputs
		read_ready, write_ready,
		readdata_left, readdata_right,
		AUD_DACDAT
	);

endmodule


module flipflop (D, Q, rst_n, clk);
	input rst_n;
	input clk;
	input [23:0] D;
	output reg [23:0] Q;

	always @ (posedge clk, negedge rst_n)
	begin
		if(rst_n == 0)
			Q = 23'd0;
		else
			Q = D;
	end
endmodule

