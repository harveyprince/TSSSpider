<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
	.name{
		padding-left: 20px;
  		font-size: 20px;
  		padding-bottom: 20px;
	}
	.name span{
		font-size: 15px;
  		padding-right: 10px;
	}
</style>
<div class="menu-wrap">
	<nav class="menu">
		<div class="name"><span>欢迎</span><s:property value="name" /></div>
		<div class="icon-list">
			<a href="follow"><i class="fa fa-fw fa-star-o"></i><span>订阅设置</span></a>
			<a href="mail"><i class="fa fa-fw fa-bell-o"></i><span>邮件查询</span></a>
			<a href="signout"><i class="fa fa-sign-out"></i><span>退出</span></a>
		</div>
	</nav>
	<button class="close-button" id="close-button">Close Menu</button>
	<div class="morph-shape" id="morph-shape" data-morph-open="M-1,0h101c0,0,0-1,0,395c0,404,0,405,0,405H-1V0z">
		<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 100 800" preserveAspectRatio="none">
			<path d="M-1,0h101c0,0-97.833,153.603-97.833,396.167C2.167,627.579,100,800,100,800H-1V0z"/>
		</svg>
	</div>
</div>
<button class="menu-button" id="open-button">Open Menu</button>