<!DOCTYPE html>
<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>オンラインショップ</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="/jsp/header.jsp" />
	<div id="mainArea">
		<h1><c:out value="${productlist[0].category.categoryName}" /></h1>

		<%-- contents start --%>

		<div id="target" style="color: red;">
			<c:out value="${message}" />
		</div>

		<c:if test="${!empty productlist}">
			<h2>検索結果 <c:out value="${productlist.size()}" />件</h2>
			<form method="get" action="${pageContext.request.contextPath}/mserv">
				<table border="1" id="product.list"><%--qq isso --%>
					<tr>
            			<th>商品ID</th>
            			<th>商品名</th>
            			<th>価格</th>
			            <th>在庫数</th>
			            <th></th>
			            <th></th>
        			</tr>
        <c:forEach var="product" items="${productlist}">
            <tr>
                <td><c:out value="${product.productId}" /></td>
                <td><c:out value="${product.productName}" /></td>
                <td><c:out value="${product.price}" />円</td>
                <td><c:out value="${product.stock.quantity}" />個</td>
                <td><a href="product-detail-view.jsp?productId=<c:out value="${product.productId}" />">詳細を見る</a></td>
                <td><input type="submit" value="カートに入れる"
						onclick="this.form.flag.value='B0101AddToCart'">
				</td>
            </tr>
        </c:forEach>
    			</table>
			</form>
		</c:if>
				<p>
			<a href="${pageContext.request.contextPath}/mserv">[トップに戻る]</a>
		</p>
		<%-- contents end --%>
	</div>
	<div id="footerArea">
		<small> Copyright YYYY FUJITSU LEARNING MEDIA LIMITED </small>
	</div>
</body>
</html>