import React from "react";
import { Link } from "react-router-dom";
import { AuthContext } from "../App";

export default function NavBar() {
  const { dispatch } = React.useContext(AuthContext);
  const handleLogout = () => {
    dispatch({
      type: "LOGOUT",
    });
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/">
          CMS
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarTogglerDemo02"
          aria-controls="navbarTogglerDemo02"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <Link className="nav-link active" aria-current="page" to="/home">
                Home
              </Link>
            </li>
            <li className="nav-item">
              {/* hard code to remove */}
              <Link className="nav-link" to="/MyProfile/1">
                MyProfile
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/Projects">
                Projects
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/Tasks">
                Tasks
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/login">
                <button onClick={handleLogout}>Logout</button>
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}

// <form className="d-flex">
//             <input
//               className="form-control me-2"
//               type="search"
//               placeholder="Search"
//               aria-label="Search"
//             />
//             <button className="btn btn-outline-success" type="submit">
//               Search
//             </button>
//           </form>
