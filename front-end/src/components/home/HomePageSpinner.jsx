import React from 'react';

function HomePageSpinner() {
  return (
    <div
      className='d-flex justify-content-center '
      style={{
        minHeight: '300px',
        minWidth: '100%',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
      }}
    >
      <div
        className='spinner-border'
        role='status'
        style={{
          width: '3rem',
          height: '3rem',
        }}
      >
        <span className='sr-only'></span>
      </div>
    </div>
  );
}

export default HomePageSpinner;
