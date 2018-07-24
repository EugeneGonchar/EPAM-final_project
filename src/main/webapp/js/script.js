var date = new Date();
var firstDateDefault = new Date().setHours(date.getHours()+11);
var secondDateDefault = new Date().setDate(date.getDate()+3);
var date1 = new Date();
date1.setTime(firstDateDefault);

var date2 = new Date();
date2.setTime(secondDateDefault);

$(function () {
    $('#datetimepicker1').datetimepicker({
        minDate: date1,
        defaultDate: date1,
        format: 'YYYY-MM-DD HH:MM',
        stepping: 30
    });
    $('#datetimepicker2').datetimepicker({
        minDate: date1,
        defaultDate: date2,
        format: 'YYYY-MM-DD HH:MM',
        stepping: 30,
        useCurrent: false //Important! See issue #1075
    });
    $("#datetimepicker1").on("dp.change", function (e) {
        $('#datetimepicker2').data("DateTimePicker").minDate(e.date);
    });
    $("#datetimepicker2").on("dp.change", function (e) {
        $('#datetimepicker1').data("DateTimePicker").maxDate(e.date);
    });
});

