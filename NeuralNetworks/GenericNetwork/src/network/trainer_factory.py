from network.backward_propagator import BackwardPropagator
from network.update_parameters import UpdateParameters
from network.cost_calculator import CostCalculator
from network.trainer import Trainer

class TrainerFactory:
    def build(forward_propagator, training_input, training_output, iterations, learning_rate):
        backward_propagator = BackwardPropagator()
        parameter_updater = UpdateParameters(learning_rate)

        return Trainer(forward_propagator, backward_propagator, parameter_updater, iterations, training_input, training_output, CostCalculator)
