import React, { useEffect, useState } from 'react';
import './Resources.css';

export default function Resources({ resources, title, denominator }) {
  const [resourcesObject, setResources] = useState();

  useEffect(() => {
    setResources(resources);
    console.log(resources);
  }, [resources]);

  return (
    <div>
      <h3 className='mb-3'>{title}</h3>

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
                  <img src={value.icon} width="100" height="100" alt={value.name}></img>
                  <p className='textElementMaterials'>{value.amount}{denominator}</p>
                </div>
              </div>
            );
          })}
      </div>
    </div>
  );
}
