
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
    </div>
</nav>

<div id="main-content" class="container-fluid">
    <h1 class="page-header text-center">Book a Hike</h1>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
            <form action="Rate" method="post" class="form-horizontal" role="form" name="hikeForm" id="hikeForm">
                <div class="form-group">
                    <label for="hikeName" class="col-sm-2 control-label">Hike Name</label>
                    <div class="col-sm-10">
                        <select id="hikeName" name="hikeName" class="form-control">
                            <option value="GARDINER">GARDINER</option>
                            <option value="HELLROARING">HELLROARING</option>
                            <option value="BEATEN">BEATEN</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="hikeName" class="col-sm-2 control-label">Number in Party</label>
                    <div class="col-sm-10">
                        <select id="partyNumber" name="partyNumber" class="form-control">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="startDate" class="col-sm-2 control-label">Start Date</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="startDate" name="startDate" placeholder="MM/DD/YYYY">
                    </div>
                </div>
                <div class="form-group">
                    <label for="duration" class="col-sm-2 control-label">Duration</label>
                    <div class="col-sm-10">
                        <select id="duration" name="duration" class="form-control"></select>
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
</div>

<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>

