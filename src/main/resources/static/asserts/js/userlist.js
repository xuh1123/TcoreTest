$(function() {
	// 页面加载完立即向服务器发请求，加载用户信息，默认第一页
	loadadmins(1);
});

loadadmins = function(page) {
	$.ajax({
				url : "userlist/"+page,
//				type : "post",
				type : "get",
//				data:{"page":page},
				dataType : "json",
		//		contentType: "application/json",
				success : function(data) {
	//				alert("走了成功的回调");
					var totalpages=data.totalpages;
				//	console.log(data);
					 $(".tcdPageCode").createPage({
					        pageCount:totalpages,
					        current:page,
					        backFn:function(p){
					        	//修改页码隐藏框中的数据
					        	$("#current").val(p);
					        	//更新表格的数据
					            loadadmins(p);
					        }
					    });
					 //将表头复选框的状态切换为未勾选
					 
					 //循环前清空数据，但是表头要留着，所以在自己添加的tr中加上class
					 //对其进行清空操作
					 $("#listdata").empty();
					// 遍历
					$.each(
									data.list,
									function(index, obj) {
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
				"error" : function() {
					alert("系统繁忙！");
				}
			});
}