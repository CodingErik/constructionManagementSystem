import React from 'react';

function Spinner() {
  return (
    <div
      class='d-flex justify-content-center '
      style={{
        minHeight: '90vh',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
      }}
    >
      <div
        class='spinner-border'
        role='status'
        style={{
          width: '5rem',
          height: '5rem',
        }}
      >
        <span class='sr-only'></span>
      </div>
    </div>
  );
}

export default Spinner;
