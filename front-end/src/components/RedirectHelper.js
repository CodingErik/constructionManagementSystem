<<<<<<< HEAD
import { useHistory } from 'react-router-dom';
import decode from 'jwt-decode';
=======
import { useHistory } from "react-router-dom";
import decode from "jwt-decode";
>>>>>>> 902ab594be9068888221cdd5f2f2e3586dcb0b7a

function RedirectIfTokenNull() {
  const token = localStorage.getItem('token');

<<<<<<< HEAD
  let history = useHistory();
  if (!token) {
    history.push('/login');
  } else if (
    Date.now() >
    decode(JSON.parse(localStorage.getItem('token'))).exp * 1000
  ) {
    localStorage.clear();
=======
    let history = useHistory();
    if (!localStorage.getItem("token")) {
        history.push("/login");
    } 
    // else if, check if expired
    // if expired, delete local storage and redirect to login
>>>>>>> 902ab594be9068888221cdd5f2f2e3586dcb0b7a

    history.push('/login');
  }
}

export default RedirectIfTokenNull;
