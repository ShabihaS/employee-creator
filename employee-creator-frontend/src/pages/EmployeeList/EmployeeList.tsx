
import { useState } from 'react'
import ConfirmModal from '../../components/ConfirmModal/ConfirmModal'
import { Link } from 'react-router-dom'
import {
  useMutation,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query'

import {
  getEmployees,
  deleteEmployee,
} from '../../services/employeeApi'

import Button from '../../components/Button/Button'

import styles from './EmployeeList.module.scss'

const EmployeeList = () => {
  const queryClient = useQueryClient()

  const {
    data: employees,
    isLoading,
    isError,
  } = useQuery({
    queryKey: ['employees'],
    queryFn: getEmployees,
  })

  const deleteMutation = useMutation({
    mutationFn: deleteEmployee,

    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ['employees'],
      })
    },
  })

  const [selectedEmployee, setSelectedEmployee] = useState<{
  id: number
  name: string
} | null>(null)

  const handleDelete = (id: number, name: string) => {
  setSelectedEmployee({
    id,
    name,
  })
}

const confirmDelete = () => {
  if (!selectedEmployee) {
    return
  }

  deleteMutation.mutate(selectedEmployee.id, {
    onSuccess: () => {
      setSelectedEmployee(null)
    },
  })
}

const cancelDelete = () => {
  if (!deleteMutation.isPending) {
    setSelectedEmployee(null)
  }
}

  if (isLoading) {
    return (
      <main className={styles.page}>
        <p className={styles.statusMessage}>
          Loading employees...
        </p>
      </main>
    )
  }

  if (isError) {
    return (
      <main className={styles.page}>
        <div className={styles.errorMessage}>
          <p>Unable to load employees. Please try again.</p>

          <Button
            type="button"
            onClick={() =>
              queryClient.invalidateQueries({
                queryKey: ['employees'],
              })
            }
          >
            Try Again
          </Button>
        </div>
      </main>
    )
  }

  return (
    <main className={styles.page}>
      <div className={styles.header}>
        
        <div>
          <h1>Employee List</h1>

          <p>
            Please click the edit button to find more
            details about an employee.
          </p>
        </div>

        <Link
          to="/employees/new"
          className={styles.addButton}
        >
          Add Employee
        </Link>
      </div>

       {deleteMutation.isError && (
    <p className={styles.deleteError}>
      Unable to remove employee. Please try again.
    </p>
  )}

      {employees?.length === 0 ? (
        <p className={styles.statusMessage}>
          No employees found.
        </p>
      ) : (
        <div className={styles.employeeList}>
          {employees?.map((employee) => (
            <article
              key={employee.id}
              className={styles.employeeCard}
            >
              <div>
                <h2>
                  {employee.firstName}{' '}
                  {employee.lastName}
                </h2>

                <p>{employee.email}</p>

                <p>{employee.contractType}</p>
              </div>

              <div className={styles.actions}>
                <Link
                  to={`/employees/${employee.id}/edit`}
                  className={styles.editButton}
                >
                  Edit
                </Link>

                <Button
                  type="button"
                  variant="danger"
                  disabled={deleteMutation.isPending}
                  onClick={() =>
  handleDelete(
    employee.id,
    `${employee.firstName} ${employee.lastName}`
  )
}
                >
                  {deleteMutation.isPending
                    ? 'Removing...'
                    : 'Remove'}
                </Button>
              </div>
            </article>
          ))}
        </div>
      )}
      <ConfirmModal
  isOpen={selectedEmployee !== null}
  title="Delete Employee"
  message={
    selectedEmployee
      ? `Are you sure you want to delete ${selectedEmployee.name}?`
      : ''
  }
  onConfirm={confirmDelete}
  onCancel={cancelDelete}
  isConfirming={deleteMutation.isPending}
/>
    </main>
  )
}

export default EmployeeList