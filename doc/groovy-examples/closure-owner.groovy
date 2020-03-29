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
// Closure: Owner of a closure
//     http://docs.groovy-lang.org/docs/groovy-2.4.6/html/documentation/#_owner_of_a_closure

assert !(this instanceof Main)
assert this instanceof Script

// We declare a closure, and we execute it immediately.
({
    assert !(owner instanceof Main)
    assert(owner instanceof Script)
    assert(owner == this)
})()

// We declare a closure within a class (OwnerClassA).
// We instantiate the class that declares
// the closure within another class (OwnerClassB).

class OwnerClassA {
    def my_closure = { return { owner } }

    def get_closure1() {
        return { owner }
    }
    def get_closure2() {
        return my_closure
    }

}

class OwnerClassB {
    def a = new OwnerClassA()

    def run1() {
        a.get_closure1()
    }

    def run2() {
        a.get_closure2()
    }
}

def b = new OwnerClassB()
def c1 = b.run1()
assert c1 instanceof Closure
assert c1() == b.a

def c2 = b.run2()
assert c2 instanceof Closure
assert c2() instanceof Closure
assert c2()() != b.a
assert c2()() == b.a.my_closure
