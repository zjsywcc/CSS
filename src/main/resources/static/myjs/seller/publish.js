$(document).ready(function(){
    //初始化
    $("#publish_modal").hide();
    $('#photoURI').on('input propertychange', function() {
        var value = $('#photoURI').val();
        if (value != '') {
            $("#imgpre").attr("src",value);
        }
    });
});



//发布商品
function publish() {
    var title = $("#title").val();
    var intro = $("#intro").val();
    var photo = $("#photoURI").val();
    var content = $("#content").val();
    var price = $("#price").val();

    if(title === '' ||
        intro === '' ||
        photoURI === ''||
        content === '' ||
        price === '' || !Number(price)){
        $("#publish_info").html("输入内容非法!");
        $("#publish_modal").css("display","inline-block");
        return;
    }

    $.ajax({
        url : "../api/v1/seller/publish",
        dataType : "json",
        type : "POST",
        async : false,
        data : {
                 title: title,
                 intro: intro,
                 photo: photo,
                 content: content,
                 price: price
        },
        error : function(error) {
            console.log(error.responseText);
        },
        success : function(e) {
            //发布成功
            if(e.code === 0){
                location.href = "../";
                console.log(e.msg);
            } else {
            }
        }
    });
}