import sys
import json
from network_factory import NetworkFactory
from configuration_parser import ConfigurationParser
from graph import Graph
from prediction_results_writer import PredictionResultsWriter

def main():
    problem_directory = "problems/%s" % sys.argv[1]

    file = json.load(open("%s/config.json" % problem_directory, 'r'))

    configuration = ConfigurationParser.parse(file)

    network = NetworkFactory.build(configuration)

    fit_results = network.fit()

    grapher = Graph(problem_directory, fit_results.cost_history, fit_results.parameters)

    grapher.plot()

    prediction_results = network.predict(configuration.predict_input, configuration.predict_threshold)

    prediction_results_writer = PredictionResultsWriter(problem_directory, configuration.predict_input, prediction_results.raw, prediction_results.binary)

    prediction_results_writer.write()

main()
