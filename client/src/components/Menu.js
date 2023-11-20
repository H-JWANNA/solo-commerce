import { Link } from "react-router-dom";

export default function Menu() {
  return (
    <nav className="menu">
      <Link to="/">홈</Link>
      <Link to="/search/stores">매장으로 검색</Link>
      <Link to="/search/products">상품으로 검색</Link>
    </nav>
  );
}
