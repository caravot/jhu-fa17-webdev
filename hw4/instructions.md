# Assignment

This homework will have you create some Java classes and subclasses.

Each concrete class must have a toString() method defines that prints out its attributes. These objects are not desgined to be the most efficient or consistent objects (some throw exceptions for bad arguments, some go to defaults). Instead, I want to see how you handle different input cases. Don't try to over-analyze the problems. Even if I don't specify how to handle bad input, use common sense! You should always handle bad input somehow, do not throw runtime exceptions! Don't assign values to a variable that don't make sense ANYWHERE in your code (example, don't set the number of engines on an aircraft to a negative number!).

Make sure that attributes are not publicly accessible!

Also, you need to review the Coding Guidelines which explain acceptable ways to format your source code. Please use these in all future homeworks.

## Step 1

Define an interface Contact

That has the following methods (notice that some are for type String, this means you'll have to convert them to int's)
* getLength/setLength (int)
* getSpeed/setSpeed (int)
* setSpeed(String)
* getName/setName (String)
* getType/setType (String) (This is an arbitrary string label for anything of class Contact)

## Step 2

Define an abstract class Ship that implements the Contact Interface. 

* The methods in contact should be defined (no longer abstract, but they can be overriden later on).

## Step 3

Define a class Destroyer that subclasses Ship

* Has the following attributes and get/set methods: numberMissile
* Supports int and String setNumberMissiles() arguments. If the String argument of setNumberMissiles() encounters a parsing error, set the numberMissiles to 2.

## Step 4

Define a class Submarine that subclasses Ship

* Has the following attributes and get/set methods: numberTorpedos
* Supports int and String setNumberTorpedos() arguments. If the String argument of setNumberTorpedos() encounters a parsing error, set the numberTorpedos to 2


## Step 5

Define an abstract class Aircraft that implements the Contact Interface. This class should also contain a getAltitude/setAltitude(int) method.

## Step 6

Define a class P3 that extends the Aircraft abstract class

* That has the following attributes and get/set methods: numberEngines
* Not string method is necessary for this one, just handle integers for the accessor (get) and modifier (set) methods.


## Step 7: In a test class

* Create 2 Destroyers
* Create 2 Submarines
* Create 2 P3s
* Make a collection of Destroyers (you select the type of Collection)
* Make a collection of Submarines (you select the type)
* Make a collection that holds all Ships
* Make a collection that holds all Contacts
* You get to pick the names and values for the classes above.

## Step 8

Print out the list of Contacts to System.out.println(). You should override the toString() method to return something "meaningful" for each class. Again, no hard requirements, just use a little common sense (i.e. print out more than the name).

Note: Use defensive programming whenever you can, for example, none of your methods should let the user set the number of items to a negative number.

You can choose what the constructors set these parameters to in your classes (they can be defaults or you can provide them). Typically, you try to exercise your code as much as possible in a test case so try a bit of each.

In the Test class, again, you can choose what values you wish to assign to the test cases.

Also, in your test class, you should exercise your execption handling case for the submarine by setting the number of torpedoes in one of your submarine classes to the string "Foo".

# Submission

Zip up the source code and the Java classes and a screen shot of Step 8 and post them to the Blackboard Assignments

