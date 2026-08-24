import { useMutation } from '@tanstack/react-query'
import { Link, useNavigate } from 'react-router-dom'

import EmployeeForm from '../../components/EmployeeForm/EmployeeForm'
import {
  ApiRequestError,
  createEmployee,
} from '../../services/employeeApi'
import type { EmployeeFormData } from '../../schemas/employeeSchema'

import styles from './AddEmployee.module.scss'

const AddEmployee = () => {
  const navigate = useNavigate()

  const createEmployeeMutation = useMutation({
    mutationFn: createEmployee,

    onSuccess: (employee) => {
      console.log('Employee created:', employee)
      navigate('/')
    },

    onError: (error) => {
      console.error('Failed to create employee:', error)
    },
  })

  const onSubmit = (data: EmployeeFormData) => {
    createEmployeeMutation.mutate(data)
  }

  return (
    <main className={styles.page}>
      <Link to="/" className={styles.backLink}>
        ← Back to Employee List
      </Link>

      <h1 className={styles.title}>Employee Details</h1>

      <p className={styles.description}>
        Please enter the employee details below.
      </p>

      <EmployeeForm
        onSubmit={onSubmit}
        submitLabel="Save"
        isSubmitting={createEmployeeMutation.isPending}
        onCancel={() => navigate('/')}
        serverError={
          createEmployeeMutation.error instanceof ApiRequestError
            ? createEmployeeMutation.error
            : null
        }
      />
    </main>
  )
}

export default AddEmployee