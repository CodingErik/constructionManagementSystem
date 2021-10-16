import React, { useEffect, useState } from 'react';
import './Resources.css';

export default function Resources({ resources, title, denominator, denominatorValue }) {
  const [resourcesObject, setResources] = useState();
  const [innerInventoryBar, setInnerInventoryBar] = useState();

  useEffect(() => {
    setResources(resources);
    setInnerInventoryBar("innerInventoryBar")
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
                  <div className="wrapperInventoryBar">
                    <div className={innerInventoryBar} style={{width:`${value.amount*100/denominatorValue}px`}}></div>
                  </div>
                  <p className='textElementMaterials'>{value.amount}{denominator}</p>
                </div>
              </div>
            );
          })}
      </div>
    </div>
  );
}
