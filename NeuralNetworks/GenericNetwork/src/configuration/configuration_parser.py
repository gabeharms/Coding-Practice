from configuration.configuration import Configuration
import numpy as np

class Layer:
    def __init__(self, neurons, activation):
       self.neurons = neurons
       self.activation = activation

class ConfigurationParser:
    def parse(json):
        training_input = np.array(list(json["input"].values())).transpose()
        predict_input = np.array(list(json["predict_input"].values())).transpose()
        return Configuration(
                training_input,
                np.array(json["output"]),
                [Layer(training_input.shape[0], "tanh")] + list(map(lambda layer: Layer(layer["neurons"], layer["activation"]), json["layers"])),
                int(json["iterations"]),
                float(json["learning_rate"]),
                predict_input,
                np.array(json["predict_output"]),
                float(json["predict_threshold"])
        )
