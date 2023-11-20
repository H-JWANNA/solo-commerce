import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { SERVER_URL } from "../api/getUrl";
import { LoginState } from "../recoil/LoginState";
import secureLocalStorage from "react-secure-storage";

export default function Header() {
  const [isLogin, setIsLogin] = useState(LoginState);
  const navigate = useNavigate();

  console.log("load Header");

  const handleLogout = async () => {
    try {
      axios.delete(`${SERVER_URL}/auth/logout`, {
        headers: {
          Authorization: secureLocalStorage.getItem("token"),
        },
      });
      secureLocalStorage.removeItem("token");
      setIsLogin(false);
      navigate("/");
    } catch (err) {
      console.log(err);
      alert("로그아웃에 실패했습니다.");
    }
  };

  return (
    <header>
      {isLogin ? (
        <>
          <button onClick={handleLogout}>로그아웃</button>
        </>
      ) : (
        <>
          <Link to="/register">회원가입</Link>
          <Link to="/login">로그인</Link>
        </>
      )}
    </header>
  );
}
