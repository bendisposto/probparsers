lift: LiftExec.class
	java -cp :btypes_persistent.jar LiftExec
traffic: TrafficLightExec.class
	java -cp :btypes_persistent.jar TrafficLightExec
sieve: Sieve.class
	java -cp :btypes_persistent.jar Sieve
iset: IncreasingSet.class
	java -cp :btypes_persistent.jar IncreasingSet
set: SetOperation.class
	java -cp :btypes_persistent.jar SetOperation

Lift.class: Lift.java
	javac -cp btypes_persistent.jar Lift.java
LiftExec.class: LiftExec.java
	javac -cp btypes_persistent.jar LiftExec.java Lift.java
TrafficLight.class: TrafficLight.java
	javac -cp btypes_persistent.jar TrafficLight.java
TrafficLightExec.class: TrafficLightExec.java
	javac -cp btypes_persistent.jar TrafficLightExec.java TrafficLight.java
Sieve.class: Sieve.java
	javac -cp btypes_persistent.jar Sieve.java
IncreasingSet.class: IncreasingSet.java
	javac -cp btypes_persistent.jar IncreasingSet.java
SetOperation.class: SetOperation.java
	javac -cp btypes_persistent.jar SetOperation.java

clean:
	rm *.class