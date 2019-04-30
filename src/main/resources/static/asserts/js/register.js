//四个表单项是否都通过验证
var array={"email":false,"empName":false,"deptNo":false,/*"birth":false,*/"gender":false};
//邮箱验证
$(function() {
	$("#email").blur(function(){
		array.email=false;
		var email=$("#email").val();
		//非空检测
		if(email==""){
			$("#p_email").html("*员工邮箱不能为空!");
			return;
		}
		//格式检测
	var regexp=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;	
	if(!regexp.test(email)){
		$("#p_email").html("邮箱格式不正确");
		return;
	}
	$("#p_email").html("");
	array.email = true;
	});
});
//员工姓名验证
$(function() {
	$("#empName").blur(function() {
		array.empName=false;
		var empName=$("#empName").val();
		//非空检测
		if(empName==""){
			$("#p_empName").html("*请输入员工姓名!");
			return;
		}
			$("#p_empName").html("");
			array.empName = true;
	})
	
});
//部门编号验证
$(function() {
	$("#deptNo").blur(function(){
		array.deptNo=false;
		var deptNo=$("#deptNo").val();
		//非空检测
		if(deptNo==""){
			$("#p_deptNo").html("*部门编号不能为空!");
			return;
		}
		$("#p_deptNo").html("");
		array.deptNo = true;
			
	})
});

//生日验证
/*$(function() {
	$("#birth").click(function(){
		array.birth=false;
		var birth=$("#birth").val();
		//非空检测
		if(birth==""){
			$("#p_birth").html("*员工生日不能为空!");
			return;
		}
	$("#p_birth").html("");
	array.birth = true;
	});
});*/

//性别验证
$(function() {
	array.gender = false;
	$("#man").click(function() {
		if($("#man").get(0).checked){
			array.gender = true;
	//		alert("男");
			return;
		}
	});
	$("#female").click(function() {
		if($("#female").get(0).checked){
			array.gender = true;
	//		alert("女");
			return;
		}
	});
	
});
//表单提交处理
function puton(){
	var email=$("#email").val();
	var empName=$("#empName").val();
	var deptNo=$("#deptNo").val();
	var birth=$("#birth").val();
	//检查表单是否都通过了验证
	if (array.email&&array.empName&&array.deptNo&&array.gender&&birth!="") {
		document.forms[0].submit();
	}else if (email!=""&&empName!=""&&deptNo!=""&&birth!="") {
		document.forms[0].submit();
	} else {
		alert("表单信息未填写完毕，请检查！");
		return false;
	}
}