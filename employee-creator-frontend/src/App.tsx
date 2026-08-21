import { BrowserRouter, Routes, Route } from 'react-router-dom'
import EmployeeList from './pages/EmployeeList'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<EmployeeList />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App