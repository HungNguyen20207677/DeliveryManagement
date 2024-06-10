import { createBrowserRouter } from "react-router-dom";
import App from "../App";
import HomePage from "../HomePage/HomePage";
import LoginPage from "../LoginPage/LoginPage";
export const router = createBrowserRouter([
    {
        path:"/",
        element: <App />,
        children: [
            { path:"", element: <HomePage />},
            { path:"login", element: <LoginPage />},
        ]
    }
])