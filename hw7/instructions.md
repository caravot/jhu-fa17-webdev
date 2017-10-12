# Assignment

Our good friends at Beartooth Hiking Company have heard of our newly gained experience in Servlets, and now wish to have a web page made for their booking purposes.

For this homework, you are to make a Form that will hold input fields for allowing a user to set the begin date and duration of a potential tour. This form will submit the information to a Servlet, which will then show the cost estimates for tours from the BHC.

You need to name your project <your_last_name>_<whatever project id you want>. For example spiegel_servlet. We will all be deploying our solutions to the the class computer application server and this way we won't overwrite each others work. From now on, use a new name for each homework, in other words, my next homework might be project spiegel_servlet2.

For this application, you should have controls for

* Setting the hike
* Setting the beginning date
  * Year
  * Month
  * Day
* Setting the duration of the hike
* Submitting the query
* Showing the results
  * Total Cost

I have provided the same two classes to assist you in your project. You can download them here.

The first class, BookingDay, is a class that allows you to define a single BookingDay. There are two constructors

* BookingDay()
* BookingDay(int year, int month, int day)

The first constructor allows the class to be used as a Java Bean. The second allows you to define a day based on a four digit year, a month (1-Jan, 12-Dec) and a day. Take a look at the source code and look at the other methods provided. There is a isValidDate() method to see if the arguments you provided describe a real date.

The second class, Rates, is a class that allows you to define a tour period and then get a cost for the tour. You need to define a begin date and a duration, and then you can get the cost of the tour. Don't forget to call isValidDates() to verify that your input dates are within the season and are valid.

It's up to you how to lay out the window, but keep in mind the balance and symmetry issues.

Do not use any JSP pages for this homework. Just use Servlets.

# Submission

The submission of this homework will be two items:

* A servlet/web page showing this method of obtaining quotes for BHC
  * Follow the Uploading Your Servlet instructions to deploy the WAR file to the server. Please note that when you run your app on your local machine it is running on port 8084 (or something similar), but the class server is running on port 80, you may have to tweek any HTML links on your home page to account for this.
* A zip of your source file uploaded to Blackboard and the URL of you application in the submission comments section for the Blackboard Assignments. Do NOT forget to include the URL of your project!