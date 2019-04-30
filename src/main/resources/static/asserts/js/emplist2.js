$(function() {
	// 页面加载完立即向服务器发请求，加载用户信息，默认第一页
	loadadmins(1);
});

loadadmins = function(page) {
	$.ajax({
				url : "totest/"+page,
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
										var gender =0;
										//转换日期格式
										var birth = new Date(obj.birth).toLocaleDateString();
										
										if(obj.gender==0){
											gender = "男"
										}else {
											gender = "女"
										}
										str+="<tr id='empinfo' name='empinfo'><td>"
										+obj.id+"</td><td>"
										+obj.lastName+"</td><td>"
										+obj.email+"</td><td>"
										+gender+"</td><td>"
										+obj.did+"</td><td>"
										+birth+"</td><td> <a class='btn btn-sm btn-primary' href='toaddemp/"+obj.id+"'>编辑</a>"
										+"<button  type='submit' class='btn btn-sm btn-danger deleteBtn' onclick='deleteemp("+obj.id+")'>删除</button>"
										+"</td><tr>";
										$("#listdata").append(str);	
									});
				},
				"error" : function() {
					alert("系统繁忙！");
				}
			});
}