This is the code generator **B2Program** for generating code from B to other programming languages.

A subset of Java is supported for now.

The generated code in Python is not supported as the implementations of needed B Types are missed.

The generated code for C works for a subset of the generated code that works for Java. Sets and couples are not supported for C. 
Including other machines is not supported in C, too. 
The only types that are implemented for C are BInteger and BBoolean.

An example where code generation for C works is the machine Lift



<br>
<br>
<br>
<br>
<br>



**Starting the code generator:**

Java : Example: gradle run -Planguage="java" -Pfile="de/hhu/stups/codegenerator/testfiles/Lift.mch"

Python: Example: gradle run -Planguage="python" -Pfile="de/hhu/stups/codegenerator/testfiles/Lift.mch"

C: Example: gradle run -Planguage="c" -Pfile="de/hhu/stups/codegenerator/testfiles/Lift.mch"


<br>
<br>
<br>
<br>
<br>



**Compile the generated code in Java:**

1. Run 'gradle build' in project btypes
2. Move btypes-all-2.9.12-SNAPSHOT.jar to same folder as the generated classes
3. javac -cp btypes-all-2.9.12-SNAPSHOT.jar *files*
4. Example: javac -cp btypes-all-2.9.12-SNAPSHOT.jar TrafficLightExec.java TrafficLight.java (Code generated from TrafficLightExec.mch which includes TrafficLight.mch)

**Compile the generated code in C:**

1. Move BInteger and BBoolean to same folder as generated code (see btypes/src/main/c)
2. gcc *input file* -o *output file*
3. Example: gcc Lift.c -o Lift


**Execute the generated code in Java:** 
1. Write a main function in the generated main class
2. java -cp :btypes-all-2.9.12-SNAPSHOT.jar *main file*
3. Example: java -cp :btypes-all-2.9.12-SNAPSHOT.jar TrafficLightExec



**Execute the generated code in C:** 
1. Write a main function in the generated main file
2. ./*main file*
3. Example: ./Lift


Analysis of the Performance is described in the README in directory *benchmarks*



