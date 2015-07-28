var search = "";
var coursepage = 0;
function getCourse(page){
	var data = new FormData();
	data.append("page",page);
	data.append("key",search);
	$.ajax({
		data: data,
		type: "POST",
		url: "JSON/course",
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			var json = data;
			if(json.code==1){
				coursepage = page;
				var list = json.course;
				$(".courselist-panel table").html("");
				for(var idx in list){
					var co = list[idx];
					$(".courselist-panel table").append(
						$('<tr class="active">').append(
							$("<td>").append(
								$("<a>").attr("href","http://218.94.159.102/tss/en/"+co.code+"/index.html").text(co.code)
							)
						).append(
							$("<td>").text(co.name)
						).append(
							$("<td>").text(co.teacher)
						).append(
							$("<td>").append(
								$('<i class="fa fa-plus-square follow">').attr("code",co.code)
							)
						)
					);
				}
				$(".follow").click(function(){
					var fo  = $(this);
					var code = fo.attr("code");
					var fdata = new FormData();
					fdata.append("code",code);
					$.ajax({
						data: fdata,
						type: "POST",
						url: "JSON/followCourse",
						cache: false,
						contentType: false,
						processData: false,
						success: function(data) {
							var json = data;
							if(json.code==1){
								getCourse(coursepage);
								getFollow(followpage);
							}else{
								Messenger().post(json.comment);
							}
						},
						error:function(){
							Messenger().post("请求出现异常");
						}
					});
				});
			}else{
				Messenger().post(json.comment);
			}
		},
		error:function(){
			Messenger().post("请求出现异常");
		}
	});
}
var followpage = 0;
function getFollow(page){
	var data = new FormData();
	data.append("page",page);
	$.ajax({
		data: data,
		type: "POST",
		url: "JSON/followlist",
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			var json = data;
			if(json.code==1){
				followpage = page;
				var list = json.course;
				$(".follow-panel table").html("");
				for(var idx in list){
					var co = list[idx];
					$(".follow-panel table").append(
						$('<tr class="active">').append(
							$("<td>").append(
								$("<a>").attr("href","http://218.94.159.102/tss/en/"+co.code+"/index.html").text(co.code)
							)
						).append(
							$("<td>").text(co.name)
						).append(
							$("<td>").text(co.teacher)
						).append(
							$("<td>").append(
								$('<i class="fa fa-minus-square unfollow">').attr("code",co.code)
							)
						)
					);
				}
				$(".unfollow").click(function(){
					var fo  = $(this);
					var code = fo.attr("code");
					var fdata = new FormData();
					fdata.append("code",code);
					$.ajax({
						data: fdata,
						type: "POST",
						url: "JSON/unfollowCourse",
						cache: false,
						contentType: false,
						processData: false,
						success: function(data) {
							var json = data;
							if(json.code==1){
								getCourse(coursepage);
								getFollow(followpage);
							}else{
								Messenger().post(json.comment);
							}
						},
						error:function(){
							Messenger().post("请求出现异常");
						}
					});
				});
			}else{
				if(page==0){
					$(".follow-panel table").html("");
				}else{
					Messenger().post(json.comment);
				}
			}
		},
		error:function(){
			Messenger().post("请求出现异常");
		}
	});
}
$(document).ready(function(){
	getCourse(0);
	getFollow(0);
	// 翻页
	$(".courselist-panel .previous").click(function(){
		if(coursepage>0){
			getCourse(coursepage-1);
		}
	});
	$(".courselist-panel .next").click(function(){
		getCourse(coursepage+1);
	});
	$(".follow-panel .previous").click(function(){
		if(followpage>0){
			getFollow(followpage-1);
		}
	});
	$(".follow-panel .next").click(function(){
		getFollow(followpage+1);
	});
	// 检索
	$("input.search").focus(function(){
		$(document).keyup(keysearch);
	});
	$("input.search").blur(function(){
		$(document).unbind("keyup",keysearch);
	});
	$("button.search").click(function(){
		search = $(".search").val();
		getCourse(coursepage);
	});
	$(".followWay-panel tr").click(function(){
		var fdata = new FormData();
		var tway = $(this);
		fdata.append("way",$(this).attr("value"));
		$.ajax({
			data: fdata,
			type: "POST",
			url: "JSON/changeway",
			cache: false,
			contentType: false,
			processData: false,
			success: function(data) {
				var json = data;
				if(json.code==1){
					tway.removeClass("active");
					tway.addClass("success");
					tway.siblings().removeClass("success");
					tway.siblings().addClass("active");
				}else{
					Messenger().post(json.comment);
				}
			},
			error:function(){
				Messenger().post("请求出现异常");
			}
		});
	});
});
function keysearch(event){
	var keyCode = event.keyCode;
	console.log(keyCode);
	if(keyCode==13){
		search = $("input.search").val();
		getCourse(coursepage);
	}
}