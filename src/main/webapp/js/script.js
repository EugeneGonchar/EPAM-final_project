$(function () {
    $('#datetimepicker1').datetimepicker({
        minDate: new Date(),
        defaultDate: new Date(),
        stepping: 30
    });
    $('#datetimepicker2').datetimepicker({
        stepping: 30,
        defaultDate: new Date().getDate() + 3,
        useCurrent: false //Important! See issue #1075
    });
    $("#datetimepicker1").on("dp.change", function (e) {
        $('#datetimepicker2').data("DateTimePicker").minDate(e.date);
    });
    $("#datetimepicker2").on("dp.change", function (e) {
        $('#datetimepicker1').data("DateTimePicker").maxDate(e.date);
    });
});

