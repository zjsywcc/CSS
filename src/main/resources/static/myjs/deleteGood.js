(function (w, d, u) {
    var plist = util.get('plist');
    if (!plist) {
        return;
    }
    var layer = new Layer();
    var loading = new Loading();
    var page = {
        init: function () {
            plist.addEventListener('click', function (e) {
                var ele = e.target;
                var delId = ele.dataset && ele.dataset.del;
                if (delId) {
                    this.ondel(delId);
                    return;
                }
            }.bind(this), false);
        },
        ondel: function (id) {
            layer.reset({
                            content: '确定要删除该内容吗？',
                            onconfirm: function () {
                                layer.hide();
                                loading.show();
                                $.ajax({
                                        url: '../api/v1/seller/delete/good',
                                        dataType : "json",
                                        type : "POST",
                                        async : false,
                                        data: {goodId: id},
                                        error : function(error) {
                                            console.log(error.responseText);
                                        },
                                        success : function(e) {
                                            if(e.code === 0) {
                                                loading.result('删除成功');
                                                location.href = "../";
                                            }
                                            if(e.code === -1) {

                                            }
                                        }});
                            }.bind(this)
                        }).show();
        },
        delItemNode: function (id) {
            var item = util.get('p-' + id);
            if (item && item.parentNode) {
                item.parentNode.removeChild(item);
            }
        }
    };
    page.init();
})(window, document);