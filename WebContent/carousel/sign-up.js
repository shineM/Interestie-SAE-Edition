/**
 * Sign-up
 */

$(document)
		.ready(
				function() {
					$("input[name='username']").focus();

					// 用户名判断
					$("input[name='username']").focus(function() {
						$(this).next().removeClass("show")

					});
					$("input[name='username']")
							.blur(
									function() {
										var thisdocument = $(this);
										if (thisdocument.val() == "") {

											thisdocument.next()
													.addClass("show");
											thisdocument.next().html("请输入姓名");
										} else {

											var username1 = $(
													"input[name='username']")
													.val();
											var password1 = "©√∂ˆ˙ƒøßπ∆∂∂¥ƒ˚ß˜ƒ";
											$
													.ajax({
														type : "post",
														url : "/login?username="
																+ encodeURI(encodeURI(username1))
																+ "&password="
																+ encodeURI(encodeURI(password1)),
														dataType : "HTML",
														success : function(data) {

															if ("2" == data) {
																thisdocument
																		.next()
																		.addClass(
																				"show");
																thisdocument
																		.next()
																		.html(
																				"用户已存在");
															}

														}
													});

										}

									});
					// 密码判断
					$("input[name='password']").focus(function() {
						$(this).next().removeClass("show")

					});
					$("input[name='password']").blur(function() {

						if ($(this).val() == "") {

							$(this).next().addClass("show");
							$(this).next().html("请输入密码");
						} else if ($(this).val().length < 6) {
							$(this).next().addClass("show");
							$(this).next().html("密码至少六位");

						}

					});
					// 邮箱判断
					$("input[name='email']").focus(function() {
						$(this).next().removeClass("show")

					});
					$("input[name='email']")
							.blur(
									function() {
										var thisdocument = $(this);
										if ($(this).val() == "") {

											$(this).next().addClass("show");
											$(this).next().html("请输入邮箱");
										} else if (!$(this)
												.val()
												.match(
														/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)) {
											$(this).next().addClass("show");
											$(this).next().html("邮箱格式不对");

										} else {

											var username1 = $(
													"input[name='email']")
													.val();
											var password1 = "mail∂ˆ˙ƒøßπ∆∂∂¥ƒ˚ß˜ƒ";
											$
													.ajax({
														type : "post",
														url : "/login?username="
																+ encodeURI(encodeURI(username1))
																+ "&password="
																+ encodeURI(encodeURI(password1)),
														dataType : "HTML",
														success : function(data) {

															if ("2" == data) {
																thisdocument
																		.next()
																		.addClass(
																				"show");
																thisdocument
																		.next()
																		.html(
																				"该邮箱已被使用");
															}

														}
													});

										}
									});
					// // 提交
					$(".finish-sign-up")
							.click(
									function() {
										var username = $("input[name='username']").val();
										var userpwd =  $("input[name='password']").val();
										var useremail =  $("input[name='email']").val();
										var userTags = "";
										var choosedTags = document
												.getElementsByClassName("choosed");
										for (var int = 0; int < choosedTags.length; int++) {
											userTags += choosedTags[int].value + "~";
										}
										
										userTags.substring(0, choosedTags.length - 1);
										if (choosedTags.length == 0) {
											//！！！！！！如果用户未选择标签，则设默认值！！！！！！！！！
										    userTags = "App~开发者社区"	
										} 
										
											$.ajax({
												type : "post",
												url : "/sign-up?username="
														+ encodeURI(encodeURI(username)) + "&password="
														+ encodeURI(encodeURI(userpwd))+ "&email="
														+ encodeURI(encodeURI(useremail))+ "&tags="
														+ encodeURI(encodeURI(userTags)),
												dataType : "HTML",
												success : function(data) {
													if ("2"==data) {
														setCookie("username", username, 365);
													} 
												}

											});
										
									});
					// 标签
					$(".topic-tag-button").click(function() {

						$(this).toggleClass("choosed");
					});
					// jQuery time
					var current_fs, next_fs, previous_fs; // fieldsets
					var left, opacity, scale; // fieldset properties which we
					// will
					// animate
					var animating; // flag to prevent quick multi-click
					// glitches
					$(".next").click(
							function() {

								var iscorrect = $(".error").hasClass("show");
								if (iscorrect) {
									return false;
								} else {
									if (animating)
										return false;
									animating = true;

									current_fs = $(this).parent();
									next_fs = $(this).parent().next();

									// activate next step on progressbar using
									// the index of
									// next_fs
									$("#progressbar li").eq(
											$("fieldset").index(next_fs))
											.addClass("page-active");

									// show the next fieldset
									next_fs.show();
									// hide the current fieldset with style
									current_fs.animate({
										opacity : 0
									}, {
										step : function(now, mx) {
											// as the opacity of current_fs
											// reduces to 0 -
											// stored in "now"
											// 1. scale current_fs down to 80%
											scale = 1 - (1 - now) * 0.2;
											// 2. bring next_fs from the
											// right(50%)
											left = (now * 50) + "%";
											// 3. increase opacity of next_fs to
											// 1 as it
											// moves in
											opacity = 1 - now;
											current_fs.css({
												'transform' : 'scale(' + scale
														+ ')'
											});
											next_fs.css({
												'left' : left,
												'opacity' : opacity
											});
										},
										duration : 800,
										complete : function() {
											current_fs.hide();
											animating = false;
										},
										// this comes from the custom easing
										// plugin
										easing : 'easeInOutBack'
									});
								}
							});

					$(".previous").click(
							function() {
								if (animating)
									return false;
								animating = true;

								current_fs = $(this).parent();
								previous_fs = $(this).parent().prev();

								// de-activate current step on progressbar
								$("#progressbar li").eq(
										$("fieldset").index(current_fs))
										.removeClass("page-active");

								// show the previous fieldset
								previous_fs.show();
								// hide the current fieldset with style
								current_fs.animate({
									opacity : 0
								}, {
									step : function(now, mx) {
										// as the opacity of current_fs reduces
										// to 0 -
										// stored in "now"
										// 1. scale previous_fs from 80% to 100%
										scale = 0.8 + (1 - now) * 0.2;
										// 2. take current_fs to the right(50%)
										// - from
										// 0%
										left = ((1 - now) * 50) + "%";
										// 3. increase opacity of previous_fs to
										// 1 as it
										// moves in
										opacity = 1 - now;
										current_fs.css({
											'left' : left
										});
										previous_fs.css({
											'transform' : 'scale(' + scale
													+ ')',
											'opacity' : opacity
										});
									},
									duration : 800,
									complete : function() {
										current_fs.hide();
										animating = false;
									},
									// this comes from the custom easing plugin
									easing : 'easeInOutBack'
								});
							});

					$(".submit").click(function() {
						return false;
					})
					$(".gotohome").click(function() {
						
						window.location.href = "/";
					})

				});
