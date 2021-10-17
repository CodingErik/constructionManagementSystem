import { AiFillEye } from 'react-icons/ai';
import { useEffect, useRef, useState } from 'react';
import { EmployeeAPI } from '../../api';

function AdminEditEmployeeInfoModal({
  employeeInfo,
  modalId,
  handleBasicInformationUpdateSubmit,
}) {
  const employeeNameRef = useRef(null);
  const [employeeTitle, setEmployeeTitle] = useState('');
  const employeeEmailRef = useRef(null);
  const employeePhoneRef = useRef(null);
  const employeeDateOfBirthRef = useRef(null);
  const employeeSalaryRef = useRef(null);
  const employeeYearsOfExperienceRef = useRef(null);
  const passwordNewInput = useRef('');
  const passwordConfirmationInput = useRef(null);

  useEffect(() => {
    setEmployeeTitle(employeeInfo.title);
  }, [employeeInfo]);

  const handlePasswordSubmit = async (event) => {
    event.preventDefault();
    if (
      passwordNewInput.current.value === '' ||
      passwordNewInput.current.value === passwordConfirmationInput.current.value
    ) {
      EmployeeAPI.updatePassword(
        employeeInfo.id,
        passwordConfirmationInput.current.value
      ).then((res) => {
        if (res.status === 204) {
          alert('Password Updated');
        } else alert(res.data);
      });
    } else {
      alert('Passwords Do Not Match');
    }
  };

  const togglePasswordShow = (passwordInputRef) => {
    if (passwordInputRef.current.type === 'password') {
      passwordInputRef.current.type = 'text';
    } else if (passwordInputRef.current.type === 'text') {
      passwordInputRef.current.type = 'password';
    }
  };

  const sendUpdatedUserInfo = (event) => {
    event.preventDefault();
    const updatedInfo = {
      ...employeeInfo,
      name: employeeNameRef.current.value,
      title: employeeTitle,
      email: employeeEmailRef.current.value,
      phoneNumber: employeePhoneRef.current.value,
      dateOfBirth: employeeDateOfBirthRef.current.value,
      salary: employeeSalaryRef.current.value,
      yearsOfExperience: employeeYearsOfExperienceRef.current.value,
      projectId: employeeInfo.project ? employeeInfo.project.id : 0,
    };
    console.log(updatedInfo);
    handleBasicInformationUpdateSubmit(updatedInfo);
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
          <div className='modal-content'>
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
              <form onSubmit={(event) => sendUpdatedUserInfo(event)}>
                <h5 className='ms-4 text-start'>Basic Information</h5>
                <fieldset>
                  <div className='form-group'>
                    <label
                      htmlFor='employee-name'
                      className='form-label mt-4 ms-4 d-flex align-items-start'
                    >
                      Employee Id #{employeeInfo.id}:
                    </label>
                    <input
                      className='form-control  m-auto'
                      id='employee-name'
                      defaultValue={employeeInfo.name}
                      ref={employeeNameRef}
                    />
                  </div>
                  <div className='form-group'>
                    <label
                      htmlFor='employee-title'
                      className='form-label mt-4 ms-4 d-flex align-items-start'
                    >
                      Employee Title:
                    </label>
                    <select
                      className='form-select  m-auto'
                      id='title'
                      value={employeeTitle}
                      onChange={(event) => setEmployeeTitle(event.target.value)}
                    >
                      <option value='architect'>Architect</option>
                      <option value='employee'>Employee</option>
                    </select>
                  </div>
                  <div className='form-group'>
                    <label
                      htmlFor='employee-email'
                      className='form-label mt-4 ms-4 d-flex align-items-start'
                    >
                      Email:
                    </label>
                    <input
                      className='form-control  m-auto'
                      id='employee-email'
                      defaultValue={employeeInfo.email}
                      ref={employeeEmailRef}
                    />
                  </div>
                  <div className='form-group'>
                    <label
                      htmlFor='employee-phone'
                      className='form-label mt-4 ms-4 d-flex align-items-start'
                    >
                      Phone:
                    </label>
                    <input
                      id='employee-phone'
                      type='tel'
                      pattern='[0-9]{3}-[0-9]{3}-[0-9]{4}'
                      className='form-control m-auto'
                      defaultValue={employeeInfo.phoneNumber}
                      ref={employeePhoneRef}
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
                      className='form-control m-auto'
                      id='employee-dateOfBirth'
                      defaultValue={employeeInfo.dateOfBirth}
                      ref={employeeDateOfBirthRef}
                    />
                  </div>
                  <div className='form-group'>
                    <label
                      htmlFor='employee-yearsOfExperience'
                      className='form-label mt-4 ms-4 d-flex align-items-start'
                    >
                      Years Of Experience:
                    </label>
                    <input
                      id='employee-yearsOfExperience'
                      type='number'
                      className='form-control m-auto'
                      defaultValue={employeeInfo.yearsOfExperience}
                      ref={employeeYearsOfExperienceRef}
                    ></input>
                  </div>
                  <div className='form-group'>
                    <label
                      htmlFor='employee-salary'
                      className='form-label mt-4 ms-4 d-flex align-items-start'
                    >
                      Salary:
                    </label>
                    <div className='input-group'>
                      <span className='input-group-text '>$</span>
                      <input
                        type='number'
                        className='form-control m-auto'
                        id='employee-salary'
                        defaultValue={employeeInfo.salary}
                        ref={employeeSalaryRef}
                      />
                    </div>
                  </div>
                </fieldset>
                <button className='btn btn-info mt-3' type='submit'>
                  Update Personal Information
                </button>
              </form>
              <form
                style={{ marginTop: '50px' }}
                onSubmit={handlePasswordSubmit}
              >
                <h5 className='ms-4 text-start'>Password</h5>
                <fieldset>
                  <div className='form-group'>
                    <label
                      htmlFor='employee-password'
                      className='form-label mt-4 ms-4 d-flex align-items-start'
                    >
                      New Password
                    </label>
                    <div className='input-group mb-3'>
                      <input
                        ref={passwordNewInput}
                        type='password'
                        className='form-control ms-4'
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
                      htmlFor='employee-password'
                      className='form-label mt-4 ms-4 d-flex align-items-start'
                    >
                      Confirm New Password
                    </label>
                    <div className='input-group mb-3'>
                      <input
                        ref={passwordConfirmationInput}
                        type='password'
                        className='form-control ms-4'
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

export default AdminEditEmployeeInfoModal;
