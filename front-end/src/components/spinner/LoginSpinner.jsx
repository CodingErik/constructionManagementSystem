import React from 'react';

function LoginSpinner() {
  return (
    <div
      className='d-flex justify-content-center '
      style={{
        zIndex: '100',
        height: '100vh',
        width: '100vw',
        display: 'flex',
        position: 'absolute',
        right: '0%',
        top: '0%',
        alignItems: 'center',
        justifyContent: 'center',
        backgroundColor: 'whitesmoke',
        opacity: '0.3',
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

export default LoginSpinner;
