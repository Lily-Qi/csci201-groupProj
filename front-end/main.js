let API_KEY = "af55e56cd5e247a8bd4588bdd8973928";
let curr_city;

//create local storage
function createItem() {
	localStorage.setItem('recent_city', curr_city); 
} 

//local storage manip
$("#cities").on('change', function() {
    if ($(this).val() == "Los Angeles"){
        curr_city = "Los Angeles";
        localStorage.setItem('recent_city', curr_city);
        origin();
    }
    
});

//api call
function origin(){
    if(localStorage.getItem('recent_city') == null){
        curr_city = "Los Angeles";
        createItem();
        $("#cities").val(curr_city);
    }
    else{
        curr_city = localStorage.getItem('recent_city');
        $("#cities").val(curr_city);
    }
    $.ajax({
        method: "GET",
        url: "https://api.weatherbit.io/v2.0/current?",
        data: {
            key: API_KEY,
            city: curr_city,
            country: "US",
            units: "I"
        }
    })
    .done(function(results){
        console.log("API RESULTS");
        console.log(results);
        displayResults(results);
    })
    .fail(function(results){
        console.log("ERROR");
    });
}

//display weather text
function displayResults(resultsString) {
    // get JSON object
    let resultsJS = resultsString;
    //get the data
    let info = resultsJS.data[0];
    //get the weather object within data
    let desc = info.weather;
    //update top text
    $("#temp").text(info.temp);
    $("#feels").text(info.app_temp);
    $("#desc").text(desc.description);
}

//strike text function
$("ul").on("click", 'li', function(e) {
    if((e.target.nodeName == "SPAN")){
        if( $(this).css('text-decoration-line') != 'none' )  { 
            /*success*/
            $(this).css("text-decoration","");
            $(this).css("color","#3C81A6");
        } 
        else if( $(this).css('text-decoration-line') == 'none' ) { 
            /*does not have*/
            $(this).css("text-decoration","line-through"); 
            $(this).css("color","#ffffff");
        }
    }
});

//box delete - NOT DONE
$("ul").on("click",'li',function(e) {
    var $span = e.target;
    if(e.target.nodeName == "I"){
        var $li = $span.closest('li');
    $($li).fadeOut(300, function() {
        $($li).remove();
    });
    e.stopPropagation();
    }
    
});

//add
$(document).on("keypress", "input",  function(e){
    if(e.which == 13){
        var icon = $("<i></i>");
        icon.addClass("fa-solid");
        icon.addClass("fa-square-check");
        var boxspn = $("<span></span>");
        boxspn.append(icon);
        boxspn.addClass("innersquare");
        var inputVal = $(this).val();
        var temp = " ";
        inputVal = temp.concat(inputVal);
        var spn = $("<span></span>").text(inputVal);
        spn.addClass("item");
        var li = $("<li></li>").append(boxspn);
        li.append(spn);
        $(".items").append(li);
        $(this).val('');
        e.stopPropagation();
    }
});

//plus icon
$("#plus").click(function() {
    $("input").slideToggle();
});

window.onload = origin;