import {getParameterByName, setTextNode} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    getAndShowBook();
});

function getAndShowBook() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            let book = JSON.parse(this.responseText);
            setTextNode('isbn', book.isbn);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors/' + getParameterByName('author') + '/books/'
        + getParameterByName('book'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            getAndShowBook();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/authors/' + getParameterByName('author') + '/books/'
        + getParameterByName('book'), true);

    const request = {
        'title': document.getElementById('title').value,
        'yearOfPublication': document.getElementById('yearOfPublication').value,
        'isbn': parseInt(document.getElementById('isbn').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
    window.alert("sent");
    //location.href = "../author_view/author_view.html" + "?author=" + getParameterByName('author');
}
