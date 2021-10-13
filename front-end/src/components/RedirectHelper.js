import { useHistory } from 'react-router-dom';
import decode from 'jwt-decode';

function RedirectIfTokenNull() {
  const token = localStorage.getItem('token');

  let history = useHistory();
  if (!token) {
    history.push('/login');
  } else if (
    Date.now() >
    decode(JSON.parse(localStorage.getItem('token'))).exp * 1000
  ) {
    localStorage.clear();

    history.push('/login');
  }
}

export default RedirectIfTokenNull;
