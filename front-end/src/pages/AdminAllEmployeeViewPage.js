import AllEmployeeTable from "../components/admin/AllEmployeeTable";
import { useEffect, useState } from "react";
import { EmployeeAPI } from "../api";

function AdminAllEmployeeViewPage() {
    const [originalEmployeeList, setOriginalEmployeeList] = useState([]);

    useEffect(() => {
        EmployeeAPI.getAllEmployees().then(response => {
            setOriginalEmployeeList(response.data);
        })
    }, [])

    return (
        <div>
            <AllEmployeeTable originalEmployeeList={originalEmployeeList} />
        </div>
    )
}

export default AdminAllEmployeeViewPage;