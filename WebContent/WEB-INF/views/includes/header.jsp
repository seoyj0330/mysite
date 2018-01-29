<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- 바로 사용가능하니까 import필요 없음 <%@ page import="com.javaex.vo.UserVo"%> --%>

<%-- <%		//굳이 authUser를 getAttribute로 불러올 필요가 없음 jstl로 바로 사용가능

					//object라 형변환을 해주어야함 
	UserVo authUser = (UserVo)session.getAttribute("authUser");

%> --%>
		
		<div id="header">
			<h1>MySite</h1>
			<ul>
		
			<%-- <%	
				if(authUser == null) {
			%> --%>
			
		<c:choose>	
			<c:when test ="${empty authUser }"><!-- authUser가 null이면  -->
			
				<!-- 로그인 전 -->
				<li><a href="/mysite/user?a=loginform">로그인</a></li>
				<li><a href="/mysite/user?a=joinform">회원가입</a></li>
			</c:when>	
				<%-- <% } else {  %> --%>		
			<c:otherwise>
				<!-- 로그인 후 -->	
				<li><a href="/mysite/user?a=modifyform">회원정보수정</a></li>
				<li><a href="/mysite/user?a=logout">로그아웃</a></li> 
				<li> ${authUser.name}님 안녕하세요^^;</li>
			</c:otherwise>	
		</c:choose>		
			</ul>
		</div> 