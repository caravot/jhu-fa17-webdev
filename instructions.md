# Assignment

Beartooth Hiking Company is no longer satisfied with it's implementation of the Socket Server used in one of the earlier homeworks. It wants you to develop your own server to provide the price quotes yourself.

In this homework, you are to build a socket server that will work with your original client application. It should be multi-threaded and should not crash when bad data is given to it, that means you need to check for:

* wrong number of arguments
* wrong type of arguments
* bad data in arguments

And, because we don't want to be anti-social, you need to provide feedback when something goes wrong (the good old text after the cost in the reply). Your job as a programmer when writing a server is to figure out how a user can break your program, and then defend against it!

My port: 20004

You should accept data in the form of:

```begin_year:begin_month:begin_day:hike:duration (e.g. 2008:7:1:1:2)```

The years are in four digits, and all values are separated by ":"s. Also, it is okay to assume that the client will have already checked that the duration of the hike is correct. For this requirement assume the Hikes are designated as follows.

<table>
<tr><th>Int Value</th><th>Hike</th></tr><br>
<tr><td>0</td><td>Gardiner</td></tr><br>
<tr><td>1</td><td>Hellroaring</td></tr><br>
<tr><td>2</td><td>Beaten</td></tr>
</table>

Please note that the input to your program needs to be a String.

The returned result will be the cost followed by a ":", followed by some text. If things go well, you'll give the cost and the text "Quoted Rate", if there is a problem, the cost will by -0.01 and the text will have some explanation. You can use your GUI from the earlier homework to test your server (what fun!). DO NOT INCLUDE A "GREETING" LINE in the returned data, just return the answer! My test program want's exactly what I've specified, if you put something else in, you aren't supplying the required response!

Even though you'll need some type of client to test your server (perhaps the one you've completed earlier?), I'll use my own client to test your app, so no client submission is required or desired.

# Submission

* Step 1: After your project works on your local machine,
* Step 2: Now right click on it's Netbeans project name and select "build". This will build a jar file that you can upload to the class computer.
* Step 3: Upload the jar file to the class computer. Place it in your home directory (not public_html).
* Step 4: You can run your app by typing java -jar <jar name> at the command line. To kill the server, just type Ctrl-C.
* Step 5: When you are happy with things, run your app by typing "nohup java -jar <jar name> &" at the command prompt. If you make a mistake, you will need to get he process id of the app you started. Run "ps -ef | grep <your last name>" to find your processes, get the process id from the output then run "kill -9 <pid>" to kill it.
* Step 6: Zip your source code and upload it to Web Assignments. In the Student comments section make sure you put the port number you used so I can test the code!