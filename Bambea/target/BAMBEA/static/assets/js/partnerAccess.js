var message = '<i class="fa fa-ban"></i> ' + $("#mapLang").attr("editAccessDenied");
function bindInfoWindow(marker, map, infowindow, majid, minid, title, id) {
    google.maps.event.addListener(marker, 'click', function (event) {
        currentmark = this;
        infowindow.setContent(message );
        infowindow.open(map, marker);
    });
}
function partnerAccess(){
    google.maps.event.addListener(map, 'click', function (event) {  
            infowindow.setContent(message);
            infowindow.setPosition(event.latLng);
            infowindow.open(map);
        });
    }
