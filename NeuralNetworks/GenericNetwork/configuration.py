    

class Configuration:
    def __init__(self, input, output, layers, iterations, learning_rate, predict_input, predict_output, predict_threshold):
        self.input = input
        self.output = output
        self.layers = layers
        self.iterations = iterations 
        self.learning_rate = learning_rate
        self.predict_input = predict_input
        self.predict_output = predict_output
        self.predict_threshold = predict_threshold

    def input():
        return self.input

    def output():
        return self.output

    def layers():
        return self.layers

    def iterations():
        return self.iterations

    def learning_rate():
        return self.learning_rate

    def predict_input():
        return self.predict_input

    def predict_output():
        return self.predict_output

    def predict_threshold():
        return self.predict_threshold
