# Assignment

This assignment extends the work from last assignment. I would like you to extend the Web Service you created in that assignment to accept input in the form of a JSON string (that gets deserialized into a Java object) and generates a JSON response back to the client who will use it's content to update page on the client.

There are two major parts to this assignment. For the server code (service and java business logic) you will need to create a utility object that maps to the JSON you will be ingesting. This includes number in party, hike, start date and hike duration in days. It is probably easiest to map the fields as Strings and primitives and then use them to build your components (BookingDay, Rates) in your Java logic. You will also need another utility object to package the result and or error message that gets passed back to the client. Once you have your utility objects you will need to incorporate them in your service method from last assignment as either an input parameter or return object. Don't forget to update the annotations for your modifications.

The second part of the assignment will be providing a client page to collect the parameters from the user, call back to your service, and then provide the result back to the user ON THE SAME PAGE. This actually involves things that we have not covered to this point in the class. However, I am providing the following Reference HTML Page for you to use as a template for your client page. The page contains an empty HTML form for you to fill with your input elements and an empty result area that is updated with the data returned by the service and formatted for the users readability. It also includes JavaScript that takes the form data (after user submits with button push) and converts it into a JSON string, makes an AJAX POST call back to your service and then processes the result. The only thing you really need to do to the JavaScript section is update the success function to read, parse and update the page with the JSON results returned from the service. The rest of the JavaScript should function as it is. The reference page contains direction in the form of comments that you should find helpful as you make updates.

Once completed, you should be able to bring up the client page with the form, update the parameters and upon clicking the Get Rate button see the results appear below the form in the output area.

As with the last assignment, you are *strongly* encouraged to use Netbeans to build this application with Tomcat. I'd like you to make a ReSTful Java Application that will include your client page and that you will setup and configure as a web application to be deployed to the class web server as a war file. Also, don't forget to make sure you have an appropriately configured service using either the ApplicationConfig.xml, web.xml in your war file so that it is recognized on the class server.

You should be able to use most of the logic from your last assignment, but you will need to add the new utility objects along with logic to get the information from the objects to your existing code and from your code back to the utility object for return to client. You will also need to update the service signature and annotations to make sure it ingests and returns JSON formatted data.

Your final project source should include all your back end code, service, utility objects and any other busines logic, client side page that will be used to call into the service and any configuration files (e.g., web.xml) that are used in your implementation.

# Submission

A Zip file of the following should be posted to the Blackboard assignment:

* Screen shots of your client working with your server (show one bad data and one good data result).
* All of the source code for the project (client and service).
* Also upload your war file to the class server and provide the URL of your form page that accesses your web service in your blackboard submission.