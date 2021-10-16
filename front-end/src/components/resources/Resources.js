import React, { useEffect, useState } from 'react';
import HomePageSpinner from '../home/HomePageSpinner';
import './Resources.css';

export default function Resources({
  resources,
  title,
  denominator,
  denominatorValue,
}) {
  const [resourcesObject, setResources] = useState();
  const [innerInventoryBar, setInnerInventoryBar] = useState();
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    setResources(resources);
    setInnerInventoryBar('innerInventoryBar');
    // console.log(resources);
    setIsLoading(false);
  }, [resources]);

  return (
    <div className=''>
      <h4 className='mb-4'>{title}</h4>
      <div
        className='resourcesWrapper row mt-3'
        style={{
          minHeight: '375px',
          minWidth: '275px',
        }}
      >
        {isLoading ? (
          <HomePageSpinner />
        ) : (
          resourcesObject &&
          Object.entries(resourcesObject).map(([key, value]) => {
            return (
              <div className='col-6'>
                <div className='materialOrMachineryIcon'>
                  <p className='textElementMaterials'>{value.name}</p>
                  <img
                    src={value.icon}
                    width='50'
                    height='50'
                    alt={value.name}
                  ></img>
                  <div className='wrapperInventoryBar'>
                    <div
                      className={innerInventoryBar}
                      style={{
                        width: `${(value.amount * 97.6) / denominatorValue}px`,
                      }}
                    ></div>
                  </div>
                  <p className='textElementMaterials'>
                    {value.amount}
                    {denominator}
                  </p>
                </div>
              </div>
            );
          })
        )}
      </div>
    </div>
  );
}
