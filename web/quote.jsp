<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Beartooth Hiking Company</title>

    <jsp:useBean id="hikeReservation" class="ravotta.carrie.HikeReservation" scope="session" />

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
    </div>
</nav>

<div id="main-content" class="container-fluid">
    <h1 class="page-header text-center">Quote</h1>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 text-center">
            <p><strong>Hike Name: </strong> <jsp:getProperty name="hikeReservation" property="hikeName" /></p>
            <p><strong>Start Date: </strong> <jsp:getProperty name="hikeReservation" property="startDate" /></p>
            <p><strong>Duration: </strong> <jsp:getProperty name="hikeReservation" property="duration" /></p>
            <p><strong>Quote: </strong> $<%= request.getAttribute("message") %></p>
        </div>
    </div>
</div>

<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>

