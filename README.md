# Calculator

Console application calculator

author : Wojciech Pawluk 
email: pawlukwojciech208@gmail.com
phone: +48 728 206 292

Short Description: 
It's a console calculator with basic arithmetic operations and history.
Expressions are calculated in sequence of math actions and can combine in any order.  

List of Functionalities:
1. Displaying welcome message with statement to call help function. 
2. Help function displaying all functions and every way to invoke them.
3. Basic arithmetic operations:
   1. Addition
   2. Subtraction
   3. Multiplication
   4. Division
4. Displaying notation of the form of arithmetic equation after every operation. 
5. History function displaying all executed expressions (during the session) in the form of list.
6. Detailed function displaying all executed expressions (during the session) in detailed list showing every step of calculation.
7. Exit function allowing to exit application
8. Handles some exceptions. (But not all of them).

Examples of operations:

To evoke help command:
-h 
or 
--help
or 
help

To evaluate 2+2 expression: 
-o 2+2
or
--operator  2+2

To evaluate  1+4-5*10 expression:
-o  1+4-5*10

Changes needed to run the application:
You only need to run the CalculatorApplication.