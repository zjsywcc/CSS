$(document).ready(function(){
    getPurchases();
});

function appendPurchaseHistory(purchases) {
    var costSum = 0;
    var history = "<tbody>";
    for(var index in purchases) {
        var purchase = purchases[index];
        history += '<tr>';
        history += '<td><a href="/item?id=' + purchase.goodId + '"><img src="' + purchase.goodPhoto+ '" alt=""/></a></td>';
        history += '<td><h4><a href="/item?id=' + purchase.goodId + '">' + purchase.goodTitle + '</a></h4></td>';
        history += '<td><span class="v-time">' + dateTimeFormatter(purchase.purchaseTime) + '</span></td>';
        history += '<td><span class="v-num">' + purchase.goodNum + '</span></td>';
        history += '<td><span class="v-unit">¥</span><span class="value">' + purchase.goodCost + '</span></td>';
        history += '</tr>';
        costSum += purchase.goodCost * purchase.goodNum;
    }
    history += '<tfoot><tr><td colspan="4"><div class="total">总计：</div></td><td><span class="v-unit">¥</span><span class="value">' + costSum + '</span></td></tr></tfoot>';
    $("#purchaseHistory").append(history);
}

function getPurchases() {
    $.ajax({
        url: '../api/v1/buyer/purchases',
        dataType : "json",
        type : "POST",
        async : false,
        data: {},
        error : function(error) {
            console.log(error.responseText);
        },
        success : function(e) {
            if(e.code === 0) {
                var purchases = JSON.parse(e.data);
                appendPurchaseHistory(purchases);
            }
            if(e.code === -1) {

            }
        }});
}

function dateTimeFormatter(timestamp){
    var date = new Date(timestamp * 1000);//如果date为13位不需要乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + ' ';
    var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
    var m = (date.getMinutes() <10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
    var s = (date.getSeconds() <10 ? '0' + date.getSeconds() : date.getSeconds());
    return Y+M+D+h+m+s;
}
