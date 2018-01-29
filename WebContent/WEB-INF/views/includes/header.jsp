<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="header">
	<h1>myomyo site ☆</h1>
	<ul>
		<c:choose>
			<c:when test="${empty authUser }">
				<!-- 로그인 전 -->
				<li><a href="/mysite/user?a=loginform">로그인</a></li>
				<li><a href="/mysite/user?a=joinform">회원가입</a></li>
				<!-- 링크걸때 /mysite뒤에 /user는 UserServlet에 있는 user이고 contorller를 통해 들어가야하므로 바로 joinform.jsp라고 쓰지 않음-->
			</c:when>
			<c:otherwise>
				<!-- 로그인 후 -->
				<li><a href="/mysite/user?a=modifyform">회원정보수정</a></li>
				<li><a href="/mysite/user?a=logout">로그아웃</a></li>
				<li>${authUser.name} 님안녕하세요</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>