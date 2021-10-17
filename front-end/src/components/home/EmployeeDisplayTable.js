import { useState, useEffect } from 'react';
import HomePageSpinner from './HomePageSpinner';
let columnBooleans = {
  employeeId: false,
  project: false,
  name: false,
  email: false,
  phoneNumber: false,
};

function EmployeeDisplayTable({ originalEmployeeList, title, filter }) {
  const [employeeList, setEmployeeList] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    setEmployeeList(
      [...originalEmployeeList].map((employee) => {
        if (employee.project === null) {
          employee.project = { id: 0, name: 'unassigned' };
          return employee;
        }
        return employee;
      })
    );

    setIsLoading(false);
  }, [originalEmployeeList]);

  const handleEmployeeColumnHeaderClick = (
    neededVariable,
    booleanVariable,
    methodTranslate
  ) => {
    const sort_by = (neededField, reverse, primer) => {
      const getField = (obj, path) =>
        path.split('.').reduce((value, el) => value[el], obj);
      const key = primer
        ? function (x) {
            return primer(getField(x, neededField));
          }
        : function (x) {
            return getField(x, neededVariable);
          };
      reverse = !reverse ? 1 : -1;

      return function (a, b) {
        return (a = key(a)), (b = key(b)), reverse * ((a > b) - (b > a));
      };
    };

    if (columnBooleans[booleanVariable]) {
      columnBooleans[booleanVariable] = false;
    } else {
      columnBooleans[booleanVariable] = true;
    }
    setEmployeeList(
      [...employeeList].sort(
        sort_by(
          neededVariable,
          columnBooleans[booleanVariable],
          methodTranslate
        )
      )
    );
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
        {isLoading ? (
          <HomePageSpinner />
        ) : (
          <table className='table table-hover m-auto'>
            <thead>
              <tr>
                <th
                  className='col-1'
                  onClick={() =>
                    handleEmployeeColumnHeaderClick(
                      'id',
                      'employeeId',
                      parseInt
                    )
                  }
                >
                  Id
                </th>
                <th
                  className='col-2'
                  onClick={() =>
                    handleEmployeeColumnHeaderClick('name', 'name', (a) =>
                      a.toUpperCase()
                    )
                  }
                >
                  Name
                </th>
                <th
                  className='col-3'
                  onClick={() =>
                    handleEmployeeColumnHeaderClick(
                      'project.name',
                      'project',
                      (a) => a.toUpperCase()
                    )
                  }
                >
                  Project
                </th>
                <th
                  className='col-2'
                  onClick={() =>
                    handleEmployeeColumnHeaderClick('email', 'email', (a) =>
                      a.toUpperCase()
                    )
                  }
                >
                  Email
                </th>
                <th
                  className='col-2'
                  onClick={() =>
                    handleEmployeeColumnHeaderClick(
                      'phoneNumber',
                      'phoneNumber',
                      (a) => a.toUpperCase()
                    )
                  }
                >
                  Phone #
                </th>
              </tr>
            </thead>
            <tbody>
              {employeeList
                .filter(
                  (employee) =>
                    employee?.title?.toLowerCase() === filter?.toLowerCase()
                )
                .map((filteredEmployee) => (
                  <tr className='table-active' key={filteredEmployee.id}>
                    <th scope='row'>{filteredEmployee.id}</th>
                    <td>{filteredEmployee.name}</td>
                    <td>{filteredEmployee.project?.name || 'None'} </td>
                    <td>{filteredEmployee.email}</td>
                    <td>{filteredEmployee.phoneNumber || 'None'}</td>
                    <td></td>
                  </tr>
                ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}

export default EmployeeDisplayTable;
