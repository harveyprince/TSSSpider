$(document).ready(function(){
	$(".form-box").FormBoxInit({
			loginAble:true,
			loginBox:['email','password'],
			loginAction:"http://www.baidu.com",
			signupAble:true,
			signupBox:['name','email','password'],
			signupAction:"http://www.baidu.com",

			//functions
			onformsubmit:null,
			onloginformsubmit:function(e,which){
				var $box = $(which).find('.alarm-box').alarmInit();
				var action = $(which).attr('action');
				var data = new FormData();
				$(which).find('input').each(function(){
					data.append($(this).attr('name'),$(this).val());
				});
				$.ajax({
					data: data,
					type: "POST",
					url: action,
					cache: false,
					contentType: false,
					processData: false,
					success: function(data) {
						//callback code
					},
					error:function(){
						$box.alarm("请求发生异常");
					}
				});
				e.preventDefault();
			},
			onsignupformsubmit:function(e,which){
				var $box = $(which).find('.alarm-box').alarmInit();
				var action = $(which).attr('action');
				var data = new FormData();
				$(which).find('input').each(function(){
					data.append($(this).attr('name'),$(this).val());
				});
				$.ajax({
					data: data,
					type: "POST",
					url: action,
					cache: false,
					contentType: false,
					processData: false,
					success: function(data) {
						//callback code
					},
					error:function(){
						$box.alarm("请求发生异常");
					}
				});
				e.preventDefault();
			}
		});
});