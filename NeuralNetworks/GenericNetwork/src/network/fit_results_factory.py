from network.fit_results import FitResults

class FitResultsFactory:
    def build(network, cost_history):
        return FitResults(cost_history, network.parameters)
