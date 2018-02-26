var httpRequest = false;

$(document).ready(function () {
    $(".btn-done").on('click', function () {
        var row = $(this).closest("tr");
        var id = row.find(".id").html();
        var url = '/done/' + id;

        ajaxRequest();

        httpRequest.onreadystatechange = function () {
            makeDone(httpRequest, row)
        }
        httpRequest.open('GET', url, true);
        httpRequest.send(null);
    });
});

function ajaxRequest() {
    httpRequest = false;

    if (window.XMLHttpRequest) {
        httpRequest = new XMLHttpRequest();
        if (httpRequest.overrideMimeType) {
            httpRequest.overrideMimeType('text/xml');
        }
    } else if (window.ActiveXObject) {
        try {
            httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
            }
        }
    }

    if (!httpRequest) {
        alert('Cannot create an XMLHTTP instance');
        return false;
    }
}

function makeDone(http_request, row) {

    if (http_request.readyState == 4) {
        if (http_request.status == 200) {
            row.addClass("done");
        } else {
            alert('There was a problem with the request. ');
        }
    }
}
