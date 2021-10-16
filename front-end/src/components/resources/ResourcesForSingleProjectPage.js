import React, { useEffect, useState } from 'react';
import './Resources.css';
import AddResourcesModal from './AddResourcesModal';

export default function ResourcesForSingleProjectPage({ resources, title, projectId, hasAuthority}) {
    const [resourcesObject, setResources] = useState();

    useEffect(() => {
        setResources(resources);
    }, [resources]);

    return (
        <div>
            <h4 className='mb-3'>{title}</h4>
            <div
                className='resourcesWrapper row mt-3'
                style={{
                    minHeight: '375px',
                    minWidth: '275px',
                }}
            >
                {resourcesObject &&
                    Object.entries(resourcesObject).map(([key, value]) => {
                        return (
                            <div className='col-6'>
                                <div className='materialOrMachineryIcon'>
                                    <p className='textElementMaterials'>
                                        {value.name}
                                    </p>
                                    <img src={value.icon} width="60" height="60" alt={value.name}></img>
                                    <p className='textElementMaterials'>{value.amount}</p>
                                </div>
                            </div>
                        );
                    })}
            </div>
            <button
                type='button'
                className='btn btn-outline-warning'
                data-bs-toggle='modal'
                data-bs-target='#addResourceModal'
            // disabled={!hasAuthority}
            >
                Add Resources
            </button>
            <AddResourcesModal modalId="addResourceModal" hasAuthority={hasAuthority} projectId={projectId}></AddResourcesModal>
        </div>
    );
}
