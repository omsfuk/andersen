Waves.attach('.nav-list li')
Waves.attach('.waves-btn', ['waves-circle'])
Waves.attach('.post .post-tags li')
Waves.init()
var menuState = 'out'
var searchState = 'in'
$("#menu-btn").click(function () {
    if (menuState == 'out') {
        // $("#menu").class
        // $("#menu").attr('class', 'left')
        $("#menu").animate({
            left: '-240px'
        }, 'normal', 'easeOutCirc')
        $("#body").animate({
            left: '0px'
        })
        menuState = 'in'
    } else {
        $("#menu").animate({
            left: '0'
        }, 'normal', 'easeOutCirc')
        // $("#menu").attr('class', 'right')
        $("#body").animate({
            left: '120px'
        })
        menuState = 'out'
    }
})
$("#search-btn").click(function() {
    if (searchState == 'in') {
        $("#search").animate({
            left: '0px'
        }, 'fast')
        searchState = 'out'
    } else {
        $("#search").animate({
            left: '300px'
        })
        searchState = 'in'
    }
})
// $("#page-title").show('slow')

$("#share-btn").click(function () {
    window.location.href = '/archive'
})

$(document).ready(function () {
    // $("html").css('visibility', 'visible')
})
var topNavState = 'in'
$(document).scroll(function () {
    if (document.body.scrollTop >= 100) {
        if (topNavState == 'in') {
            $("#topNav").slideDown('fast')
            $('#returnBtn').animate({
                right: '20px'
            })
            topNavState = 'out'
        }
    } else {
        if (topNavState == 'out') {
            $("#topNav").slideUp('fast')
            $('#returnBtn').animate({
                right: '-60px'
            })
            topNavState = 'in'
        }
    }
})

document.getElementById("topNav").innerHTML = document.title

$('#returnBtn').click(function(){$('html,body').animate({scrollTop: '0px'}, 800, 'easeOutCirc');});

loadEvents = []
loadEvents.push(function () {
    $("#page-title").addClass('in')
    $(".up-down-ani-area").addClass('up')
})
document.addEventListener("DOMContentLoaded", function() {
    for (var x in loadEvents) {
        loadEvents[x]()
    }
}, false);
unloadEvents = []
unloadEvents.push(function () {
    $("#page-title").addClass('out')
    $(".up-down-ani-area").addClass('down')
})
$("a").click(function () {
    let link = this.href
    for (let x in unloadEvents) {
        unloadEvents[x]()
    }
    setTimeout(function() {
        window.location.href = link
    }, 500)
    return false;
})