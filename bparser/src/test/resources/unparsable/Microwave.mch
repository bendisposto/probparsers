MACHINE Microwave

SETS
  State = {s1,s2,s3,s4,s5,s6,s7}
  
VARIABLES
  Start, Close, Heat, Error, CurState
  
INVARIANT
  Start : BOOL & Start : BOOL & Start : BOOL & Start : BOOL &
  CurState : State
  
INITIALISATION
   Start, Close, Heat, Error, CurState :=  FALSE,FALSE,FALSE,FALSE, s1
   
OPERATIONS


#define CHANGE_STATE_S1(msg) atomic { printf(msg); Start = 0; Close = 0; Heat = 0; Error = 0; State = 1; }
#define CHANGE_STATE_S2(msg) atomic { printf(msg); Start = 1; Close = 0; Heat = 0; Error = 1; State = 2; }
#define CHANGE_STATE_S3(msg) atomic { printf(msg); Start = 0; Close = 1; Heat = 0; Error = 0; State = 3; }
#define CHANGE_STATE_S4(msg) atomic { printf(msg); Start = 0; Close = 1; Heat = 1; Error = 0; State = 4; }
#define CHANGE_STATE_S5(msg) atomic { printf(msg); Start = 1; Close = 1; Heat = 0; Error = 1; State = 5; }
#define CHANGE_STATE_S6(msg) atomic { printf(msg); Start = 1; Close = 1; Heat = 0; Error = 0; State = 6; }
#define CHANGE_STATE_S7(msg) atomic { printf(msg); Start = 1; Close = 1; Heat = 1; Error = 0; State = 7; }

/* Main Microwave Process */
active proctype Microwave()
{
	do
::	State == 1 -> CHANGE_STATE_S2("Start Oven")
::	State == 1 -> CHANGE_STATE_S3("Close Door")

::	State == 2 -> CHANGE_STATE_S5("Close Door")

::	State == 3 -> CHANGE_STATE_S1("Open Door")
::	State == 3 -> CHANGE_STATE_S6("Start Oven")

::	State == 4 -> CHANGE_STATE_S1("Open Door")
::	State == 4 -> CHANGE_STATE_S3("Done")
::	State == 4 -> CHANGE_STATE_S4("Cook")

::	State == 5 -> CHANGE_STATE_S2("Open Door")
::	State == 5 -> CHANGE_STATE_S3("Reset")

::	State == 6 -> CHANGE_STATE_S7(" Warm up")

::	State == 7 -> CHANGE_STATE_S4("Start Cooking")
	od	
}
