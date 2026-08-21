export interface Employee {
  id: number
  firstName: string
  middleName?: string
  lastName: string
  email: string
  mobile: string
  residentialAddress: string
  contractType: 'PERMANENT' | 'CONTRACT'
  startDate: string
  endDate?: string
  ongoing: boolean
  employmentType: 'FULL_TIME' | 'PART_TIME'
  hoursPerWeek: number
}