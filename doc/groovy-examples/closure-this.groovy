import org.apache.bsf.Main

// Keep in mind that:
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
// Closure: the meaning of this
//     http://docs.groovy-lang.org/docs/groovy-2.4.6/html/documentation/#closure-this

assert !(this instanceof Main)
assert this instanceof Script

// We declare a closure, and we execute it immediately.
({
    assert !(this instanceof Main)
    assert(this instanceof Script)
})()

// We declare a closure within a class (ThisClassA).
// We instantiate the class that declares
// the closure within another class (ThisClassB).

class ThisClassA {
    def get_closure() {
        return { this }
    }
}

class ThisClassB {
    def a = new ThisClassA()

    def run() {
        a.get_closure()
    }
}

def b = new ThisClassB()
def c = b.run()
assert c instanceof Closure
assert c() == b.a
