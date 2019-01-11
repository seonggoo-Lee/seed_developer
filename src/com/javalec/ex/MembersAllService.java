package com.javalec.ex;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MembersAllService implements Service {

	@Override
	public ArrayList<MemberDto> execute(HttpServletRequest request, HttpServletResponse response) {
		
		MemberDao dao = MemberDao.getInstance();
		
		return dao.getMembersAll();
	}

	@Override
	public int insert(MemberDto dto) {
		MemberDao dao = MemberDao.getInstance();
		
		
		return dao.insertMember(dto);
	}



	

	

}
