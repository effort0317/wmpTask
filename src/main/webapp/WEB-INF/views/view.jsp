<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <form action="/server/printController.do" method="post">
            URL: <input type="text" name="url" />
            TYPE:
            <select name="type">
                <option value="all">Text 전체</option>
                <option value="noTag">HTML태그제외</option>
            </select>
            출력 묶음 단위 : <input type="text" name="unit" />
            <button type="submit">출력</button>
        </form>
        <ul>
            <li>몫: ${effectVO.share}</li>
            <li>나머지: ${effectVO.rest}</li>
        </ul>
    </body>
</html>