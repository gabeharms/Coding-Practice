import numpy as np
from network.parameters import Parameters
from network.activation_functions import ActivationFunctions

ACTIVATION_MAP = {
        "tanh": ActivationFunctions.tanh,
        "sigmoid": ActivationFunctions.sigmoid
}

ACTIVATION_PRIME_MAP = {
        "tanh": ActivationFunctions.tanh_prime,
        "sigmoid": ActivationFunctions.sigmoid_prime
}

class ParametersFactory:
    def build(layers):
        np.random.seed(2)

        parameters = {}
        for layer_index in range(1, len(layers)):
            layer_string = "layer%s" % layer_index
            parameters[layer_string] = {}
            parameters[layer_string]["W"] = np.random.rand(layers[layer_index].neurons, layers[layer_index-1].neurons)
            parameters[layer_string]["b"] = np.zeros((layers[layer_index].neurons, 1))
            parameters[layer_string]["activation"] = ParametersFactory.__get_activation(layers[layer_index].activation)
            parameters[layer_string]["activation_prime"] = ParametersFactory.__get_activation_prime(layers[layer_index].activation)

        return Parameters(parameters)

    def __get_activation(str):
        return ACTIVATION_MAP[str]

    def __get_activation_prime(str):
        return ACTIVATION_PRIME_MAP[str]

