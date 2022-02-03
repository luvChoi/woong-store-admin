<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../_include/inc_header.jsp" %>

<div class="prod_title">
	<span class="ms-1" id="span_1">상품관리</span>
	<span class="mx-2" id="span_2"> | &nbsp; 상세보기</span>
</div>

<div class="prod_table">
	<table class="table table-bordered mb-3">
		<tr class="align-middle text-center">
			<th class="table-light col-2">상품번호</th>	
			<td class="col-4">
				${dto.no }
			</td>
			<th class="table-light col-2">상품분류</th>	
			<td class="col-4">
				${dto.classification }
			</td>
		</tr>
		<tr class="align-middle text-center">
			<th class="table-light col-2">상품명</th>
			<td class="col-4">
				${dto.name }
			</td>
			<th class="table-light col-2">제조사</th>	
			<td class="col-4">
				${dto.maker }
			</td>
		</tr>	
		<tr class="align-middle text-center">
			<th class="table-light col-2">섬네일이미지</th>	
			<td colspan="3">
				<div class="row align-middle text-center">
				<c:set var="infoImgArr" value="${fn:split(dto.info_thumbImg, '|') }" />			
				<c:forEach var="i" begin="0" end="${fn:length(infoImgArr)-1 }" step="1" >					
					<c:choose>
						<c:when test="${infoImgArr[i] != '-' }" >
							<c:set var="imgArr" value="${fn:split(infoImgArr[i], ',') }" />					
							<div class="col-4 my-3">
								<img src="${path }/attach/product_img/${imgArr[1] }" style="height: 100px; width: 120px;"><br>
								<span id="imgTag">${imgArr[1] }</span>
							</div>						
						</c:when>
						<c:otherwise>
							<div class="col-4 my-2">
								<img src="${path }/attach/product_img/_no_img.png" style="height: 100px; width: 100px;"><br>							
								<span id="imgTag">이미지없음</span>
							</div>
						</c:otherwise>				
					</c:choose>
				</c:forEach>
				</div>		
			</td>			
		</tr>
		<tr class="align-middle text-center">
			<th class="table-light col-2">판매가</th>
			<td class="col-4">
				<fmt:formatNumber value="${dto.selling_price }" pattern="#,###" />&nbsp;원
			</td>
			<th class="table-light col-2">매입가</th>	
			<td class="col-4">
				<fmt:formatNumber value="${dto.purchase_price }" pattern="#,###" />&nbsp;원
			</td>
		</tr>
		<tr class="align-middle text-center">
			<th class="table-light col-2">재고</th>	
			<td class="col-4">
				<fmt:formatNumber value="${dto.stock }" pattern="#,###" />&nbsp;EA				
			</td>
			<th class="table-light col-2">할인율</th>	
			<td class="col-4">
				${dto.sale_percent }&nbsp;%
			</td>
		</tr>		
		<tr class="align-middle text-center">
			<th class="table-light col-2">상세설명</th>	
			<td colspan="3">
				<textarea class="form-control" name="description" style="height: 150px">${dto.description }</textarea>	
			</td>			
		</tr>
		<tr class="align-middle text-center">
			<th class="table-light col-2">
				<div class="ms-3">등록일</div>
			</th>
			<td class="col-4">
				${dto.regi_date }
			</td>
			<th class="table-light col-2">
				<div class="ms-3">수정일</div>
			</th>	
			<td class="col-4">${dto.upd_date }</td>
		</tr>
	</table>
	<form name="Sakjeform">
		<input type="hidden" name="no" value="${dto.no }">
	</form>	
	<div>
		<a type="button" href="${path }/product_servlet/list.do" class="btn btn-light me-1">목록으로</a>
		<a type="button" href="${path }/product_servlet/chuga.do" class="btn btn-light me-1">상품등록</a>
		<a type="button" href="${path }/product_servlet/sujung.do?no=${dto.no }" class="btn btn-light me-1">상품수정</a>
		<a type="button" href="#" onClick="sakje();" class="btn btn-light me-1">상품삭제</a>			
	</div>
</div>

<script>
function sakje() {
	if(confirm('상품정보를 삭제하시겠습니까?')){
		Sakjeform.method = "post";
		Sakjeform.action = "${path}/product_servlet/sakjeProc.do";
		Sakjeform.submit();
	}
}
</script>
