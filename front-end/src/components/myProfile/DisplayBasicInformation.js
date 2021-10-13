import UserPersonalInformationModal from "./UserPersonalInformationFormModal";

function DisplayBasicInformation({ userInfo }) {
    return (
        <div className="col col-8-lg">
            <div className="row mb-3">
                <div className="col-4">
                    <h2>My Info </h2>
                </div>
                <div className="col-5"></div>
                <div className="col-3">
                    <button
                        style={{ display: "inline" }}
                        type="button"
                        className="btn btn-warning"
                        data-bs-toggle="modal" 
                        data-bs-target="#editPersonalInformationModal"
                    >
                        Edit Info
                    </button>
                </div>
                <UserPersonalInformationModal userInfo={userInfo} modalId="editPersonalInformationModal"></UserPersonalInformationModal>
            </div>
            <div className="row">
                <div className="col-12">
                    <ul className="list-group text-black">
                        <div className="list-group-item bg-transparent text-start ">
                            <span id="employee-id">Id: {userInfo.id}</span>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <span id="employee-name">
                                Name: {userInfo.name}
                            </span>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <p id="employee-username">
                                Username: {userInfo.username}
                            </p>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <span id="employee-title">Title: {userInfo.title}</span>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <p id="employee-phoneNumber">
                                Phone: {userInfo?.phoneNumber}
                            </p>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <p id="employee-email">Email: {userInfo.email}</p>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <span id="employee-dateOfBirth">
                                Date Of Birth: {userInfo?.dateOfBirth}
                            </span>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <p id="employee-salary">Salary: {userInfo?.salary}</p>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <p id="employee-yearsOfExperience">
                                Experience: {userInfo?.yearsOfExperience}
                            </p>
                        </div>

                        <div className="list-group-item bg-transparent text-start ">
                            <p id="employee-userSince">
                                User Since: {userInfo.userSince}
                            </p>
                        </div>
                    </ul>
                </div>
            </div>
        </div>
    )
}

export default DisplayBasicInformation;