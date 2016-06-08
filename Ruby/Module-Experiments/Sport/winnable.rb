module Winnable
  def winnable
    winner
  end

  def is_over?()
    !winner.nil?
  end
end
