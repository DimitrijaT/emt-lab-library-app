import "./App.css";
import React, { Component } from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";
import Header from "../Header/header";
import LibraryRepository from "../../repository/libraryRepository";
import Books from "../Book/BookList/books";
import BookAdd from "../Book/BookAdd/bookAdd";
import BookEdit from "../Book/BookEdit/bookEdit";
import Categories from "../Category/CategoryList/categories";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      books: [],
      categories: [],
      authors: [],
      contries: [],
      selectedBook: [],
    };
  }

  render() {
    return (
      <Router>
        <Header />
        <main>
          <div className="container">
            <Routes>
              <Route
                path={"/books"}
                element={
                  <Books
                    books={this.state.books}
                    onDelete={this.deleteBook}
                    onEdit={this.getBook}
                    onReduceCopies={this.reduceCopies}
                  />
                }
              />
              <Route
                path={"/categories"}
                element={<Categories categories={this.state.categories} />}
              />
              <Route
                path={"/books/add"}
                element={
                  <BookAdd
                    books={this.state.books}
                    authors={this.state.authors}
                    categories={this.state.categories}
                    onAddBook={this.addBook}
                  />
                }
              />
              <Route
                path={"/books/edit/:id"}
                element={
                  <BookEdit
                    books={this.state.books}
                    authors={this.state.authors}
                    categories={this.state.categories}
                    book={this.state.selectedBook}
                    onEditBook={this.editBook}
                  />
                }
              />
              <Route
                path={"*"}
                element={<Navigate to={"/books"} replace={true} />}
              />
            </Routes>
          </div>
        </main>
      </Router>
    );
  }

  loadBooks = () => {
    LibraryRepository.fetchBooks().then((data) => {
      this.setState({
        books: data.data,
      });
    });
  };

  loadCategories = () => {
    LibraryRepository.fetchCategories().then((data) => {
      this.setState({
        categories: data.data,
      });
    });
  };

  loadAuthors = () => {
    LibraryRepository.fetchAuthors().then((data) => {
      this.setState({
        authors: data.data,
      });
    });
  };

  deleteBook = (id) => {
    LibraryRepository.deleteBook(id).then(() => {
      this.loadBooks();
    });
  };

  addBook = (name, category, authorId, availableCopies) => {
    LibraryRepository.addBook(name, category, authorId, availableCopies).then(
      () => {
        this.loadBooks();
      }
    );
  };

  getBook = (id) => {
    LibraryRepository.getBook(id).then((data) => {
      this.setState({
        selectedBook: data.data,
      });
    });
  };

  reduceCopies = (id) => {
    LibraryRepository.markAsTaken(id).then(() => {
      this.loadBooks();
    });
  };

  editBook = (id, name, category, authorId, availableCopies) => {
    LibraryRepository.editBook(
      id,
      name,
      category,
      authorId,
      availableCopies
    ).then(() => this.loadBooks());
  };

  componentDidMount() {
    this.loadBooks();
    this.loadAuthors();
    this.loadCategories();
  }
}

export default App;
