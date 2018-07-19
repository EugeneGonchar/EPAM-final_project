var today = new Date();

$(function () {
    $('#datetimepicker1').datetimepicker({
        minDate: today,
        defaultDate: today,
        stepping: 30
    });
    $('#datetimepicker2').datetimepicker({
        stepping: 30,
        defaultDate: today.setDate(today.getDate() + 3),
        useCurrent: false //Important! See issue #1075
    });
    $("#datetimepicker1").on("dp.change", function (e) {
        $('#datetimepicker2').data("DateTimePicker").minDate(e.date);
    });
    $("#datetimepicker2").on("dp.change", function (e) {
        $('#datetimepicker1').data("DateTimePicker").maxDate(e.date);
    });
});

