$(document).ready(function(){
	$(".form-box").FormBoxInit({
			loginAble:true,
			loginBox:['email','password'],
			loginAction:"JSON/signin",
			signupAble:true,
			signupBox:['name','email','password'],
			signupAction:"JSON/signup",

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
						var json = data;
						if(json.code==1){
							window.location.href = "follow";
						}else{
							$box.alarm(json.comment);
						}
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
						var json = data;
						if(json.code==1){
							$box.alarm("请查收激活邮件");
							$box.alarm("30分钟内有效");
						}else{
							$box.alarm(json.comment);
						}
					},
					error:function(){
						$box.alarm("请求发生异常");
					}
				});
				e.preventDefault();
			}
		});
});