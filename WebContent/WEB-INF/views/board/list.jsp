<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">

		<!-- header -->
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>

		<!-- /navigation -->
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>

		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value=""> 
					<input	type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>

					<c:forEach items="${list}" var="b" varStatus="status">
						<c:choose>
							<c:when test="${ !empty authUser && authUser.name eq b.name}">
								<tr>
									<td>${b.no}</td>
									<td><a href="/mysite/board?a=view&no=${b.no }">${b.title }</a></td>
									<!-- 여기서 링크를 "/mysite/board?a=view&writer=f" 이렇게 주면 a태그 자체가사용자가 주소로 들어갈 수 있게 하는것이므로 보안문제. -->
									<td>${b.name }</td>
									<td>${b.hit }</td>
									<td>${b.regDate }</td>
									<td><a href="/mysite/board?a=delete&no=${b.no }" class="del"> 삭제</a></td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td>${b.no }</td>
									<td><a href="/mysite/board?a=view&no=${b.no }">${b.title }</a></td>
									<td>${b.name }</td>
									<td>${b.hit }</td>
									<td>${b.regDate}</td>
									<td></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</table>

				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li><a href="">2</a></li>
						<li class="selected">3</li>
						<li><a href="">4</a></li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>
				<c:choose>
					<c:when test="${empty authUser }">
						<p align="center">로그인 후 사용해주세요</p>
					</c:when>
					<c:otherwise>
						<div class="bottom">
							<a href="/mysite/board?a=writeform" id="new-book">글쓰기</a>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<!-- /footer -->
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>
	<!-- /footer -->

	</div>
</body>
</html>