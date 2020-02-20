from fit_results import FitResults

class FitResultsFactory:
    def build(network):
        return FitResults(network.cost_history, network.parameters)
