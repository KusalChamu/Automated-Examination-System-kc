import { createBrowserRouter, createRoutesFromElements, RouterProvider, Route } from 'react-router-dom';
import Login from './pages/Login';
import StudentHomepage from './pages/StudentHomePage';
import StudentQuiz from './pages/StudentQuiz';
import StudentAssignment from './pages/StudentAssignment';
import StudentExams from './pages/StudentExams';
import StudentNotes from './pages/StudentNotes';
import StudentReports from './pages/StudentReports';
import Scomplains from './pages/Scomplains';
import { UserProvider } from "./components/userContext"; 
import AdminHome from './pages/AdminHome';
import LecturerHome from './pages/Lecturer/LecturerHome';
import CreateEvent from './pages/Lecturer/LecturerCreate';
import LecturerNotify from './pages/Lecturer/LecturerNotify';
import LecturerNote from './pages/Lecturer/LecturerNote';
import LecturerReports from './pages/Lecturer/LecturerReports';
import Lcomplain from './pages/Lecturer/Lcomplain';

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route>
      <Route path="/" element={<Login />} />
      <Route path="/student" element={<StudentHomepage />} />
      <Route path="/student-quiz" element={<StudentQuiz />} />
      <Route path="/student-assign" element={<StudentAssignment />} />
      <Route path="/student-exam" element={<StudentExams />} />
      <Route path="/student-note" element={<StudentNotes />} />
      <Route path="/student-report" element={<StudentReports />} />
      <Route path="/student-complain" element={<Scomplains />} />
      <Route path="/admin" element={<AdminHome />} />
      <Route path="/lecturer" element={<LecturerHome />} />
      <Route path="/create-event" element={<CreateEvent />} />
      <Route path="/notify" element={<LecturerNotify />} />
      <Route path="/lecturer-note" element={<LecturerNote />} />
      <Route path="/lecturer-report" element={<LecturerReports />} />
      <Route path="/lecturer-complain" element={<Lcomplain />} />
    </Route>
  )
);

function App() {
  return (
    <UserProvider>  {/* Wrap RouterProvider with UserProvider */}
      <RouterProvider router={router} />
    </UserProvider>
  );
}

export default App;
