# Assignment

BHC wants to look for new ways to publish their rates again. They've heard about these nifty "Web Services" and want to provide their quote service to non-Java users.

In this assignment, you'll use the basic Rate calculator to provide a Web Service. You are *strongly* recommended to use Netbeans to build this application and Tomcat. I'd like you to make a ReSTful Java Application (this means it doesn't even have to be GUI based) that you will setup and configure as a Java ReST service to be deployed to the class web server as a war file.

The client you'll create will have the same inputs/outputs as we've used so far (number in party, hike, start date and hike duration in days). You can pass those parameters from the client form that you used for your previous Servlet assignment, but this time, instead of your form calling a servlet it will call a ReST service, passing the Form Parameters along as well. Much of the code that you used to support your servlet can just be reused to back your web service as well, for this assignment you can just take the return string and pass it back to the client wrapped as an html response (no need for fancy page on return) to demonstrate you understand the ReST services components. 

I would still like you to return error messages to the client (also wrapped in HTML) but as with successful output I am just looking for the content of the result and not style. 

To generate HTML, you can control the type of response using the @Produces annotation. Also, don't forget to make sure you have an appropriately configured service using either the ApplicationConfig.xml, web.xml in your war file so that it is recognized on the class server.

Your final project source should include both the back end service java code, but also your client side form that will be used to call into the service. I won't be grading your client beyond making sure it corresponds to the service on the backend and that the parameters correspond as well.

As with the servlet assignment I expect good error checking: Do not forget that the web page on the browser and service are not a "single" application. I can, (and will), generate input to your service without going through your web page and it's error checking. Your service should check for bad input regardless of how much "filtering" you do on your web page.

# Submission

A Zip file of the following should be posted to the Blackboard assignment:

* Screen shots of your client working with your server (show one bad data and one good data result).
* All of the source code for the project (client and service).
* Also upload your war file to the class server and provide the URL of your form page that accesses your web service in your blackboard submission.