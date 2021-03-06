$(function() {
	var systemInfo = {
		showMenu : function() {
			leftColumShow(6);
			var coluId = 10;
			if (getRequest("secondColuId") > 0) {
				coluId = getRequest("secondColuId");
			}
			var parentColuId = getRequest("firstColuId");
			$("#topMenu").find('li').each(function() {
				$(this).removeClass();
			});
			$("#coluId" + coluId + "").attr("class", "active"); // 添加菜单选中样式
			$("#firstColuId" + parentColuId + "").attr("class", "active"); // 添加菜单选中样式
		},
		showserverinfo : function() {
			$.post("../../handler/system/viewServerInfo", function(data) {
				if (data.ret == 1) {
					var msg = data.data.data;
					$("#osname").append(msg.osname + " V" + msg.osversion + " " + msg.ostype);
					$("#jdkpath").append(msg.jdkpath + " V" + msg.jdkversion);
					$("#userhome").append(msg.userhome);
					$("#appdir").append(msg.appdir);
					$("#servipaddr").append(msg.servipaddr);
					$("#freememory").append(msg.freememory + " MB");
					$("#totalmemory").append(msg.totalmemory + " MB");
					$("#maxmemory").append(msg.maxmemory + " MB");
				}
			}, "json");

		},
		/**
		 * 显示服务器状态
		 */
		showserverstatus : function() {
			var serstatus_title = "<strong>60秒钟自动刷新服务器状态信息：</strong><br />";
			$("#servstatus").empty().append(serstatus_title);
			$.post("../../handler/system/viewServerStatus", function(data) {
				if (data.ret == 1) {
					var msg = data.data.data;
					$("#servstatus").append(msg.servstatus);
				}
			}, "json");
		},
		init : function() {
			// 初始化showMenu
			this.showMenu();
			// 显示服务器状态
			this.showserverinfo();
			this.showserverstatus();
		}
	};
	systemInfo.init();
	// 60秒自动刷新
	setInterval(function() {
		systemInfo.showserverstatus();
	}, 60000);// 通过闭包得到当前作用域，好访问保存好的对象that
});