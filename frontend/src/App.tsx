import "react-toastify/dist/ReactToastify.css"
import { ToastContainer } from 'react-toastify'
import { UserProvider } from './Context/useAuth'
import './index.css';
import LoginPage from "./LoginPage/LoginPage";

function App() {
  return (

    <div >
      <LoginPage />
      {/* <UserProvider>
        <LoginPage />
        <ToastContainer />
      </UserProvider> */}
    </div>

  )
}

export default App
