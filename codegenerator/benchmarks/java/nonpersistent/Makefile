lift: LiftExec.class
	java -cp :btypes.jar LiftExec
traffic: TrafficLightExec.class
	java -cp :btypes.jar TrafficLightExec
sieve: Sieve.class
	java -cp :btypes.jar Sieve
iset: IncreasingSet.class
	java -cp :btypes.jar IncreasingSet
set: SetOperation.class
	java -cp :btypes.jar SetOperation

Lift.class: Lift.java
	javac -cp btypes.jar Lift.java
LiftExec.class: LiftExec.java
	javac -cp btypes.jar LiftExec.java Lift.java
TrafficLight.class: TrafficLight.java
	javac -cp btypes.jar TrafficLight.java
TrafficLightExec.class: TrafficLightExec.java
	javac -cp btypes.jar TrafficLightExec.java TrafficLight.java
Sieve.class: Sieve.java
	javac -cp btypes.jar Sieve.java
IncreasingSet.class: IncreasingSet.java
	javac -cp btypes.jar IncreasingSet.java
SetOperation.class: SetOperation.java
	javac -cp btypes.jar SetOperation.java

clean:
	rm *.class