
class Trainer:
    def __init__(self, forward_propagator, backward_propagator, parameter_updater, iterations, training_input, training_output, cost_calculator):
        self.forward_propagator = forward_propagator
        self.backward_propagator = backward_propagator
        self.parameter_updater = parameter_updater
        self.iterations = iterations
        self.training_input = training_input
        self.training_output = training_output
        self.cost_calculator = cost_calculator

    def run(self, parameters):
        for i in range(self.iterations):
            results = self.forward_propagator.execute(parameters, self.training_input)
            print('******************************************')
            print(parameters.get_weights(1))
            gradients = self.backward_propagator.execute(parameters, results, self.training_output)
            # print(gradients)
            self.parameter_updater.execute(results, parameters, gradients['dWs'], gradients['dbs'], self.training_input)
            print(self.cost_calculator.execute(self.training_output, results.get_last_A()))
            print('******************************************')
