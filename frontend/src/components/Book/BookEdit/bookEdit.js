import React from "react";
import { useNavigate } from "react-router-dom";

const BookEdit = (props) => {
  const navigate = useNavigate();
  const [formData, updateFormData] = React.useState({
    name: "",
    category: 0,
    authorId: 0,
    availableCopies: 0,
  });

  const handleChange = (e) => {
    updateFormData({
      ...formData,
      [e.target.name]: e.target.value.trim(),
    });
  };

  const onFormSubmit = (e) => {
    e.preventDefault();
    const name = formData.name !== "" ? formData.name : props.book.name;
    const category =
      formData.category !== "" ? formData.category : props.book.category;
    const authorId =
      formData.authorId !== "" ? formData.authorId : props.book.author.id;
    const availableCopies =
      formData.availableCopies !== ""
        ? formData.availableCopies
        : props.book.availableCopies;

    props.onEditBook(props.book.id, name, category, authorId, availableCopies);

    navigate("/books");
  };

  return (
    <div className="row mt-5">
      <div className="col-md-5">
        <form onSubmit={onFormSubmit}>
          <div className="form-group">
            <label htmlFor="name">Product name</label>
            <input
              type="text"
              className="form-control"
              id="name"
              name="name"
              required
              placeholder={props.book.name}
              onChange={handleChange}
            />
          </div>
          <div className="form-group">
            <label>Author</label>
            <select
              name="authorId"
              id="authorId"
              className="form-control"
              onChange={handleChange}
            >
              {props.authors.map((term) => {
                if (
                  props.book.author !== undefined &&
                  props.book.author.id === term.id
                )
                  return (
                    <option selected={props.book.author.id} value={term.id}>
                      {term.name}
                    </option>
                  );
                else return <option value={term.id}>{term.name}</option>;
              })}
            </select>
          </div>
          <div className="form-group">
            <label>Category</label>
            <select
              name="category"
              className="form-control"
              onChange={handleChange}
            >
              {props.categories.map((term) => (
                <option key={term} value={term}>
                  {term}
                </option>
              ))}
            </select>
          </div>
          <button id="submit" type="submit" className="btn btn-primary">
            Submit
          </button>
        </form>
      </div>
    </div>
  );
};

export default BookEdit;
