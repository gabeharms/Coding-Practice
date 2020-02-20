from fit_results_factory import FitResultsFactory
from predict_results_factory import PredictResultsFactory

class Network:
    def __init__(self, forward_propagator, parameters, trainer):
        self.forward_propagator = forward_propagator
        self.parameters = parameters
        self.trainer = trainer
        self.cost_history = []

    def fit(self):
        self.trainer.run(self.parameters)
        return FitResultsFactory.build(self)

    def predict(self, input, threshold):
        forward_propogation_results = self.forward_propagator.execute(self.parameters, input)
        return PredictResultsFactory.build(forward_propogation_results, threshold)

