var curr_month;
var curr_year;
var curr_day;
var months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
var num_of_days;
var NCmonth;
var NCyear;

function origin(){
    var date = new Date();
    document.getElementById('calheading').innerHTML = months[date.getMonth()] + ' ' + date.getFullYear();
    curr_month = date.getMonth();
    curr_year = date.getFullYear();
    NCmonth = date.getMonth();
    NCyear = date.getFullYear();
    curr_day = date.getDate();
    num_of_days = getNumOfDays();
    reloadCal();
}

function getNumOfDays() {
    const date = new Date();
    return new Date(curr_year, curr_month + 1, 0).getDate();
}

function addTask(name, dat) {
    if(document.getElementById(dat).innerHTML == ""){
        document.getElementById(dat).innerHTML = name;
    }
    else{
        window.alert("Space occupied!");
        /*
        var parent = $("#" + dat).parentElement;
        console.log(parent);
        var spn2 = $("<span></span>");
        spn2.addClass("event");
        spn2.text(name);
        parent.appendChild(spn2);
        */
    }
}

function reloadCal(){
    var tmp = 1;
    var body = $("<tbody></tbody>");
    body.attr('id', 'table_body');
    for(let i = 0; i < 5; i++){
        //make tr
        var week = $("<tr></tr>");
        for(let j = 0; j < 7; j++){
            if(tmp <= num_of_days){
                //make td
                var tmpday = tmp;
                var tmpmnth = (curr_month+1);
                if(tmpday <= 9){
                    tmpday = "0" + tmpday;
                }
                if(tmpmnth <= 9){
                    tmpmnth = "0" + tmpmnth;
                }
                var datestr = curr_year  + "-" + tmpmnth + "-" + tmpday;
                var day = $("<td></td>");
                var div1 = $("<div></div>");
                var div2 = $("<div></div>");
                div1.addClass("day-field-wrapper");
                div2.addClass("events-wrapper");
                var spn1 = $("<span></span>");
                var spn2 = $("<span></span>");
                spn1.addClass("day-field");
                spn2.addClass("event");
                spn2.attr("id",datestr);
                if(curr_month == NCmonth && curr_year == NCyear){
                    if(tmp == curr_day){
                        spn1.addClass("today");
                    }
                }
                spn1.text(tmp.toString());
                div1.append(spn1);
                div2.append(spn2);
                day.append(div1);
                day.append(div2);
                week.append(day);
                tmp += 1;
            }
        }
        body.append(week);
    }
    $('#month_cal').append(body);
}

//chevron right
$("#chevright").click(function () {
    if(curr_month == 11){
        curr_month = 0;
        curr_year += 1;
    }
    else {curr_month += 1;}
    num_of_days = getNumOfDays();
    document.getElementById('calheading').innerHTML = months[curr_month] + ' ' + curr_year;
    $('#table_body').remove();
    reloadCal();
});

//chevron left
$("#chevleft").click(function () {
    if(curr_month == 0){
        curr_month = 11;
        curr_year -= 1;
    }
    else{curr_month -= 1;}
    num_of_days = getNumOfDays();
    document.getElementById('calheading').innerHTML = months[curr_month] + ' ' + curr_year;
    $('#table_body').remove();
    reloadCal();
});

var taskModal = document.getElementById('Modalform');
$('#addTask').click(function (){
    var modalBodyInput1 = taskModal.querySelector('.modal-body #task-title');
    var modalBodyInput2 = taskModal.querySelector('.modal-body #task-date');
    var task = modalBodyInput1.value;
    var datee = modalBodyInput2.value;
    addTask(task, datee);
})

window.onload = origin;
