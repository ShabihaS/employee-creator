import { BrowserRouter, Routes, Route } from 'react-router-dom'
import EmployeeList from './pages/EmployeeList/EmployeeList'
import AddEmployee from './pages/AddEmployee/AddEmployee'
import EditEmployee from './pages/EditEmployee/EditEmployee'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<EmployeeList />} />
        <Route path="/employees/new" element={<AddEmployee />} />
        <Route path="/employees/:id/edit" element={<EditEmployee />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App