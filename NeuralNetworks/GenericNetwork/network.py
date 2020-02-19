
class Network:
    def __init__(self, forward_propagator, parameters, trainer):
        self.forward_propagator = forward_propagator
        self.parameters = parameters
        self.trainer = trainer
        self.cost_history = []

    def fit(self):
        _, self.cost_history = self.trainer.run(self.parameters)

    def predict(self, input, threshold):
        results = self.forward_propagator.execute(self.parameters, input)

        print(results.get_last_A())

        return {"raw": results.get_last_A(), "threshold": list(map(lambda x: 1 if x >= threshold else 0, results.get_last_A()[0])) }
