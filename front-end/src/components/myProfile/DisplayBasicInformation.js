import UserPersonalInformationModal from './UserPersonalInformationFormModal';

function DisplayBasicInformation({ userInfo, updateUserBasicInformation }) {
  return (
    <div className='col col-12-lg'>
      <div className='row mb-3'>
        <div className='col-4'>
          <h2>My Info </h2>
        </div>
        <div className='col-5'></div>
        <div className='col-3'></div>
      </div>
      <div className='row'>
        <div className='col-12'>
          <ul className='list-group text-black'>
            <div className='list-group-item bg-transparent text-start '>
              <span id='employee-id'>Id: {userInfo.id}</span>
            </div>
            <div className='list-group-item bg-transparent text-start '>
              <span id='employee-name'>Name: {userInfo.name}</span>
            </div>
            <div className='list-group-item bg-transparent text-start '>
              <p id='employee-username'>Username: {userInfo.username}</p>
            </div>
            <div className='list-group-item bg-transparent text-start '>
              <span id='employee-title'>Title: {userInfo.title}</span>
            </div>
            <div className='list-group-item bg-transparent text-start '>
              <p id='employee-phoneNumber'>
                Phone: {userInfo?.phoneNumber || 'None'}
              </p>
            </div>
            <div className='list-group-item bg-transparent text-start '>
              <p id='employee-email'>Email: {userInfo.email}</p>
            </div>
            <div className='list-group-item bg-transparent text-start '>
              <span id='employee-dateOfBirth'>
                Date Of Birth: {userInfo?.dateOfBirth || 'None'}
              </span>
            </div>
            <div className='list-group-item bg-transparent text-start '>
              <p id='employee-salary'>Salary: {userInfo?.salary || 'None'}</p>
            </div>
            <div className='list-group-item bg-transparent text-start '>
              <p id='employee-yearsOfExperience'>
                Experience: {userInfo?.yearsOfExperience} year
              </p>
            </div>

            <div className='list-group-item bg-transparent text-start '>
              <p id='employee-userSince'>User Since: {userInfo.userSince}</p>
            </div>
          </ul>
        </div>
        <button
          // style={{ display: 'inline' }}
          type='button'
          className='btn btn-warning btn-block mt-3'
          data-bs-toggle='modal'
          data-bs-target='#editPersonalInformationModal'
        >
          Edit Info
        </button>
        <UserPersonalInformationModal
          userInfo={userInfo}
          modalId='editPersonalInformationModal'
          updateUserBasicInformation={updateUserBasicInformation}
        ></UserPersonalInformationModal>
      </div>
    </div>
  );
}

export default DisplayBasicInformation;
