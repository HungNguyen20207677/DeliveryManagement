
import * as Yup from "yup";
import { useAuth } from "../Context/useAuth";
import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";


type Props ={};

type LoginFromInputs = {
    userName: string;
    password: string;
};

const validation = Yup.object().shape({
    userName: Yup.string().required("Username is required"),
    password: Yup.string().required("Password is required"),

});

const LoginPage = (props: Props) => {
    const { loginUser } = useAuth();
    const { register, handleSubmit, formState: { errors },} = useForm<LoginFromInputs>({ resolver: yupResolver(validation)});
    
    const handleLogin = (form: LoginFromInputs) => {
        loginUser(form.userName, form.password);
    }
    return (
        <div className="flex items-center justify-center min-h-screen bg-blue-50">
      <div className="w-full max-w-md p-8 space-y-8 bg-white rounded-lg shadow">
        <div className="flex justify-center mb-4">
          <img src="path_to_your_logo.png" alt="Logo" className="h-10" />
        </div>
        <h2 className="mt-6 text-3xl font-extrabold text-center text-gray-900">Đăng Nhập</h2>
        <form className="mt-8 space-y-6" action="#" method="POST">
          <div className="rounded-md shadow-sm">
            <div className="mb-4">
              <label htmlFor="username" className="sr-only">Tên người dùng</label>
              <input
                id="username"
                type="text"
                required
                className="relative block w-full px-3 py-2 text-gray-900 placeholder-gray-500 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                placeholder="Nhập tên người dùng"
                {...register("userName")}
              />
              {errors.userName ? <p>{errors.userName.message}</p> : ""}
            </div>
            <div>
              <label htmlFor="password" className="sr-only">Mật khẩu</label>
              <input
                id="password"
                type="password"
                required
                className="relative block w-full px-3 py-2 text-gray-900 placeholder-gray-500 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                placeholder="Nhập mật khẩu"
                {...register("password")}
              />
              {errors.password ? <p>{errors.password.message}</p> : ""}

            </div>
          </div>
          <div>
            <button
              type="submit"
              className="relative flex justify-center w-full px-4 py-2 text-sm font-medium text-white bg-blue-600 border border-transparent rounded-md group hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
            >
              Đăng Nhập
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default LoginPage;