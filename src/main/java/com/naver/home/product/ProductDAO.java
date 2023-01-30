package com.naver.home.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.naver.home.util.DBConnection;

public class ProductDAO {
	
	//getMax
	public Long getProductNum() throws Exception {
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "SELECT PRODUCT_SEQ.NEXTVAL FROM DUAL";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		rs.next();
		
		Long num = rs.getLong(1);
		
		DBConnection.disconnect(rs, st, connection);
		
		return num;
		
		
	}
	
	public List<ProductOptionDTO> getOptionList() throws Exception {
		ArrayList<ProductOptionDTO> ar = new ArrayList<ProductOptionDTO>();
		Connection connection = DBConnection.getConnection();
		
		String sql = "SELECT * FROM PRODUCTOPTION ORDER BY OPTION_PRICE";
		PreparedStatement st = connection.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			ProductOptionDTO productOptionDTO = new ProductOptionDTO();
			productOptionDTO.setOption_num(rs.getLong("OPTOPN_NUM"));
			productOptionDTO.setPrduct_num(rs.getLong("PRODUCT_NUM"));
			productOptionDTO.setOption_name(rs.getString("OPTION_NAME"));
			productOptionDTO.setOption_price(rs.getInt("OPTION_PRICE"));
			productOptionDTO.setOption_jego(rs.getInt("OPTION_JEGO"));
			ar.add(productOptionDTO);
		}
		
		DBConnection.disconnect(rs, st, connection);
		
		return ar;
		
	}

	public int setAddOption(ProductOptionDTO productOptionDTO) throws Exception {
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "INSERT INTO PRODUCTOPTION (OPTION_NUM, PRODUCT_NUM, OPTION_NAME, OPTION_PRICE, OPTION_JEGO) "
				+ "VALUES (PRODUCTOPTION_SEQ.NEXTVAL, ?, ?, ?, ?)";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setLong(1, productOptionDTO.getPrduct_num());
		st.setString(2, productOptionDTO.getOption_name());
		st.setInt(3, productOptionDTO.getOption_price());
		st.setInt(4, productOptionDTO.getOption_jego());
		
		int result = st.executeUpdate();
		
		DBConnection.disconnect(st, connection);
		
		return result;
		
	}
	
	
	//return은 Arraylist의 부모형 List로 해두는 것이 좋음
	public List<ProductDTO> getProductList() throws Exception{
		ArrayList<ProductDTO> ar = new ArrayList<ProductDTO>();
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "SELECT PRODUCT_NUM, PRODUCT_NAME, PRODUCT_JUMSU "
				+ "FROM PRODUCT ORDER BY PRODUCT_JUMSU DESC";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProduct_num(rs.getLong("PRODUCT_NUM"));
			productDTO.setProduct_name(rs.getString("product_name"));
			productDTO.setProduct_jumsu(rs.getDouble("product_jumsu"));
			ar.add(productDTO);
		}
		
		DBConnection.disconnect(rs, st, connection);
		
		return ar;
		
	}
	
	public int setAddProduct(ProductDTO productDTO) throws Exception{
		Connection connection = DBConnection.getConnection();
		
		String sql = "INSERT INTO PRODUCT (PRODUCT_NUM, PRODUCT_NAME, PRODUCT_DETAIL, PRODUCT_JUMSU) "
				+ "VALUES (?, ?, ?, 0.0)";
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setLong(1, productDTO.getProduct_num());
		st.setString(2, productDTO.getProduct_name());
		st.setString(3, productDTO.getProduct_detail());
		//st.setDouble(3, productDTO.getProduct_jumsu());
		
		int result = st.executeUpdate();
		
		DBConnection.disconnect(st, connection);
		
		return result;
		
	}
	
	
}

