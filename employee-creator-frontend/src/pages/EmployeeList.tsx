import { useQuery } from '@tanstack/react-query'
import { getEmployees } from '../services/employeeApi'

const EmployeeList = () => {
  const {
    data: employees,
    isLoading,
    isError,
  } = useQuery({
    queryKey: ['employees'],
    queryFn: getEmployees,
  })

  if (isLoading) {
    return <p>Loading employees...</p>
  }

  if (isError) {
    return <p>Failed to load employees.</p>
  }

  return (
    <div>
      <h1>Employees</h1>

      {employees?.map((employee) => (
        <div key={employee.id}>
          <h2>
            {employee.firstName} {employee.lastName}
          </h2>

          <p>{employee.email}</p>
          <p>{employee.contractType}</p>
        </div>
      ))}
    </div>
  )
}

export default EmployeeList