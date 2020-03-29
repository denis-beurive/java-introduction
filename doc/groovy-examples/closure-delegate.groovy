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

def Closure c = {
    // The variable name is undefined in the lexical scope of the closure.
    // However, this code works.
    name.toUpperCase()
}

class DelegateClass {
    String name;

    DelegateClass(String in_name) {
        this.name = in_name
    }
}

def DelegateClass d = new DelegateClass("Joe")
c.delegate = d
println(c())
