<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../_include/inc_header.jsp" %>

<div class="prod_title">
	<span class="ms-1" id="span_1">상품관리</span>
	<span class="mx-2" id="span_2"> | &nbsp; 상품수정</span>
</div>

<form name="form">
<div class="prod_table">
	<table class="table table-bordered mb-3">
		<tr class="align-middle text-center">
			<th class="table-light col-2">상품번호</th>	
			<td class="col-4">
				${dto.no }
			</td>
			<th class="table-light col-2">상품분류</th>	
			<td>
				<div class="mx-auto row">		
					<select class="form-select form-select-sm w-50" name="classification" id="prod_bunryu">
						<option value="" >선택하세요</option>
						<c:forEach var="dto" items="${prodTypeList }">
							<option value="${dto.classification }" >${dto.classification }</option>						
						</c:forEach>
					</select>
				</div>			
			</td>
		</tr>
		<tr class="align-middle text-center">
			<th class="table-light col-2">상품명</th>
			<td class="col-4">
				<input type="text" class="form-control form-control-sm text-center" name="name" value="${dto.name }">
			</td>
			<th class="table-light col-2">제조사</th>	
			<td class="col-4">
				<div class="mx-auto row">		
					<select class="form-select form-select-sm w-50" name="maker" id="maker">
						<option value="">선택하세요</option>
						<c:forEach var="dto" items="${MakerList }">
							<option value="${dto.maker }" >${dto.maker }</option>
						</c:forEach>						
					</select>
				</div>
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
							<div class="col-4 my-2">
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
		<tr class="align-middle">
			<th class="table-light col-2 text-center">이미지 변경</th>
			<td colspan="3">
				<div class="row align-middle mt-1 mb-2">
					<div class="col-4">
						<label for="formFileSm_0" class="form-label mb-1"><span class="text-success fw-bold">섬네일이미지1</span></label>
						<input id="formFileSm_0" type="file" name="img_0" class="form-control form-control-sm text-center">
					</div>
					<div class="col-4">
						<label for="formFileSm_1" class="form-label mb-1"><span class="text-success fw-bold">섬네일이미지2</span></label>
						<input id="formFileSm_1" type="file" name="img_1" class="form-control form-control-sm text-center">
					</div>
					<div class="col-4">
						<label for="formFileSm_2" class="form-label mb-1"><span class="text-success fw-bold">섬네일이미지3</span></label>
						<input id="formFileSm_2" type="file" name="img_2" class="form-control form-control-sm text-center">
					</div>
				</div>	
			</td>
		</tr>		
		<tr class="align-middle ">
			<th class="table-light text-center col-2">판매가</th>
			<td class="col-4">					
				<input type="text" name="selling_price" id="selling_price" value="${dto.selling_price }" class="form-control form-control-sm w-75 d-inline-block text-center" onkeyup="addComma(this);">			
				<span class="d-inline-block ms-1">원</span>
			</td>
			<th class="table-light text-center col-2">매입가</th>	
			<td class="col-4">
				<input type="text" name="purchase_price" id="purchase_price" value="${dto.purchase_price }" class="form-control form-control-sm w-75 d-inline-block text-center">			
				<span class="d-inline-block ms-1">원</span>				
			</td>
		</tr>
		<tr class="align-middle">
			<th class="table-light text-center col-2">재고</th>	
			<td class="col-4">
				<div class="row align-middle">
					<div class="col-6">
						<input type="text" name="stock" id="stock" value="${dto.stock }" class="form-control form-control-sm w-75 d-inline-block text-center" >
						<span class="d-inline-block">EA</span>
					</div>				
					<div class="col-6">
						<span class="d-inline-block text-primary fw-bold me-1">+ 입고</span>
						<input type="text" name="incoming" id="incoming" value="0" class="form-control form-control-sm w-50 d-inline-block text-center" >
					</div>
				</div>				
			</td>
			<th class="table-light text-center col-2">할인율</th>
			<td class="col-4">
				<input type="text" name="sale_percent" value="${dto.sale_percent }" class="form-control form-control-sm w-75 d-inline-block text-center" >
				<span class="d-inline-block ms-1">%</span>
			
			</td>
		</tr>		
		<tr class="align-middle text-center">
			<th class="table-light col-2">상세설명</th>	
			<td colspan="3">
				<textarea class="form-control" name="description" style="height: 110px">${dto.description }</textarea>	
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
	<div class="text-center">
		<button type="button" onClick="list();" class="btn btn-light me-1">목록으로</button>
		<button type="button" onClick="sujung();" class="btn btn-primary ms-1" >상품수정</button>
	</div>
</div>
<input type="hidden" name="no" value="${dto.no }">
<input type="hidden" name="uploadCounter" id="uploadCounter">
</form>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script>
$(document).ready(function() {
	$("#prod_bunryu").val("${dto.classification }").prop("selected", true);
	$("#maker").val("${dto.maker }").prop("selected", true);
	$("#purchase_price").val(addComma($("#purchase_price").val()));
	$("#selling_price").val(addComma($("#selling_price").val()));
	$("#stock").val(addComma($("#stock").val()));
	$("#incoming").val(addComma($("#incoming").val()));
	
	$("input:text[id='purchase_price']").on("keyup", function() {
	       $(this).val(addComma($(this).val().replace(/[^0-9]/g,"")));
	    });
    $("input:text[id='selling_price']").on("keyup", function() {
        $(this).val(addComma($(this).val().replace(/[^0-9]/g,"")));
     });
    $("input:text[id='stock']").on("keyup", function() {
        $(this).val(addComma($(this).val().replace(/[^0-9]/g,"")));
     });
    $("input:text[id='incoming']").on("keyup", function() {
        $(this).val(addComma($(this).val().replace(/[^0-9]/g,"")));
     });
});

function addComma(value) {
    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function list() {
	location.href = "${path }/product_servlet/list.do";
}

function sujung() {
	if(form.name.value == "") {
		alert('상품명을 입력하세요.');
		form.name.focus();
		return;
	}
	if(form.classification.value == "") {
		alert('상품분류를 선택하세요.');
		form.classification.focus();
		return;
	}
	if(form.maker.value == "") {
		alert('제조사를 선택하세요.');
		form.maker.focus();
		return;
	}
	if(form.purchase_price.value == "0") {
		alert('매입가를 입력하세요.');
		form.purchase_price.focus();
		return;
	}
	if(form.selling_price.value == "0") {
		alert('판매가를 입력하세요.');
		form.selling_price.focus();
		return;
	}
	if(form.description.value == "") {
		alert('상품설명을 입력하세요.');
		form.description.focus();
		return;
	}
	var length = $("input[type=file]").length;
	$("#uploadCounter").val(length);
	
	document.form.enctype = "multipart/form-data";
	document.form.method = "post";
	document.form.action = "${path }/product_servlet/sujungProc.do";
	document.form.submit();
}
</script>