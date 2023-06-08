import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";

import Home from "./Screen/Page/Home";
import SignUp from "./Screen/Page/SignUp";

function App() {
  return (
      <BrowserRouter>
          <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/signUp" element={<SignUp />} />
          </Routes>
      </BrowserRouter>
  );
}

export default App;
