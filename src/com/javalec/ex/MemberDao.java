package com.javalec.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class MemberDao {
	
	
	public static final int MEMBER_NONEXISTENT = 0; //존재하지 않는
	public static final int MEMBER_EXISTENT = 1; //존재하는
	public static final int MEMBER_JOIN_FAIL = 0; 
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	private static MemberDao instance = new MemberDao(); 

	private MemberDao() {}  // 외부 생성제한.
	
	public static MemberDao getInstance() {
		/*if(instance == null) {
			instance = new MemberDao();
		}*/  
		// 위에서 초기화 하였기에 생략.
		return instance ;
	}
	
	public int confirmId(String id){
		int ri = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select id from members where id = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);;;
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ri = MemberDao.MEMBER_EXISTENT;
			}else {
				ri = MemberDao.MEMBER_NONEXISTENT;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}

	
	
	
	public int insertMember(MemberDto dto) {
		int ri = 0;
	
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String query = "insert into members values (?,?,?,?,?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);;;
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.geteMail());
			pstmt.setTimestamp(5, dto.getrDate());
			pstmt.setString(6, dto.getAddress());
			pstmt.executeUpdate();
			ri = MemberDao.MEMBER_JOIN_SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int userCheck(String id, String pw) {
		
		int ri = 0;
		String dbPw;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select pw from members where id =?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbPw = rs.getString("pw");
				if(dbPw.equals(pw)) {
					ri = MemberDao.MEMBER_LOGIN_SUCCESS; //로그인 OK
				} else {
					ri = MemberDao.MEMBER_LOGIN_PW_NO_GOOD; //비번X
				} 
			} else {
				ri = MemberDao.MEMBER_LOGIN_IS_NOT;//회원X
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			if(rs != null ) rs.close();
			if(pstmt != null ) pstmt.close();
			if(conn != null ) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return ri;
		
	}
	
	public MemberDto getMember(String id){
		
		Connection conn = null;;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from members where id=?";
		MemberDto dto = null;
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDto();
				dto.setId( rs.getString("id"));
				dto.setPw( rs.getString("pw"));
				dto.setName( rs.getString("name"));
				dto.seteMail( rs.getString("eMail"));
				dto.setrDate( rs.getTimestamp("rDate"));
				dto.setAddress(rs.getString("address"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public int updateMember(MemberDto dto) {
		int ri =0;
		Connection conn = null;;
		PreparedStatement pstmt = null;
		String query = "update members set pw=?,eMail=?,address=? where id=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.geteMail());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getId());
			ri = pstmt.executeUpdate();
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		return ri;
	}
	
public ArrayList<MemberDto> getMembersAll() {
		
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		Connection conn= null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from members";
		try {
		conn = getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			MemberDto dto = new MemberDto();
			dto.setId( rs.getString("id"));
			dto.setPw( rs.getString("pw"));
			dto.setName( rs.getString("name"));
			dto.seteMail( rs.getString("eMail"));
			dto.setrDate( rs.getTimestamp("rDate"));
			dto.setAddress(rs.getString("address"));
			
			dtos.add(dto);
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if( conn != null)conn.close();
			
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	private Connection getConnection() {
		Context context = null;
		DataSource dataSource = null;
		Connection conn = null;
		
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
			conn = dataSource.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return conn;
	}

	
	
	
}
