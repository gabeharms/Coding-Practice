import sys
import json
from network_factory import NetworkFactory
from configuration_parser import ConfigurationParser

def main():
  file = json.load(open("problems/%s/config.json" %(sys.argv[1]), 'r'))

  configuration = ConfigurationParser.parse(file)

  network = NetworkFactory.build(configuration)

  network.fit()

main()
