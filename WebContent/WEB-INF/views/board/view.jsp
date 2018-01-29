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
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${board.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${board.content }<br>
							</div>
						</td>
					</tr>
				</table>

				<c:choose>
					<c:when test="${board.userNo == authUser.no}">
						<div class="bottom">
							<a href="/mysite/board?a=list">글목록</a> 
							<a href="/mysite/board?a=modifyform&no=${board.no}">글수정</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="bottom">
							<a href="/mysite/board?a=list">글목록</a>
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