import {getParameterByName, setTextNode} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));
});

function updateInfoAction(event) {
    event.preventDefault();
    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/authors');

    const request = {
        'name': document.getElementById('name').value,
        'yearOfBirth': parseInt(document.getElementById('yearOfBirth').value),
        'country': document.getElementById('country').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
    window.alert("sent")
    //location.href = "../author_list/author_list.html";
}
