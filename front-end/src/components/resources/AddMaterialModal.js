import { useRef } from 'react';
import brickIcon from '../../assets/brick.png';
import steelIcon from '../../assets/steel.png';
import cementIcon from '../../assets/cement.png';
import lumberIcon from '../../assets/lumber.png';

function AddMaterialModal({
  modalId,
  handleAddMaterialsToProject,
  hasAuthority,
  projectId,
  maxMaterialAmount,
}) {
  const brickRef = useRef();
  const cementRef = useRef();
  const lumberRef = useRef();
  const steelRef = useRef();

  const handleSubmitForm = (event) => {
    event.preventDefault();
    const resourceObject = {
      projectId: projectId,
      brick: brickRef.current.value,
      cement: cementRef.current.value,
      lumber: lumberRef.current.value,
      steel: steelRef.current.value,
    };
    handleAddMaterialsToProject(resourceObject);
  };

  return (
    <div className='container'>
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
                <div
                  className='resourcesWrapper row mt-3'
                  style={{
                    minHeight: '375px',
                    minWidth: '275px',
                  }}
                >
                  <div className='col-6'>
                    <div className='material'>
                      <p className='textElementMaterials'>Brick</p>
                      <img
                        src={brickIcon}
                        width='60'
                        height='60'
                        alt='brickIcon'
                      ></img>
                      <input
                        className='form-control m-auto mt-3'
                        id='brick'
                        type='number'
                        readOnly={!hasAuthority}
                        ref={brickRef}
                        style={{ width: '30%' }}
                        defaultValue={0}
                        disabled={maxMaterialAmount.brick === 0 ? true : false}
                        min={0}
                        max={
                          maxMaterialAmount.brick
                            ? maxMaterialAmount.brick
                            : 1000
                        }
                      />
                      <small>Max Bricks: {maxMaterialAmount.brick}</small>
                    </div>
                  </div>
                  <div className='col-6'>
                    <div className='material'>
                      <p className='textElementMaterials'>Cement</p>
                      <img
                        src={cementIcon}
                        width='60'
                        height='60'
                        alt='cementIcon'
                      ></img>
                      <input
                        className='form-control m-auto mt-3'
                        id='cement'
                        type='number'
                        readOnly={!hasAuthority}
                        ref={cementRef}
                        style={{ width: '30%' }}
                        defaultValue={0}
                        disabled={maxMaterialAmount.cement === 0 ? true : false}
                        min={0}
                        max={
                          maxMaterialAmount.cement
                            ? maxMaterialAmount.cement
                            : 1000
                        }
                      />
                      <small>Max Cement: {maxMaterialAmount.cement}</small>
                    </div>
                  </div>
                  <div className='col-6'>
                    <div className='material'>
                      <p className='textElementMaterials'>Lumber</p>
                      <img
                        src={lumberIcon}
                        width='60'
                        height='60'
                        alt='lumberIcon'
                      ></img>
                      <input
                        className='form-control m-auto mt-3'
                        id='lumber'
                        type='number'
                        readOnly={!hasAuthority}
                        ref={lumberRef}
                        style={{ width: '30%' }}
                        defaultValue={0}
                        disabled={maxMaterialAmount.lumber === 0 ? true : false}
                        min={0}
                        max={
                          maxMaterialAmount.lumber
                            ? maxMaterialAmount.lumber
                            : 1000
                        }
                      />
                      <small>Max Lumber: {maxMaterialAmount.lumber}</small>
                    </div>
                  </div>
                  <div className='col-6'>
                    <div className='material'>
                      <p className='textElementMaterials'>Steel</p>
                      <img
                        src={steelIcon}
                        width='60'
                        height='60'
                        alt='steelIcon'
                      ></img>
                      <input
                        className='form-control m-auto mt-3'
                        id='steel'
                        type='number'
                        readOnly={!hasAuthority}
                        ref={steelRef}
                        style={{ width: '30%' }}
                        defaultValue={0}
                        disabled={maxMaterialAmount.steel === 0 ? true : false}
                        max={
                          maxMaterialAmount.steel
                            ? maxMaterialAmount.steel
                            : 1000
                        }
                        min={0}
                      />
                      <small>Max Steel: {maxMaterialAmount.steel}</small>
                    </div>
                  </div>
                </div>

                <button
                  className='btn btn-info mt-3'
                  type='submit'
                  disabled={
                    !hasAuthority ||
                    (maxMaterialAmount.brick === 0 &&
                      maxMaterialAmount.cement === 0 &&
                      maxMaterialAmount.lumber === 0 &&
                      maxMaterialAmount.steel === 0)
                  }
                >
                  Purchase Resources
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AddMaterialModal;
