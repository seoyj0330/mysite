package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;


@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("user 진입");
		request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("a");
		
		if("joinform".equals(actionName)) {
			
			System.out.println("joinform 진입 ");
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinform.jsp");
		
		} else if("join".equals(actionName) ) {
		
			System.out.println("join 진입");
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo();
			userVo.setName(name);
			userVo.setEmail(email);;
			userVo.setPassword(password);
			userVo.setGender(gender);
			
			System.out.println(userVo.toString());
			
			UserDao userDao = new UserDao();
			userDao.insert(userVo);
			
			WebUtil.forward(request, response, "WEB-INF/views/user/joinsuccess.jsp");
		
		} else if("loginform".equals(actionName)) {
			
			System.out.println("loginform 진입");
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
	
		} else if("login".equals(actionName)) {
			
			System.out.println("login 진입");
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			System.out.println(email + "/" + password);
			
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUser(email, password);
			
			
			if(userVo == null) {
				System.out.println("로그인 실패");
				WebUtil.redirect(request, response, "/mysite/user?a=loginform&result=fail");
			
			} else {
				System.out.println("로그인 성공");
				
				//세션을 쓰려면 HttpSession 이게 있어야함
				HttpSession session = request.getSession(true);
				session.setAttribute("authUser", userVo);
				
				WebUtil.redirect(request, response,"/mysite/main");
			}
					
			
		} else if("logout".equals(actionName) ) {
			HttpSession session = request.getSession();
			session.removeAttribute("authUSer");
			session.invalidate();
			
			WebUtil.redirect(request, response, "/mysite/main");
		
		} else if("modifyform".equals(actionName)) {
			
			System.out.println("modifyform 진입");
			
			//데이터 가져옴
				//세션에서 no를 get   getNo()
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");  // authUser없으면 로그인 안한상태임
			
				//no가 없으면 loginform으로 리다이렉트 
			if(authUser == null) {		//로그인 상태가 아님
				WebUtil.redirect(request, response, "/WEb-INf/views/user/loginform.jsp");
			} else { 					//정보가 있기 때문에 로그인상태 
			//no가 있으면 dao에서 가져와서 유저객체에 담기 
			int no = authUser.getNo();
			
			UserDao userDao = new UserDao();
			UserVo userVo =  userDao.getUser(no);			//vo에 바로 담아주기 
			
			//setAttribute로 세션에 데이터 저장 
			request.setAttribute("userVo", userVo); 
			
			//포워드 
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
		}
			
		} else if("modify".equals(actionName)) {
			
			//항상 세션이 먼저라는 걸 기억하기!!
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			if(authUser == null) { //비로그인상태
				//로그인폼으로 리다이렉트,
			} else { 		//로그인상태
				
				//vo,(no,name,password,gender)
				
				int no = authUser.getNo();
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String email = request.getParameter("email");
				String gender = request.getParameter("gender");
				
				UserVo userVo = new UserVo(no, name, "",  password, gender);
				
				//dao.update(vo)
				UserDao userDao = new UserDao();
				userDao.update(userVo);
				
				//session name 값 변경
				authUser.setName(name);
				
				//메인으로 리다이렉트
				WebUtil.redirect(request, response, "/mysite/main");
			}
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
