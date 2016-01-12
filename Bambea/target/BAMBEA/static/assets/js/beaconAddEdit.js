var beaconAddStart =    "<form accept-charset='UTF-8' id='beaconForm' method='POST' action='beacon/add' class='form-horizontal'>";
    var beaconEditStart =   "<form accept-charset='UTF-8' id='beaconEditForm' method='POST' action='/Bambea/beacon/edit' class='form-horizontal'>" +
                                "<input name='id' id='id' type='hidden'/>";
    var beaconFormContent = "<table>" +
                                "<tr>" +
                                    "<td>" +
                                        "<label control-label style='padding: 3px'>" + $("#mapLang").attr("majid") + "</label>" +
                                    "</td>" +
                                    "<td>" +
                                        "<input name='majid' id='majid' type='number' placeholder='Major ID' class='form-control input-sm' required> " +
                                    "</td>" +
                                "</tr>" +
                                "<tr>" +
                                    "<td>" +
                                        "<label control-label style='padding: 3px'>" + $("#mapLang").attr("minid") + "</label>" +
                                    "</td>" +
                                    "<td>" +
                                        "<input name='minid' id='minid' type='number' placeholder='Minor ID' class='form-control input-sm' required> " +
                                    "</td>" +
                                "</tr>" +
                                "<tr>" +
                                    "<td>" +
                                        "<label control-label style='padding: 3px'>" + $("#mapLang").attr("name") + "</label>" +
                                    "</td>" +
                                    "<td>" +
                                        "<input name='title' id='title' type='text' placeholder='Beacon naam' class='form-control input-sm' required> " +
                                    "</td>" +
                                "</tr>" +
                                "<tr>" +
                                    "<td>" +
                                        "<label control-label style='padding: 3px'>" + $("#mapLang").attr("lat") + "</label>" +
                                    "</td>" +
                                    "<td>" +
                                        "<input name='lat' type='text' class='form-control input-sm' id='lat' required >" +
                                    "</td>" +
                                "</tr>" +
                                "<tr>" +
                                    "<td>" +
                                        "<label control-label style='padding: 3px'>" + $("#mapLang").attr("lng") + "</label>" +
                                    "</td>" +
                                    "<td>" +
                                        "<input name='lng' type='text' class='form-control input-sm' id='lng' required> " +
                                    "</td>" +
                                "</tr>" +
                            "</table>" +
                            "<div class='col-md-4' style='padding: 3px'>" +
                                "<button type='submit' name='submit' class='btn btn-primary btn-sm'><i class='fa fa-save'></i>" + $("#mapLang").attr("save") + "</button>" +
                            "</div>" +                           
                        "</form>";
    var beaconEditEnd = "<form accept-charset='UTF-8' id='beaconDeleteForm' method='POST' action='/Bambea/beacon/delete' class='form-horizontal'>" + 
                            "<div class='col-md-4' style='padding: 3px'>" +
                                "<a id ='deleteButton' onclick=\"return confirm('" + $("#mapLang").attr("deleteConfirm") + "')\"><button type='submit' class='btn btn-danger btn-sm'><i class='fa fa-trash'></i>" + $("#mapLang").attr("delete") + "</button></a>" +
                            "</div>" +
                        "</form>";

var beaconAddForm = beaconAddStart + beaconFormContent;
var beaconEditForm = beaconEditStart + beaconFormContent + beaconEditEnd;

