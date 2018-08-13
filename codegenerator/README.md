This is a code generator for generating code from B to other programming languages.

A subset of Java is supported for now.

The generated code in Python is not supported as the implementations of needed B Types is missed.

The generated code for C works for a subset of the generated code that works for Java. Sets and couples as well as 
local declarations and operations calls are not supported for C. Including other machines is not supported in C, too. 
The only types that are implemented for C are BInteger and BBoolean.

An example where code generation for C works is the machine Lift







Starting the code generator:

Java : Example: gradle run -Planguage = "java" -Pfile = "de/hhu/stups/codegenerator/testfiles/Lift.mch"
Python: Example: gradle run -Planguage = "python" -Pfile = "de/hhu/stups/codegenerator/testfiles/Lift.mch"
C: Example: gradle run -Planguage = "c" -Pfile = "de/hhu/stups/codegenerator/testfiles/Lift.mch"