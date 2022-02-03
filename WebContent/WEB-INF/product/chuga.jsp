<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../_include/inc_header.jsp" %>

<div class="prod_title">
	<span class="ms-1" id="span_1">상품관리</span>
	<span class="mx-2" id="span_2"> | &nbsp; 상품등록</span>
</div>

<form name="form">
<div class="prod_table">
	<table class="table table-bordered mb-3">
		<tr class="align-middle">
			<th class="table-light">
				<div class="ms-3 my-1"><span style="color: red">* &nbsp;</span>상품명</div>			
			</th>
			<td>
				<div class="ms-2">
					<input type="text" class="form-control form-control-sm" name="name">
				</div>
			</td>
		</tr>
		<tr class="align-middle">
			<th class="table-light">
				<div class="ms-3 my-1"><span style="color: red">* &nbsp;</span>상품분류</div>
			</th>
			<td>
				<div class="ms-2 row">		
					<select class="form-select form-select-sm w-25" name="classification" >
						<option value="" selected>선택하세요</option>
						<c:forEach var="dto" items="${prodTypeList }">
							<option value="${dto.classification }" >${dto.classification }</option>						
						</c:forEach>
					</select>
				</div>			
			</td>
		</tr>
		<tr class="align-middle">
			<th class="table-light">
				<div class="ms-3 my-1"><span style="color: red">* &nbsp;</span>제조사</div>
			</th>
			<td>
				<div class="ms-2 row">		
					<select class="form-select form-select-sm w-25" name="maker" >
						<option value="" selected>선택하세요</option>
						<c:forEach var="dto" items="${MakerList }">
							<option value="${dto.maker }" >${dto.maker }</option>
						</c:forEach>
					</select>
				</div>			
			</td>
		</tr>
		<tr class="align-middle">
			<th class="table-light">
				<div class="ms-3 my-1"><span style="color: red">* &nbsp;</span>매입가</div>
			</th>
			<td>
				<div class="ms-2 row">
					<input type=text class="form-control form-control-sm w-25" name="purchase_price" id="purchase_price" value="0" onkeyup="addComma(this);"> &nbsp;원
				</div>
			</td>
		</tr>
		<tr class="align-middle">
			<th class="table-light">
				<div class="ms-3 my-1"><span style="color: red">* &nbsp;</span>판매가</div>
			</th>
			<td>
				<div class="ms-2 row">				
					<input type="text" class="form-control form-control-sm w-25" name="selling_price" id="selling_price" value="0"> &nbsp;원
				</div>
			</td>
		</tr>
		<tr class="align-middle">
			<th class="table-light">
				<div class="ms-3 my-1"> &nbsp;&nbsp;&nbsp; 할인율</div>
			</th>
			<td>
				<div class="ms-2 row">
					<input type="number"  class="form-control form-control-sm w-25" name="sale_percent" value="0"> &nbsp;%
				</div>
			</td>
		</tr>
		<tr class="align-middle">
			<th class="table-light">
				<div class="ms-3 my-1"> &nbsp;&nbsp;&nbsp; 재고</div>
			</th>
			<td>
				<div class="ms-2 row">
					<input type="text"  class="form-control form-control-sm w-25" name="stock" id="stock" value="0"> &nbsp; EA	
				</div>			
			</td>			
		</tr>
		<tr class="align-middle">
			<th class="table-light">
				<div class="ms-3 my-1"> &nbsp;&nbsp;&nbsp; 섬네일이미지1</div>
			</th>
			<td>
				<div class="ms-2 w-50">
					<input class="form-control form-control-sm" id="formFileSm" type="file" name="img_01">
				</div>
			</td>
		</tr>
		<tr class="align-middle">
			<th class="table-light">
				<div class="ms-3 my-1"> &nbsp;&nbsp;&nbsp; 섬네일이미지2</div>
			</th>
			<td>
				<div class="ms-2 w-50">
					<input class="form-control form-control-sm" id="formFileSm" type="file" name="img_02">
				</div>
			</td>
		</tr>
		<tr class="align-middle">
			<th class="table-light">
				<div class="ms-3 my-1"> &nbsp;&nbsp;&nbsp; 섬네일이미지3</div>
			</th>
			<td>
				<div class="ms-2 w-50">
					<input class="form-control form-control-sm" id="formFileSm" type="file" name="img_03">
				</div>			
			</td>
		</tr>
		<tr class="align-middle">
			<th class="table-light">
				<div class="ms-3 my-1"><span style="color: red">* &nbsp;</span>상품설명</div>
			</th>
			<td>				
				<div class="form-floating ms-2">
				 	<textarea class="form-control" name="description" style="height: 120px"></textarea>				  	
				</div>						
			</td>
		</tr>	
	</table>
	<div class="text-center">
		<button type="button" onClick="list();" class="btn btn-light me-1">목록으로</button>
		<button type="button" onClick="chuga();" class="btn btn-primary ms-1" >상품등록</button>		
	</div>
</div>
</form>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script>

$(document).ready(function(){
    $("input:text[id='purchase_price']").on("keyup", function() {
       $(this).val(addComma($(this).val().replace(/[^0-9]/g,"")));
    });
    $("input:text[id='selling_price']").on("keyup", function() {
        $(this).val(addComma($(this).val().replace(/[^0-9]/g,"")));
     });
    $("input:text[id='stock']").on("keyup", function() {
        $(this).val(addComma($(this).val().replace(/[^0-9]/g,"")));
     });
  });
  
function addComma(value) {
    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function list() {
	location.href = "${path }/product_servlet/list.do";
}

function chuga() {
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
	document.form.enctype = "multipart/form-data";
	document.form.method = "post";
	document.form.action = "${path }/product_servlet/chugaProc.do";
	document.form.submit();
}

</script>