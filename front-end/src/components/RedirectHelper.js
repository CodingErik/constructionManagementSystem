import { useHistory } from 'react-router-dom';
import decode from 'jwt-decode';

function RedirectIfTokenNull() {
  const token = localStorage.getItem('token')
    ? localStorage.getItem('token')
    : null;

  let history = useHistory();

  try {
    if (!token) {
      history.push('/login');
    } else if (Date.now() > decode(JSON.parse(token)).exp * 1000) {
      localStorage.clear();

      history.push('/login');
    }
  } catch (e) {
    console.log(e);
  }
}

export default RedirectIfTokenNull;
