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
                    <div className="list-group text-black">
                        <div className="list-group-item bg-transparent text-start ">
                            <div id="employee-id">Id: {userInfo.id}</div>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <div id="employee-name">
                                Name: {userInfo.name}
                            </div>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <div id="employee-username">
                                Username: {userInfo.username}
                            </div>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <div id="employee-title">Title: {userInfo.title}</div>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <div id="employee-phoneNumber">
                                Phone: {userInfo?.phoneNumber}
                            </div>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <div id="employee-email">Email: {userInfo.email}</div>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <div id="employee-dateOfBirth">
                                Date Of Birth: {userInfo?.dateOfBirth}
                            </div>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <div id="employee-salary">Salary: {userInfo?.salary}</div>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <div id="employee-yearsOfExperience">
                                Experience: {userInfo?.yearsOfExperience}
                            </div>
                        </div>
                        <div className="list-group-item bg-transparent text-start ">
                            <div id="employee-userSince">
                                User Since: {userInfo.userSince}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default DisplayBasicInformation;