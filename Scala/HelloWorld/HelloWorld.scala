

object HelloWorld {
  def main(args: Array[String]) {
    println("Hello, world!")
  }
}

/*
  The 'object' declaration introduces a singleton object; a class with a single
  instance. This declaration declares both the class HelloWorld, and an instance
  of the HelloWorld class. This instance is created on demand, the first time
  it is used.

  You will also notice that main isn't declared as 'static'. This is because static
  members do not exist in scala. Rather than defining static members, the Scala
  programmer declares these members in singleton objects.

  Compile as follows:
    > scalac HelloWorld.scala
  This should generate a HelloWorld.class file. Which you can then run follows:
    > scala -classpath . HellowRold

*/
