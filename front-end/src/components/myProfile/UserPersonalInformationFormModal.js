import { AiFillEye, AiFillEyeInvisible } from 'react-icons/ai';
import './UserPersonalInformationFormModal.css';
import { useRef } from 'react';
import { EmployeeAPI, LoginAPI } from '../../api';

function UserPersonalInformationModal({ userInfo, modalId , updateUserBasicInformation}) {
  const userNameRef = useRef(null);
  const userEmailRef = useRef(null);
  const userPhoneRef = useRef(null);
  const userDateOfBirthRef = useRef(null);
  const userYearsOfExperienceRef = useRef(null);
  const passwordOriginalInput = useRef(null);
  const passwordNewInput = useRef(null);
  const passwordConfirmationInput = useRef(null);

  const handleBasicInformationUpdateSubmit = (event) => {
    event.preventDefault();
    const updatedInformation = {
      ...userInfo,
      name: userNameRef.current.value,
      email: userEmailRef.current.value,
      phoneNumber: userPhoneRef.current.value,
      dateOfBirth: userDateOfBirthRef.current.value,
      yearsOfExperience: userYearsOfExperienceRef.current.value,
      projectId: userInfo.project ? userInfo.project.id : 0,    
    };
    updateUserBasicInformation(updatedInformation);
  };

  const handlePasswordSubmit = async (event) => {
    event.preventDefault();
    const passwordCheck = await LoginAPI.login(
      userInfo.username,
      passwordOriginalInput.current.value
    );
    if (passwordCheck.status !== 200) {
      alert('Original password is incorrect');
    } else if (passwordNewInput.current.value === '') {
      alert('No new password');
    } else if (
      passwordNewInput.current.value !== passwordConfirmationInput.current.value
    ) {
      alert('Password not match');
    } else {
      EmployeeAPI.updatePassword(
        userInfo.id,
        passwordConfirmationInput.current.value
      ).then((res) => {
        if (res.status === 204) {
          alert('Password Updated');
        } else alert(res.data);
      });
    }
  };

  const togglePasswordShow = (passwordInputRef) => {
    if (passwordInputRef.current.type === 'password') {
      passwordInputRef.current.type = 'text';
    } else if (passwordInputRef.current.type === 'text') {
      passwordInputRef.current.type = 'password';
    }
  };

  return (
    <div className='container'>
      <div
        className='modal fade '
        id={modalId}
        tabIndex='-1'
        aria-labelledby='editPersonalInformationModal'
        aria-hidden='true'
      >
        <div className='modal-dialog modal-xl'>
          <div className='modal-content container'>
            <div className='modal-header'>
              <h4 className='modal-title' id='exampleModalLabel'>
                Edit Personal Information
              </h4>
              <button
                type='button'
                className='btn-close'
                data-bs-dismiss='modal'
                aria-label='Close'
              ></button>
            </div>
            <div className='modal-body'>
              <form onSubmit={handleBasicInformationUpdateSubmit}>
                <h5 className='ms-4 text-start'>Basic Information</h5>
                <fieldset>
                  <div className='form-group'>
                    <label
                      htmlFor='user-name'
                      className='form-label mt-4 ms-4 d-flex align-items-start'
                    >
                      Employee Id #{userInfo.id}:
                    </label>
                    <input
                      className='form-control m-auto inputInformation'
                      id='user-name'
                      defaultValue={userInfo.name}
                      ref={userNameRef}
                    />
                  </div>
                  <div className='form-group'>
                    <label
                      htmlFor='user-email'
                      className='form-label mt-4 ms-4 d-flex align-items-start'
                    >
                      Email:
                    </label>
                    <input
                      className='form-control m-auto inputInformation'
                      id='user-email'
                      defaultValue={userInfo.email}
                      ref={userEmailRef}
                    />
                  </div>
                  <div className='form-group'>
                    <label
                      htmlFor='user-phone'
                      className='form-label mt-4 ms-4 d-flex align-items-start'
                    >
                      Phone:
                    </label>
                    <input
                      id='user-phone'
                      type='tel'
                      pattern='[0-9]{3}-[0-9]{3}-[0-9]{4}'
                      className='form-control m-auto inputInformation'
                      defaultValue={userInfo.phoneNumber}
                      ref={userPhoneRef}
                    ></input>
                    <small className='form-label ms-4 d-flex align-items-start'>
                      Format: 123-456-7890
                    </small>
                  </div>
                  <div className='form-group'>
                    <label
                      htmlFor='dateOfBirth'
                      className='form-label mt-4 ms-4 d-flex align-items-start'
                    >
                      Date Of Birth:
                    </label>
                    <input
                      type='date'
                      className='form-control m-auto inputInformation'
                      id='user-dateOfBirth'
                      defaultValue={userInfo.dateOfBirth}
                      ref={userDateOfBirthRef}
                    />
                  </div>
                  <div className='form-group'>
                    <label
                      htmlFor='user-yearsOfExperience'
                      className='form-label mt-4 ms-4 d-flex align-items-start'
                    >
                      Years Of Experience:
                    </label>
                    <input
                      className='form-control m-auto inputInformation'
                      id='user-yearsOfExperience'
                      defaultValue={userInfo.yearsOfExperience}
                      ref={userYearsOfExperienceRef}
                    />
                  </div>
                </fieldset>
                <button className='btn btn-info mt-3' type='submit'>
                  Apply Changes
                </button>
              </form>

              <form
                style={{ marginTop: '30px' }}
                onSubmit={handlePasswordSubmit}
              >
                <h5 className='ms-4 text-start'>Password</h5>
                <fieldset>
                  <div className='form-group'>
                    <label
                      htmlFor='user-oldPassword'
                      className='form-label mt-4 ms-4 d-flex align-items-start'
                    >
                      Old Password
                    </label>
                    <div className='input-group mb-3'>
                      <input
                        ref={passwordOriginalInput}
                        type='password'
                        className='form-control m-auto'
                        placeholder='**********'
                        id='confirmPassword'
                      />
                      <button
                        className='btn'
                        type='button'
                        id='showPassword'
                        onClick={() =>
                          togglePasswordShow(passwordOriginalInput)
                        }
                      >
                        <AiFillEye size={25} />
                      </button>
                    </div>
                    <label
                      htmlFor='user-password'
                      className='form-label mt-4 ms-4 d-flex align-items-start'
                    >
                      New Password
                    </label>
                    <div className='input-group mb-3'>
                      <input
                        ref={passwordNewInput}
                        type='password'
                        className='form-control m-auto'
                        id='confirmPassword'
                      />
                      <button
                        className='btn'
                        type='button'
                        id='showPassword'
                        onClick={() => togglePasswordShow(passwordNewInput)}
                      >
                        <AiFillEye size={25} />
                      </button>
                    </div>
                    <label
                      htmlFor='user-password'
                      className='form-label mt-4 ms-4 d-flex align-items-start'
                    >
                      Confirm New Password
                    </label>
                    <div className='input-group mb-3'>
                      <input
                        ref={passwordConfirmationInput}
                        type='password'
                        className='form-control m-auto'
                        id='confirmPassword'
                      />
                      <button
                        className='btn'
                        type='button'
                        id='showConfirmPassword'
                        onClick={() =>
                          togglePasswordShow(passwordConfirmationInput)
                        }
                      >
                        <AiFillEye size={25} />
                      </button>
                    </div>
                  </div>
                </fieldset>
                <button className='btn btn-info mt-3' type='submit'>
                  Update Password
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default UserPersonalInformationModal;
