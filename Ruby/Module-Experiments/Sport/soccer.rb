require_relative 'sport'

class Soccer < Sport
  attr_reader :goals
  
  def initialize(goals)
    @goals = goals
    super(nil)
  end

end
