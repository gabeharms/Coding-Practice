
/*
  Scala is a pure object-oriented language in the sense that everythign is
  an object, including numbers or functions. It differs from Java in that
  respect since Java distinguishes primitive types such as (boolean and int)
  from reference types, and does not enable one to manipulate functions
  as values.

  Numbers are Objects:

  Since numbers are like objects they also have methods. And in fact, an
  arithmetic expression like the following:
    1 + 2 * 4 / x
  consists exclusively of method calls, because it is equivalent to the following:
    (1).+(((2).*(3))./(x))

  The parentheses around the numbers in the second version are necessary because
  Scala's lexer uses a longest match rule for tokens. The reason that this tokenization
  is chosen is because 1. is a longer valid match than 1 and 1. is interpreted as
  the literal 1.0 making it a Double rather than an Int.

  Functions are Objects:

  Perhaps more surprising to the Java programmer, functions are also objects. Therefore
  it is possible to pass functions as arguments, store them in variables, and return them
  from other functions.

  This ability to manipulate functions as values is one of the cornerstones of a very
  interesting programming paradigm called functional programming.

  Lets consider the following example
*/

object Timer {
  def oncePerSecond(callback: () => Unit) {
    while(true) { callback(); Thread sleep 1000 }
  }
  def timeFlies() {
    println("time flies like an arrow...")
  }

  def main(args: Array[String]) {
    oncePerSecond(() => timeFlies())
  }
}

/*
  Anonymous Functions:

  While this program is easy to understand, it can be refined a bit. Notice that timeFlies isn't
  used or needed anywhere else. Maybe it would be a better fit as an anonymous function rather than
  a named class method on Timer.
*/

object TimerAnonymous {
  def oncePerSecond(callback: () => Unit) {
    while(true) { callback(); Thread sleep 1000 }
  }

  def main(args: Array[String]) {
    oncePerSecond(() => println("time flies like an arrow"))
  }
}

/*
  The => separates a function's argument list from its body.
*/


