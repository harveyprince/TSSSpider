<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="no-js">
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<title>TSSSpider-课程订阅设置</title>
	<link rel="stylesheet" type="text/css" href="./Source/Public/dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="./Source/Public/drawer/css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/drawer/css/demo.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/font-awesome-4.2.0/css/font-awesome.min.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/drawer/css/menu_elastic.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/css/common/page.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/css/user/follow.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/hubspot/css/messenger.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/hubspot/css/messenger-theme-air.css" />
</head>
<body>
	<div class="container">
		<%@include file="../common/drawer.jsp" %>
		<div class="content-wrap">
			<div class="content harveyprince">
				<header>
					<div>课程订阅设置</div>
				</header>
				<div class="main-panel">
					<div class="panel-block courselist-panel col-md-4">
						<header>课程列表</header>
						<div class="input-group">
						      <span class="input-group-btn">
						        <button class="btn btn-default search" type="button"><i class="fa fa-search"></i></button>
						      </span>
						      <input type="text" class="form-control search" placeholder="按课程名、教师名、课程号检索">
						</div><!-- /input-group -->
						<table class="table table-striped">
						</table>
						<footer>
							<nav>
							  <ul class="pager">
							    <li class="previous"><a href="javascript:void(0)"><span aria-hidden="true">&larr;</span> 上一页</a></li>
							    <li class="next"><a href="javascript:void(0)">下一页 <span aria-hidden="true">&rarr;</span></a></li>
							  </ul>
							</nav>
						</footer>
					</div>
					<div class="panel-block follow-panel col-md-4">
						<header>订阅列表</header>
						<table class="table table-striped">
						</table>
						<footer>
							<nav>
							  <ul class="pager">
							    <li class="previous"><a href="javascript:void(0)"><span aria-hidden="true">&larr;</span> 上一页</a></li>
							    <li class="next"><a href="javascript:void(0)">下一页 <span aria-hidden="true">&rarr;</span></a></li>
							  </ul>
							</nav>
						</footer>
					</div>
					<div class="panel-block followWay-panel col-md-4">
						<header>订阅方式</header>
						<table class="table table-striped">
							<tr class="active" value="0">
								<td>全部关注</td>
								<td><i class="fa fa-check"></i></td>
							</tr>
							<tr class="active" value="1">
								<td>白名单</td>
								<td><i class="fa fa-check"></i></td>
							</tr>
							<tr class="active" value="2">
								<td>黑名单</td>
								<td><i class="fa fa-check"></i></td>
							</tr>
							<tr class="active" value="3">
								<td>取消通知</td>
								<td><i class="fa fa-check"></i></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div><!-- /content-wrap -->
	</div><!-- /container -->

</body>
<script src="./Source/Public/jquery/jquery.min.js"></script>
<script src="./Source/Public/backbone-1.2.1/underscore.js"></script>
<script src="./Source/Public/backbone-1.2.1/backbone-min.js"></script>
<script src="./Source/Public/hubspot/js/messenger.min.js"></script>
<script src="./Source/Public/drawer/js/snap.svg-min.js"></script>
<script src="./Source/Public/drawer/js/classie.js"></script>
<script src="./Source/Public/drawer/js/main3.js"></script>
<script type="text/javascript">
	Messenger.options = {
	    extraClasses: 'messenger-fixed messenger-on-top',
	    theme: 'air'
	}
	$(".followWay-panel tr[value='<s:property value="followWay" />']").removeClass("active");
	$(".followWay-panel tr[value='<s:property value="followWay" />']").addClass("success");
</script>
<script src="./Source/Public/js/user/follow.js"></script>
</html>