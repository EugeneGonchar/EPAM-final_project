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

$(function () {
    $('#datetimepicker3').datetimepicker({

    })
});

$(function () {
   $('#select_status').change(function () {
       if (this.value === 'accident'){
            var accident = '<div class="container col-12 my-3 p-0">\n' +
                '    <div class="mb-1 border-bottom border-muted ">\n' +
                '        <div class="h5 text-dark text-muted">Accident</div>\n' +
                '    </div>\n' +
                '    <div>\n' +
                '        Message\n' +
                '    </div>\n' +
                '    <div class="form-group my-2 col-4 p-0">\n' +
                '        <div class="input-group date">\n' +
                '            <div class="input-group-prepend">\n' +
                '                                                                        <span class="input-group-text">\n' +
                '                                                                            <i class="fa fa fa-calendar">\n' +
                '                                                                            </i>\n' +
                '                                                                        </span>\n' +
                '            </div>\n' +
                '            <input type="text" id="datetimepicker3" class="form-control" name="accident_date"/>\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="input-group my-2">\n' +
                '        <div class="input-group-prepend">\n' +
                '            <span class="input-group-text">Description:</span>\n' +
                '        </div>\n' +
                '        <textarea class="form-control" aria-label="With textarea" name="accident_description"></textarea>\n' +
                '    </div>\n' +
                '    <div class="row my-2 justify-content-end">\n' +
                '        <div class="col-6">\n' +
                '            <div class="input-group">\n' +
                '                <div class="input-group-prepend">\n' +
                '                    <span class="input-group-text">Material damage:</span>\n' +
                '                </div>\n' +
                '                <input type="text" class="form-control" aria-label="Amount (to the nearest dollar)" name="accident_material_damage">\n' +
                '                <div class="input-group-append">\n' +
                '                    <span class="input-group-text">$</span>\n' +
                '                </div>\n' +
                '            </div>\n' +
                '        </div>\n' +
                '    </div>\n' +
                '</div>';
            var div = document.getElementById("place_for_details");
            div.innerHTML = accident;
       } else if (this.value === 'dismissed') {
            var description = '<div class="container col-12 my-3 p-0">\n' +
                '    <div>\n' +
                '        Message\n' +
                '    </div>\n' +
                '    <div class="input-group my-2">\n' +
                '        <div class="input-group-prepend">\n' +
                '            <span class="input-group-text">Description:</span>\n' +
                '        </div>\n' +
                '        <textarea class="form-control" aria-label="With textarea" name="dismissed_description"></textarea>\n' +
                '    </div>\n' +
                '</div>';
           var div = document.getElementById("place_for_details");
           div.innerHTML = description;
       } else {
           var div = document.getElementById("place_for_details");
           div.innerHTML = '';
       }
   }) 
});
