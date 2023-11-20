import { Outlet } from "react-router-dom";

export default function SearchProducts() {
  return (
    <div>
      <div>
        <input type="text" placeholder="검색어를 입력하세요" />
        <button type="submit">검색</button>
      </div>
      <Outlet />
    </div>
  );
}
