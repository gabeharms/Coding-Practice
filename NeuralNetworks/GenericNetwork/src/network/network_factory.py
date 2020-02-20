from network.parameters_factory import ParametersFactory
from network.forward_propagator import ForwardPropagator
from network.trainer_factory import TrainerFactory
from network.network import Network

class NetworkFactory:
    def build(configuration):
        parameters = ParametersFactory.build(configuration.layers)
        forward_propagator = ForwardPropagator(configuration.layers)
        trainer = TrainerFactory.build(forward_propagator, configuration.input, configuration.output, configuration.iterations, configuration.learning_rate)

        return Network(forward_propagator, parameters, trainer)
