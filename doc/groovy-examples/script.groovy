/**
 * @brief This file implements a basic script.
 *
 * @note See http://docs.groovy-lang.org/docs/groovy-2.4.6/html/documentation/#_public_static_void_main_vs_script
 */

import groovy.inspect.Inspector
import org.apache.bsf.Main

println("Number of argument: ${args.length}")
int n = 0
args.each {
    println("* ${n++}: ${it}")
}

// this.println() <=> println() <=> Script.println()
// => they are all the same function.
//
// Explanation:
//
// See http://docs.groovy-lang.org/docs/groovy-2.4.6/html/documentation/#_scripts_versus_classes
//
// A Groovy script is, in fact, embedded in a class called "Main", which implements the interface
// "Script". Consequently, from within the script, "this" refers to an instance of "Script".
//
// * The script code is embedded within a method called "run()".
// * The script "functions" are converted into methods (of the class "Main").
// * If you define classes within the script, these classes will be moved outside the "Main" class.
// * "this" refers to the current instance of "Script".

assert !(this instanceof Main)
assert this instanceof Script

inspector = new Inspector(this)
inspector.classProps.each {
    println("* ${it}")
}

println("This is a test")
this.println("This is a test")
Script.println("This is a test")

// However, "Script.println()" is not "System.out.println()", as you can see:
//
//     public static void println(Object self, Object value) {
//        if (self instanceof Writer) {
//            PrintWriter pw = new GroovyPrintWriter((Writer)self);
//            pw.println(value);
//        } else {
//            System.out.println(InvokerHelper.toString(value));
//        }
//    }

System.out.println("This is a test")

// The variable "name" is local to the method "run()".
// "print_name()" is converted into a method.
// Therefore, the variable "name" cannot be accessed from the function "print_name()".

String name = "My name"

// To make the value of "name" accessible from the "function/method" "print_name()",
// let's create a class property.
this.setProperty('name', name) // or simply: this.name = name

void print_name() {
    println(this.name) // or simply: println(name)
}

print_name()
