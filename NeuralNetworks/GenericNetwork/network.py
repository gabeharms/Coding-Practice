
class Network:
    def __init__(self, forward_propagator, parameters, trainer):
        self.forward_propagator = forward_propagator
        self.parameters = parameters
        self.trainer = trainer
        self.cost_history = []

    def fit(self):
        _, self.cost_history = self.trainer.run(self.parameters)

    def predict(self, input):
        self.forward_propagator(parameters, input)
