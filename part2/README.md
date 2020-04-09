# Description

This project presents a slightly more advanced scenario:

* produce a JAR archive (the equivalent of a C _library_).
* execute the application by specifying a list of JAR files to search from for the application's entry point.

| Variable       | Description                                     |
|----------------|-------------------------------------------------|
| `ROOTDIR`      | The directory at the top of the project's tree. |
| `DIR`          | `${ROOTDIR}/class`                              |

In this example, we introduce the use of a JAR archive:
the file `lib/commons-cli-1.3.1.jar`.

The directory structure is given below:

    ${ROOTDIR}
    +--- lib
    |   +--- commons-cli-1.3.1.jar
    +--- src
    |   +--- com
    |   |   +--- beurive
    |   |   |   +--- Main.java

Compile the main class `Main.java`:

    javac -d ${DIR} -classpath ${ROOTDIR}\lib\commons-cli-1.3.1.jar ${ROOTDIR}\src\com\beurive\Main.java

This will create the class file `Main.class`:

    ${ROOTDIR}
    +--- class
    |   +--- com
    |   |   +--- beurive
    |   |   |   +--- Main.class
    +--- lib
    |   +--- commons-cli-1.3.1.jar
    +--- src
    |   +--- com
    |   |   +--- beurive
    |   |   |   +--- Main.java

Create the JAR file:

    jar cvf ${ROOTDIR}\lib\app.jar -C ${DIR} .

> Please note that the option `-C` asks the JAR utility to change directory to `${DIR}` (`${ROOTDIR}\class\`).
> This option is very important because the path to the main class (that is `com/beurive/Main.class`) must follow the main class's fully qualified name (that is `com.beurive.Main`).

You obtain the following source tree:

    ${ROOTDIR}
    +--- class
    |   +--- com
    |   |   +--- beurive
    |   |   |   +--- Main.class
    +--- lib
    |   +--- app.jar
    |   +--- commons-cli-1.3.1.jar
    +--- src
    |   +--- com
    |   |   +--- beurive
    |   |   |   +--- Main.java

Check the content of the JAR file:

    jar tvf ${ROOTDIR}\lib\app.jar
        0 Mon Apr 03 13:51:56 CEST 2017 META-INF/
       69 Mon Apr 03 13:51:56 CEST 2017 META-INF/MANIFEST.MF
        0 Mon Apr 03 11:55:42 CEST 2017 com/
        0 Mon Apr 03 11:55:42 CEST 2017 com/beurive/
     1277 Mon Apr 03 11:55:42 CEST 2017 com/beurive/Main.class

You must make sure that, within the JAR file, the path to the class that contains the application entry point (`com.beurive.Main`) is: `com/beurive/Main.class`.

Execute the application:

    java -cp "${ROOTDIR}\lib\commons-cli-1.3.1.jar;${ROOTDIR}\lib\app.jar" com.beurive.Main

> Please note that the fully qualified name of the main class (`com.beurive.Main`) will be translated into a path (`com/beurive/Main.class`).
> This path will be used to search for a class into the JAR files.
