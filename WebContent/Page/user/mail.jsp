<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="no-js">
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<title>TSSSpider-邮件列表</title>
	<link rel="stylesheet" type="text/css" href="./Source/Public/dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="./Source/Public/drawer/css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/drawer/css/demo.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/font-awesome-4.2.0/css/font-awesome.min.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/drawer/css/menu_elastic.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/css/common/page.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/css/user/mail.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/hubspot/css/messenger.css" />
	<link rel="stylesheet" type="text/css" href="./Source/Public/hubspot/css/messenger-theme-air.css" />
</head>
<body>
	<div class="container">
		<%@include file="../common/drawer.jsp" %>
		<div class="content-wrap">
			<div class="content harveyprince">
				<header>
					<div><i class="fa fa-chevron-left"></i>邮件列表查看<i class="fa fa-chevron-right"></i></div>
				</header>
				<div class="main-panel">
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
	    extraClasses: 'messenger-fixed messenger-on-top messenger-on-right',
	    theme: 'air'
	}
	// Messenger().post("Your request has succeded!");
</script>
<script src="./Source/Public/js/user/mail.js"></script>
</html>