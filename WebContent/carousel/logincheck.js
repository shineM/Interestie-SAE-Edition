/**
 * 登录
 */

$(document).ready(
		function() {
			 $(document).keydown(function(event){
				    if(event.keyCode==13){
				    	$(".submit-button").click();
				    }
				 });
			$(".submit-button").click(
					function() {
						var inputuser = $("input[name='username']").val();
						var inputpswd = $("input[name='password']").val();
						if (inputuser == null || inputuser == ""||inputpswd == null || inputpswd == "") {
							alert("用户和密码不能为空")
						} else {

							$.ajax({
								type : "post",
								url : "/login?username="
										+ encodeURI(encodeURI(inputuser))
										+ "&password="
										+ encodeURI(encodeURI(inputpswd)),
								dataType : "HTML",
								success : function(data) {

									if ("2" == data) {
										alert("密码错误");
									} else if ("3" == data) {
										alert("用户不存在");
									} else {
										setCookie("username", data, 365);
										window.location.href = "/";
									}

								},
							error:function(){
								alert("服务器异常，请联系管理员");
							}
							});
						}
					});
			//breakdown the labels into single character spans
			$(".flp label").each(function(){
				var sop = '<span class="ch">'; //span opening
				var scl = '</span>'; //span closing
				//split the label into single letters and inject span tags around them
				$(this).html(sop + $(this).html().split("").join(scl+sop) + scl);
				//to prevent space-only spans from collapsing
				$(".ch:contains(' ')").html("&nbsp;");
			})

			var d;
			//animation time
			$(".flp input").focus(function(){
				//calculate movement for .ch = half of input height
				var tm = $(this).outerHeight()/2 *-1 + "px";
				//label = next sibling of input
				//to prevent multiple animation trigger by mistake we will use .stop() before animating any character and clear any animation queued by .delay()
				$(this).next().addClass("focussed").children().stop(true).each(function(i){
					d = i*50;//delay
					$(this).delay(d).animate({top: tm}, 200, 'easeOutBack');
				})
			})
			$(".flp input").blur(function(){
				//animate the label down if content of the input is empty
				if($(this).val() == "")
				{
					$(this).next().removeClass("focussed").children().stop(true).each(function(i){
						d = i*50;
						$(this).delay(d).animate({top: 0}, 500, 'easeInOutBack');
					})
				}
			})

			





		});
