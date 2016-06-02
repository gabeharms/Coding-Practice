extern crate rand; // Lets rust know we will be using the crate added to the dependencies
// and is equivalent to 'use rand'

use std::io; // rusts standard io library
use std::cmp::Ordering;
use rand::Rng; // Allows us to use a method that requires 'Rng' to be in the scope to work.
// The idea is that methods are defined on something called 'traits', and for the 
// method to work, it needs the 'trait' to be in scope. 

// main() is the entry point into a rust program. fn declares
// a new function. Since no return type was specified it is assumed
// to be an empty tuple (ordered list).
fn main() {
    println!("Guess the number!"); // println! is a macro, which we know because of the '!'

    // 'rand::thread_rng()' is a functio nto get a copy of the random number generator, which
    // is local to the particular thread of execution we're in. Because we used 'use rand::Rng'
    // above, we now have 'gen_range()' method available. This method takes two args, and generates
    // a number between them (inclusive on lower bound and exclusive on the upper bound).
    let secret_number = rand::thread_rng().gen_range(1, 101);


    // The 'loop' keyword gives us an infinite loop
    loop { 

        println!("Please input your guess.");

        // 'let' statement is used to create 'variable bindings'. This statement 
        // creates a new binding called 'guess' and binds it to the value to the right
        // of the equals sign. In other languages this is called a 'variable', but rust's
        // 'variable bindings' are alittle different. For example, they are immutable by 
        // default. Thats why our statement uses 'mut', to enable the binding to be mutated.
        // 'let' doesn't actually take a variable name on the left side, but a 'pattern'.
        //
        // 'String' is a string type, provided by the standard library. A 'String' is a
        // growable UTF-8 encoded bit of text. The '::new()' syntax uses '::' because this 
        // is an 'associated function' of a particular type. Which means its associated with
        // 'String' it self rather than an instance of 'String' (so just call it a static method
        // then, jeesh).
        //
        // Rust has a very strong static type system. However, it also has type inference, which is 
        // why we are able to write this statement without specifying the type on the left side.
        let mut guess = String::new();

        // 'io::stdin()' calls an 'associated function' on the 'io' library we imported at
        // the top. Without imported the library we would have had to called it like 'std::io::stdin()'.
        //
        // 'stdin()' in particular returns a handle to the standard input for your terminal. Then
        // the '.read_line(&mut guess)' method is called on this handle. Methods are like 'associated
        // functions' except they are only available for instances of the class (how is that different
        // than any other language?). When we call this method, we are passing one argument to
        // 'read_line()', '&mut guess'.

        // 'read_line()' doesn't take a string as input, but rather a '&mut String'. Rust has a feature
        // called 'references' (yes just like every other language), which allows you to have multiple 
        // references to one piece of data which can reduce copying. 

        // References are a complex feature, as one of Rust's major selling points is how safe and easy
        // it is to use references. References are immutable by default just like 'variable bindings'.
        // This is why we needed to write '&mut guess'. Passign just '&guess' would have prevented 
        // 'read_line()' from mutating its reference to 'guess'.
        //
        // The return value of 'read_line()' is an io::Result. Rust has a number of types named
        // 'Result' in its standard library: a generic Result, and then specific versions for 
        // sub libraries like 'io::Result'.
        //
        // The purpose of 'Result' types is to encode the error handling information. Values of the
        // 'Result' type, like any type, have methods defined on them. In this case 'io::Result' has an
        // 'expect()' mehtod that takes a value it's called on, and if it isn't a successful one,
        // 'panic!'s with a message you passed it. A 'panic!' like this will cause our program to crash
        // displaying the message. If we don't call this method on the 'io::Result' return type, the
        // program will compile but with the following warning 'warning: unused result which must be
        // used'. Rust warns us that we haven't used the Result value. Rust is trying to tell you that
        // you haven't handled a possible error. Luckily, if we want the program to crash, we can use 
        // the 'expect()' method. If we desired to recover from the error, we would do something else.
        io::stdin().read_line(&mut guess)
            .expect("Failed to read line");

        // Rust allows us to 'shadow' the previous guess with a new one. Which will essentiall just
        // overwrite 'guess'. 
        //
        // We bind it to the 'guess.trim().parse()' expression. Where 'guess' in the expression still
        // references our old 'guess' 'varibale binding'. The 'trim()' method on String will eliminate
        // white space. 'parse' method on Strings parses a string into some kind of number. Since it
        // can parse a variety of numbers, we need to give Rust a hint as to the type we want.
        //
        // In 'let guess: u32', the colon after guess tells Rust we're going to annotate its type. 
        // 'u32' is an unsigned 32 bit integer, which is one of Rust's several built in types. 
        //
        // Just like 'read_line()', 'parse()' could produce an error. So we call the same method 
        // 'expect()' on the return result of 'parse()' which will crash and print the message if there
        // is an error.
        //
        // This is generally how you move from 'crash on error' to 'acctually handle the error', by
        // switching from 'expect()' to a match statement. A 'Result' is returned by 'parse()',
        // which is an enum like 'Ordering'. But in this case, each variant has some data
        // associated wiht it: 'Ok' is a success, and 'Err' is a failure. Each contains more 
        // information: the successfully parsed integer, or an error type. In this case, we 'match'
        // on 'Ok(num)', which sets the name 'num' to the unwrapped 'Ok' value(the integer), and 
        // then we return it on the right-hand side. In the 'Err' case, we don't care what kind of
        // error it is, so we just use the catch all '_' instead of a specific error type to catch.
        // This will catch everything that isn 'Ok', and continue lets us move to the next
        // iteration of the loop, which enables us to ignore all errors and continue with our
        // program
        let guess: u32 = match guess.trim().parse() {
            Ok(num) => num,
            Err(_) => continue,
        };
        //.expect("Please type a number!");

        // Prints the 'guess' string, where '{}' is a placeholder and takes the first arguement
        // which is 'guess' (string interpolation). 
        println!("You guessed: {}", guess);

        // The cmp() method can be called on anything that can be compared, and it takes a reference
        // to the thing you want to compare it to. It returns the Ordering type. We ust a match
        // statement to determine excatly what kind of Ordering it is. Ordering is an enum. Thus, the 
        // result of cmp will be a finite type of the 'Ordering' enum's 3 variants: 'Less', 'Equal', 
        // 'Greater'. The match statement takes a value of a type, and lets you create an case for 
        // each possible value. Since we have 3 variants in 'Ordering' we have 3 cases in our match
        // statement.
        //
        // If we don't conver the 'guess' from a 'String' type to a the 'i32' type that rand gives
        // back, the compary method on 'guess' will not compile
        match guess.cmp(&secret_number) {
            Ordering::Less    => println!("Too small!"),
            Ordering::Greater => println!("Too big!"),
            Ordering::Equal   => { 
                // Note: We're allowed to pass an entire block rather than just
                // a statement
                println!("You win!");
                // 'break' will exit the loop. And exiting this loop mean exiting
                // our program.
                break;
            },
        }
    }
    println!("The secret number is: {}", secret_number);
}
