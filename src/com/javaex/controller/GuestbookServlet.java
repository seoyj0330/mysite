package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;


@WebServlet("/gb")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("gbservlet실행");
		
		String actionName = request.getParameter("a");
		
		if("add".equals(actionName)) {
			System.out.println("gb.add 진입");
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			System.out.println(name + password + content);
			
			//생성자 사용하여 넣기 
			GuestbookVo gbVo = new GuestbookVo(0 ,name,password,content, "");
			System.out.println(gbVo.toString());

/*			gbVo.setName(name);			//vo에 웹에서 받아온 데이터 넣기
			gbVo.setPassword(password);
			gbVo.setContent(content);
*/
			//디비 접근을위해 dao객체에 vo 넣기
			GuestbookDao gbDao = new GuestbookDao(); 
			gbDao.insert(gbVo);
			
			WebUtil.redirect(request, response, "/mysite/gb?a=list");
		
		} else if("list".equals(actionName)) {
			System.out.println("gb.list 진입");
			
			//어레이 리스트로 dao에서 getList메소드를 이용해서 리스트 데이터들을 넣음 
			GuestbookDao gbDao = new GuestbookDao();
			List<GuestbookVo> list = gbDao.getList();
			
			//setAttribute로 데이터들을 저장 
			request.setAttribute("list", list);
			
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
		
		} else if("deleteform".equals(actionName)) {
			System.out.println("gb.deleteform 진입");
			
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");

		} else if("delete".equals(actionName)) {
			System.out.println("gb.delete 진입");
			
			//jsp에서 데이터를 받아 변수에 저장~
			// 웹에서는 숫자가 아닌 문자열로 인식하기 때문에 문자열 > 숫자로 변환
//			String no = request.getParameter("no");
//			
//			int no2 = Integer.parseInt(no);
			int no = Integer.valueOf(request.getParameter("no"));
			String password = request.getParameter("password");
			
			GuestbookDao gbDao = new GuestbookDao();
			gbDao.delete(no,password);
			
			WebUtil.redirect(request, response, "/mysite/gb?a=list");
			
		}
		
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
