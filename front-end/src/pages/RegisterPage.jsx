import React, { useState, useEffect } from 'react';
import { Link, useHistory } from 'react-router-dom';
import { LoginAPI, EmployeeAPI } from '../api';
import Message from '../components/Message';
import LoginSpinner from '../components/spinner/LoginSpinner';
import { AiFillEye, AiFillEyeInvisible } from 'react-icons/ai';

function RegisterPage() {
  const [name, setName] = useState('');
  const [title, setTitle] = useState('Employee');
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [message, setMessage] = useState(null);
  const [hasAdmin, setHasAdmin] = useState(true);
  const [isPasswordShow, setIsPasswordShow] = useState('password');
  const [isConfirmPasswordShow, setIsConfirmPasswordShow] =
    useState('password');
  const [isLoading, setIsLoading] = useState(true);

  const history = useHistory();

  useEffect(() => {
    EmployeeAPI.getEmployeeByTitle('admin').then((res) => {
      if (res.data.length) {
        setHasAdmin(false);
      }
    });

    setIsLoading(false);
  }, [isLoading, isPasswordShow, hasAdmin]);

  const submitHandler = async (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      setMessage('Passswords do not match');
    } else if (password.length < 6) {
      setMessage("Password length can't be shorter than 6");
    } else {
      setMessage(null);
      setIsLoading(true);

      const res = await LoginAPI.register(
        name,
        title,
        username,
        email,
        password
      );

      setIsLoading(false);
      if (res.status === 201) {
        setMessage('Account created');

        alert('Account created');

        history.push('/login');
      } else {
        setMessage(res.errorMsg);
      }
    }
  };

  const togglePasswordShow = () =>
    isPasswordShow === 'password'
      ? setIsPasswordShow('text')
      : setIsPasswordShow('password');
  const toggleConfirmPasswordShow = () =>
    isConfirmPasswordShow === 'password'
      ? setIsConfirmPasswordShow('text')
      : setIsConfirmPasswordShow('password');

  return (
    <div>
      {isLoading ? <LoginSpinner /> : ''}
      <div className='container'>
        <div className='row'>
          <div className='col-lg-10 col-xl-9 mx-auto'>
            <div className='card flex-row my-5 border-0 shadow rounded-3 overflow-hidden'>
              <div className='card-img-left d-none d-md-flex'></div>
              <div className='card-body p-4 p-sm-5'>
                <h5 className='card-title text-center mb-5 fw-light fs-5'>
                  Register
                </h5>
                <form onSubmit={submitHandler}>
                  {message && <Message variant='danger'>{message}</Message>}
                  <div className='form-floating mb-3'>
                    <input
                      type='text'
                      className='form-control'
                      id='floatingInputName'
                      placeholder='myName'
                      required
                      autoFocus
                      value={name}
                      onChange={(e) => setName(e.target.value)}
                    />
                    <label htmlFor='floatingInputName'>Employee Name</label>
                  </div>
                  <div className='form-floating mb-3'>
                    <select
                      onChange={(e) => setTitle(e.target.value)}
                      value={title}
                      className='form-select'
                      id='exampleSelect1'
                    >
                      <option>Architect</option>
                      <option>Employee</option>
                      {hasAdmin && <option>Admin</option>}
                    </select>
                    <label htmlFor='floatingInputTitle'>Employee Title</label>
                  </div>

                  <div className='form-floating mb-3'>
                    <input
                      type='text'
                      className='form-control'
                      id='floatingInputUsername'
                      placeholder='myusername'
                      required
                      value={username}
                      onChange={(e) => setUsername(e.target.value)}
                    />
                    <label htmlFor='floatingInputUsername'>Username</label>
                  </div>

                  <div className='form-floating mb-3'>
                    <input
                      type='email'
                      className='form-control'
                      id='floatingInputEmail'
                      placeholder='name@example.com'
                      required
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                    />
                    <label htmlFor='floatingInputEmail'>Email address</label>
                  </div>

                  <hr />

                  <div className='form-floating mb-3 input-group'>
                    <input
                      type={isPasswordShow}
                      className='form-control'
                      id='floatingPassword'
                      placeholder='Password'
                      required
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                    />
                    <label
                      htmlFor='floatingPassword'
                      style={{
                        zIndex: 100,
                      }}
                    >
                      Password
                    </label>
                    <button
                      className='btn mt-0 '
                      type='button'
                      id='showConfirmPassword'
                      onClick={() => togglePasswordShow()}
                    >
                      <AiFillEye size={35} />
                    </button>
                  </div>

                  <div className='form-floating mb-3 input-group'>
                    <input
                      type={isConfirmPasswordShow}
                      className='form-control'
                      id='floatingPasswordConfirm'
                      placeholder='Confirm Password'
                      required
                      value={confirmPassword}
                      onChange={(e) => setConfirmPassword(e.target.value)}
                    />
                    <label
                      htmlFor='floatingPasswordConfirm'
                      style={{
                        zIndex: 100,
                      }}
                    >
                      Confirm Password
                    </label>

                    <button
                      className='btn mt-0 '
                      type='button'
                      id='showConfirmPassword'
                      onClick={() => toggleConfirmPasswordShow()}
                    >
                      <AiFillEye size={35} />
                    </button>
                  </div>

                  <div className='d-grid mb-2'>
                    <button
                      className='btn btn-lg btn-primary btn-login fw-bold text-uppercase'
                      type='submit'
                    >
                      Register
                    </button>
                  </div>

                  <Link className='d-block text-center mt-2 small' to='/login'>
                    Have an account? Sign In
                  </Link>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default RegisterPage;
