var cartList;
$(document).ready(function () {
    listCart();
    $("#buy").click(function (e) {
        buy(cartList);
    })
});

function setPlusMinus(cartList) {
    $("#newTable").click(function (e) {
        var e = arguments[0] || window.event;
        target = e.srcElement ? e.srcElement : e.target;
        if (target.nodeName == "SPAN" && target.className == "moreNum") {
            var num = target.parentElement.children[1].textContent;
            var id = target.parentElement.children[2].textContent;
            num++;
            target.parentElement.children[1].textContent = num;
            cartList[id].goodNum = num;
        } else if (target.nodeName == "SPAN" && target.className == "lessNum") {
            var num = target.parentElement.children[1].textContent;
            var id = target.parentElement.children[2].textContent;
            num--;
            if (num < 0) {
                alert("该商品数量为0");
            } else {
                target.parentElement.children[1].textContent = num;
                cartList[id].goodNum = num;
            }
        }
        return false;
    });
}

function appendCart(cartList) {
    var cartContent = "<tr>" +
        "<th>" + '内容名称' + "</th>" +
        "<th>" + '数量' + "</th>" +
        "<th>" + '价格' + "</th>" +
        "</tr>";
    for (var i = 0; i < cartList.length; i++) {
        cartContent +=
            "<tr>" +
            "<td>" + cartList[i].goodTitle + "</td>" +
            "<td>" +
            "<span class=\"lessNum\">" + "-" + "</span>" +
            "<span class=\"totalNum\" id=\"allNum\">" + cartList[i].goodNum + "</span>" +
            "<span id=\"thisId\">" + i + "</span>" +
            "<span class=\"moreNum\">" + "+" + "</span>" + "</td>" +
            "<td>" + cartList[i].goodCost + "</td>" +
            "</tr>";
    }
    $("#newTable").append(cartContent);
}

function listCart() {
    $.ajax({
        url: '../api/v1/buyer/listCart',
        dataType: "json",
        type: "POST",
        async: false,
        data: {},
        error: function (error) {
            console.log(error.responseText);
        },
        success: function (e) {
            if (e.code === 0) {
                console.log(e.msg);
                cartList = JSON.parse(e.data);
                appendCart(cartList);
                setPlusMinus(cartList);
            } else {
                console.log(e.msg);
            }
        }
    });
}

function buy(cartList) {
    var loading = new Loading();
    var layer = new Layer();
    // layer.reset({
    //     content: '确认购买吗？',
    //     onconfirm: function () {
    //         layer.hide();
    //         loading.show();
    $.ajax({
        url: '../api/v1/buyer/buy',
        dataType: "json",
        type: "POST",
        async: false,
        data: {
            cartList: JSON.stringify(cartList)
        },
        error: function (error) {
            console.log(error.responseText);
        },
        success: function (e) {
            if (e.code === 0) {
                console.log(e.msg);
                // loading.result('购买成功',function(){
                location.href = '../buyer/purchases';
                // });
            } else {
                console.log(e.msg);
                // loading.result(e.msg || '购买失败');
            }
        }
    });
    //     }
    // });

}

function back() {
    window.history.back();
}