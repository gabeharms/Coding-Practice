
class Parameters:
    def __init__(self, parameters):
        self.parameters = parameters

    def get_weights(self, layer_index):
        return self.parameters["layer%s" % layer_index]["W"]

    def set_weights(self, layer_index, W):
        self.parameters["layer%s" % layer_index]["W"] = W

    def get_biases(self, layer_index):
        return self.parameters["layer%s" % layer_index]["b"]

    def set_biases(self, layer_index, b):
        self.parameters["layer%s" % layer_index]["b"] = b

    def layer_count(self):
        return len(self.parameters.keys())
