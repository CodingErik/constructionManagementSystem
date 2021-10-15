import { useState, useEffect } from 'react';
import EmployeeDisplayTable from './EmployeeDisplayTable';

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
