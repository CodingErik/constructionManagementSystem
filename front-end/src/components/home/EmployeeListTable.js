import { useState, useEffect } from 'react';
import EmployeeDisplayTable from './EmployeeDisplayTable';
let columnBooleans = {
  employeeId: false,
  name: false,
  projectId: false,
  email: false,
  phoneNumber: false,
};

function EmployeeListTable({ employeeList }) {
  return (
    <div className='mt-3'>
      <EmployeeDisplayTable
        originalEmployeeList={employeeList}
        title='Architects'
        filter='architect'
      />
      <EmployeeDisplayTable
        originalEmployeeList={employeeList}
        title='Employees'
        filter='employee'
      />
    </div>
  );
}

export default EmployeeListTable;
