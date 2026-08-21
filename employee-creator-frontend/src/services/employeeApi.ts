import type { Employee } from '../types/employee'

const API_URL = `${import.meta.env.VITE_API_URL}/employees`

export const getEmployees = async (): Promise<Employee[]> => {
  const response = await fetch(API_URL)

  if (!response.ok) {
    throw new Error('Failed to fetch employees')
  }

  return response.json()
}