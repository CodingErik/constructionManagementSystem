import { useRef } from "react";
import craneIcon from "../../assets/crane.png";
import drillIcon from "../../assets/drill.png";
import forkliftIcon from "../../assets/forklift.png";
import ladderIcon from "../../assets/ladder.png";


function AddMachineModal({
    modalId,
    handleAddMachinesToProject,
    hasAuthority,
    projectId,
    maxMachineAmount
}) {

    const craneRef = useRef();
    const drillRef = useRef();
    const forkliftRef = useRef();
    const ladderRef = useRef();

    const handleSubmitForm = (event) => {
        event.preventDefault();
        const resourceObject = {
            projectId: projectId,
            crane: craneRef.current.value,
            drill: drillRef.current.value,
            forklift: forkliftRef.current.value,
            ladder: ladderRef.current.value
        };
        handleAddMachinesToProject(resourceObject);
    }

    return (
        <div class='container'>
            <div
                className='modal fade'
                id={modalId}
                tabIndex='-1'
                aria-labelledby='addModal'
                aria-hidden='true'
            >
                <div className='modal-dialog modal-lg'>
                    <div className='modal-content'>
                        <div className='modal-header'>
                            <h5 className='modal-title'>Add Resources</h5>
                            <button
                                type='button'
                                className='btn-close'
                                data-bs-dismiss='modal'
                                aria-label='Close'
                            ></button>
                        </div>
                        <div className='modal-body'>
                            <form
                                style={{ margin: '0 auto' }}
                                onSubmit={(event) => handleSubmitForm(event)}
                            >
                                <div className='resourcesWrapper row mt-3'
                                    style={{
                                        minHeight: '375px',
                                        minWidth: '275px',
                                    }}>
                                    <div className="col-6">
                                        <div className='material'>
                                            <p className='textElementMaterials'>
                                                Cranes
                                            </p>
                                            <img src={craneIcon} width="60" height="60" alt="craneIcon"></img>
                                            <input
                                                className='form-control m-auto mt-3'
                                                id='crane'
                                                type="number"
                                                readOnly={!hasAuthority}
                                                ref={craneRef}
                                                style={{ width: "30%" }}
                                                defaultValue={0}
                                                disabled={maxMachineAmount.crane === 0 ? true : false}
                                                min={0}
                                                max={maxMachineAmount.crane ? maxMachineAmount.crane : 30}
                                            />
                                            <small >
                                                Max Cranes: {maxMachineAmount.crane}
                                            </small>
                                        </div>
                                    </div>
                                    <div className="col-6">
                                        <div className='material'>
                                            <p className='textElementMaterials'>
                                                Drills
                                            </p>
                                            <img src={drillIcon} width="60" height="60" alt="cementIcon"></img>
                                            <input
                                                className='form-control m-auto mt-3'
                                                id='drill'
                                                type="number"
                                                readOnly={!hasAuthority}
                                                ref={drillRef}
                                                style={{ width: "30%" }}
                                                defaultValue={0}
                                                disabled={maxMachineAmount.drill === 0 ? true : false}
                                                min={0}
                                                max={maxMachineAmount.drill ? maxMachineAmount.drill : 30}
                                            />
                                            <small >
                                                Max Drills: {maxMachineAmount.drill}
                                            </small>
                                        </div>
                                    </div>
                                    <div className="col-6">
                                        <div className='material'>
                                            <p className='textElementMaterials'>
                                                Forklifts
                                            </p>
                                            <img src={forkliftIcon} width="60" height="60" alt="lumberIcon"></img>
                                            <input
                                                className='form-control m-auto mt-3'
                                                id='forklift'
                                                type="number"
                                                readOnly={!hasAuthority}
                                                ref={forkliftRef}
                                                style={{ width: "30%" }}
                                                defaultValue={0}
                                                disabled={maxMachineAmount.forklift === 0 ? true : false}
                                                min={0}
                                                max={maxMachineAmount.forklift ? maxMachineAmount.forklift : 30}
                                            />
                                            <small >
                                                Max Forklifts: {maxMachineAmount.forklift}
                                            </small>
                                        </div>
                                    </div>
                                    <div className="col-6">
                                        <div className='material'>
                                            <p className='textElementMaterials'>
                                                Ladders
                                            </p>
                                            <img src={ladderIcon} width="60" height="60" alt="steelIcon"></img>
                                            <input
                                                className='form-control m-auto mt-3'
                                                id='ladder'
                                                type="number"
                                                readOnly={!hasAuthority}
                                                ref={ladderRef}
                                                style={{ width: "30%" }}
                                                defaultValue={0}
                                                disabled={maxMachineAmount.ladder === 0 ? true : false}
                                                max={maxMachineAmount.ladder ? maxMachineAmount.ladder : 30}
                                                min={0}
                                            />
                                            <small >
                                                Max Ladders: {maxMachineAmount.ladder}
                                            </small>
                                        </div>
                                    </div>
                                </div>

                                <button
                                    className='btn btn-info mt-3'
                                    type='submit'
                                    disabled={!hasAuthority || (maxMachineAmount.crane === 0 && maxMachineAmount.drill === 0 && maxMachineAmount.forklift === 0 && maxMachineAmount.ladder === 0)}
                                >
                                    Purchase Machines
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddMachineModal;