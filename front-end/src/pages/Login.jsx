import React from 'react';
import { useState, useEffect } from 'react';
import { Link, useHistory, Redirect } from 'react-router-dom';
import { LoginAPI } from '../api';
import Message from '../components/Message';

export default function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState(null);
  const history = useHistory();

  const submitHandler = async (e) => {
    e.preventDefault();

    const res = await LoginAPI.login(username, password);
    if (res.status === 200) {
      setMessage(null);

      alert('login');

      history.push('/Home');
    } else {
      setMessage(res.errorMsg);
    }
  };

  if (localStorage.getItem('token')) {
    return <Redirect to='/Home' />;
  }
  return (
    <div class='container'>
      <div class='row'>
        <div class='col-lg-10 col-xl-9 mx-auto'>
          <div class='card flex-row my-5 border-0 shadow rounded-3 overflow-hidden'>
            <div class='card-img-left d-none d-md-flex'></div>
            <div class='card-body p-4 p-sm-5'>
              <h5 class='card-title text-center mb-5 fw-light fs-5'>Login</h5>

              <form onSubmit={submitHandler}>
                {message && <Message variant='danger'>{message}</Message>}
                <div class='form-floating mb-3'>
                  <input
                    type='text'
                    class='form-control'
                    id='floatingInputUsername'
                    placeholder='myusername'
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                    autofocus
                  />
                  <label for='floatingInputUsername'>Username</label>
                </div>

                <hr />

                <div class='form-floating mb-3'>
                  <input
                    type='password'
                    class='form-control'
                    id='floatingPassword'
                    placeholder='Password'
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                  />
                  <label for='floatingPassword'>Password</label>
                </div>

                <div class='d-grid mb-2'>
                  <button
                    class='btn btn-lg btn-primary btn-login fw-bold text-uppercase'
                    type='submit'
                  >
                    Login
                  </button>
                </div>

                <Link class='d-block text-center mt-2 small' to='/register'>
                  Don't have an account? Register
                </Link>

                <hr class='my-4' />
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
