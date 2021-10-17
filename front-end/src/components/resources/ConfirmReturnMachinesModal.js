function ConfirmReturnMachinesModal({
    modalId,
    handleReturnMachinesToProject,
    hasAuthority,
}) {

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
                            <h5 className='modal-t itle'>Confirm Machines Return</h5>
                            <button
                                type='button'
                                className='btn-close'
                                data-bs-dismiss='modal'
                                aria-label='Close'

                            ></button>
                        </div>
                        <div className='modal-body'>
                            <button
                                type='button'
                                className='btn btn-outline-warning'
                                data-bs-dismiss='modal'
                                disabled={!hasAuthority}
                                aria-label='Close'
                                onClick={() => handleReturnMachinesToProject(true)}>
                                Yes
                            </button>
                            <button
                                type='button'
                                className='btn btn-outline-warning'
                                data-bs-dismiss='modal'
                                disabled={!hasAuthority}
                                aria-label='Close'
                                onClick={() => handleReturnMachinesToProject(false)}>
                                No
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ConfirmReturnMachinesModal;