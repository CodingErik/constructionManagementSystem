import React from 'react';
import { Link, useHistory, NavLink } from 'react-router-dom';
import { useEffect, useState } from 'react/cjs/react.development';

const checkToken = async () => {
  let token = (await localStorage.getItem('token'))
    ? JSON.parse(localStorage.getItem('token'))
    : null;

  if (!token) {
    return false;
  }

  return true;
};

export default function NavBar() {
  const [isLogin, setIslogin] = useState(false);
  const history = useHistory();

  const handleLogout = () => {
    localStorage.clear();

    history.push('/login');
  };

  useEffect(() => {
    checkToken().then((res) => {
      if (res) {
        setIslogin(true);
      } else {
        setIslogin(false);
      }
    });
  });

  if (!isLogin) {
    return (
      <nav className='navbar navbar-expand-lg navbar-light bg-light'>
        <div className='container-fluid'>
          <Link className='navbar-brand' to='/'>
            CMS
          </Link>
        </div>
      </nav>
    );
  }

  return (
    <nav className='navbar navbar-expand-lg navbar-light bg-light'>
      <div className='container-fluid'>
        <Link className='navbar-brand' to='/'>
          CMS
        </Link>
        <button
          className='navbar-toggler'
          type='button'
          data-bs-toggle='collapse'
          data-bs-target='#navbarTogglerDemo02'
          aria-controls='navbarTogglerDemo02'
          aria-expanded='false'
          aria-label='Toggle navigation'
        >
          <span className='navbar-toggler-icon'></span>
        </button>
        <div className='collapse navbar-collapse' id='navbarTogglerDemo02'>
          <ul className='navbar-nav me-auto mb-2 mb-lg-0'>
            <li className='nav-item'>
              <Link className='nav-link active' aria-current='page' to='/home'>
                Home
              </Link>
            </li>
            <li className='nav-item'>
              {/* hard code to remove */}
              <Link className='nav-link' to='/MyProfile'>
                MyProfile
              </Link>
            </li>
            <li className='nav-item'>
              <Link className='nav-link' to='/Projects'>
                Projects
              </Link>
            </li>
            <li className='nav-item'>
              <Link className='nav-link' to='/Tasks'>
                Tasks
              </Link>
            </li>
            <li className='nav-item'>
              <NavLink onClick={handleLogout} to='/login' className='nav-link'>
                Logout
              </NavLink>
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
