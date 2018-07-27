var date = new Date();

var firstDateDefault = new Date().setHours(date.getHours()+2);
var secondDateDefault = new Date().setDate(date.getDate()+3);
var secondMinDateDefault = new Date().setHours(date.getHours()+3);

var firstMaxDateDefault = new Date().setMonth(date.getMonth() + 6);
var secondMaxDateDefault = new Date().setFullYear(date.getFullYear() + 1);

var date1 = new Date();
date1.setTime(firstDateDefault);
var date2 = new Date();
date2.setTime(secondDateDefault);
var date5 = new Date();
date5.setTime(secondMinDateDefault)

var date3 = new Date();
date3.setTime(firstMaxDateDefault);
var date4 = new Date();
date4.setTime(secondMaxDateDefault);

$(function () {
    $('#datetimepicker1').datetimepicker({
        minDate: date1,
        defaultDate: date1,
        format: 'YYYY-MM-DD HH:mm',
        maxDate: date3,
        stepping: 30
    });
    $('#datetimepicker2').datetimepicker({
        minDate: date5,
        defaultDate: date2,
        format: 'YYYY-MM-DD HH:mm',
        maxDate: date4,
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

