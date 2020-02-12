import numpy as np
from parameters import Parameters

class ParametersFactory:
    def build(layers):

        parameters = {}
        for layer_index in range(1, len(layers)):
            layer_string = "layer%s" % layer_index
            parameters[layer_string] = {}
            parameters[layer_string]["W"] = np.random.rand(layers[layer_index].neurons, layers[layer_index-1].neurons)
            parameters[layer_string]["b"] = np.random.rand(layers[layer_index].neurons, 1)

        return Parameters(parameters)
            

