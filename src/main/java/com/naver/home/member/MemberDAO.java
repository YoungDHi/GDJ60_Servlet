package com.naver.home.member;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.naver.home.util.DBConnection;

public class MemberDAO {
	
	//setAddmember
	public int setAddmember(MemberDTO memberDTO) throws Exception {
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "INSERT INTO MEMBER (MEMBER_ID, MEMBER_PASSWORD, MEMBER_NAME, "
				+ "MEMBER_ADDRESS, MEMBER_PHONE, MEMBER_EMAIL) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setString(1, memberDTO.getMember_id());
		st.setString(2, memberDTO.getMember_password());
		st.setString(3, memberDTO.getMember_name());
		st.setString(4, memberDTO.getMember_address());
		st.setString(5, memberDTO.getMember_phone());
		st.setString(6, memberDTO.getMember_email());
		
		int result = st.executeUpdate();
		
		DBConnection.disconnect(st, connection);
		
		return result;
		
		
		
	}

	public static void main(String[] args) {
	
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMember_id("id1");
		memberDTO.setMember_password("tw1");
		memberDTO.setMember_name("name1");
		memberDTO.setMember_address("address1");
		memberDTO.setMember_phone("phone1");
		memberDTO.setMember_email("email1");
		try {
			int result = memberDAO.setAddmember(memberDTO);
			System.out.println(result == 1);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
