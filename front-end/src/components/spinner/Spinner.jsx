import React from 'react';

function Spinner() {
  return (
    <div
      className='d-flex justify-content-center '
      style={{
        minHeight: '90vh',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
      }}
    >
      <div
        className='spinner-border'
        role='status'
        style={{
          width: '5rem',
          height: '5rem',
        }}
      >
        <span className='sr-only'></span>
      </div>
    </div>
  );
}

export default Spinner;
