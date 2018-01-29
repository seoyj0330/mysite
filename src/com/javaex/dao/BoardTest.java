package com.javaex.dao;

import com.javaex.vo.BoardVo;

public class BoardTest {

	public static void main(String[] args) {
		
		//write  테스트
  		BoardVo boardVo = new BoardVo();
  		boardVo.setTitle("들어가라");
  		boardVo.setContent("얍얍얍");
  		boardVo.setUser_no(4);
		boardVo.setNo(1);
		boardVo.setHit(0);
		BoardDao dao = new BoardDao();
		
		//dao.write(boardVo);
		
		System.out.println(boardVo.toString());
		
	}

}
