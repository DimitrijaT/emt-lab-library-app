import React, { Component } from "react";
import ReactPaginate from "react-paginate";
import { Link } from "react-router-dom";
import BookTerm from "../BookTerm/bookTerm";

class Books extends Component {
  constructor(props) {
    super(props);
    this.state = {
      page: 0,
      size: 5,
    };
  }
  render() {
    const offset = this.state.size * this.state.page 
    const nextPageOffset = offset + this.state.size 
    const pageCount = Math.ceil(this.props.books.length / this.state.size)
    const books = this.getBooksPage(offset, nextPageOffset)
    return (
      <div className="container my-4">
        <table className="table table-striped">
          <thead>
            <tr>
              <th scope="col">Book Name</th>
              <th scope="col">Category</th>
              <th scope="col">Author</th>
              <th scope="col">Available Copies</th>
              <th scope="col" className="text-right">
                Actions
              </th>
            </tr>
          </thead>
          <tbody>{books}</tbody>
        </table>
        <div className="row">
          <div className="col-sm-12 col-md-12">
            <Link className="btn btn-block btn-dark" to="/books/add">
              Add New Book
            </Link>
          </div>

          <ReactPaginate
            previousLabel={<i className="fas fa-chevron-left">back</i>}
            nextLabel={<i className="fas fa-chevron-right">next</i>}
            breakLabel={
              <a href="/#" className="page-link">
                ...
              </a>
            }
            breakClassName={"break-me"}
            pageClassName={"ml-1 page-item"}
            pageCount={pageCount}
            marginPagesDisplayed={2}
            pageRangeDisplayed={5}
            onPageChange={this.handlePageClick}
            containerClassName={"pagination m-4 justify-content-center"}
            activeClassName={"active"}
            pageLinkClassName={"page-link"}
            previousClassName={"page-item"}
            nextClassName={"page-item"}
            previousLinkClassName={"page-link"}
            nextLinkClassName={"page-link"}
          />
        </div>
      </div>
    );
  }
  handlePageClick = (data) => {
    let selected = data.selected;
    this.setState({
      page: selected,
    });
  };

  getBooksPage = (offset, nextPageOffset) => {
    return this.props.books
      .map((term) => {
        return (
          <BookTerm
            term={term}
            onDelete={this.props.onDelete}
            onEdit={this.props.onEdit}
            onReduceCopies={this.props.onReduceCopies}
          />
        );
      })
      .filter((product, index) => {
        return index >= offset && index < nextPageOffset;
      });
  };
}

export default Books;
