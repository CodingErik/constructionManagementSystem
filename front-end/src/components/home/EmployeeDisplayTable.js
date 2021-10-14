import { useState, useEffect } from 'react';
let columnBooleans = {
  employeeId: false,
  projectId: false,
  name: false,
  email: false,
  phoneNumber: false,
};

function EmployeeDisplayTable({ originalEmployeeList, title, filter }) {
  const [employeeList, setEmployeeList] = useState([]);

  useEffect(() => {
    setEmployeeList([...originalEmployeeList]);
  }, [originalEmployeeList]);

  const handleEmployeeColumnHeaderClick = (target) => {
    const sort_by = (field, reverse, primer) => {
      const key = primer
        ? function (x) {
            return primer(x[field]);
          }
        : function (x) {
            return x[field];
          };
      reverse = !reverse ? 1 : -1;

      return function (a, b) {
        return (a = key(a)), (b = key(b)), reverse * ((a > b) - (b > a));
      };
    };

    switch (target) {
      case 'employeeId':
        columnBooleans.employeeId = columnBooleans.employeeId ? false : true;
        setEmployeeList(
          [...employeeList].sort(
            sort_by('id', columnBooleans.employeeId, parseInt)
          )
        );
      // case 'projectId':
      //   columnBooleans.projectId = columnBooleans.projectId ? false : true;
      //   setEmployeeList(
      //     [...employeeList].sort(
      //       sort_by('projectId', columnBooleans.projectId, parseInt)
      //     )
      //   );
      case 'name':
        columnBooleans.name = columnBooleans.name ? false : true;
        setEmployeeList(
          [...employeeList].sort(
            sort_by('name', columnBooleans.name, (a) => a.toLowerCase())
          )
        );
      case 'email':
        columnBooleans.email = columnBooleans.email ? false : true;
        setEmployeeList(
          [...employeeList].sort(
            sort_by('email', columnBooleans.email, (a) => a.toLowerCase())
          )
        );
      // case 'phoneNumber':
      //   columnBooleans.phoneNumber = columnBooleans.phoneNumber ? false : true;
      //   setEmployeeList(
      //     [...employeeList]
      //       .filter((e) => e.phoneNumber)
      //       .sort((a, b) => {
      //         return (
      //           a.phoneNumber.split('-').join('') >
      //           b.phoneNumber.split('-').join('')
      //         );
      //       })
      //   );
      //   console.log(employeeList);
    }
  };
  return (
    <div className='row mt-3'>
      <h3 className='mb-3'>{title}</h3>
      <div
        className='table-responsive'
        style={{
          width: '100%',
          maxHeight: '400px',
          overflow: 'scroll',
        }}
      >
        <table className='table table-hover'>
          <thead>
            <tr>
              <th
                scope='col'
                onClick={() => handleEmployeeColumnHeaderClick('employeeId')}
              >
                Employee Id
              </th>
              <th
                scope='col'
                onClick={() => handleEmployeeColumnHeaderClick('name')}
              >
                Name
              </th>
              <th
                scope='col'
                onClick={() => handleEmployeeColumnHeaderClick('projectId')}
              >
                Project Id
              </th>
              <th
                scope='col'
                onClick={() => handleEmployeeColumnHeaderClick('email')}
              >
                Email
              </th>
              <th
                scope='col'
                onClick={() => handleEmployeeColumnHeaderClick('phoneNumber')}
              >
                Phone Number
              </th>
            </tr>
          </thead>
          <tbody>
            {employeeList
              .filter((employee) => employee.title.toLowerCase() === filter)
              .map((filteredEmployee) => (
                <tr className='table-active' key={filteredEmployee.id}>
                  <th scope='row'>{filteredEmployee.id}</th>
                  <td>{filteredEmployee.name}</td>
                  <td>{filteredEmployee.projectId}</td>
                  <td>{filteredEmployee.email}</td>
                  <td>{filteredEmployee.phoneNumber}</td>
                  <td></td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default EmployeeDisplayTable;
