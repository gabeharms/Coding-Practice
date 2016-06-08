require_relative 'sport'
require_relative 'sport_result'

class Football < Sport 
  include SportResult.new()
  attr_reader :touchdowns

  def initialize(touchdowns)
    @touchdowns = touchdowns 
    super("Football", 22)
  end

end
