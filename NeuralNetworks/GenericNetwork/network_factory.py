from parameters_factory import ParametersFactory
from forward_propagator import ForwardPropagator
from trainer_factory import TrainerFactory
from network import Network

class NetworkFactory:
    def build(configuration):
        parameters = ParametersFactory.build(configuration.layers)
        forward_propagator = ForwardPropagator(configuration.layers)
        trainer = TrainerFactory.build(forward_propagator, configuration.input, configuration.output, configuration.iterations, configuration.learning_rate)

        return Network(forward_propagator, parameters, trainer)
