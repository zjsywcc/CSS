$(document).ready(function(){
    appendGood(1, 'test1', 5, 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=edca6824a2773912db268361c8188675/9922720e0cf3d7ca600211eff91fbe096a63a9b6.jpg');
    appendGood(2, 'test2', 100, 'https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg');
});

function appendGood(good_id, good_title, good_cost, image_url) {
    var good = '<li>';
    good = good + '<a href="/item?id=' + good_id + '" class="link">';
    good = good + '<div class="img"><img src="' + image_url + '" alt="test"/></div>';
    good = good + '<h3>' + good_title + '</h3>';
    good = good + '<div class="price"><span class="v-unit">¥</span><span class="v-value">' + good_cost + '</span></div></a></li>';
    $("[id=plist]").append(good);
}