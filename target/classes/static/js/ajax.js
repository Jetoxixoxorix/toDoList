var http_request = false;

$(document).ready(function () {
    $(".submit").click(function () {
        var row = $(this).closest("tr");
        var id = row.find(".id").html();

        http_request = false;

        var url = '/done/' + id;

        if (window.XMLHttpRequest) {
            http_request = new XMLHttpRequest();
            if (http_request.overrideMimeType) {
                http_request.overrideMimeType('text/xml');
            }
        } else if (window.ActiveXObject) {
            try {
                http_request = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                try {
                    http_request = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e) {
                }
            }
        }

        if (!http_request) {
            alert('Giving up :( Cannot create an XMLHTTP instance');
            return false;
        }

        http_request.onreadystatechange = function () {
            makeDone(http_request, row)
        }
        http_request.open('GET', url, true);
        http_request.send(null);
    });
});


function makeDone(http_request, row) {

    if (http_request.readyState == 4) {
        if (http_request.status == 200) {
            row.toggleClass("done");
        } else {
            alert('There was a problem with the request. ');
        }
    }

}