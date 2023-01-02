import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    getAndShowAuthor();
    getAndShowBooks();
});

function getAndShowBooks() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayBooks(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors/' + getParameterByName('author') + '/books', true);
    xhttp.send();
}

function displayBooks(books) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    books.books.forEach(book => {
        tableBody.appendChild(createTableRow(book));
    })
    let div = document.getElementById("add_book");
    const a = document.createElement('a');
    a.appendChild(document.createTextNode("add a book"));
    a.href = '../book_add/book_add.html?author=' + getParameterByName('author');
    div.appendChild(a);
}

function createTableRow(book) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(book.title));
    tr.appendChild(createLinkCell('edit', '../book_edit/book_edit.html?author='
        + getParameterByName('author') + '&book=' + book.isbn));
    tr.appendChild(createButtonCell('delete', () => deleteBook(book)));
    return tr;
}

function deleteBook(book) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            getAndShowBooks();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/authors/' + getParameterByName('author')
        + '/books/' + book.isbn, true);
    xhttp.send();
}

function getAndShowAuthor() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayAuthor(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors/' + getParameterByName('author'), true);
    xhttp.send();
}

function displayAuthor(author) {
    setTextNode('authorName', author.name);
    setTextNode('yearOfBirth', author.yearOfBirth);
    setTextNode('country', author.country);
}
