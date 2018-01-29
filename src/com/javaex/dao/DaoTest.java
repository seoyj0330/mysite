package com.javaex.dao;

import com.javaex.vo.UserVo;

public class DaoTest {

	public static void main(String[] args) {
	
	/*		insert 회원가입 테스트
  		UserVo userVo = new UserVo();
		userVo.setName("박형식");
		userVo.setEmail("hs@park");
		userVo.setPassword("9090");
		userVo.setGender("female");
		
		
		UserDao userDao = new UserDao();
		
		userDao.insert(userVo);
	*/

		
		//login할때      getUSer가 잘 작동하는 지 test
		UserDao userDao = new UserDao();
		UserVo userVo = userDao.getUser("jung@jung.juna", "55");
		System.out.println(userVo);
	
	}

}
