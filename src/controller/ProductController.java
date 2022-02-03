package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tika.Tika;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.ProductDAO;
import model.dto.ProductDTO;

@WebServlet("/product_servlet/*")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		
		String page = "";
		String inc_page = "../product/";
		
		if(url.contains("list.do") == true) {
			//System.out.println("list");
			ProductDAO dao = new ProductDAO();
			List<ProductDTO> list = dao.getSelectAll();
			
			inc_page += "list.jsp";
			
			request.setAttribute("list", list);
			request.setAttribute("inc_page", inc_page);
			
			page = "/WEB-INF/_main/main.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("view.do") == true) {
			//System.out.println("view");
			
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			
			ProductDTO dto = new ProductDTO();
			dto.setNo(no);
			
			ProductDAO dao = new ProductDAO();
			dto = dao.getSelectOne(dto);
			
			inc_page += "view.jsp";
			
			request.setAttribute("dto", dto);
			request.setAttribute("inc_page", inc_page);
			
			page = "/WEB-INF/_main/main.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("chuga.do") == true) {
			ProductDAO dao = new ProductDAO();
			List<ProductDTO> prodTypeList = dao.getProductType();
			List<ProductDTO> MakerList = dao.getMakerList();
						
			inc_page += "chuga.jsp";
			request.setAttribute("inc_page", inc_page);
			request.setAttribute("prodTypeList", prodTypeList);
			request.setAttribute("MakerList", MakerList);
			
			page = "/WEB-INF/_main/main.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("chugaProc.do") == true) {			
			//String attach_path = "C:/csw/_shop_attach";
			String attach_path = "/home/hosting_users/soulofaiur/shop_attach";
			String upload_path = attach_path + "/product_img";
			//System.out.println(upload_path);
			int max_upload = 10 * 1024 * 1024; //10MB
			
			java.io.File isDir = new java.io.File(upload_path);
			if(!isDir.exists()) {
				try {
					isDir.mkdir();
				} catch(Exception e) { e.printStackTrace(); }				
			}
			
			MultipartRequest multi = new MultipartRequest(
					request,
					upload_path,
					max_upload,
					"UTF-8",
					new DefaultFileRenamePolicy());
			
			String[] arrStr = new String[3];
			
			//while문 사용
			Enumeration files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String formTagName = (String) files.nextElement();
				String fileOrgName = multi.getOriginalFileName(formTagName);
				String fileName = multi.getFilesystemName(formTagName);
				String fileType = multi.getContentType(formTagName);
				long fileSize = 0;
				String mimeType = "";
				
				java.io.File file = multi.getFile(formTagName);
				if(file != null) {
					fileSize = file.length();
					
					Tika tika = new Tika();
					mimeType = tika.detect(file);
				}
				
				String imsi = "-";
				if(fileSize > 0 && mimeType != null && fileType.equals(mimeType)) {
					imsi = fileOrgName + "," + fileName + "," + fileType + "," + mimeType;					
				}
				String temp_ = formTagName.substring(formTagName.length()-1);
				int temp = Integer.parseInt(temp_);				
				arrStr[temp-1] = imsi;
			}
			String info_thumbImg = "";
			for(int i=0; i<arrStr.length; i++) {
				info_thumbImg += "|" + arrStr[i];
			}
			info_thumbImg = info_thumbImg.substring(1);
			
			String classification = multi.getParameter("classification");
			String name = multi.getParameter("name");
			String maker = multi.getParameter("maker");
			String purchase_price_ = multi.getParameter("purchase_price");
			String selling_price_ = multi.getParameter("selling_price");
			String sale_percent_ = multi.getParameter("sale_percent");
			String stock_ = multi.getParameter("stock");
			String description = multi.getParameter("description");
			
			//콤마 제거
			purchase_price_ = purchase_price_.replace(",", "");
			selling_price_ = selling_price_.replace(",", "");
			sale_percent_ = sale_percent_.replace(",", "");
			stock_ = stock_.replace(",", "");	
			
			int purchase_price = Integer.parseInt(purchase_price_);
			int selling_price = Integer.parseInt(selling_price_);
			int sale_percent = Integer.parseInt(sale_percent_);
			int stock = Integer.parseInt(stock_);
						
			//상세설명 특수문자 변환
			description = description.replace("<", "&lt;");
			description = description.replace(">", "&gt;");
			description = description.replace("'", "&quot;");
			
			ProductDTO dto = new ProductDTO();
			dto.setClassification(classification);
			dto.setName(name);
			dto.setMaker(maker);
			dto.setPurchase_price(purchase_price);
			dto.setSelling_price(selling_price);
			dto.setSale_percent(sale_percent);
			dto.setStock(stock);
			dto.setInfo_thumbImg(info_thumbImg);
			dto.setDescription(description);
			
			ProductDAO dao = new ProductDAO();
			int result = dao.setInsert(dto);
					
			String msg = "";
			String move = path + "/product_servlet/list.do";
			if(result > 0) {
				msg = "정상적으로 등록되었습니다.";				
			} else {
				msg = "처리 중 오류가 발생했습니다.";
			}
			System.out.println(msg);
			response.sendRedirect(move);
			
		} else if(url.contains("sujung.do") == true) {
			//System.out.println("sujung");			
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			
			ProductDTO dto = new ProductDTO();
			dto.setNo(no);
			
			ProductDAO dao = new ProductDAO();			
			dto = dao.getSelectOne(dto);
			List<ProductDTO> prodTypeList = dao.getProductType();
			List<ProductDTO> MakerList = dao.getMakerList();
			
			inc_page += "sujung.jsp";
			
			request.setAttribute("dto", dto);
			request.setAttribute("inc_page", inc_page);
			request.setAttribute("prodTypeList", prodTypeList);
			request.setAttribute("MakerList", MakerList);
			
			page = "/WEB-INF/_main/main.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("sujungProc.do") == true) {			
			//String attach_path = "C:/csw/_shop_attach";
			String attach_path = "/home/hosting_users/soulofaiur/shop_attach";
			String upload_path = attach_path + "/product_img";
			int max_upload = 1 * 1024 * 1024; //10MB
			
			MultipartRequest multi = new MultipartRequest( //파일이 나눠져서 오기 때문에 multipart를 사용
					request,
					upload_path,
					max_upload,
					"UTF-8",
					new DefaultFileRenamePolicy());
			
			String no_ = multi.getParameter("no");
			String classification = multi.getParameter("classification");
			String name = multi.getParameter("name");
			String maker = multi.getParameter("maker");
			String purchase_price_ = multi.getParameter("purchase_price");
			String selling_price_ = multi.getParameter("selling_price");
			String sale_percent_ = multi.getParameter("sale_percent");
			String stock_ = multi.getParameter("stock");
			String incoming_ = multi.getParameter("incoming");
			String description = multi.getParameter("description");
			
			//콤마 제거
			purchase_price_ = purchase_price_.replace(",", "");
			selling_price_ = selling_price_.replace(",", "");
			sale_percent_ = sale_percent_.replace(",", "");
			stock_ = stock_.replace(",", "");
			incoming_ = incoming_.replace(",", "");
			
			int no = Integer.parseInt(no_);
			int purchase_price = Integer.parseInt(purchase_price_);
			int selling_price = Integer.parseInt(selling_price_);
			int sale_percent = Integer.parseInt(sale_percent_);
			int stock = Integer.parseInt(stock_);
			int incoming = Integer.parseInt(incoming_);
			stock = stock + incoming; //재고+입고 연산
						
			//상세설명 특수문자 변환
			description = description.replace("<", "&lt;");
			description = description.replace(">", "&gt;");
			description = description.replace("'", "&quot;");
			
			String uploadCounter_ = multi.getParameter("uploadCounter");
			int uploadCounter = Integer.parseInt(uploadCounter_);
			
			String sujung_infoImg = "";
			for(int i=0; i < uploadCounter; i++) {
				String formTagName = "img_" + i;
				System.out.println(formTagName);
				String fileOrgName = multi.getFilesystemName(formTagName);
				String fileName = multi.getFilesystemName(formTagName);
				String fileType = multi.getContentType(formTagName);
				
				long fileSize = 0;
				String mimeType = null;
				
				java.io.File file = multi.getFile(formTagName);
				if(file != null) {
					fileSize = file.length();
					
					Tika tika = new Tika();
					mimeType = tika.detect(file);					
				}
				
				if(fileSize > 0 && mimeType != null && fileType.equals(mimeType)) {
					sujung_infoImg += "|" + fileOrgName + "," + fileName + "," + fileSize + "," + mimeType;
				} else {
					sujung_infoImg += "|" + "-";
				}				
			}
			sujung_infoImg = sujung_infoImg.substring(1);
			
			ProductDTO dto = new ProductDTO();
			dto.setNo(no);
			
			ProductDAO dao = new ProductDAO();
			ProductDTO imgDto = dao.getSelectOne(dto);
			String db_infoImg = imgDto.getInfo_thumbImg();
			
			String[] OldAttach = db_infoImg.split("[|]");
			String[] NewAttach = sujung_infoImg.split("[|]");
			String[] arrStr = new String[NewAttach.length];
			
			//생성된 이미지 정보와 비교
			for(int i=0; i<arrStr.length; i++) {
				if(NewAttach[i].equals("-")) { // 파일변경 X
					arrStr[i] = OldAttach[i];
				} else { // 파일변경 O
					if(!OldAttach[i].equals("-")) {
						String[] imsiArr = OldAttach[i].split(",");
						java.io.File f1 = new java.io.File(upload_path + "/" + imsiArr[1]);
						if(f1.exists()) {
							f1.delete();
						}
					}
					arrStr[i] = NewAttach[i];
				}
			}
			String info_thumbImg = "";
			for(String s : arrStr) {
				info_thumbImg += "|" + s;
			}
			info_thumbImg = info_thumbImg.substring(1);
			
			dto.setClassification(classification);
			dto.setName(name);
			dto.setMaker(maker);
			dto.setPurchase_price(purchase_price);
			dto.setSelling_price(selling_price);
			dto.setSale_percent(sale_percent);
			dto.setStock(stock);
			dto.setInfo_thumbImg(info_thumbImg);
			dto.setDescription(description);
			
			dao.setUpdate(dto);
			int result = dao.setUpdate(dto);
			
			String msg = "";
			String move = path + "/product_servlet/view.do?no=" + no;
			if(result > 0) {
				msg = "정상적으로 수정되었습니다.";
			} else {
				msg = "처리 중 오류가 발생했습니다.";
			}
			System.out.println(msg);
			response.sendRedirect(move);
			
		} else if(url.contains("sakjeProc.do") == true) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			
			ProductDTO dto = new ProductDTO();
			dto.setNo(no);
			
			ProductDAO dao = new ProductDAO();
			dto = dao.getSelectOne(dto);
			
			String infothumImg = dto.getInfo_thumbImg(); // -|-|-
			
			//파일경로
			String attach_path = "/home/hosting_users/soulofaiur/shop_attach";
			String upload_path = attach_path + "/product_img";
						
			int result = dao.setDelete(dto);
			
			String msg = "";
			String move = path + "/product_servlet/list.do";
			
			if(result > 0) { // 정상 삭제 : 파일 삭제
				if(!infothumImg.equals("-|-|-")) { //첨부파일이 있으면
					String[] strArr = infothumImg.split("[|]");
					for(String s : strArr) {
						if(!s.equals("-")) { //상품 이미지가 있다면 
							String[] infoArr = s.split("\\,");
							String file_path = upload_path + "/";
							file_path += infoArr[1];
							java.io.File f1 = new java.io.File(file_path);
							if(f1.exists()) {
								f1.delete();
							}						
						}
					}
				}
				msg = "정상적으로 삭제되었습니다.";	
			} else { // 처리 오류 : 파일 삭제 X
				msg = "처리 중 오류가 발생했습니다.";
			}			
			response.sendRedirect(move);
		}		
	}
}
