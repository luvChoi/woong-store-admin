package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.DB;
import model.dto.ProductDTO;

public class ProductDAO {
	//--- 공통부분 시작 ---------------------------------------------------------------------------
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//메서드
	public void getConn() {
		try {
			conn = DB.dbConn();
			System.out.println("-- 오라클 접속 성공 --");
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
		}
	}
	public void getConnClose() {
		DB.dbConnClose(rs, pstmt, conn);
	}	
	//--- 공통부분 종료 ---------------------------------------------------------------------------
	
	//상품목록
	public List<ProductDTO> getSelectAll() {
		List<ProductDTO> list = new ArrayList<>();
		getConn();
		try {
			String sql = "select * from product order by no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setNo(rs.getInt("no"));
				dto.setClassification(rs.getString("classification"));
				dto.setName(rs.getString("name"));
				dto.setMaker(rs.getString("maker"));
				dto.setPurchase_price(rs.getInt("purchase_price"));
				dto.setSelling_price(rs.getInt("selling_price"));
				dto.setSale_percent(rs.getInt("sale_percent"));
				dto.setStock(rs.getInt("stock"));
				dto.setInfo_thumbImg(rs.getString("info_thumbImg"));
				dto.setDescription(rs.getString("description"));
				dto.setRegi_date(rs.getDate("regi_date"));
				dto.setUpd_date(rs.getDate("upd_date"));
				list.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return list;
	}
	
	//상세보기
	public ProductDTO getSelectOne(ProductDTO dto) {
		ProductDTO dtoView = new ProductDTO();
		getConn();
		try {
			String sql = "select * from product where no = ?";			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dtoView.setNo(rs.getInt("no"));
				dtoView.setClassification(rs.getString("classification"));
				dtoView.setName(rs.getString("name"));
				dtoView.setMaker(rs.getString("maker"));
				dtoView.setPurchase_price(rs.getInt("purchase_price"));
				dtoView.setSelling_price(rs.getInt("selling_price"));
				dtoView.setSale_percent(rs.getInt("sale_percent"));
				dtoView.setStock(rs.getInt("stock"));
				dtoView.setInfo_thumbImg(rs.getString("info_thumbImg"));
				dtoView.setDescription(rs.getString("description"));
				dtoView.setRegi_date(rs.getDate("regi_date"));
				dtoView.setUpd_date(rs.getDate("upd_date"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return dtoView;
	}
	
	//상품분류List 호출
	public List<ProductDTO> getProductType() {
		List<ProductDTO> list = new ArrayList<>();
		getConn();
		try {
			String sql = "select * from productType order by no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setClassify_no(rs.getInt("no"));
				dto.setClassification(rs.getString("product_type"));
				list.add(dto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}		
		return list;
	}
	
	//상품 MakerList 호출
	public List<ProductDTO> getMakerList() {
		List<ProductDTO> list = new ArrayList<>();
		getConn();
		try {
			String sql = "select * from makerList order by no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setMaker_no(rs.getInt("no"));
				dto.setMaker(rs.getString("maker_name"));
				list.add(dto);
			}				
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}		
		return list;
	}
		
	//상품등록
	public int setInsert(ProductDTO dto) {
		int result = 0;
		getConn();
		try {
			String sql = "";
			sql += "insert into product (no, classification, name, maker, purchase_price, selling_price,";		
			sql += " sale_percent, stock, info_thumbImg, description, regi_date, upd_date)";
			//sql += " values ((select nvl(max(no),0)+1 from product), ";
			//sql += " ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
			sql += " select ifnull(max(no),0)+1, ";			
			sql += " ?, ?, ?, ?, ?, ?, ?, ?, ?, curdate(), curdate()";
			sql += " from product";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getClassification());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getMaker());
			pstmt.setInt(4, dto.getPurchase_price());
			pstmt.setInt(5, dto.getSelling_price());
			pstmt.setInt(6, dto.getSale_percent());
			pstmt.setInt(7, dto.getStock());
			pstmt.setString(8, dto.getInfo_thumbImg());
			pstmt.setString(9, dto.getDescription());
			result = pstmt.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}		
		return result;
	}
	
	//상품수정
	public int setUpdate(ProductDTO dto) {
		int result = 0;
		getConn();
		try {
			String sql = "";
			sql += "update product set";
			sql += " classification=?, name=?, maker=?, purchase_price=?, selling_price=?,";
			sql += " sale_percent=?, stock=?, info_thumbImg=?, description=?, upd_date=curdate()";
			sql += " where no = ?";
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, dto.getClassification());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getMaker());
			pstmt.setInt(4, dto.getPurchase_price());
			pstmt.setInt(5, dto.getSelling_price());
			pstmt.setInt(6, dto.getSale_percent());
			pstmt.setInt(7, dto.getStock());
			pstmt.setString(8, dto.getInfo_thumbImg());
			pstmt.setString(9, dto.getDescription());
			pstmt.setInt(10, dto.getNo());
			result = pstmt.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}		
		return result;		
	}
	
	//상품삭제
	public int setDelete(ProductDTO dto) {
		int result = 0;
		getConn();
		try {
			String sql = "delete from product where no=?";			
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, dto.getNo());
			result = pstmt.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}		
		return result;
	}
	
}
