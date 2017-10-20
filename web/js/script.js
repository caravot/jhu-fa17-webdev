$(document).ready(function() {
  // get hike durations when hike name changes
  $('select#hikeName').on('change', getHikeDurations);

  // create date picker
  var startDate = $('input#startDate').datepicker({
    showButtonPanel: true,
    changeMonth: true,
    changeYear: true,
    minDate: "-2M",
    maxDate: "+1Y"
  });

  // set todays date
  startDate.datepicker('setDate', new Date());

  // populate the first hike on page load
  getHikeDurations();

  // validate required fields
  $('#hikeForm').on('submit', function (e) {
    // check that form is valid
    if(document.getElementById('startDate').value === '' ||
      document.getElementById('hikeName').value === '' ||
      document.getElementById('duration').value === '') {
      alert("All fields are required");

      // stop form submission
      e.preventDefault();
    }
  })
});

/**
 * get duration options for a specified hike
 */
function getHikeDurations() {
  var hikeName = $('select#hikeName > option:selected').val();
  var durationObj = $('select#duration');

  // clean up from previous lookup
  durationObj.find('option').remove();
  durationObj.attr('disabled', false);

  $.get('Rate', { hikeName: hikeName }, function(data) {
    var days = data.split(",");

    // disable dropdown if no durations
    if (days.length <= 0) {
      durationObj.attr('disabled', true);
    }

    // add durations to dropdown
    for (var i = 0; i < days.length; i++) {
      durationObj.append($('<option></option>').val(days[i]).html(days[i]));
    }
  });
}