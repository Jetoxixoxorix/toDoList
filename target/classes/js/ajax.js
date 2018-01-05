/*var http_request = false;

function makeRequest(url) { //nie wiem jaki url

    http_request = false;

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
            } catch (e) {}
        }
    }

    if (!http_request) {
        alert('lel');
        return false;
    }

    http_request.onreadystatechange = function () {
        makeDone(http_request);
    };

    http_request.open('GET', url, true); //chyba post?
    http_request.send(null); // to jak post to powinny się znajdować dane
}

function makeDone(http_request) {
    try {
        if (http_request.readyState == 4) {
            if (http_request.status == 200) {
                alert('prox');
               /!*!/!* document.getElementById("task").style.color = "blue";*!/     *!/  //tutaj logika co sie stanie gdy odpowiedz pozytywna
            } else {
                alert('no elo');
            }
        }

    } catch (e) {
        alert('lul')
    }

}*/
