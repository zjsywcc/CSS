$(document).ready(function(){
    getGoods(0, 100);
});

function appendGood(good_id, good_title, good_cost, image_url) {
    var good = '<li>';
    good = good + '<a href="/item?id=' + good_id + '" class="link">';
    good = good + '<div class="img"><img src="' + image_url + '" alt="test"/></div>';
    good = good + '<h3>' + good_title + '</h3>';
    good = good + '<div class="price"><span class="v-unit">¥</span><span class="v-value">' + good_cost + '</span></div></a></li>';
    $("[id=plist]").append(good);
}

function getGoods(index, pageSize) {
    $.ajax({
        url : "../api/v1/goods",
        dataType : "json",
        type : "POST",
        async : false,
        data : {index: index,
                pageSize: pageSize},
        error : function(error) {
            console.log(error.responseText);
        },
        success : function(e) {
            //
            if(e.code === 0) {
                var itemList = JSON.parse(e.data);
                console.log(itemList);
                for(var index in itemList) {
                    var item = itemList[index];
                    appendGood(item.id, item.title, item.cost, item.photo);
                }
            }
            //
            if(e.code === -1){

            }
        }
    });
}