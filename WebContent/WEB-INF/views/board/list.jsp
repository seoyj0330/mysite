<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- blist -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
			<!--  header include -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		
			<!-- navigation include -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="/mysite/board" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
					<input type="hidden" name="a" value="list">
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
					<c:forEach items = "${list }" var="userboardVo">
					<tr>
						<td>${userboardVo.boardno }</td>
						<td><a href="/mysite/board?a=viewform&boardno=${userboardVo.boardno }">${userboardVo.title }</a></td>
						<td>${userboardVo.name }</td>
						<td>${userboardVo.hit }</td>
						<td>${userboardVo.date }</td>
						
						
						
						<c:choose>
							<c:when test="${empty authUser }">	<!-- 로그인 안했을 때 -->
							
							</c:when>
							
							<c:otherwise>	<!-- 로그인 했을때  -->
								<c:choose>
									<c:when test="${authUser.no  eq userboardVo.user_no}">
										<td><a href="/mysite/board?a=delete&deleteno=${userboardVo.boardno }" class="del">삭제</a></td>
									</c:when>
									<c:otherwise>
										
									</c:otherwise>
								</c:choose>
							</c:otherwise>
							
						</c:choose>
						</tr>
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
					<c:when test="${empty authUser }"> <!-- 로그인 안했을때 -->
									
					</c:when>
					<c:otherwise>
						<div class="bottom">
						<a href="/mysite/board?a=writeform" id="new-book">글쓰기</a>
						</div>
					</c:otherwise>
				</c:choose>
						
						
			</div>
		</div>
		
		<!--  footer include -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div>
</body>
</html>