package com.javalec.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.ex.dto.BDto;

public class BDao {

	DataSource dataSource;
	
	public BDao() {
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

	public ArrayList<BDto> list() {
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			
			String query = "select * from mvc_board order by bGroup desc,bStep asc";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
			int bId = rs.getInt("bId");
			String bName = rs.getString("bName");
			String bTitle =	rs.getString("bTitle");
			String bContent = rs.getString("bContent");
			Timestamp bDate = rs.getTimestamp("bDate");
			int bHit = rs.getInt("bHit");
			int bGroup = rs.getInt("bGroup");
			int bStep = rs.getInt("bStep");
			int bIndent = rs.getInt("bIndent");
			
			BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			dtos.add(dto);
			
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		
		return dtos;
	}

	public BDto contentView(String bId) {
		BDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		upHit(bId);
		
		try {
			conn = dataSource.getConnection();
			
			String query = "select * from mvc_board where bId = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
			String bName = rs.getString("bName");
			String bTitle = rs.getString("bTitle");
			String bContent = rs.getString("bContent");
			Timestamp bDate = rs.getTimestamp("bDate");	
			int bHit = rs.getInt("bHit");	
			int bGroup = rs.getInt("bGroup");	
			int bStep = rs.getInt("bStep");
			int bIndent = rs.getInt("bIndent");	
				
			dto = new BDto(Integer.parseInt(bId), bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);

			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}

	private void upHit(String bId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query ="update mvc_board set bHit=bHit+1 where bId=?";
		
		try {
		
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bId);
			int ri = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}


	public void modify(String bId, String bName, String bTitle, String bContent) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "update mvc_board set bName=?,bTitle=?,bContent=? where bId=?";
		try {
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bId));
			int i =pstmt.executeUpdate();
			
			if(i == 1) {
				System.out.println("��������");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

	public void delete(String bId) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		String query ="delete from mvc_board where bId=?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			pstmt.executeUpdate();
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}try {
			
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public void write(String bName, String bTitle, String bContent) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		
		
		String query ="insert into mvc_board(bId,bName,bTitle,bContent,bHit,bGroup,bStep,bIndent) values(mvc_board_seq.nextval,?,?,?,0,mvc_board_seq.currval,0,0)";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.executeUpdate();
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}try {
			
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		
	}


	public BDto reply_view(String bId) {
		BDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			
			String query = "select * from mvc_board where bId = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
			String bName = rs.getString("bName");
			String bTitle = rs.getString("bTitle");
			String bContent = rs.getString("bContent");
			Timestamp bDate = rs.getTimestamp("bDate");	
			int bHit = rs.getInt("bHit");	
			int bGroup = rs.getInt("bGroup");	
			int bStep = rs.getInt("bStep");
			int bIndent = rs.getInt("bIndent");	
				
			dto = new BDto(Integer.parseInt(bId), bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);

			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}


	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep,
			String bIndent) {
		
		replyShape(bGroup,bStep);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "insert into mvc_board(bId,bName,bTitle,bContent,bGroup,bStep,bIndent)"
					+ " values(mvc_board_seq.nextval,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep) + 1);
			pstmt.setInt(6, Integer.parseInt(bIndent) + 1); 
			pstmt.executeUpdate();
			
			
			
			
		
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
}
		
		
		
		
	


	private void replyShape(String bGroup, String bStep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "update mvc_board set bStep=bStep+1 where bGroup=? and bStep >?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bGroup));
			pstmt.setInt(2, Integer.parseInt(bStep));
			int ri = pstmt.executeUpdate();
			
		}catch (Exception e) { 	
			e.printStackTrace();
		}finally {
			try {
				
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
	
	
	}
	
	
	
}
