# RpnCalculator

Programming Exercise – RPN Calculator

Some of the best calculators in the world have an 'RPN' (reverse polish notation) mode.
We would like you to write a command-line based RPN calculator.

Requirements

The calculator has a stack that can contain real numbers.
The calculator waits for user input and expects to receive strings containing whitespace separated lists of numbers and opera tors.
Numbers are pushed on to the stack. Operators operate on numbers that are on the stack.
Available operators are +, -, *, /, sqrt, undo, clear
Operators pop their parameters off the stack, and push their results back onto the stack.
The 'clear' operator removes all items from the stack.
The 'undo' operator undoes the previous operation. "undo undo" will undo the previo us two operations.
sqrt performs a square root on the top item from the stack
The '+', '-', '*', '/' operators perform addition, subtraction, multiplication and division respectively on the top two items from the stack.
After processing an input string, the calculator displays the current contents of the stack as a space-separated list.
Numbers should be stored on the stack to at least 15 decimal places of precision, but displayed to 10 decimal places (or less if it
causes no loss of precision).
All numbers should be formatted as plain decimal strings (ie. no engineering formatting).
If an operator cannot find a sufficient number of parameters on the stack, a warning is displayed:
operator <operator> (position: <pos>): insufficient parameters

After displaying the warning, all further processing of the string terminates and the current state of the stack is displayed.


Usage:
1. Download the zip file
2. Open the command prompt 
3. cd path-to-unzip-file-directory
4. mvn clean install
5. mvn package assembly:single
6. java -jar target/RpnCalculator-0.0.1-SNAPSHOT-jar-with-dependencies.jar

Note: I am not sure how the position 15 comes from in the Example 8 of the tech-programming-test-rpn-1.1.7.pdf, I assume it is the position of the trouble operator in order of input from user. I program it at this way. Please take note at this.