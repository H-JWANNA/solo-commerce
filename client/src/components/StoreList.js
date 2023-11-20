import axios from "axios";
import { SERVER_URL } from "../api/getUrl";
import { useEffect, useState } from "react";

export default function StoreList() {
  const [stores, setStores] = useState([]);
  const [pageInfo, setPageInfo] = useState();

  const fetchData = async () => {
    const res = await axios.get(`${SERVER_URL}/search/stores`);
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
            <th>매장 이름</th>
            <th>매장 주소</th>
          </tr>
        </thead>
        {stores.map((store) => (
          <tbody key={store.storeId}>
            <tr>
              <td>{store.storeName}</td>
              <td>{store.address}</td>
            </tr>
          </tbody>
        ))}
      </table>
    </div>
  );
}
