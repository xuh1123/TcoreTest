function searchUser(page){	
//   alert("函数进来了");
	console.log(page);
   var info = document.getElementById("mysearch").value;
	if(!info==""){
	$.ajax({
		url:"searchUser/"+info,
//		type:"get",
		type:"post",
		data:{"info":info,"page":page},
	//	async:false,
	//	contentType:"application/json",
//		async:false,
		dataType:"json",
		success:function(data){
			console.log(data);
			var totalpages=data.totalpages;
			//	console.log(data);
				$(".tcdPageCode").empty();
				 $(".tcdPageCode").createPage({
				       pageCount:totalpages,
				        current:page,
				        backFn:function(p){
				        	//修改页码隐藏框中的数据
				        	$("#current").val(p);
				        	//更新表格的数据
				        	searchEmp(p); 
				        }
				    });
				$("#listdata").empty();

			$.each(data.list,function(i,obj) {
				var str = "";
				var roleid =0;
				if(obj.roleid==1){
					roleid = "超级管理员"
				}else {
					roleid = "普通用户"
				}
				str+="<tr id='userinfo' name='userinfo'><td>"
				+obj.id+"</td><td>"
				+obj.username+"</td><td>"
				+roleid+"</td><td> <a class='btn btn-sm btn-primary' href='toadduser/"+obj.id+"'>编辑</a>"
				+"<button  type='submit' class='btn btn-sm btn-danger deleteBtn' onclick='deleteuser("+obj.id+")'>删除</button>"
				+"</td><tr>";
				$("#listdata").append(str);	
				
				});			
			
		},
		error:function(){
			alert("系统繁忙!");
		}
	
		});
}else{
	alert("请输入关键字查询员工!");
	location.reload();
	return;
	}
}
 