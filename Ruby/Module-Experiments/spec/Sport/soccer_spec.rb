require 'spec_helper'

describe Soccer do

  describe "responds to" do
    subject { described_class.new(0) }
    
    it "goals" do
      expect(subject).to respond_to(:goals)
    end

    it "winner" do
      expect(subject).to respond_to(:winner)
    end

    it "is_over?" do
      expect(subject).to respond_to(:is_over?)
    end
  end
end
