
/*
  As we have seen above, Scala is an object oriented language, and thus it 
  has a concept of class. Classes in Scala are declared using syntax similar
  to Java's. One important difference is that classes in Scala can have
  parameters.

class Complex(real: Double, imaginary: Double) {
  def re() = real
  def im() = imaginary
}

  This class takes two arguments, which must be passed when creating an instance
  of the Complex class like so:
    new Complex(1.5, 2.3)

  Also notice that we didn't provide return types for the above. These will be inferred
  automatically by the compiler.

  The compiler is not alway able to infer types like it can here, and there is no simple
  rule to know exactly when it can and cannot.

  Methods without arguments:
  
  A small problem of the above methods is that in order to call them, one has to put an
  empty pari of parenthesis after there name:

class ComplexNumbers {
  def main(args: Array[String]) {
    val c = new Complex(1.2, 3.4)
    println("imaginary part: " + c.im())
  }
}

  it would be nicer to access those functions as if they wre fields. This is possible in Scala
  by defining them as methods without arguments. Such methods differ from methods with zero
  arguments in that they don't have poarenthesis after their name. So we could rewrite as follows:

class Complex(real: Double, imaginary: Double) {
  def re = real
  def im = imaginary
}

  Inheritance and overriding:

  All classes in Scala inherit from a super-class. When no super class is defined, Scala.AnyRef
  is implicitly used.

  It is possible to override methods inherited from a super-class in Scala, but it's required that
  you explicitly specify that a method overrides another using the 'override' modifier:
*/

class Complex(real: Double, imaginary: Double) {
  def re = real
  def im = imaginary
  override def toString() =
    "" + re + (if (im < 0) "-" else "+") + im + "i"
}
