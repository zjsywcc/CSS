$(document).ready(function(){
    itemDetail();
});

function itemDetail() {
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
    var goodImage = $("#goodImage");
    var goodTitle = $("#goodTitle");
    var goodIntro = $("#goodIntro");
    var goodPrice = $("#goodPrice");
    var goodContent = $("#goodContent");

    goodImage.attr("src", goodDetails.photo);
    goodTitle.html(goodDetails.title);
    goodIntro.html(goodDetails.intro);
    goodPrice.html(goodDetails.cost);
    goodContent.html(goodDetails.content);

    var editGood = $("#editGood");
    var goodId = getUrlParam("id");
    editGood.attr("href", "/seller/edit?id=" + goodId);

    var soldNum = goodDetails.soldNum;
    var addToCart = $("#addToCart");
    var bought = $("#bought");
    var boughtPrice = $("#boughtPrice");
    if(soldNum > 0) {
        addToCart.hide();
        bought.show();
        boughtPrice.show();
    } else {
        addToCart.show();
        bought.hide();
        boughtPrice.hide();
    }

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
