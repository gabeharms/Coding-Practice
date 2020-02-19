import sys
import json
from network_factory import NetworkFactory
from configuration_parser import ConfigurationParser
from graph import Graph

def main():
  file = json.load(open("problems/%s/config.json" %(sys.argv[1]), 'r'))

  configuration = ConfigurationParser.parse(file)

  network = NetworkFactory.build(configuration)

  network.fit()

  grapher = Graph("problems/%s/" % (sys.argv[1]), network.cost_history, network.parameters)

  grapher.plot()

main()
