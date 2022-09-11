// Variable declaration
var arrivalsId = "arrivals";
var searchHistoryId = "searchHistory";

var getArrivalsPath = "arrivals/";
var searchesPath = "history/";
var resultDiv = "arrivalResult";
var resultTable = "arrivalResultTable"
var searchResultDiv = "searchResult";
var searchResultTable = "searchResultTable";

/* ------------ On ready functions -----------*/
$(document).ready(function() {

    /* --- Functions declaration --- */
    getArrivals(arrivalsId, getArrivalsPath, resultDiv, resultTable);
    searches(searchHistoryId, searchesPath, searchResultDiv, searchResultTable);


    // Get and return results of bus arrivals on-lick
    function getArrivals(id, url, resultDiv, resultTable) {
        $("#"+id).click(function(event){
            // Start AJAX process
            $.ajax({
                url : "arrivals", 	// the endpoint
                type : 'GET', 	        // http method
                dataType : 'json', 	    // expected data back from the server

                // handle a successful response
                success : function(json) {
						
                    if (!json["ERROR"]) {
                        var content = extractArrivalData(json);
                        document.getElementById(resultDiv).hidden  = false;
                        document.getElementById(resultTable).innerHTML = content;

                    } else {
                        document.getElementById(resultDiv).hidden  = false;
                        document.getElementById(resultTable).innerHTML = json["ERROR"];
                    }
                    /**/

                },
                // handle a non-successful response
                error : function(xhr,errmsg,err) {
					console.log(err);
					console.log(errmsg);
                    alert("ERROR: " + errmsg);
                }
            });
        });
    }


    // Return historical bus arrivals searches by user (on same IP address)
    function searches(id, url, resultDiv, resultTable) {
        $("#"+id).click(function(event){
			data = {
                page: document.getElementById("page").value,
                size: document.getElementById("size").value
            };
            // Start AJAX process
            $.ajax({
                url : url, 	// the endpoint
                type : 'GET', 	        // http method
               	data : data, 		    // data sent with the get request
                dataType : 'json', 	    // expected data back from the server

                // handle a successful response
                success : function(json) {
		
                    if (!json["ERROR"]) {
                        var content = extractArrivalData(json);
                        //console.log(content);
                        document.getElementById(resultDiv).hidden  = false;
                        document.getElementById(resultTable).innerHTML = content;

                    } else {
                        document.getElementById(resultDiv).hidden  = false;
                        document.getElementById(resultTable).innerHTML = json["ERROR"];
                    }
                },

                // handle a non-successful response
                error : function(xhr,errmsg,err) {
                    alert("ERROR: " + errmsg);
                }
            });
        });
    }


    // Extract arrival details from json object
    function extractArrivalData(jsonObject) {
        var content = "";
        
        for (let i=0; i < jsonObject.length; i++) {
            content = content +
                      "<tr>" +
                      "<td>" + jsonObject[i]["stationName"] + "</td>" +
                      "<td>" + jsonObject[i]["destinationName"] + "</td>" +
                      "<td>" + jsonObject[i]["towards"] + "</td>" +
                      "<td>" + jsonObject[i]["expectedArrival"] + "</td>" +
                      "<td>" + jsonObject[i]["timeToLive"] + "</td>" +
                      "<td>" + jsonObject[i]["timeCreated"] + "</td>" +
                      "</tr>";
        }

        return content;
    }
    
});