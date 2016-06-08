class Winnable < Module
  def set_winner(winner)
    @winner = winner
  end

  def winner
    winner   
  end

  def is_over?
    !winner.nil?
  end
end
