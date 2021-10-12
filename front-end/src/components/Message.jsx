import React from 'react';

function Message({ variant, children }) {
  return (
    <div
      className={`alert alert-${variant}`}
      style={{ fontSize: '1.5rem' }}
      role='alert'
    >
      {children}
    </div>
  );
}

Message.defaultProps = {
  variant: 'info',
};

export default Message;
