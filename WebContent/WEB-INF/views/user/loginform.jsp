<%@page import="com.javaex.util.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>

	<div id="container">

		<!-- header -->
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>

		<!-- /navigation -->
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>

		<div id="wrapper">
			<div id="content">
				<div id="user">

					<form id="login-form" name="loginform" method="get" action="/mysite/user">
						
						<label class="block-label" for="email">이메일</label>
						<input id="email" name="email" type="text" value="">
						
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value="">
						 						
						<c:if test="${'fail' eq param.result }">
							<p>로그인실패<p>
						</c:if>
						<input type="hidden" name="a" value="login" />
						<input type="submit" value="로그인">
					</form>

				</div>
				<!-- /user -->
			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->

		<!-- /footer -->
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>

	</div>
	<!-- /container -->


</body>
</html>