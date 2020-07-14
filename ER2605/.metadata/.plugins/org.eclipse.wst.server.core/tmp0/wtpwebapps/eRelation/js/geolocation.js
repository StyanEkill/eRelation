$(document).ready(() => {
    var inp = document.querySelectorAll(".endereco input");
    var bt = document.getElementById("bt");
    bt.addEventListener("click", busca);

    var platform = new H.service.Platform({
        'apikey': '6syyeIWKtBAd73kpa0tjpBZ1x3NEc0eJVXiIa7eshbI'
    });

    var defaultLayers = platform.createDefaultLayers();

    // Instantiate (and display) a map object:
    var map = new H.Map(document.getElementById('mapContainer'),
        defaultLayers.vector.normal.map, {
            zoom: 16
        });

    function busca() {
        var index = document.querySelector(".endereco select").selectedIndex;
        var opcoes = document.querySelector(".endereco select").options;

        var geocodingParams = {
            searchText: inp[0].value + ", " + inp[2].value + " - " +
                inp[1].value + ", " + inp[3].value + " - " + opcoes[index].text
        };
        // Define a callback function to process the geocoding response:
        var onResult = function(result) {
            var locations = result.Response.View[0].Result,
                position,
                marker;
            // Add a marker for each location found
            for (i = 0; i < locations.length; i++) {
                position = {
                    lat: locations[i].Location.DisplayPosition.Latitude,
                    lng: locations[i].Location.DisplayPosition.Longitude
                };

                document.querySelector("input[name='lat']").value = position.lat;
                document.querySelector("input[name='lng']").value = position.lng;
                map.setCenter(position);
                marker = new H.map.Marker(position);
                map.addObject(marker);
            }
        };

        var geocoder = platform.getGeocodingService();

        geocoder.geocode(geocodingParams, onResult, function(e) {
            alert(e);
        });
    }
});