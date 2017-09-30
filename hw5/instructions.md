# Assignment

Now that you are an expert Swing designer, let's take some of that knowledge and apply it.

This homework is going to introduce the mythical Beartooth Hiking Company (BHC). This small tour business will give backpacking tours in the Beartooth-Absaroka wilderness during the summer months of each year. The owner has decided that he'd like to provide a way for people to get quotes on how much a tour will cost, and has approached you to design a solution. (If you have a feeling that you'll see more of the BHC in weeks to come, you are absolutely correct!).

For this homework, you are to make a window (a JFrame) that will hold controls for allowing a user to set the begin date and duration of a potential tour. Since making a Web-Start application is no longer an option, you need to learn how to bundle you application in a nice "jar" file so I can see how it works.

For this application, you should have controls for:

* Setting the beginning date
  * Year
  * Month
* Day
* Setting the duration of the hike (note that different hikes have different duration options)
* Submitting the query (use a JButton)
* Showing the results (DON'T use a JButton)
  * Total Cost

I have provided two classes to assist you in your project. You can download them here.

The first class, BookingDay, is a class that allows you to define a single BookingDay. There are two constructors

* BookingDay()
* BookingDay(int year, int month, int day)

The first constructor allows the class to be used a a Java Bean in later homeworks. The second allows you to define a day based on a four digit year, a month (1-Jan, 12-Dec) and a day. Take a look at the source code and look at the other methods provided. There is a isValidDate() method to see if the arguments you provided describe a real date.

The second class, Rates, is a class that allows you to define a tour period and then get a cost for the tour. You need to define a begin date and a duration and then you can get the cost of the tour. Don't forget to call isValidDates() to verify that your input dates are within the season and are valid.

It's up to you how to lay out the window, but keep in mind the balance and symmetry issues.

You are responsible for defensive programming! I should not be able to "break" your application by typing in junk to the input fields. In addition, use JOptionPanes to inform the user of what went wrong and how they should fix it. Tell them what is wrong! Don't just say an error has occurred without telling them exactly what happened!

If you use an awkward style of input (require two digits for a month), you must let the user what you expect them to enter. DO NOT MAKE THEM GUESS. Beware GridLayout. Students have been tempted to "slap something together" with GridLayout and it doesn't look very good. Put a little thought into your layout. Also be specific with error messages, avoid the "something isn't right" class of message, tell the user what is wrong.

Use the values for the hikes that were given in Week 2's homework. You will need someway for a user to select which hike they want and then based on that the duration.

# Submission

Upload your source code and the jar file to Blackboard.

#Grading

General Grading Guidelines

* Reasonable code design/code guidelines/General Grading Guidelines 25%
* Application runs correctly 40%
* Error checking/Defensive Programming 20%
* Good error feedback (used JOptionPane and appropriate text messages) 10% 
* Extra Effort 5%