# Description

This document contains notes about Groovy.

# Script files are embedded into a class called Main

See: [Scripts versus classes](http://docs.groovy-lang.org/docs/groovy-2.4.6/html/documentation/#_scripts_versus_classes)

The simple script:

	int v=6
    println 'Hello'
    int power(int n) { 2**n }
    println "2^6==${power(v)}"

Is converted into:

	import org.codehaus.groovy.runtime.InvokerHelper
	class Main extends Script {
	    int power(int n) { 2** n}
	    def run() {
	    	int v=6
	        println 'Hello'
	        println "2^6==${power(v)}"
	    }
	    static void main(String[] args) {
	        InvokerHelper.runScript(Main, args)
	    }
	}

This is very important to know.

You can see that:
* the _functions_ defined into the script (for example `power(int n)`) are, in reality, methods (of the class `Main`).
* variables defined in the scripts are local to the method `run()`.
  Thus, these variables cannot be accessed from (what look like) the functions. In other words, do not expect the script variables to be "global".

Example: [script.groovy](groovy-examples/script.groovy)

# Closure delegate

The notion of delegate is very important to understand.
Indeed, the Gradle syntax relies heavily on this concept.

See: [Delegate of a closure](http://docs.groovy-lang.org/docs/groovy-2.4.6/html/documentation/#_delegate_of_a_closure)

When you see, for example, the Gradle code:

	dependencies {
	    compile "joda-time:joda-time:2.2"
	    testCompile "junit:junit:4.12"
	}

* `dependencies` is a function that takes a closure as a unique parameter.
* the closure (passed as the parameter for the function `dependencies`) is `{ ... }`.
* an object that defines the methods `compile` and `testCompile` is assigned to the closure delegate.
  Thus, the code `compile "joda-time:joda-time:2.2"` will execute the method
  `compile` (of the closure delegate), with the parameter `"joda-time:joda-time:2.2"`.

Example: [closure-function.groovy](groovy-examples/closure-function.groovy)

See also:

* [closure-this.groovy](groovy-examples/closure-this.groovy)
* [closure-owner.groovy](groovy-examples/closure-owner.groovy)
* [closure-delegate.groovy](groovy-examples/closure-delegate.groovy)

# Maps

The Gradle syntax often use maps. Thus it is important to learn about it.

Example: [maps.groovy](groovy-examples/maps.groovy)

