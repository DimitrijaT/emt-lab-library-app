import React from "react";
import CategoryTerm from "../CategoryTerm/categoryTerm";

const Categories = (props) => {
  return (
    <div className={"container mm-4 mt-5"}>
      <div className={"row"}>
        <div className={"row"}>
          <table className={"table table-striped"}>
            <thead>
              <tr>
                <th scope={"col"}>Category</th>
              </tr>
            </thead>
            <tbody>
              {props.categories.map((term) => {
                return <CategoryTerm term={term}></CategoryTerm>;
              })}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default Categories;
