require 'pry'
class SportResult < Module
  attr_reader :winner

  def initialize()
    binding.pry
    @winner = nil
  end

  def in_over?()
    return false
  end

  def included(host)
    binding.pry
    host.define_method 'winner', {@winnder}
  end
end
