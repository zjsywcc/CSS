var imageMode = "urlUpload";
var imageUrl;
$(document).ready(function () {
    //初始化
    $("#edit_modal").hide();
    $('#goodPhoto').on('input propertychange', function () {
        var value = $('#goodPhoto').val();
        if (value !== '') {
            $("#imgpre").attr("src", value);
        }
    });
    itemDetail();
    setUploadType();
});

function itemDetail() {
    console.log("getItemDetail");
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
                var detail = JSON.parse(e.data);
                setDetail(detail);
            }
            if (e.code === -1) {

            }
        }
    });
}

function setDetail(goodDetails) {
    var goodPhoto = $("#photoURI");
    var goodTitle = $("#title");
    var goodIntro = $("#intro");
    var goodPrice = $("#price");
    var goodContent = $("#content");

    goodPhoto.val(goodDetails.photo);
    $("#imgpre").attr("src", goodDetails.photo);
    goodTitle.val(goodDetails.title);
    goodIntro.val(goodDetails.intro);
    goodPrice.val(goodDetails.cost);
    goodContent.val(goodDetails.content);
}

//编辑商品
function editGood() {
    var id = getUrlParam("id");
    var title = $("#title").val();
    var intro = $("#intro").val();
    var photo = $("#photoURI").val();
    var content = $("#content").val();
    var price = $("#price").val();

    if (checkValid()) {
        $.ajax({
            url: "../api/v1/seller/edit/good",
            dataType: "json",
            type: "POST",
            async: false,
            data: {
                goodId: id,
                goodTitle: title,
                goodIntro: intro,
                goodPhoto: photo,
                goodContent: content,
                goodCost: price
            },
            error: function (error) {
                console.log(error.responseText);
            },
            success: function (e) {
                //发布成功
                if (e.code === 0) {
                    console.log(e.msg);
                    location.href = "../item?id=" + id;
                } else {
                }
            }
        });
    } else {
        console.log("非法表单!");
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

function setImage(photoURI) {
    $("#photoURI").val(photoURI);
    $("#imgpre").attr("src", photoURI);
}

function setUploadType() {
    var image = $("#photoURI");
    var uploadInput = $("[name=file]");
    $("#uploadType").click(function (e) {
        e = window.event || e;
        o = e.srcElement || e.target;
        if (o.nodeName === "INPUT") {
            var s, h;
            o.value === 'url' ? (s = 'urlUpload', h = 'fileUpload') : (s = 'fileUpload', h =
                'urlUpload');
            imageMode = o.value === 'url' ? "urlUpload" : "fileUpload";
            // image.classList.remove("z-err");
            uploadInput.removeAttr("class", "z-err");
            $("#" + s).removeAttr("style", "none");
            $("#" + h).css("display", "none");
        }
    });
}

function uploadPhoto() {
    var uploadInput = $("#fileUp");
    var maxAllowedSize = 1000000;
    var file = uploadInput.prop('files')[0];
    if (file === undefined) {
        alert("请先选择文件!");
        return;
    }
    var sub = file.name.substring(file.name.lastIndexOf('.') + 1);
    if (sub != 'jpg' && sub != 'png' && sub != 'bmp') {
        alert("请上传jpg、png、bmp结尾的图片");
        return;
    }
    if (file.size > maxAllowedSize) {
        alert("超过文件上传大小限制");
    } else {
        var formData = new FormData();
        formData.append('file', file, file.name);
        formData.enctype = "multipart/form-data";

        var xhr = new XMLHttpRequest();
        xhr.open("post", "../api/v1/seller/upload", true);
        xhr.onload = function () {
            if (xhr.status === 200) {
                var o = JSON.parse(xhr.responseText);
                if (o.code === 0) {
                    $("#urlUpload").removeAttr("style", "none");
                    $("#fileUpload").css("display", "none");
                    imageUrl = o.data;
                    setImage(imageUrl);
                    alert(o.msg);
                } else {
                    alert(o.msg);
                }
            } else {
                alert('文件上传失败');
            }
        };
        xhr.send(formData);
    }
}

function checkValid() {
    var form = util.get('form');
    var title = form['title'];
    var intro = form['intro'];
    var image = form['photoURI'];
    var content = form['content'];
    var price = form['price'];
    var uploadInput = form['file'];

    var result = true;
    [
        [title, function (value) {
            return value.length < 2 || value.length > 80
        }],
        [intro, function (value) {
            return value.length < 2 || value.length > 140
        }],
        [image, function (value) {
            return imageMode == "urlUpload" && value == '';
        }],
        [content, function (value) {
            return value.length < 2 || value.length > 1000
        }],
        [price, function (value) {
            return value == '' || !Number(value)
        }]
    ].forEach(function (item) {
        var value = item[0].value.trim();
        if (item[1](value)) {
            item[0].classList.add('z-err');
            result = false;
        }
        item[0].value = value;
    });
    if (imageMode == "fileUpload" && !imageUrl) {
        uploadInput.classList.add('z-err');
        result = false;
    }
    return result;
}