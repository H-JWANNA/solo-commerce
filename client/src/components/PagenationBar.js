import { useEffect, useState } from "react";

export default function PagenationBar(pageInfo) {
  const [start, setStart] = useState(0);
  const [end, setEnd] = useState(0);
  const [curPage, setCurPage] = useState(0);

  useEffect(() => {
    const MAX_PAGE = 5;
    setStart((pageInfo.page / MAX_PAGE) * MAX_PAGE + 1);
    setEnd(
      pageInfo.totalPages == 0
        ? 1
        : start + (MAX_PAGE - 1) < pageInfo.totalPages
        ? start + MAX_PAGE - 1
        : pageInfo.totalPages
    );
  }, [pageInfo]);

  return (
    <nav>
      <ul>
        {start > 1 ? (
          <>
            <li onClick={() => setCurPage(1)}>&lt;&lt;</li>
            <li onClick={() => setCurPage(curPage - 1)}>&lt;</li>
          </>
        ) : (
          <></>
        )}

        {end < pageInfo.totalPages ? (
          <>
            <li onClick={() => setCurPage(curPage + 1)}>&gt;</li>
            <li onClick={() => setCurPage(pageInfo.totalPages)}>&gt;&gt;</li>
          </>
        ) : (
          <></>
        )}
      </ul>
    </nav>
  );
}
