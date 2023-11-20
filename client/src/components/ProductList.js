import axios from "axios";
import { useEffect, useState } from "react";
import { SERVER_URL } from "../api/getUrl";

export default function ProductList() {
  const [products, setStores] = useState([]);
  const [pageInfo, setPageInfo] = useState();

  const fetchData = async () => {
    const res = await axios.get(`${SERVER_URL}/search/products`);
    setStores(res.data.data);
    setPageInfo(res.data.pageInfo);
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>상품명</th>
            <th>가격</th>
            <th>상품 정보</th>
          </tr>
        </thead>
        {products.map((product) => (
          <tbody key={product.productId}>
            <tr>
              <td>{product.productName}</td>
              <td>{product.price}</td>
              <td>{product.information}</td>
            </tr>
          </tbody>
        ))}
      </table>
    </div>
  );
}
