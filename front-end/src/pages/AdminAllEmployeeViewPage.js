import AllEmployeeTable from '../components/admin/AllEmployeeTable';
import { useEffect, useState } from 'react';
import { EmployeeAPI } from '../api';
import Spinner from '../components/spinner/Spinner';

function AdminAllEmployeeViewPage() {
  const [originalEmployeeList, setOriginalEmployeeList] = useState([]);
  const [isLoading, setIsloading] = useState(true);

  useEffect(() => {
    EmployeeAPI.getAllEmployees().then((response) => {
      setOriginalEmployeeList(response.data);
    });

    setIsloading(false);
  }, []);

  if (isLoading) {
    return <Spinner />;
  }

  return (
    <div>
      <AllEmployeeTable originalEmployeeList={originalEmployeeList} />
    </div>
  );
}

export default AdminAllEmployeeViewPage;
