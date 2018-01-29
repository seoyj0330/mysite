<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
									<%-- 다 jstl로 하기때문에 자바코드는 필요없고 불러낼 필요X  <%@ page import = "com.javaex.vo.GuestbookVo" %>
																				  <%@ page import = "java.util.List" %>
																												<!-- attribute가 object타입이기 때문에 형변환이 필요함  -->
																			  	 <% List<GuestbookVo> glist = (List<GuestbookVo>) request.getAttribute("list"); %> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
</head>
<body>

	<div id="container">
		
			<!--  header include -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		
			<!-- navigation include -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
						
						<!-- 폼에서 나온 데이터를 어떤방식으로 어디로 보낼지 고르는것-->
					<form action="/mysite/gb" method="get">
						
						<table>
							<tr>
								<td>이름</td><td><input type="text" name="name" /></td>
								<td>비밀번호</td><td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 " /></td>
							</tr> 	
						</table>
						<input type="text" name = "a" value= "add">
					</form>
					<ul>
						<li>
							<%-- <%for(GuestbookVo vo : glist) {%> --%>
							<c:forEach items = "${list }" var="gbVo" >
							<table>
								<tr>
									<td>${gbVo.no} </td>
									<td>${gbVo.name} </td>
									<td>${gbVo.date} </td>
									<td><a href="/mysite/gb?a=deleteform&no=${gbVo.no }">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>${gbVo.content}</td>
								</tr>
							</table>
							<br>
							</c:forEach>
						</li>
					</ul>
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<!--  footer include -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div> <!-- /container -->

</body>
</html>