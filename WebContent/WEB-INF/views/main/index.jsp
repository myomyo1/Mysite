<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/main.css" rel="stylesheet"
	type="text/css">
<title>Mysite</title>
</head>
<body>
	<div id="container">
		<!-- header -->
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>

		<!-- /navigation -->
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>

		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img style="width: 500px" id="profile" src="/mysite/assets/images/profile2.jpg">
					<h2>
						멍멍! 멍!!멍멍멍!! 왈!!! 왈왈!! <br />
					</h2>
					<p>	
						왈왈 엄마가 여기는 웹 프로그램밍 실습과제 예제 사이트래요!!왈!! <br>
						JAVA 수업 + 데이터베이스 수업 + 웹프로그래밍 수업 사이ㅌ으으 멍멍!!!!!!<br>
															
						<br> <a href="/mysite/guestbook?a=list">방명록</a>에 글 남기기 <br>
					</p>
				</div>
			</div>
		</div>

		<!-- /footer -->
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>

	</div>
</body>
</html>