<%@ page import="ravotta.carrie.Reservation" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Beartooth Hiking Company</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/jquery-ui.min.css">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<nav id="header-nav" class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Beartooth Hiking Company</a>
        </div>
        <p class="navbar-text navbar-right">
            <a href="reservations.jsp" class="navbar-link">Search Reservations</a>
        </p>
    </div>
</nav>

<div id="main-content" class="container-fluid">
    <div class="row">
        <div class="col-lg-offset-3 col-md-offset-3 col-sm-offset-3 col-lg-6 col-md-6 col-sm-6">
            <h1 class="page-header text-center">Search Reservations</h1>
            <form action="reservations" method="post" class="form-horizontal" role="form" name="hikeForm" id="hikeForm">
                <div class="form-group">
                    <label for="startDate" class="col-sm-2 control-label">Start Date</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="startDate" name="startDate" placeholder="MM/DD/YYYY">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-offset-3 col-md-offset-3 col-sm-offset-3 col-lg-6 col-md-6 col-sm-6">
            <h1 class="page-header text-center">Reservation List</h1>
            <table class="table table-bordered table-striped">
                <thead>
                    <th>Dates</th>
                    <th>Location</th>
                    <th>Guide Name</th>
                    <th>Guest Name</th>
                </thead>
                <tbody>
                    <%
                        ArrayList<Reservation> rsvpList = (ArrayList<Reservation>) request.getSession().getAttribute("queryResults");
                        if (rsvpList != null) {
                            Iterator<Reservation> iter = rsvpList.iterator();
                            while (iter.hasNext()) {
                                Reservation rsvp = iter.next();
                    %>
                        <tr>
                            <td><%=rsvp.getStartDay() %></td>
                            <td><%=rsvp.getLocation() %></td>
                            <td><%=rsvp.getGuideName() %></td>
                            <td><%=rsvp.getGuestName() %></td>
                        </tr>
                    <%
                            }

                            // no results
                            if (rsvpList.size() == 0) {
                    %>
                                <tr><td colspan="4" align="center">No Results</td></tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>

