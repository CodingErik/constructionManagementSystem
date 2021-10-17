import React from 'react';
import { useState, useEffect } from 'react';
import { Link, useHistory, Redirect } from 'react-router-dom';
import { LoginAPI } from '../api';
import Message from '../components/Message';
import LoginSpinner from '../components/spinner/LoginSpinner';
import { AiFillEye, AiFillEyeInvisible } from 'react-icons/ai';

export default function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState(null);
  const [isPasswordShow, setIsPasswordShow] = useState('password');
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {}, [isLoading, isPasswordShow]);

  const history = useHistory();

  const submitHandler = async (e) => {
    e.preventDefault();
    setIsLoading(true);

    const res = await LoginAPI.login(username, password);

    setIsLoading(false);
    if (res.status === 200) {
      setMessage(null);

      history.push('/Home');
    } else {
      setMessage(res.errorMsg);
    }
  };

  if (localStorage.getItem('token')) {
    return <Redirect to='/Home' />;
  }

  const togglePasswordShow = () =>
    isPasswordShow === 'password'
      ? setIsPasswordShow('text')
      : setIsPasswordShow('password');

  return (
    <div className='container'>
      {isLoading ? <LoginSpinner /> : ''}
      <div className='row'>
        <div className='col-lg-10 col-xl-9 mx-auto'>
          <div className='card flex-row my-5 border-0 shadow rounded-3 overflow-hidden'>
            <div className='card-img-left d-none d-md-flex'></div>
            <div className='card-body p-4 p-sm-5'>
              <h5 className='card-title text-center mb-5 fw-light fs-5'>Login</h5>

              <form onSubmit={submitHandler}>
                {message && <Message variant='danger'>{message}</Message>}
                <div className='form-floating mb-3'>
                  <input
                    type='text'
                    className='form-control'
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

                <div className='form-floating mb-3 input-group'>
                  <input
                    type={isPasswordShow}
                    className='form-control m-auto'
                    id='floatingPassword'
                    placeholder='Password'
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                  />
                  <label
                    for='floatingPassword'
                    style={{
                      zIndex: 100,
                    }}
                  >
                    Password
                  </label>
                  <button
                    class='btn mt-0 '
                    type='button'
                    id='showConfirmPassword'
                    onClick={() => togglePasswordShow()}
                  >
                    <AiFillEye size={35} />
                  </button>
                </div>

                <div className='d-grid mb-2'>
                  <button
                    className='btn btn-lg btn-primary btn-login fw-bold text-uppercase'
                    type='submit'
                  >
                    Login
                  </button>
                </div>

                <Link className='d-block text-center mt-2 small' to='/register'>
                  Don't have an account? Register
                </Link>

                <hr className='my-4' />
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
