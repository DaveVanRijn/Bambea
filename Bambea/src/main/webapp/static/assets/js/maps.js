var beacons = beacons;
var myArr;
var currentmark;
var infowindow;
var map;
function gmap_default_init() {

    infowindow = new google.maps.InfoWindow();
    var geocoder = new google.maps.Geocoder();
    var address = "Amsterdam";

    

    //var image = 'http://gitaarleslangedijk.nl/wp-content/themes/gitaarles/js/marker.png';

    var mapProp = {
        center: new google.maps.LatLng(52.649104, 4.829791),
        zoom: 15,
        disableDefaultUI: false,
        zoomControl: true,
        scrollwheel: true,
        draggable: true,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    map = new google.maps.Map(document.getElementById("gmap-default"), mapProp);
    var image;
    


    

    

    geocoder.geocode({
        "address": address
    }, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            map.setCenter(results[0].geometry.location);

            //infowindow.open(map, marker);
        } else {
            console.log("Er is iets fout gegaan: " + status);
        }
    });

    var xmlhttp = new XMLHttpRequest();
    var url = "/Bambea/api/beacons/all";

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            myArr = JSON.parse(xmlhttp.responseText);
            addBeacons(myArr);
        }
    }

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    function addBeacons(beacons) {

        var i;

        for (i = 0; i < beacons.length; i++) {
            marker = new google.maps.Marker({
                position: new google.maps.LatLng(beacons[i]["lat"], beacons[i]["lng"]),
                map: map,
                draggable: false,
                inDB: true
                        //icon: image
            });

            // Open infowidow
            bindInfoWindow(marker, map, infowindow, beacons[i]["majid"], beacons[i]["minid"], beacons[i]["title"], beacons[i]["id"]);
        }
    }
    
    if (typeof addEdits == 'function') { addEdits(); }
    if (typeof partnerAccess == 'function') { partnerAccess(); }
    
}


function gmap_red_init() {
    var e, a = new google.maps.LatLng(40.674389, -73.9455),
            t = "custom_style",
            p = [{
                    stylers: [{
                            hue: "#890000"
                        }, {
                            visibility: "simplified"
                        }, {
                            gamma: .5
                        }, {
                            weight: .5
                        }]
                }, {
                    elementType: "labels",
                    stylers: [{
                            visibility: "off"
                        }]
                }, {
                    featureType: "water",
                    stylers: [{
                            color: "#890000"
                        }]
                }],
            o = {
                zoom: 12,
                center: a,
                mapTypeControlOptions: {
                    mapTypeIds: [google.maps.MapTypeId.ROADMAP, t]
                },
                mapTypeId: t
            };
    e = new google.maps.Map(document.getElementById("gmap-red"), o);
    var n = {
        name: "Custom Style"
    },
    m = new google.maps.StyledMapType(p, n);
    e.mapTypes.set(t, m)
}

function gmap_weather_init() {
    var e = {
        zoom: 6,
        center: new google.maps.LatLng(49.265984, -123.127491)
    },
    a = new google.maps.Map(document.getElementById("gmap-weather"), e),
            t = new google.maps.weather.WeatherLayer({
                temperatureUnits: google.maps.weather.TemperatureUnit.CELSIUS
            });
    t.setMap(a);
    var p = new google.maps.weather.CloudLayer;
    p.setMap(a)
}

function gmap_initialize() {
    gmap_default_init(), gmap_red_init(), gmap_weather_init()
}

function loadScript() {
    var api_key = 'AIzaSyBByp3qqX57iZJUzEPvgHgT-g1Tvv1HpIk';
    var e = document.createElement("script");
    e.type = "text/javascript", e.src = "https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=weather&sensor=false&callback=gmap_initialize", document.body.appendChild(e)
    //e.type = "text/javascript", e.src = "https://maps.googleapis.com/maps/api/js?key="+ api_key +"&amp;sensor=false&amp;callback=gmap_initialize", document.body.appendChild(e);
}

$(function () {
    $("#gmap-default").length && loadScript();
});