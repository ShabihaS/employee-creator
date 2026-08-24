import { z } from 'zod'

export const employeeSchema = z
  .object({
    firstName: z
      .string()
      .min(1, 'First name is required')
      .max(50, 'First name must be 50 characters or less'),

    middleName: z
      .string()
      .max(50, 'Middle name must be 50 characters or less')
      .optional(),

    lastName: z
      .string()
      .min(1, 'Last name is required')
      .max(50, 'Last name must be 50 characters or less'),

    email: z
      .string()
      .min(1, 'Email is required')
      .email('Please enter a valid email address'),

    mobile: z
      .string()
      .min(1, 'Mobile number is required')
      .regex(
        /^(?:\+61|0)4\d{8}$/,
        'Please enter a valid Australian mobile number'
      ),

    residentialAddress: z
      .string()
      .min(1, 'Residential address is required')
      .max(200, 'Address must be 200 characters or less'),

    contractType: z.enum(['PERMANENT', 'CONTRACT']),

    startDate: z
      .string()
      .min(1, 'Start date is required'),

    endDate: z
      .string()
      .optional(),

    ongoing: z.boolean(),

    employmentType: z.enum([
      'FULL_TIME',
      'PART_TIME',
    ]),

    hoursPerWeek: z
      .number({
        error: 'Hours per week is required',
      })
      .min(1, 'Hours per week must be greater than 0')
      .max(168, 'Hours per week cannot exceed 168'),
  })
  .superRefine((data, ctx) => {
    if (data.ongoing && data.endDate) {
      ctx.addIssue({
        code: 'custom',
        path: ['endDate'],
        message: 'End date must be empty when ongoing is selected',
      })
    }

    if (
      data.contractType === 'CONTRACT' &&
      !data.ongoing &&
      !data.endDate
    ) {
      ctx.addIssue({
        code: 'custom',
        path: ['endDate'],
        message: 'End date is required for a contract employee',
      })
    }
  })

export type EmployeeFormData = z.infer<typeof employeeSchema>