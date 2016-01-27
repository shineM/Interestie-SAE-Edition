/**
 * 主JS
 */
var islogin;
var user = "";
var myTags = "篮球~App";
function getCookie(c_name) {
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(c_name + "=")
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1
			c_end = document.cookie.indexOf(";", c_start)
			if (c_end == -1)
				c_end = document.cookie.length
			return unescape(document.cookie.substring(c_start, c_end))
		}
	}
	return ""
}

function setCookie(c_name, value, expiredays) {
	var exdate = new Date()
	exdate.setDate(exdate.getDate() + expiredays)
	document.cookie = c_name + "=" + escape(value)
			+ ((expiredays == null) ? "" : "; expires=" + exdate.toGMTString())
}

function checkCookie() {
	username = getCookie('username');

	if (username != null && username != "") {
		islogin = true;
		user = username;
		/* myTags = tag-cookie; */
		/*
		 * tag-cookie = getCookie('tag-cookie'); if (tag-cookie != null &&
		 * tag-cookie!="") { myTags = tag-cookie; }else { myTags = "App~NBA"; }
		 */

	} else {
		islogin = false;
//		window.location.href = "login.html";
	}

}

// =============================分割线========================================
//检查cookie
checkCookie();

$(document).ready(
		function() {
			if (islogin == true) {
				// ==========如果用户cookie存在

				document.getElementById("user").style.visibility = "hidden";

				document.getElementById("login-button").innerHTML = user
						.charAt(0).toUpperCase();
				$("#login-button").attr("title","注销");
				$(".user").toggleClass("user-pic");
				$.ajax({
					type : "post",
					url : "/login?username="
							+ encodeURI(encodeURI(user)) + "&password="
							+ encodeURI(encodeURI("∆ß©ƒ∂ßåΩ≈ç√∫˜µ")),
					dataType : "HTML",
					success : function(data) {

						if (data == null) {
							alert("请添加标签");
						} else {
							myTags = data;
							changeTagBoard(myTags);
							if (myTags != null & myTags != "") {
								reloadContent(1, myTags);
							} else {
								reloadContent(1, myTags);
							}

						}

					}
				});
			} else {
				reloadContent(1, myTags);
			}
			//-------------------编辑标签
			$(".topic-tag-button").click(function() {

				$(this).toggleClass("choosed");
			});
			//-------------------提交修改结果
			$("#tagSubmit").click(
					function() {
						myTags = "";
						var choosedTags = document
								.getElementsByClassName("choosed");
						
						for (var int = 0; int < choosedTags.length; int++) {
							myTags += choosedTags[int].value + "~";
						}
						myTags.substring(0, choosedTags.length - 1);
						if (choosedTags.length == 0) {
							alert("请至少选择一个");
						} else {
							$("#myModal").modal('hide');

							document.getElementById("pageNum").innerHTML = "1";
							reloadContent(1, myTags);

							changeTagBoard(myTags);
							$.ajax({
								type : "post",
								url : "/update?user="
										+ encodeURI(encodeURI(user)) + "&tags="
										+ encodeURI(encodeURI(myTags)),
								dataType : "HTML",
								success : function(data) {
									return;
								}

							});
						}
					});
			// 标签板选取单个标签
			$("input.littletag").click(function() {

				var string = $(this).attr("value");

				myTags = string;
				reloadContent(1, myTags);
			});
			// 上一页
			$("button#beforePage").click(function() {

				var pageString = document.getElementById("pageNum").innerHTML;
				var pageNum = parseInt(pageString);

				if (pageNum == 1) {

					reloadContent(1, myTags);
				} else {

					reloadContent(pageNum - 1, myTags);
					document.getElementById("pageNum").innerHTML = pageNum - 1;
				}
			});
			//下一页
			$("button#nextPage").click(function() {

				var pageString = document.getElementById("pageNum").innerHTML;
				var pageNum = parseInt(pageString);

				reloadContent(pageNum + 1, myTags);
				document.getElementById("pageNum").innerHTML = pageNum + 1;
			});

		});
// 更新右侧标签板
function changeTagBoard(tags) {

	var board = document.getElementById("board");
	board.innerHTML = "";
	var list = tags.split("~", 30);
	if (list.length == 1) {
		var e = document.createElement("input");
		e.setAttribute("type", "button");
		e.setAttribute("class", "littletag");
		e.setAttribute("value", list[0]);
		e.setAttribute("onclick", "tagclick('" + list[0] + "')");
		board.appendChild(e);
	} else {
		for (var int = 0; int < list.length - 1; int++) {
			var e = document.createElement("input");
			e.setAttribute("type", "button");
			e.setAttribute("class", "littletag");
			e.setAttribute("value", list[int]);
			e.setAttribute("onclick", "tagclick('" + list[int] + "')");
			board.appendChild(e);
		}
	}
}
// 修改个人标签
function updateMytags(user, tags) {
	$.ajax({
		type : "post",
		url : "/update?user=" + encodeURI(encodeURI(user))
				+ "&tags=" + encodeURI(encodeURI(tags)),
		dataType : "HTML",
		success : function(data) {
			return;
		}

	});
}
//前往这个标签
function tagclick(tag) {
	changeTagBoard(tag);
	myTags = tag;
	reloadContent(1, myTags);
}
//重新加载当前页面
function reloadContent(page, tags) {
	document.getElementById("loadAni").style.visibility = "visible";

	sendValues(page, tags);
}
//获取数据
function sendValues(page, tags) {

	xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = callback;
	var url = "/get?page=" + page + "&tags="
			+ encodeURI(encodeURI(tags));
	// ajax传递中文可以使用两次转码
	xmlHttp.open("GET", url, true);
	xmlHttp.send();
}
function callback() {

	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		$("li.contentList,li.pre-load").remove();
		$(".pre-load ul").hide();
		$('body,html').animate({
			scrollTop : 0
		}, 100);
		var value = xmlHttp.responseText;
		var list = value.split("***", 1000);
		var resultArea = document.getElementById("resultArea");
		for (var i = 0; i < list.length / 4 - 1; i++) {
			var e = document.createElement("li");
			e.className = "contentList";
			var url = list[4 * i + 1];
			var tag = list[4 * i + 2];
			var from = list[4 * i + 3];

			e.innerHTML = "<a class='main-title' href="
					+ url
					+ " target='_blank'>"
					+ list[4 * i]
					+ "</a> <div class='article-tag'><input type='button' onclick='tagclick("
					+ "\"" + tag + "\"" + ");' class='article-tag-url' value="
					+ tag + "><p>· " + from + " · </p></div>";
			e.className = "contentList";

			resultArea.appendChild(e);
			document.getElementById("loadAni").style.visibility = "hidden";
		}
	}

}
function addElementLi(obj) {
	var ul = document.getElementById(obj);
	var li = document.createElement("li");
	ul.appendChild(li);
}
