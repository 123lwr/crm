<%--
  Created by IntelliJ IDEA.
  User: 86157
  Date: 2022/1/12
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    //创建人、创建时间应该是自动生成
    String createTime= DateTimeUtil.getSysTime();
    //创建人为当前登录的用户
    String createBy=((User)(request.getSession().getAttribute("user"))).getCreateBy();


    $.ajax({
    url:"",
    data:{

    },
    type:"",
    datatype:"",
    success:function (data) {

    }
    })
</body>
</html>
