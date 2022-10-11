#HOW TO RUN JAVA CODE AND JUNIT TEST SCRIPT IN THE TERMINAL

Unless stated, the same procedure is followed on Linux, MacOS, and Windows OS.
Ensure that the latest JDK is installed: at least JDK 17
The junit-platform-console-standalone-1.9.1.jar jar file will be required and can be downloaded from 
Maven Central repository)

STEP 1: Download the java files to a easily accessible directory
From the terminal, using the "cd" command to change directory
Example: $ cd C:\Users\downloads\files

STEP 2: COMPILE THE JAVA CODE
Compile the code use the following commands, noting the extension:
$ javac SayTheNumber.java
If the compilation is a success, there will not be any errors and a class
File under the same directory is created.

STEP 3: RUN THE JAVA CODE
To run the .class file and execute the Java program we use the command, noting no extension:
$ java SayTheNumber
The program's instructions should appear asking for an integer input. Enter digits to have their words be displayed.

STEP 4.1: COMPILE THE TEST SUIT
To run Unit tests you need to download junit-platform-console-standalone-1.9.1.jar from Maven Central repository.
Save the jar file in an easily accessible file.

STEP 4.2: 
In the directory where the java test file is found, SayTheNumberTest.java, compile the file using:
$ javac -cp .:<absolute path of the junit-platform-console-standalone-1.9.1.jar jar file> SayTheNumberTest.java
(Eample: % javac -cp .:/Users/rusheil/Downloads/junit-platform-console-standalone-1.9.1.jar SayTheNumberTest.java)
(Note that the "<>" are not included in the command, the absolute path of the jar file should be inserted here.)
No errors should occur.
 
STEP 5: RUN THE TEST 
Run the test using:
$ java -jar <absolute path of the junit-platform-console-standalone-1.9.1.jar jar file> --class-path . --scan-class-path
(Example: $ -jar /Users/rusheil/Downloads/junit-platform-console-standalone-1.9.1.jar --class-path . --scan-class-path)

The above instructions indicate how to compile and run the java files of SayTheNumber project from download to run in the terminal.
