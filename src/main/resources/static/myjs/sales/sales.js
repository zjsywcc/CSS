var userType;
$(document).ready(function () {
    userType = getUserType();
    console.log("userType: ", userType);
    getGoods(0, 100);

});

function appendGood(good_id, good_title, good_cost, image_url, sold_num) {
    var good = '<li>';
    good += '<a href="/item?id=' + good_id + '" class="link">';
    good += '<div class="img"><img src="' + image_url + '" alt="test"/></div>';
    good += '<h3>' + good_title + '</h3>';
    good += '<div class="price"><span class="v-unit">¥</span><span class="v-value">' + good_cost + '</span></div></a>';
    if (userType === 1 && sold_num > 0) {
        good += '<span class="had"><b>已购买</b></span>';
    }
    if (userType === 0 && sold_num === 0) {
        good += '<span class="u-btn u-btn-normal u-btn-xs del" data-del="' + good_id + '">删除</span>';
    }
    if (userType === 0 && sold_num > 0) {
        good += '<span class="had"><b>已售出' + sold_num + '件</b></span>';
    }
    good += '</li>';
    $("[id=plist]").append(good);
}

function getGoods(index, pageSize) {
    $.ajax({
        url: "../api/v1/goods",
        dataType: "json",
        type: "POST",
        async: false,
        data: {
            index: index,
            pageSize: pageSize
        },
        error: function (error) {
            console.log(error.responseText);
        },
        success: function (e) {
            //
            if (e.code === 0) {
                var itemList = JSON.parse(e.data);
                console.log(itemList);
                for (var index in itemList) {
                    var item = itemList[index];
                    appendGood(item.id, item.title, item.cost, item.photo, item.soldNum);
                }
            }
            //
            if (e.code === -1) {

            }
        }
    });
}


function getUserType() {
    var type = -1;
    $.ajax({
        url: "../api/v1/user",
        dataType: "json",
        type: "POST",
        async: false,
        data: {},
        error: function (error) {
            console.log(error.responseText);
        },
        success: function (e) {
            //
            if (e.code === 0) {
                var currentUser = JSON.parse(e.data);
                console.log(currentUser);
                // guest
                if (currentUser.id === -1) {
                    type = -1;
                } else if (currentUser.type === true) { // 买家
                    type = 1;
                } else {
                    type = 0;  // 卖家
                }
            }
            //
            if (e.code === -1) {
                type = -1;
            }
        }
    });
    return type;
}
