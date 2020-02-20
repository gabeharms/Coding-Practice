import os
from shutil import rmtree

class PredictionResultsWriter:
    def __init__(self, directory, prediction_input, actual_output, prediction_output_raw, prediction_output_binary):
        self.directory = "%s/prediction/" % directory
        self.prediction_input = prediction_input.transpose()
        self.actual_output = actual_output
        self.prediction_output_raw = prediction_output_raw
        self.prediction_output_binary = prediction_output_binary

    def write(self):
        rmtree(self.directory)
        os.makedirs(self.directory, exist_ok=True)

        with open("%sresults.csv" % self.directory,'w') as file:
            self.__write_header(file)
            self.__write_body(file)


    def __write_header(self, file):
        header = ""
        for input_row_index in range(self.prediction_input.shape[1]):
            header += "Input %s," % input_row_index
        header += "Actual Output, Binary Prediction, Raw Prediction\n"

        file.write(header)

    def __write_body(self, file):
        for input_row_index in range(self.prediction_input.shape[0]):
            line = ""
            for input in self.prediction_input[input_row_index]:
                line += "%s," % input
            line += "%s,%s,%s\n" % (self.actual_output[input_row_index], self.prediction_output_binary[input_row_index], self.prediction_output_raw[0][input_row_index])
            file.write(line)
