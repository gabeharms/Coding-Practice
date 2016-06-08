require './Sport/sport'
require './Sport/soccer'
require './Sport/football'
require './Sport/basketball'

require 'pry'

sport = Sport.new('Generic Sport', 10)
soccer = Soccer.new(4) 
football = Football.new(2)
binding.pry
basketball = Basketball.new(25)

