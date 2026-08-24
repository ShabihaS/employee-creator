import { Link, useNavigate, useParams } from 'react-router-dom'
import { useMutation, useQuery } from '@tanstack/react-query'
import styles from './EditEmployee.module.scss'

import EmployeeForm from '../../components/EmployeeForm/EmployeeForm'
import {
  getEmployeeById,
  updateEmployee,
} from '../../services/employeeApi'

const EditEmployee = () => {
  const { id } = useParams()
  const navigate = useNavigate()

  const employeeId = Number(id)

  const {
    data: employee,
    isLoading,
    isError,
  } = useQuery({
    queryKey: ['employee', employeeId],
    queryFn: () => getEmployeeById(employeeId),
  })

  const updateMutation = useMutation({
    mutationFn: (data: Parameters<typeof updateEmployee>[1]) =>
      updateEmployee(employeeId, data),

    onSuccess: () => {
      navigate('/')
    },
  })

  if (isLoading) {
    return <p>Loading employee...</p>
  }

  if (isError || !employee) {
    return <p>Employee could not be found.</p>
  }

  return (
  <main className={styles.page}>
    <Link to="/" className={styles.backLink}>
      ← Back to Employee List
    </Link>

    <h1 className={styles.title}>Edit Employee</h1>

    <p className={styles.description}>
      Update the employee details below.
    </p>

    <EmployeeForm
  initialData={{
    firstName: employee.firstName,
    middleName: employee.middleName ?? '',
    lastName: employee.lastName,
    email: employee.email,
    mobile: employee.mobile,
    residentialAddress: employee.residentialAddress,
    contractType: employee.contractType,
    startDate: employee.startDate,
    endDate: employee.endDate ?? '',
    ongoing: employee.ongoing,
    employmentType: employee.employmentType,
    hoursPerWeek: employee.hoursPerWeek,
  }}
  onSubmit={(data) => updateMutation.mutate(data)}
  submitLabel="Save Changes"
  isSubmitting={updateMutation.isPending}
  onCancel={() => navigate('/')}
/>

  </main>
)
}

export default EditEmployee