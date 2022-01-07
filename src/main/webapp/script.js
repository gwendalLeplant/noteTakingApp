function loadNotes() {
    var httprequest;
    // Tous les navigateurs sauf IE
    if (window.XMLHttpRequest) {
        httprequest = new XMLHttpRequest();
    } else {
        // Pour IE
        httprequest = new ActiveXObject("Msxm12.XMLHTTP");
    }

    httprequest.onreadystatechange = function() {
        if (httprequest.readyState == 4) {
            if (httprequest.status == 200) {
                var contenuJSON = JSON.parse(httprequest.responseText);
                document.querySelector("#divDisplayNotes").innerHTML = "";
                for (let i = 0; i < contenuJSON.length; i++) {
                    let newNote = document.importNode(
                        document.querySelector("#templateDisplayNote").content,
                        true
                    );
                    newNote.querySelector("#noteContent").innerHTML = contenuJSON[i].content;
                    newNote.querySelector("#creationDate").innerHTML = new Date(contenuJSON[i].creationDate).toLocaleString('fr-FR', { timeZone: 'GMT' });
                    newNote.querySelector("div[name='rowDateBtn']").setAttribute("id", contenuJSON[i].id);
                    document.querySelector("#divDisplayNotes").appendChild(newNote);
                }
            } else {
                alert("Une erreur s'est produite : " + httprequest.responseText);
            }
        }
    };

    httprequest.open("GET", "http://localhost:8080/noteApp/rest/notes", true);
    httprequest.send();

}

function prepareHttpRequest() {
    var httprequest;
    // Tous les navigateurs sauf IE
    if (window.XMLHttpRequest) {
        httprequest = new XMLHttpRequest();
    } else {
        // Pour IE
        httprequest = new ActiveXObject("Msxm12.XMLHTTP");
    }

    httprequest.onreadystatechange = function() {
        if (httprequest.readyState == 4) {
            if (httprequest.status == 200) {
                loadNotes();
            } else {
                alert("Une erreur s'est produite : " + httprequest.responseText);
            }
        }
    };
    return httprequest;
}

function sendNote() {
    let httprequest = prepareHttpRequest();
    let dataToSend = document.getElementById("newNoteContent").value;
    let form = "content=" + encodeURIComponent(dataToSend);
    httprequest.open("POST", "http://localhost:8080/noteApp/rest/notes", true);
    httprequest.setRequestHeader("Accept", "application/json");
    httprequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    httprequest.send(form);
    dataToSend.value = "";

}

function getIdNoteFromElement(e) {
    return e.parentElement.parentElement.getAttribute("id");
}

function updateNote(e) {
    let httprequest = prepareHttpRequest();
    let id = getIdNoteFromElement(e);
    let dataToSend = document.getElementById(id).parentElement.querySelector("#noteContent").value;
    let form = "content=" + encodeURIComponent(dataToSend);
    httprequest.open("PUT", "http://localhost:8080/noteApp/rest/notes/" + id, true);
    httprequest.setRequestHeader("Accept", "application/json");
    httprequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    httprequest.send(form);
}

function deleteNote(e) {
    let httprequest = prepareHttpRequest();
    let id = getIdNoteFromElement(e);
    httprequest.open("DELETE", "http://localhost:8080/noteApp/rest/notes/" + id, true);
    httprequest.setRequestHeader("Accept", "application/json");
    httprequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    httprequest.send();
}

loadNotes();