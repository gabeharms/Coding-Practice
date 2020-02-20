import os
from shutil import rmtree

class PredictionResultsWriter:
    def __init__(self, directory, prediction_input, prediction_output_raw, prediction_output_binary):
        self.directory = "%s/prediction/" % directory
        self.prediction_input = prediction_input.transpose()
        self.prediction_output_raw = prediction_output_raw
        self.prediction_output_binary = prediction_output_binary

    def write(self):
        rmtree(self.directory)
        os.makedirs(self.directory, exist_ok=True)
        with open("%sresults.csv" % self.directory,'w') as file:
            for input_row_index in range(self.prediction_input.shape[0]):
                line = ""
                for input in self.prediction_input[input_row_index]:
                    line += "%s," % input
                line += "%s,%s\n" % (self.prediction_output_raw[0][input_row_index], self.prediction_output_binary[input_row_index])
                file.write(line)

