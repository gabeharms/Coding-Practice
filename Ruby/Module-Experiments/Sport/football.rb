require_relative 'winnable'

class Football 
  include Winnable
  attr_reader :touchdowns, :winner

  def initialize(touchdowns)
    @touchdowns = touchdowns 
  end

end
