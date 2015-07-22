var mailpage = 0;
function getMail(page){
	var data = new FormData();
	data.append("page",page);
	$.ajax({
		data: fdata,
		type: "POST",
		url: "JSON/maillist",
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			var json = data;
			if(json.code==1){
				mailpage = page;
				var list = json.mail;
				for(var idx in list){
					var ma = list[idx];
					$(".main-panel").html("");
					$(".main-panel").append(
						$('<div class="mail-block">').append(
							$("<h4>").text(ma.time+">"+ma.title)
						).append(
							$('<div class="mail-content">').html(ma.content.replace('\n',"<br />"))
						)
					);
				}
			}else{
				Messenger().post(json.comment);
			}
		},
		error:function(){
			Messenger().post("请求出现异常");
		}
	});
}
$(document).ready(function(){
	getMail(0);
	$("header .fa-chevron-left").click(function(){
		if(mailpage>0){
			getMail(mailpage-1);
		}
	});
	$("header .fa-chevron-right").click(function(){
		getMail(mailpage+1);
	});
});