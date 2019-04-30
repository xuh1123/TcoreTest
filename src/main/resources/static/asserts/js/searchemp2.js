function searchEmp(page){	
//   alert("函数进来了");
	console.log(page);
   var info = document.getElementById("mysearch").value;
	if(!info==""){
	$.ajax({
		url:"searchEmpTest/"+info,
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

 