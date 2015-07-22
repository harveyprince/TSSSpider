<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="no-js">
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<title>TSSSpider-激活提示</title>
	<link rel="stylesheet" type="text/css" href="./Source/Public/dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="./Source/Public/drawer/css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/drawer/css/demo.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/font-awesome-4.2.0/css/font-awesome.min.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/drawer/css/menu_elastic.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/css/common/page.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/css/user/mail.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/css/user/activate.css" />
</head>
<body>
	<div class="container">
		<div class="content-wrap">
			<div class="content harveyprince">
				<header>
					<div>激活提示</div>
				</header>
				<div class="main-panel">
					<div class="mail-block">
						<s:if test="%{pageResult.result}">
							<h4>激活成功</h4>
							<div class="mail-content">等待<span>5</span>秒后跳转到首页</div>
						</s:if>
						<s:else>
							<h4>激活失败</h4>
							<div><s:property value="pageResult.comment" /></div>
							<div class="mail-content">等待<span>5</span>秒后跳转到首页</div>
						</s:else>
					</div>
				</div>
			</div>
		</div><!-- /content-wrap -->
	</div><!-- /container -->

</body>
<script src="./Source/Public/jquery/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var i = 5;
	var inter = setInterval(function(){
		$(".mail-content span").text(i);
		if(i==0){
			clearInterval(inter);
			window.location.href="welcome";
		}
		i--;
	},1000);
});
</script>
</html>