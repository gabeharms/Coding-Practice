
class Parameters:
    def __init__(self, parameters):
        self.parameters = parameters

        self.__init_parameter_history()


    def get_activation(self, layer_index):
        return self.parameters["layer%s" % layer_index]["activation"]

    def get_activation_prime(self, layer_index):
        return self.parameters["layer%s" % layer_index]["activation_prime"]

    def get_weights(self, layer_index):
        return self.parameters["layer%s" % layer_index]["W"]

    def set_weights(self, layer_index, W):
        layer_string = "layer%s" % layer_index
        self.parameters[layer_string]["W"] = W
        self.__set_weight_history_for_layer(layer_index, W)

    def get_biases(self, layer_index):
        return self.parameters["layer%s" % layer_index]["b"]

    def set_biases(self, layer_index, b):
        layer_string = "layer%s" % layer_index
        self.parameters[layer_string]["b"] = b
        self.__set_bias_history_for_layer(layer_index, b)

    def layer_count(self):
        return len(self.parameters.keys())

    def get_weight_history(self, layer_index):
        layer_string = "layer%s" % layer_index
        return self.parameter_history[layer_string]["W"]


    def __set_weight_history_for_layer(self, layer_index, W):
        layer_string = "layer%s" % layer_index
        for weight in range(1, W.shape[1]+1):
            for neuron in range(1, W.shape[0]+1):
                self.parameter_history[layer_string]["W"][weight][neuron] += [W[neuron-1][weight-1]]

    def __set_bias_history_for_layer(self, layer_index, b):
        layer_string = "layer%s" % layer_index
        for bias in range(1, b.shape[0]+1):
            self.parameter_history[layer_string]["b"][bias] += [b[bias-1]]

    def __init_parameter_history(self):
        self.parameter_history = {}

        for layer in range(1, self.layer_count()+1):
            layer_string = "layer%s" % layer
            self.parameter_history[layer_string] = {}
            self.parameter_history[layer_string]["W"] = {}
            self.parameter_history[layer_string]["b"] = {}

            layer_weights = self.parameters[layer_string]["W"]
            layer_biases = self.parameters[layer_string]["b"]

            for weight in range(1, layer_weights.shape[1]+1):
                self.parameter_history[layer_string]["W"][weight] = {}
                for neuron in range(1, layer_weights.shape[0]+1):
                    self.parameter_history[layer_string]["W"][weight][neuron] = []

            for bias in range(1, layer_biases.shape[0]+1):
                self.parameter_history[layer_string]["b"][bias] = []

            self.__set_weight_history_for_layer(layer, layer_weights)
            self.__set_bias_history_for_layer(layer, layer_biases)
