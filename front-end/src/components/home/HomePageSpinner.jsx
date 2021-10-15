import React from 'react';

function HomePageSpinner() {
  return (
    <div
      class='d-flex justify-content-center '
      style={{
        minHeight: '300px',
        minWidth: '100%',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
      }}
    >
      <div
        class='spinner-border'
        role='status'
        style={{
          width: '3rem',
          height: '3rem',
        }}
      >
        <span class='sr-only'></span>
      </div>
    </div>
  );
}

export default HomePageSpinner;
