import type { Employee } from '../types/employee'
import type { EmployeeFormData } from '../schemas/employeeSchema'

const API_URL = `${import.meta.env.VITE_API_URL}/employees`

export interface ApiError {
  timestamp: string
  status: number
  error: string
  message: string
  path: string
  details?: Record<string, string[]>
}

export class ApiRequestError extends Error {
  status: number
  details?: Record<string, string[]>

  constructor(
    message: string,
    status: number,
    details?: Record<string, string[]>
  ) {
    super(message)
    this.name = 'ApiRequestError'
    this.status = status
    this.details = details
  }
}

const handleResponse = async (response: Response) => {
  if (response.ok) {
    return response
  }

  const errorData: ApiError = await response.json()

  throw new ApiRequestError(
    errorData.message,
    errorData.status,
    errorData.details
  )
}

export const getEmployees = async (): Promise<Employee[]> => {
  const response = await fetch(API_URL)

   await handleResponse(response)

  return response.json()
}

export const createEmployee = async (
  employee: EmployeeFormData
): Promise<Employee> => {
  const response = await fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(employee),
  })

   await handleResponse(response)

  return response.json()
}

export const getEmployeeById = async (id: number): Promise<Employee> => {
  const response = await fetch(`${API_URL}/${id}`)

  await handleResponse(response)

  return response.json()
}

export const updateEmployee = async (
  id: number,
  employee: EmployeeFormData
): Promise<Employee> => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(employee),
  })

   await handleResponse(response)

  return response.json()
}

export const deleteEmployee = async (id: number): Promise<void> => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'DELETE',
  })

   await handleResponse(response)
}