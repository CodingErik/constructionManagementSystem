import React, { useEffect, useState } from 'react';
import './Resources.css';

export default function Resources({ resources, title,  }) {
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
    </div>
  );
}
