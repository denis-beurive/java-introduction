# Description

This project presents the very basic scenario:

* produce an executable class file from a JAVA file (`Main.java`).
* execute the class file.

| Variable       | Description                                     |
|----------------|-------------------------------------------------|
| `ROOTDIR`      | The directory at the top of the project's tree. |
| `DIR`          | `${ROOTDIR}/class`                              |

In this example, we present the basic scenario.

The directory structure is given below:

    +--- class
    +--- src
    |   +--- com
    |   |   +--- beurive
    |   |   |   +--- Main.java

Compile the main class `Main.java`:

    javac -d ${ROOTDIR}\class ${ROOTDIR}\src\com\beurive\Main.java

This will create the class file `Main.class`:

    +--- class
    |   +--- com
    |   |   +--- beurive
    |   |   |   +--- Main.class
    +--- src
    |   +--- com
    |   |   +--- beurive
    |   |   |   +--- Main.java

Check the content of the class:

    javap ${ROOTDIR}\class\com\beurive\Main.class

Then execute the application:

    cd ${ROOTDIR}\class\
    java com.beurive.Main

> Please note that you must be in the right directory, relatively to the **fully qualified name of the main class** (that is `com.beurive.Main`).
> The fully qualified name of the main class is `com.beurive.Main`.
> From the directory `$DIR`, you must access the main class by going through the relative path "`com\beurive`".
> Therefore, from the directory `$DIR`, the relative path to the main class is "`com\beurive\Main.class`".
