import { useEffect } from 'react'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import Button from '../Button/Button'

import {
  employeeSchema,
  type EmployeeFormData,
} from '../../schemas/employeeSchema'

import styles from './EmployeeForm.module.scss'
import type { ApiRequestError } from '../../services/employeeApi'

interface EmployeeFormProps {
  initialData?: EmployeeFormData
  onSubmit: (data: EmployeeFormData) => void
  submitLabel: string
  isSubmitting?: boolean
  onCancel?: () => void
  serverError?: ApiRequestError | null
}

const EmployeeForm = ({
  initialData,
  onSubmit,
  submitLabel,
  isSubmitting = false,
  onCancel,
  serverError
}: EmployeeFormProps) => {
  const {
    register,
    handleSubmit,
    reset,
    setError,
     setValue,
  watch,
    formState: { errors },
  } = useForm<EmployeeFormData>({
    resolver: zodResolver(employeeSchema),

    defaultValues: {
      firstName: '',
      middleName: '',
      lastName: '',
      email: '',
      mobile: '',
      residentialAddress: '',
      contractType: 'PERMANENT',
      startDate: '',
      finishedDate: '',
      ongoing: false,
      employmentType: 'FULL_TIME',
      hoursPerWeek: 38,
    },
  })

  const isOngoing = watch('ongoing')

  useEffect(() => {
    if (initialData) {
      reset(initialData)
    }
  }, [initialData, reset])

  useEffect(() => {
  if (serverError?.details) {
    Object.entries(serverError.details).forEach(
      ([field, messages]) => {
        setError(field as keyof EmployeeFormData, {
          type: 'server',
          message: messages[0],
        })
      }
    )
  }
}, [serverError, setError])



  return (

    
    <form
      className={styles.form}
      onSubmit={handleSubmit(onSubmit)}
    >

      {serverError && !serverError.details && (
  <p className={styles.serverError}>
    Unable to save employee. Please try again.
  </p>
)}
      {/* Personal Information */}
      <section className={styles.section}>
        <h2>Personal Information</h2>

        <div className={styles.field}>
          <label htmlFor="firstName">First Name</label>

          <input
            id="firstName"
            type="text"
            {...register('firstName')}
          />

          {errors.firstName && (
            <p className={styles.error}>
              {errors.firstName.message}
            </p>
          )}
        </div>

        <div className={styles.field}>
          <label htmlFor="middleName">Middle Name(if applicable)</label>

          <input
            id="middleName"
            type="text"
            {...register('middleName')}
          />

          {errors.middleName && (
            <p className={styles.error}>
              {errors.middleName.message}
            </p>
          )}
        </div>

        <div className={styles.field}>
          <label htmlFor="lastName">Last Name</label>

          <input
            id="lastName"
            type="text"
            {...register('lastName')}
          />

          {errors.lastName && (
            <p className={styles.error}>
              {errors.lastName.message}
            </p>
          )}
        </div>
      </section>

      {/* Contact Details */}
      <section className={styles.section}>
        <h2>Contact Details</h2>

        <div className={styles.field}>
          <label htmlFor="email">Email Address</label>

          <input
            id="email"
            type="email"
            {...register('email')}
          />

          {errors.email && (
            <p className={styles.error}>
              {errors.email.message}
            </p>
          )}
        </div>

        <div className={styles.field}>
          <label htmlFor="mobile">Mobile Number</label>

          <input
            id="mobile"
            type="tel"
            placeholder="04XXXXXXXX"
            {...register('mobile')}
          />

          {errors.mobile && (
            <p className={styles.error}>
              {errors.mobile.message}
            </p>
          )}
        </div>

        <div className={styles.field}>
          <label htmlFor="residentialAddress">
            Residential Address
          </label>

          <input
            id="residentialAddress"
            type="text"
            placeholder="Residential address"
            {...register('residentialAddress')}
          />

          {errors.residentialAddress && (
            <p className={styles.error}>
              {errors.residentialAddress.message}
            </p>
          )}
        </div>
      </section>

      {/* Employee Status */}
      <section className={styles.section}>
        <h2>Employee Status</h2>

        <fieldset className={styles.fieldset}>
          <legend>What is Contract Type</legend>

          <label>
            <input
              type="radio"
              value="PERMANENT"
              {...register('contractType')}
            />
            Permanent
          </label>

          <label>
            <input
              type="radio"
              value="CONTRACT"
              {...register('contractType')}
            />
            Contract
          </label>
        </fieldset>

        <div className={styles.field}>
          <label htmlFor="startDate">Start Date</label>

          <input
            id="startDate"
            type="date"
            {...register('startDate')}
          />

          {errors.startDate && (
            <p className={styles.error}>
              {errors.startDate.message}
            </p>
          )}
        </div>

        <div className={styles.field}>
          <label htmlFor="finishedDate">End Date</label>

          <input
  id="finishedDate"
  type="date"
  disabled={isOngoing}
  {...register('finishedDate')}
/>

          {errors.finishedDate && (
            <p className={styles.error}>
              {errors.finishedDate.message}
            </p>
          )}
        </div>

      <label className={styles.checkbox}>
  <input
    type="checkbox"
    {...register('ongoing', {
      onChange: (event) => {
        if (event.target.checked) {
          setValue('finishedDate', '')
        }
      },
    })}
  />
  Ongoing
</label>

        <fieldset className={styles.fieldset}>
          <legend>Is this on a full-time or part-time basis?</legend>

          <label>
            <input
              type="radio"
              value="FULL_TIME"
              {...register('employmentType')}
            />
            Full Time
          </label>

          <label>
            <input
              type="radio"
              value="PART_TIME"
              {...register('employmentType')}
            />
            Part Time
          </label>
        </fieldset>

        <div className={styles.field}>
          <label htmlFor="hoursPerWeek">
            Hours Per Week
          </label>

          <input
            id="hoursPerWeek"
            type="number"
            {...register('hoursPerWeek', {
              valueAsNumber: true,
            })}
          />

          {errors.hoursPerWeek && (
            <p className={styles.error}>
              {errors.hoursPerWeek.message}
            </p>
          )}
        </div>
      </section>

      {/* Form Actions */}
      <div className={styles.actions}>
  <Button
    type="submit"
    disabled={isSubmitting}
  >
    {isSubmitting ? 'Saving...' : submitLabel}
  </Button>

  {onCancel && (
    <Button
      type="button"
      variant="secondary"
      onClick={onCancel}
    >
      Cancel
    </Button>
  )}
</div>
    </form>
  )
}

export default EmployeeForm