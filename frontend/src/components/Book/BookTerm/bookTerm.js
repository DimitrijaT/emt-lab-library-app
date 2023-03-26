import React from "react";
import { Link } from "react-router-dom";

const BookTerm = (props) => {
  return (
    <tr>
      <td>{props.term.name}</td>
      <td>{props.term.category}</td>
      <td>{props.term.author.name}</td>
      <td>{props.term.availableCopies}</td>
      {/* <td>{props.term.id}</td> */}
      <td>
        <div
          className={"text-right btn-group"}
          role={"group"}
          aria-label={"Basic example"}
        >
          <Link
            title={"Delete"}
            className={"btn btn-secondary btn-danger btn-sm"}
            onClick={() => props.onDelete(props.term.id)}
          >
            Delete
          </Link>
          <Link
            className={"btn btn-secondary btn-info btn-sm"}
            onClick={() => props.onEdit(props.term.id)}
            to={`/books/edit/${props.term.id}`}
          >
            Edit
          </Link>
          <Link
            title="reduceCopies"
            className={"btn btn-secondary btn-success btn-sm"}
            onClick={() => props.onReduceCopies(props.term.id)}
          >
            Reduce Copies
          </Link>
        </div>
      </td>
    </tr>
  );
};

export default BookTerm;
