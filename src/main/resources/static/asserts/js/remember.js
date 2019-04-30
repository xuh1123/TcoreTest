$(function() {
	getCookie();
})
function setCookie(){ //设置cookie  
         var loginCode = $("#username").val(); //获取用户名信息  
         var pwd = $("#password").val(); //获取登陆密码信息  
         var checked = $("[name='rememberMe']:checked");//获取“是否记住密码”复选框

         if(checked){ //判断是否选中了“记住密码”复选框  
            $.cookie("username",loginCode, { expires: 7, path: '/' });//调用jquery.cookie.js中的方法设置cookie中的用户名  
            $.cookie("password",pwd, { expires: 7, path: '/' });//调用jquery.cookie.js中的方法设置cookie中的登陆密码，并使用base64（jquery.base64.js）进行加密  
            console.log(pwd);
         }else{   
            $.cookie("password", null);   
         }    
    }   
    function getCookie(){ //获取cookie  
         var loginCode = $.cookie("username"); //获取cookie中的用户名  
         var pwd =  $.cookie("password"); //获取cookie中的登陆密码  
         if(pwd!=""&&loginCode!=null){//密码存在的话把“记住用户名和密码”复选框勾选住  
            $("#username").val(loginCode);  
            $("#password").val(pwd); 
            $("[name='rememberMe']").attr("checked","true");  
         }  
        
    }   
function login(){   
    var userName = $('#username').value;  
    if(userName == ''){  
  //      alert("请输入用户名。");  
        return;  
    }  
    var userPass = $('#password').value; 
    if(userPass == ''){  
   //     alert("请输入密码。");  
        return;  
    }  
    //判断是否选中复选框，如果选中，添加cookie
    if($("[name='rememberMe']").attr("checked","true")){  
        //添加cookie  
        setCookie();  
  //      alert("记住密码登录。");  
        window.location = "/user/login";  
    }else{  
   //     alert("不记密码登录。");  
        window.location = "/user/login";  
    }  
}  