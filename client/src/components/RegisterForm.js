import axios from "axios";
import { useRef } from "react";
import { useNavigate } from "react-router-dom";
import { SERVER_URL } from "../api/getUrl";

export default function RegisterForm() {
  const navigate = useNavigate();

  const idRef = useRef(null);
  const pwRef = useRef(null);
  const nameRef = useRef(null);
  const emailRef = useRef(null);
  const phoneRef = useRef(null);

  const handleRegister = async () => {
    try {
      const res = await axios.post(`${SERVER_URL}/members/register`, {
        username: idRef.current.value,
        password: pwRef.current.value,
        name: nameRef.current.value,
        email: emailRef.current.value,
        phoneNumber: phoneRef.current.value,
      });

      if (res.status === 201) {
        alert("회원가입이 완료되었습니다.");
        navigate("/login");
      }
    } catch (err) {
      console.log(err);
      alert("유효하지 않거나 이미 등록된 회원입니다.");
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    handleRegister();
  };

  return (
    <form onSubmit={handleSubmit}>
      <p>* 이 붙은 항목은 필수입니다.</p>
      <div className="input_area">
        <label>* ID</label>
        <input type="text" placeholder="아이디" ref={idRef} />
      </div>
      <div className="input_area">
        <label>* Password</label>
        <input type="password" placeholder="패스워드" ref={pwRef} />
      </div>
      <div className="input_area">
        <label>* Name</label>
        <input type="text" placeholder="이름" ref={nameRef} />
      </div>
      <div className="input_area">
        <label>Email</label>
        <input type="Email" placeholder="이메일" ref={emailRef} />
      </div>
      <div className="input_area">
        <label>* Phone Number</label>
        <input type="text" placeholder="휴대폰 번호" ref={phoneRef} />
      </div>
      <button>회원 가입</button>
    </form>
  );
}
