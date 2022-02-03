<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../_include/inc_header.jsp" %>

<div class="prod_title">
	<span class="ms-1" id="span_1">상품관리</span>
	<span class="mx-2" id="span_2"> | &nbsp; 상품목록</span>
</div>

<div class="prod_list_table">
	<table class="table table-bordered" id="prod_chuga_table">
		<tr class="align-middle text-center">
			<th class="table-light col-1">상품번호</th>			
			<th class="table-light col-1">상품이미지</th>		
			<th class="table-light col-3">상품명</th>			
			<th class="table-light col-2">판매가</th>
			<th class="table-light col-1">제조사</th>
			<th class="table-light col-1">상품분류</th>
			<th class="table-light col-2">등록일</th>
		</tr>
		<c:forEach var="dto" items="${list }">
		<c:set var="infoThumImg01" value="${fn:split(dto.info_thumbImg, '|')[0] }" />
		<c:set var="infoThumImgArr" value="${fn:split(infoThumImg01, ',') }" />
		<tr class="align-middle text-center">
			<td>${dto.no }</td>			
			<c:choose>
				<c:when test="${infoThumImg01 != '-' }">
				<td class="py-1">
					<a href="${path }/product_servlet/view.do?no=${dto.no }">
						<img src="${path }/attach/product_img/${infoThumImgArr[1] }" style="height: 45px; width: 60px;" >
					</a>
				</td>
				</c:when>
				<c:otherwise>
				<td class="py-1">
					<a href="${path }/product_servlet/view.do?no=${dto.no }">
						<img src="${path }/attach/product_img/_no_img.png" style="height: 45px; width: 45px;" >
					</a>
				</td>
				</c:otherwise>
			</c:choose>
			<td>
				<a href="${path }/product_servlet/view.do?no=${dto.no }" class="text-decoration-none">${dto.name }</a>
			</td>								
			<td>			
				<fmt:formatNumber value="${dto.selling_price }" pattern="#,###" /> 원
			</td>
			<td>${dto.maker }</td>
			<td>${dto.classification }</td>
			<td>${dto.regi_date }</td>			
		</tr>
		</c:forEach>		
	</table>
</div>

<div>

</div>

<div>
	<nav aria-label="Page navigation example">
	  <ul class="pagination justify-content-center">
	    <li class="page-item">
	      <a class="page-link" href="#">Previous</a>
	    </li>
	    <li class="page-item"><a class="page-link" href="#">1</a></li>
	    <li class="page-item"><a class="page-link" href="#">2</a></li>
	    <li class="page-item"><a class="page-link" href="#">3</a></li>
	    <li class="page-item">
	      <a class="page-link" href="#">Next</a>
	    </li>
	  </ul>
	</nav>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
