import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { RecoilRoot } from "recoil";
import Footer from "./components/Footer";
import Menu from "./components/Menu";
import Main from "./pages/Main";
import Login from "./pages/Login";
import Register from "./pages/Register";
import EmptyPage from "./pages/EmptyPage";
import Header from "./components/Header";
import SearchPage from "./pages/SearchPage";
import StoreList from "./components/StoreList";
import ProductList from "./components/ProductList";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <RecoilRoot>
          <Header />
          <Menu />
          <Routes>
            <Route path="/" element={<Main />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/search" elements={<SearchPage />}>
              <Route path="stores" element={<StoreList />} />
              <Route path="products" element={<ProductList />} />
            </Route>
            <Route path="/*" elements={<EmptyPage />} />
          </Routes>
          <Footer />
        </RecoilRoot>
      </div>
    </BrowserRouter>
  );
}

export default App;
