$(document).ready(() => {

    var platform = new H.service.Platform({
        'apikey': '6syyeIWKtBAd73kpa0tjpBZ1x3NEc0eJVXiIa7eshbI'
    });

    var defaultLayers = platform.createDefaultLayers();

    // Instantiate (and display) a map object:
    var map = new H.Map(document.getElementById('mapContainer'),
        defaultLayers.vector.normal.map, {
            zoom: 16
        });

    function busca(lat, lgn) {
        // Define a callback function to process the geocoding response:
        var onResult = function(result) {
            var locations = result.Response.View[0].Result,
                position,
                marker;
            var position = {
            		lat: lat,
            		lng: lgn
            }
                map.setCenter(position);
                marker = new H.map.Marker(position);
                map.addObject(marker);
            }
        };

        var geocoder = platform.getGeocodingService();

        geocoder.geocode(geocodingParams, onResult, function(e) {
            alert(e);
        });
});