require_relative 'sport'
require_relative 'shootable'

class Basketball < Sport
  include Shootable.new()
  attr_reader :field_goals  

  def initialize(field_goals)
    @field_goals = field_goals
    super(nil)
  end

end
