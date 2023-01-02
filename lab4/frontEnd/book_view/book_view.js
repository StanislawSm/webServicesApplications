import {
    getParameterByName,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    getAndShowBookDetails();;
});

function getAndShowBookDetails() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let book = JSON.parse(this.responseText);
            setTextNode('isbn', book.isbn);
            setTextNode('title', book.title);
            setTextNode('author', getParameterByName('author'));
            setTextNode('yearOfPublication', book.yearOfPublication);

        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors/' + getParameterByName('author') + '/books/'
        + getParameterByName('book'), true);
    xhttp.send();
}

