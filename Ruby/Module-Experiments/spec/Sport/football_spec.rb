require 'spec_helper'

describe Football do

  describe "responds to" do
    subject { described_class.new(0) }
    
    it "touchdowns" do
      expect(subject).to respond_to(:touchdowns)
    end

    it "winner" do
      expect(subject).to respond_to(:winner)
    end

    it "is_over?" do
      expect(subject).to respond_to(:is_over?)
    end
  end
end
