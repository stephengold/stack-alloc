# stack-alloc
This is an adaptation of Martin Dvorak's 2008 stack-alloc project.

The source code was copied from https://github.com/gsimard/jbullet/tree/a98759025dbffa5dcdf77897c75f09b69a75279b,
and Gradle build scripts were added.

This library consists of 2 packages:
+ cz.advel.stack.instrument - to instrument Java class files, and
+ cz.advel.stack - to allocate memory at runtime.

The instrumentation package depends on:
+ the "asm-all" library to manipulate Java bytecode, and
+ the "ant" library to interface with Apache Ant build tools.

## External links
+ Original project announcement and discussion:  https://jvm-gaming.org/t/jstackalloc-stack-allocation-of-value-objects-in-java/31983
+ Pre-built Maven artifacts at the Maven Central Repository:  https://search.maven.org/artifact/com.github.stephengold/stack-alloc/1.0.1/jar
+ Gradle project to build the "asm-all" library:  https://github.com/stephengold/asm
+ The Apache Ant project:  https://ant.apache.org/
