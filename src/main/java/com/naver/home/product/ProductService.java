package com.naver.home.product;

import java.util.List;

public class ProductService {
	
	//결합도가 높다(강하다)
	
	private ProductDAO productDAO = new ProductDAO();
	
	{
		this.productDAO = new ProductDAO();
	}
	
	public ProductService() {
		
		this.productDAO = new ProductDAO();
	}
	
	//결합도가 낮다(약하다)
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO=productDAO;
		
	}
	
	
	public int setAddProduct(ProductDTO productDTO, List<ProductOptionDTO> ar) throws Exception {
		//product , option
		Long productnum = productDAO.getProductNum();
		productDTO.setProduct_num(productnum);
		int result = productDAO.setAddProduct(productDTO);
		
		for(ProductOptionDTO productOptionDTO:ar) {
			productOptionDTO.setPrduct_num(productnum);
			result = productDAO.setAddOption(productOptionDTO);
		}
		
		return result;
		
	}

	public static void main(String[] args) {
		ProductDAO productDAO = new ProductDAO();
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProduct_name("product1");
		productDTO.setProduct_detail("detail1");
		
		ProductOptionDTO productOptionDTO = new ProductOptionDTO();
		productOptionDTO.setOption_name("optionName1");
		productOptionDTO.setOption_price(100);
		productOptionDTO.setOption_jego(10);
		productOptionDTO.setPrduct_num(null);
		
		ProductOptionDTO productOptionDTO2 = new ProductOptionDTO();
		productOptionDTO2.setOption_name("optionName2");
		productOptionDTO2.setOption_price(200);
		productOptionDTO2.setOption_jego(20);
		productOptionDTO2.setPrduct_num(null);
		
		try {
			Long num = productDAO.getProductNum();
			
			productDTO.setProduct_num(num);
			
			int result = productDAO.setAddProduct(productDTO);
			
			productOptionDTO.setPrduct_num(num);
			
			if (result>0) {
				
				productDAO.setAddOption(productOptionDTO2);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
