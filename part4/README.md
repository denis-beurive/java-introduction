# Description

This project illustrates the use of Gradle

# Generate the Gradle wrapper

Make sure the environment variable `JAVA_HOME` is set (to the path of the JDK root directory).
For example `C:\Users\userid\tools\java\jdk-14`.

Make sure the JAVA tools are reachable (check the environment variable `PATH`).

See [this link](https://docs.gradle.org/current/userguide/gradle_wrapper.html).

Create the Gradle wrapper (`gradlew` or `gradlew.bat` depending on the OS):

    gradle wrapper

It will create 2 files:

    ├── gradlew
    └── gradlew.bat

Then execute the wrapper:

    gradlew.bat
    Downloading https://services.gradle.org/distributions/gradle-6.3-bin.zip
    .........10%..........20%..........30%.........40%..........50%..........60%.........70%..........80%..........90%..........100%
    
    > Task :help
    
    Welcome to Gradle 6.3.
    
    To run a build, run gradlew <task> ...
    
    To see a list of available tasks, run gradlew tasks
    
    To see a list of command-line options, run gradlew --help
    
    To see more detail about a task, run gradlew help --task <task>
    
    For troubleshooting, visit https://help.gradle.org
    
    BUILD SUCCESSFUL in 3m 20s
    1 actionable task: 1 executed

This will create the following tree:

    └── gradle
        └── wrapper
            ├── gradle-wrapper.jar
            └── gradle-wrapper.properties

# Building the scaffolding

See [Organizing Gradle Projects](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html).

Minimal settings:

    └───src
        ├───main
        │   └───java
        │
        └───test
            └───java

# Configuring Gradle

See the configuration files:

* [build.gradle](build.gradle)
* [settings.gradle](settings.gradle)

> See [Gradle explorer](https://github.com/denis-beurive/gradle-explorer).

Please note that we declare the dependency [Apache Commons Math](https://mvnrepository.com/artifact/org.apache.commons/commons-math3/3.2).

The dependency is downloaded and stored in the director whose path looks something like:

    ${HOME}/.gradle/caches/modules-2/files-2.1/org.apache.commons/commons-math3/3.2/ec2544ab27e110d2d431bdad7d538ed509b21e62/commons-math3-3.2.jar

> `${HOME}` represents the user home directory.

# Build the application

List the available tasks:

    gradlew tasks
    
Build the application:

    gradlew build
