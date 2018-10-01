
/*
  Genericity is the ability to write code parametrized by types. Java programmers
  resort to using Object, which isi the super-type of all objects. This solution is 
  however far from being ideal, since it doesn't work for basic types (int, long, float etc)
  and implies that a lot of dynamic type cases have to be inserted by the programmer

  Scala makes it possible to define generic classes to solve this problem. Let us examine
  this with an example of the simplest container class possible: a reference which can either
  point toan object or be empty
*/

class Reference[T] {
  private var contents: T = _
  def set(value: T) { contents = value }
  def get: T = contents
}

/*
  The class Reference is parametrized by a type called T. This type is used in the body 
  of the class as the type of the contents variable

  To use this class, one needs to specify which type to use for T
*/

object IntegerReference {
  def main(args: Array[String]) {
    val cell = new Reference[Int]
    cell.set(13)
    println("Reference contains the half of " + (cell.get * 2))
  }
}
