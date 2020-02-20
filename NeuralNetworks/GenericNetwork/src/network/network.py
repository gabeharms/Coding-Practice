from network.fit_results_factory import FitResultsFactory
from network.predict_results_factory import PredictResultsFactory

class Network:
    def __init__(self, forward_propagator, parameters, trainer):
        self.forward_propagator = forward_propagator
        self.parameters = parameters
        self.trainer = trainer

    def fit(self):
        cost_history = self.trainer.run(self.parameters)
        return FitResultsFactory.build(self, cost_history)

    def predict(self, input, threshold):
        forward_propogation_results = self.forward_propagator.execute(self.parameters, input)
        return PredictResultsFactory.build(forward_propogation_results, threshold)

