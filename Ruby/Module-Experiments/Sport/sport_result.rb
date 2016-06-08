require 'pry'
class SportResult < Module
  attr_reader :winner

  def initialize()
    @winner = 5
    attr_reader(:winner)
  end

  def in_over?()
    return false
  end
end
