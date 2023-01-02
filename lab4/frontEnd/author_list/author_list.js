import {
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    getParameterByName
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    getAndShowAuthors();
});

function getAndShowAuthors() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            showAuthors(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors', true);
    xhttp.send();
}

function showAuthors(authors) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    authors.authors.forEach(author => {
        tableBody.appendChild(createTableRow(author));
    })
}

function createTableRow(author) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(author.name));
    tr.appendChild(createLinkCell('view', '../author_view/author_view.html?author=' + author.name));
    tr.appendChild(createButtonCell('delete', () => deleteAuthor(author)));
    tr.appendChild(createLinkCell('edit', '../author_edit/author_edit.html?author=' + author.name));
    return tr;
}

function deleteAuthor(author) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            getAndShowAuthors();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/authors/' + author.name, true);
    xhttp.send();
}

function editAuthor(author){
    location.href = "../author_edit/author"
}
