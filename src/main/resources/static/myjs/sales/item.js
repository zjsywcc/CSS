$(document).ready(function () {
    setPlusMinus();
    itemDetail();
});

function setPlusMinus() {
    $("#minusNum").click(function (e) {
        e = window.event || e;
        o = e.srcElement || e.target;
        var num = $("#allNum").text();
        if (num > 0) {
            num--;
            $("#allNum").html(num);
        } else {
            alert("您没有购买任何商品");
        }
    });
    $("#plusNum").click(function (e) {
        e = window.event || e;
        o = e.srcElement || e.target;
        var num = $("#allNum").text();
        num++;
        $("#allNum").html(num);
    });
}

function itemDetail() {
    var goodId = getUrlParam("id");
    $.ajax({
        url: '../api/v1/good/details',
        dataType: "json",
        type: "POST",
        async: false,
        data: {goodId: goodId},
        error: function (error) {
            console.log(error.responseText);
        },
        success: function (e) {
            if (e.code === 0) {
                console.log(e.msg);
                var detail = JSON.parse(e.data);
                setDetail(detail);
            } else {

            }
        }
    });


}

function addToCart() {
    var goodId = getUrlParam("id");
    var goodNum = $("#allNum").text();

    // layer.reset({
    //     content: '确认加入购物车吗？',
    //     onconfirm: function () {
    //         layer.hide();
    //         loading.show();
    $.ajax({
        url: '../api/v1/buyer/addToCart',
        dataType: "json",
        type: "POST",
        async: false,
        data: {
            goodId: goodId,
            goodNum: goodNum
        },
        error: function (error) {
            console.log(error.responseText);
        },
        success: function (e) {
            if (e.code === 0) {
                // loading.result('添加到购物车成功', function () {
                console.log(e.msg);
                location.href = '../buyer/cart';
                // });
            } else {
                // loading.result(e.msg || '添加到购物车失败');
            }
        }
    });
    //     }
    // });
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
    if (soldNum > 0) {
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
    if (r != null) return unescape(r[2]);
    return null;
}
