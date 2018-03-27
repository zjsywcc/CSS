$(document).ready(function(){
    //初始化
    $("#edit_modal").hide();
    $('#goodPhoto').on('input propertychange', function() {
        var value = $('#goodPhoto').val();
        if (value !== '') {
            $("#imgpre").attr("src",value);
        }
    });
    itemDetail();
});

function itemDetail() {
    console.log("getItemDetail");
    var goodId = getUrlParam("id");
    $.ajax({
        url: '../api/v1/good/details',
        dataType : "json",
        type : "POST",
        async : false,
        data: {goodId: goodId},
        error : function(error) {
            console.log(error.responseText);
        },
        success : function(e) {
            if(e.code === 0) {
                var detail = JSON.parse(e.data);
                setDetail(detail);
            }
            if(e.code === -1) {

            }
        }});
}

function setDetail(goodDetails) {
    var goodPhoto = $("#goodPhoto");
    var goodTitle = $("#goodTitle");
    var goodIntro = $("#goodIntro");
    var goodPrice = $("#goodPrice");
    var goodContent = $("#goodContent");

    goodPhoto.val(goodDetails.photo);
    $("#imgpre").attr("src",goodDetails.photo);
    goodTitle.val(goodDetails.title);
    goodIntro.val(goodDetails.intro);
    goodPrice.val(goodDetails.cost);
    goodContent.val(goodDetails.content);
}

//编辑商品
function editGood() {
    var id = getUrlParam("id");
    var title = $("#goodTitle").val();
    var intro = $("#goodIntro").val();
    var photo = $("#goodPhoto").val();
    var content = $("#goodContent").val();
    var price = $("#goodPrice").val();

    if(title === '' ||
        intro === '' ||
        photo === ''||
        content === '' ||
        price === '' || !Number(price)){
        $("#edit_info").html("输入内容非法!");
        $("#edit_modal").css("display","inline-block");
        return;
    }

    $.ajax({
        url : "../api/v1/seller/edit/good",
        dataType : "json",
        type : "POST",
        async : false,
        data : {
            goodId: id,
            goodTitle: title,
            goodIntro: intro,
            goodPhoto: photo,
            goodContent: content,
            goodCost: price
        },
        error : function(error) {
            console.log(error.responseText);
        },
        success : function(e) {
            //发布成功
            if(e.code === 0){
                console.log(e.msg);
                location.href = "../item?id=" + id;
            } else {
            }
        }
    });
}

//获取url中的参数
function getUrlParam(name) {
    //构造一个含有目标参数的正则表达式对象
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    //匹配目标参数
    var r = window.location.search.substr(1).match(reg);
//返回参数值
    if (r != null) return unescape(r[2]); return null;
}