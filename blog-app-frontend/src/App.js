import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./pages/Home";
import SignIn from "./pages/SignIn";
import SignUp from "./pages/SignUp";
import About from "./pages/About";

function App() {
  return (
      <BrowserRouter>
          <Routes>
              <Route path={"/"} element={<Home/>}/>
              <Route path={"/login"} element={<SignIn/>}/>
              <Route path={"/signup"} element={<SignUp/>}/>
              <Route path={"/about"} element={<About/>}/>
          </Routes>
      </BrowserRouter>

  );
}

export default App;
