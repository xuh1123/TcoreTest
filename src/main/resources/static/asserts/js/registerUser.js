//四个表单项是否都通过验证
var array={"username":false,"password":false,"roleid":false};

//用户姓名验证
$(function() {
	$("#username").blur(function() {
		array.username=false;
		var username=$("#username").val();
		//非空检测
		if(username==""){
			$("#p_username").html("*请输入用户姓名!");
			return;
		}
			$("#p_username").html("");
			array.username = true;
	})
	
});
//用户密码验证
$(function() {
	$("#password").blur(function(){
		array.password=false;
		var password=$("#password").val();
		//非空检测
		if(password==""){
			$("#p_password").html("*用户密码不能为空!");
			return;
		}
		$("#p_password").html("");
		array.password = true;
			
	})
});

//权限验证
$(function() {
	array.roleid = false;
	$("#man").click(function() {
		if($("#man").get(0).checked){
			array.roleid = true;
			return;
		}
	});
	$("#female").click(function() {
		if($("#roleid").get(0).checked){
			array.roleid = true;
			return;
		}
	});
	
});
//表单提交处理
function puton(){
	var username=$("#username").val();
	var password=$("#password").val();
//	var roleid=$("#roleid").val();
	//检查表单是否都通过了验证
	if (array.username&&array.password&&array.roleid) {
		document.forms[0].submit();
	}else if (username!=""&&password!="") {
		document.forms[0].submit();
	} else {
		alert("表单信息未填写完毕，请检查！");
		return false;
	}
}