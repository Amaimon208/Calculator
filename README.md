# Calculator

Console application calculator

author : Wojciech Pawluk 
email: pawlukwojciech208@gmail.com
phone: +48 728 206 292

Short Description: 
It's a console calculator with basic arithmetic operations and history.

List of Functionalities:
1. Displaying welcome message with statement to call help function. 
2. Help function displaying all functions and every way to invoke them.
3. Basic arithmetic operations:
   1. Addition
   2. Subtraction
   3. Multiplication
   4. Division
4. Displaying notation of the form of arithmetic equation after every operation. 
5. History function displaying all executed (during the session) operations in the form of list.
6. Exit function allowing to exit application

Examples of operations:

To evoke help command:
-h 
or 
--help
or 
help

To evaluate expression 2+2:
-o add -p 2,2
or
-operator + -parameters 2,2

To evaluate expression 321*2*21:
-o * -p 321,2,21

Changes needed to run the application:?

-zmienić wyjątki na walidację
-zmienić podpowiedzi co do wywołania obliczeń