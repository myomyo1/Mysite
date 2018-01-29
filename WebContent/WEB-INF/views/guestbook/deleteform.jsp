<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">
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
				<div id="guestbook" class="delete-form">

					<form action="/mysite/guestbook" method="get">

						<label>비밀번호</label> <input type="password" name="password"
							value=""> <input type="hidden" name="no"
							value="${param.no}"> <input type="hidden" name="a"
							value="delete"> <input type="submit" value="확인">

					</form>
					<a href="/WEB-INT/guestbook?a=list">방명록 리스트</a>

				</div>
			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->

		<!-- /footer -->
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>
	<!-- /footer -->

	</div>
	<!-- /container -->

</body>
</html>
