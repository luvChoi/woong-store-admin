<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../_include/inc_header.jsp" %>

<!-- Header -->
<header>
	<div class="header">				
		<div class="member">	
			<ul>
				<li><a href="#" class="text-decoration-none">정보수정</a></li>
				<li><a href="${path }/main_servlet/logout.do" class="text-decoration-none">로그아웃</a></li>					
			</ul>
		</div>		
	</div>
</header>

<div id="nav">
	<div class="logo">
		<h2><a href="${path }/main_servlet/main.do" class="text-decoration-none">
			ST<img src="../_resources/images/homeTag.png" style="height: 50px; width: 50px; margin-bottom: 10px;">RE
		</a></h2>
	</div>
	<nav class="menu">
		<ul class="nav nav-pills my-auto">
			<li class="nav-item dropdown">
			    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">상품관리</a>
			    <ul class="dropdown-menu" style="margin-left: 20px;">
					<li><a class="dropdown-item" href="${path }/product_servlet/list.do">상품목록</a></li>				
			      	<li><a class="dropdown-item" href="${path }/product_servlet/chuga.do">상품등록</a></li>
			    </ul>
		  	</li>
		  	
		  	<li class="nav-item">
		  		<a class="nav-link" href="${path }/order_servlet/list.do">주문관리</a>
		  	</li>
		  	  	
		  	<li class="nav-item dropdown">
			    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">고객지원</a>
			    <ul class="dropdown-menu">
					<li><a class="dropdown-item" href="${path }/product_servlet/list.do">자주하는질문</a></li>					
			      	<li><a class="dropdown-item" href="${path }/product_servlet/chuga.do">질문답변</a></li>
			    </ul>
		  	</li>
		  	
		  	<li class="nav-item">
		  		<a class="nav-link" href="#">통계분석</a>
		  	</li>
		  	
		  	<li class="nav-item">
		  		<a class="nav-link" href="#">회원관리</a>
		  	</li>			  	
		 	  	
			<li class="nav-item dropdown">
			    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">계정관리</a>
			    <ul class="dropdown-menu">
					<li><a class="dropdown-item" href="${path }/admin_servlet/list.do">계정목록</a></li>
			      	<li><a class="dropdown-item" href="${path }/admin_servlet/chuga.do">계정등록</a></li>
			    </ul>
		  	</li>			
		</ul>
	</nav>		
</div>