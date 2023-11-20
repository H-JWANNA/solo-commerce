import axios from "axios";
import secureLocalStorage from "react-secure-storage";
import { useRef } from "react";
import { Link, useNavigate } from "react-router-dom";
import { SERVER_URL } from "../api/getUrl";
import { useRecoilState } from "recoil";
import { LoginState } from "../recoil/LoginState";

export default function LoginForm() {
  const [isLogin, setIsLogin] = useRecoilState(LoginState);
  const navigate = useNavigate();

  const idRef = useRef(null);
  const pwRef = useRef(null);

  const handleLogin = async () => {
    try {
      const body = {
        username: idRef.current.value,
        password: pwRef.current.value,
      };

      const res = await axios.post(`${SERVER_URL}/auth/login`, body);

      if (res.data !== "") {
        const token = res.headers.getAuthorization();
        secureLocalStorage.setItem("token", token);
        setIsLogin(true);

        alert(`환영합니다. ${body.username}님`);
        navigate("/");
      } else {
        alert("아이디 또는 비밀번호가 일치하지 않습니다.");
      }
    } catch (err) {
      console.log(err);
      alert("존재하지 않는 회원이거나 입력하신 정보가 일치하지 않습니다.");
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    handleLogin();
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <div className="input_area">
          <label>ID</label>
          <input type="text" ref={idRef} />
        </div>
        <div className="input_area">
          <label>Password</label>
          <input type="password" ref={pwRef} />
        </div>
        <button>로그인</button>
      </form>
      <div>
        <Link to="/register">회원가입</Link>
      </div>
    </div>
  );
}
