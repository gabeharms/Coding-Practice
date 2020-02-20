from network.predict_results import PredictResults

class PredictResultsFactory:
    def build(forward_propogation_results, threshold):
        raw = forward_propogation_results.get_last_A()
        binary = list(map(lambda x: 1 if x >= threshold else 0, raw[0])) # this currently only works for output layers with one node. just requires a 2d loop instead of 1d to fix
        return PredictResults(raw, binary)