function addEdits(){
    
    google.maps.event.addListener(infowindow, 'closeclick', function (event) {
        currentmark.setDraggable(false);
        if (currentmark.inDB !== true) {
            currentmark.setMap(null);
        }
    });
    
    google.maps.event.addListener(map, 'click', function (event) {
        addMarker(event.latLng, map);
    });
    
    function addMarker(location, map) {
        removeOpenBeacon();
        //Adds the marker to the clicked location
        var marker = new google.maps.Marker({
            position: location,
            draggable: true,
            map: map
        });
        currentmark = marker;
        infowindow.setContent(beaconAddForm);
        infowindow.open(map, marker);

        document.getElementById("lat").value = location.lat();
        document.getElementById("lng").value = location.lng();

        google.maps.event.addListener(marker, 'drag', function (event) {
            document.getElementById("lat").value = event.latLng.lat();
            document.getElementById("lng").value = event.latLng.lng();
        });
    }
   
    
        $(document).on('submit', '#beaconForm',
            function (event) {
                var token = $('#csrfToken').val();
                var header = $('#csrfHeader').val();
                var title = $('#title').val();
                var majid = $('#majid').val();
                var minid = $('#minid').val();
                var lat = $('#lat').val();
                var lng = $('#lng').val();
                var json = {"title": title, "lat": lat, "lng": lng, "majid": majid, "minid": minid};

                $.ajax({
                    url: $("#beaconForm").attr("action"),
                    contentType: 'application/json',
                    dataType: "json",
                    data: JSON.stringify(json),
                    type: "POST",
                    beforeSend: function(xhr){
                        xhr.setRequestHeader(header, token);
                        xhr.setRequestHeader("Accept", "text/plain");
                    },                   
                    success: function(el){
                        infowindow.close();
                        currentmark.inDB = true;
                        currentmark.draggable = false;
                        bindInfoWindow(currentmark, map, infowindow, majid, minid, title, el);
                    },
                    error: function (el) {
                        alert("Could not edit beacon. Please check your internet settings and try again.");                       
                    }
                });
                event.preventDefault();
            });
            
        $(document).on('submit', '#beaconEditForm',
            function (event) {
                var token = $('#csrfToken').val();
                var header = $('#csrfHeader').val();
                var id = $('#id').val();
                var majid = $('#majid').val();
                var minid = $('#minid').val();
                var title = $('#title').val();
                var lat = $('#lat').val();
                var lng = $('#lng').val();
                var json = {"id": id, "title": title, "lat": lat, "lng": lng, "majid": majid, "minid": minid};

                $.ajax({
                    url: $("#beaconEditForm").attr("action"),
                    contentType: 'application/json',                    
                    data: JSON.stringify(json),
                    type: "POST",
                    beforeSend: function(xhr){
                        xhr.setRequestHeader(header, token);
                        xhr.setRequestHeader("Accept", "text/plain");
                    },    
                    success: function(el){
                        infowindow.close();
                        currentmark.draggable = false;
                        bindInfoWindow(currentmark, map, infowindow, majid, minid, title, id);
                    },
                    error: function (el) {
                        alert("Could not edit beacon. Please check your internet settings and try again.");                       
                    }
                });
                event.preventDefault();
            });
            
            $(document).on('submit', '#beaconDeleteForm',
            function (event) {
                var token = $('#csrfToken').val();
                var header = $('#csrfHeader').val();
                var id = $('#id').val();
                
                var json = {"id": id};

                $.ajax({
                    url: $("#beaconDeleteForm").attr("action"),
                    contentType: 'application/json',                    
                    data: JSON.stringify(json),
                    type: "POST",
                    beforeSend: function(xhr){
                        xhr.setRequestHeader(header, token);
                        xhr.setRequestHeader("Accept", "text/plain");
                    },    
                    success: function(el){
                        infowindow.close();
                        currentmark.setMap(null);
                    },
                    error: function (el) {
                        alert("Could not delete beacon. Please check your internet settings and try again.");                       
                    }
                });
                event.preventDefault();
            });
        }
        
        function bindInfoWindow(marker, map, infowindow, majid, minid, title, id) {
        google.maps.event.addListener(marker, 'click', function (event) {

            removeOpenBeacon();
            currentmark = this;
            currentmark.setDraggable(true);
            infowindow.setContent(beaconEditForm);
            infowindow.open(map, marker);

            document.getElementById("id").value = id;
            document.getElementById("majid").value = majid;
            document.getElementById("minid").value = minid;
            document.getElementById("title").value = title;
            document.getElementById("lat").value = event.latLng.lat();
            document.getElementById("lng").value = event.latLng.lng();

            var a = document.getElementById("deleteButton");
            a.href = "/Bambea/beacon/delete/" + id;
            
        });

        google.maps.event.addListener(marker, 'drag', function (event) {
            document.getElementById("lat").value = event.latLng.lat();
            document.getElementById("lng").value = event.latLng.lng();
        });
    }
    
        function removeOpenBeacon() {
        var mar = infowindow.anchor;
        //Sets the closed beacon to non-draggable
        if (currentmark !== undefined) {
            currentmark.setDraggable(false);
        }

        //Removes a beacon that's not saved to the DB if another one is selected
        if (document.getElementById('deleteButton') === null && mar !== undefined && mar !== null) {
            mar.setMap(null);
        }
    }