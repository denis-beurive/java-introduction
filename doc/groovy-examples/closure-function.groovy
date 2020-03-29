import org.apache.bsf.Main

// Keep in mind that:
//
// A Groovy script is, in fact, embedded in a class called "Main", which implements the interface
// "Script". Consequently, from within the script, "this" refers to an instance of "Script".
//
// * The script code is embedded within a method called "run()".
// * The script "functions" are converted into methods (of the class "Main").
// * If you define classes within the script, these classes will be moved outside the "Main" class.
// * "this" refers to the current instance of "Script".
//
// Script vs Class
//     http://docs.groovy-lang.org/docs/groovy-2.4.6/html/documentation/#_scripts_versus_classes
//     http://docs.groovy-lang.org/docs/groovy-2.4.6/html/documentation/#_methods
//
// Closure: Delegate of a closure
//     http://docs.groovy-lang.org/docs/groovy-2.4.6/html/documentation/#_delegate_of_a_closure
// Closure: Delegation strategy
//     http://docs.groovy-lang.org/docs/groovy-2.4.6/html/documentation/#_delegation_strategy_2

assert !(this instanceof Main)
assert this instanceof Script

// We declare a closure, and we execute it immediately.
({
    assert !(owner instanceof Main)
    assert(owner instanceof Script)
    assert(owner == this)
    assert(delegate == owner)
})()

class Context {
    List<Closure> functions = [];

    List<Integer> calculate(int x) {
        List<Integer> results = []
        functions.each {
            results.push(new Integer(it.call(x)))
        }
        results
    }
}

def declare = { Closure in_function ->
    // The variable "functions" will be declared within a delegate object.
    // It is assumed to be: List<Closure> functions = []
    functions.push(in_function)
}

Context c = new Context();
declare.delegate = c
declare { x -> 2*x }
declare { x -> 3*x }
declare { x -> 4*x }

List<Integer> results = c.calculate(10)

String list = ""
results.each { list += it.toString() + " " }
println(list) // => 20 30 40
