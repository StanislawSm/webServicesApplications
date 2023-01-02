import {getParameterByName, setTextNode} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));
    getAndShowAuthor();
});

function getAndShowAuthor() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            let author = JSON.parse(this.responseText);
            setTextNode('name', author.name);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors/'
        + getParameterByName('author'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            getAndShowAuthor();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/authors/' + getParameterByName('author'), true);

    const request = {
        'name': document.getElementById('name').value,
        'yearOfBirth': parseInt(document.getElementById('yearOfBirth').value),
        'country': document.getElementById('country').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
    window.alert("sent");
    //location.href = "../author_list/author_list.html"
}
