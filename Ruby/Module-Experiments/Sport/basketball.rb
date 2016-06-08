require_relative 'sport'

class Basketball < Sport
  attr_reader :field_goals  

  def initialize(field_goals)
    @field_goals = field_goals
    super('Basketball', 10)
  end

end
