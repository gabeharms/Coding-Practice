require_relative 'sport'

class Soccer < Sport
  attr_reader :goals
  
  def initialize(goals)
    @goals = goals
    super('Soccer', 22)
  end

end
