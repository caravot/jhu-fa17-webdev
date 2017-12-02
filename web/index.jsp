<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Beartooth Hiking Company</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/jquery-ui.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">

    <script src="assets/js/jquery-1.11.3.min.js"></script>
    <script src="assets/js/jquery-ui.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script language="JavaScript">
      $(document).ready(function () {
        // create date picker
        var date = $('input#date').datepicker({
          showButtonPanel: true,
          changeMonth: true,
          changeYear: true
        });

        // set todays date
        date.datepicker('setDate', new Date());

        // validate required fields
        $('#hikeForm').on('submit', function (e) {
          e.preventDefault();

          // get form parameters and translate them to a JSON object
          // that is mapped to a JSON/Java object in service
          var viewArr = $("form#hikeForm").serializeArray();
          var view = {};

          for (var i in viewArr) {
            view[viewArr[i].name] = viewArr[i].value;
          }

          $.ajax({
            type: "POST",
            contentType: 'application/json',
            dataType: "json",
            data: JSON.stringify(view),
            url: 'api/rsvp/new',
            success: function(data) {
              $("#message").html(data.message);
            }
          });

          return false;
        })
      });
    </script>
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
    <h1 class="page-header text-center">Book a Hike</h1>
    <p class="text-center">We open June 1st and close Oct 1st.</p>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
            <form action="" method="post" class="form-horizontal" role="form" name="hikeForm"
                  id="hikeForm">
                <div class="form-group">
                    <label for="hike" class="col-sm-2 control-label">Hike Name</label>
                    <div class="col-sm-10">
                        <select id="hike" name="hike" class="form-control">
                            <option value="GARDINER">GARDINER</option>
                            <option value="HELLROARING">HELLROARING</option>
                            <option value="BEATEN">BEATEN</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="duration" class="col-sm-2 control-label">Duration</label>
                    <div class="col-sm-10">
                        <select id="duration" name="duration" class="form-control">
                            <option value="2">2 (HELLROARING)</option>
                            <option value="3">3 (GARDINER, HELLROARING)</option>
                            <option value="4">4 (HELLROARING)</option>
                            <option value="5">5 (GARDINER, BEATEN)</option>
                            <option value="7">7 (BEATEN)</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="hike" class="col-sm-2 control-label">Number in Party</label>
                    <div class="col-sm-10">
                        <select id="party" name="party" class="form-control">
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
                    <label for="date" class="col-sm-2 control-label">Start Date</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="date" name="date"
                               placeholder="MM/DD/YYYY">
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
        <div class="col-lg-12 col-md-12 col-sm-12 text-center text-danger" id="message"></div>
    </div>
</div>
</body>
</html>