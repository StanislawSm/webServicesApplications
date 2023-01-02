import {getParameterByName, setTextNode} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));
    setTextNode('author', getParameterByName('author'));
});

function updateInfoAction(event) {
    event.preventDefault();
    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/authors/' + getParameterByName('author') + '/books');

    const request = {
        'title': document.getElementById('title').value,
        'yearOfPublication': parseInt(document.getElementById('yearOfPublication').value),
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
    window.alert("sent")
    //location.href = "../author_list/author_list.html";
}
