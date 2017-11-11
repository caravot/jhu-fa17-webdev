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
    <h1 class="page-header text-center">Error!</h1>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 text-center">
            <%= request.getAttribute("message") %>
        </div>
    </div>
</div>

<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>
