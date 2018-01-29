package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserBoardVo;
import com.javaex.vo.UserVo;


@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("a");
		
		if("list".equals(actionName)) {
		
			System.out.println("list 진입");
			
			String kwd = request.getParameter("kwd");
			System.out.println(kwd);
			
			BoardDao bDao = new BoardDao();
			
			List<UserBoardVo> blist = bDao.getList();

			//kwd를 받아서 dao의 메소드로
			
			List<UserBoardVo> slist = bDao.search(kwd);
			
			
			
			if(kwd == null) {
				request.setAttribute("blist", blist);
			} else {
				request.setAttribute("blist", slist);
			}
		
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
		
		} else if("writeform".equals(actionName)) {
		
			System.out.println("writeform 진입");
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");
			
		} else if("write".equals(actionName)) {
			
			System.out.println("write 진입");
			//로그인한 사람만 글 쓰기 가능 !!
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
		
			BoardVo boardVo = new BoardVo();
			
			boardVo.setTitle(title);
			boardVo.setContent(content);
			boardVo.setHit(0);
			boardVo.setUser_no(authUser.getNo());
			
			BoardDao bDao = new BoardDao();
			bDao.write(boardVo);
			
			
			WebUtil.redirect(request, response, "/mysite/board?a=list");
		
		} else if("viewform".equals(actionName)) {
			
			System.out.println("viewform 진입");
			
			String boardno2 = request.getParameter("boardno");
			
			int boardno = Integer.parseInt(boardno2);
			System.out.println(boardno);
			
			//dao에서 가져온다
			BoardDao bDao = new BoardDao();
			
			BoardVo vo = bDao.getList(boardno); //제목, 내용, boardno, user_no, hit을 vo에 넣음 
			
			bDao.count(boardno, vo.getHit()+1); // 조회수 카운트
			
			request.setAttribute("volist", vo); //제목, 내용, boardno, user_no, hit volist에 담아 view.jsp로 보낸다
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
			
		} else if("delete".equals(actionName)) {
			
			System.out.println("delete 진입");
		
			//삭제하려고 받아온 no를 String에서 int형으로 바꾸기
			//String deletno = request.getParameter("deleteno")
			int deletno = Integer.valueOf(request.getParameter("deleteno"));
			
			BoardDao bDao = new BoardDao();
			bDao.delete(deletno);
					
			WebUtil.redirect(request, response, "/mysite/board?a=list");
		
		} else if("modifyform".equals(actionName)) {
			
			System.out.println("modifyform 진입");
			String boardno2 = request.getParameter("boardno");
			
			int boardno = Integer.parseInt(boardno2);
			
			System.out.println("boardno:"+boardno);
			
			//DAO에서 객체만들어서 modify활 것 꺼내오기
			BoardDao bdao = new BoardDao();
			BoardVo bvo = new BoardVo();
			bvo = bdao.getList(boardno);			//title, content 들어있음 getlist()에
			
			request.setAttribute("bvo", bvo);
			
			System.out.println(bvo);
			WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");
			
		} else if("modify".equals(actionName)) {
			
			System.out.println("modify 진입");
			
			//제목하고 내용 boardno을 가져온다
			String content = request.getParameter("content");
			String title = request.getParameter("title");
			String no2 = request.getParameter("no");
			int no = Integer.parseInt(no2);
			
			//vo에 가져온 제목 내용 넣기
			BoardVo bVo = new BoardVo();
			bVo.setContent(content);
			bVo.setTitle(title);
			bVo.setNo(no);
			
			//dao에 있는 메소드 modify를 실행
			BoardDao bDao = new BoardDao();
			bDao.modify(bVo);
			
			//리다이렉트~
			WebUtil.redirect(request, response, "/mysite/board?a=list");
		
		} 
		
	}

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
