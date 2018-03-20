$(document).ready(function(){
    //初始化
    $("#login_modal").hide();
});


//用户登录
function login() {
    var username=$("#username").val();
    var password=$("#password").val();

    if(username == '' || password == ''){
        $("#login_info").html("用户名或者密码不能为空!");
        $("#login_modal").css("display","inline-block");
        return;
    }

    $.ajax({
        url : "../api/v1/login",
        dataType : "json",
        type : "POST",
        async : false,
        data : {username:username,password:md5(password)},
        error : function(error) {
            console.log(error.responseText);
        },
        success : function(e) {
            //登录成功
            if(e.code === 0){
                location.href = "../";
            }
            //登录失败
            if(e.code === -1){
                $("#login_info").html("用户名或者密码错误!");
                $("#login_modal").css("display","inline-block");
            }
        }
    });
}

//用户登出
function logout() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/api/v1/logout", false);
    xhr.send(null);
    self.location.replace("/");
}