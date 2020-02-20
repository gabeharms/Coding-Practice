import matplotlib.pyplot as plt
import os

class Graph:
    def __init__(self, directory, cost_history, parameters):
        self.directory = "%splots" % directory
        self.cost_history = cost_history
        self.parameters = parameters

    def plot(self):
        os.makedirs(self.directory, exist_ok=True)
        self.__plot_cost(self.cost_history)

        for layer in range(1, self.parameters.layer_count()+1):
            self.__plot_layer_parameters(layer, self.parameters.get_weight_history(layer))

    def __plot_cost(self, cost_history):
        plt.plot(cost_history)
        plt.savefig("%s/cost.png" % self.directory)

    def __plot_layer_parameters(self, layer_num, weight_history):
        os.makedirs("%s/layer%d" % (self.directory, layer_num) , exist_ok=True)

        for weight_index in range(1, len(weight_history.keys()) + 1):
            weights = weight_history[weight_index]
            for neuron in range(1, len(weights.keys()) + 1):
                plt.plot(weights[neuron])
                plt.savefig("%s/layer%d/neuron%s-weight%s.png" % (self.directory, layer_num, neuron, weight_index))
                plt.clf()

