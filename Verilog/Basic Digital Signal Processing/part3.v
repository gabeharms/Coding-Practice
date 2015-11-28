module part3 (CLOCK_50, CLOCK2_50, KEY, FPGA_I2C_SCLK, FPGA_I2C_SDAT, AUD_XCK, 
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
	
	// Part III signals

	parameter N = 65536;
	
	wire [23:0] LdivideByN;
	wire [23:0] FIFO_outl;
	wire [23:0] temp1;
	wire [23:0] temp2;
	wire [23:0] accumL_out;
	
	wire [23:0] RdivideByN;
	wire [23:0] FIFO_outr;
	wire [23:0] temp3;
	wire [23:0] temp4;
	wire [23:0] accumR_out;
	/////////////////////////////////
	// Your code goes here 
	/////////////////////////////////
	
	assign read = read_ready;
	assign write = write_ready;
	
	//////////////////////////////////
	//Right Side
	//////////////////////////////////
	
	//Divide By N
	assign RdivideByN = readdata_right / N;
	
	//FIFO
	FIFO_updater #(N) FIFO1 (RdivideByN, read_ready, FIFO_outr );
	
	//First adder
	assign temp1 = RdivideByN - FIFO_outr;
	
	//Second adder
	assign writedata_right = accumR_out + temp1;
	
	//Accumulator
	flipflop accumR (writedata_right, accumR_out, writedata_right);
	
	//////////////////////////////////
	//Left Side
	//////////////////////////////////
	
	//Divide By N
	assign LdivideByN = readdata_left / N;
	
	//FIFO
	FIFO_updater #(N) FIFO2 (LdivideByN, read_ready, FIFO_outl );
	
	//First adder
	assign temp3 = LdivideByN - FIFO_outl;
	
	//Second adder
	assign writedata_left = accumL_out + temp3;
	
	//Accumulator
	flipflop accumL (writedata_left, accumL_out, reset, writedata_left);
	
	


	
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

module FIFO_updater #(parameter n = 10) ( read, clk, shift_out);
	input clk;
	input [23:0] read;
	output [23:0] shift_out;

	reg [23:0] temp [n-1:0];
	integer i;
	
	always @ (posedge clk)
	begin
		 for(i = n-1; i > 0; i=i-i) begin
          temp[i] <= temp[i-1];
       end
				temp[0] <= read;
	end
	assign shift_out = temp[n-1];
endmodule