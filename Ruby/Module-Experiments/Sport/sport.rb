class Sport
  attr_reader :winner

  def initialize(winner)
    @winner = winner
  end

  def is_over?
    !winner.nil?
  end

end
