var imageMode = "urlUpload";
var imageUrl;
$(document).ready(function () {
    //初始化
    $("#publish_modal").hide();
    $('#photoURI').on('input propertychange', function () {
        var value = $('#photoURI').val();
        if (value !== '') {
            $("#imgpre").attr("src", value);
        }
    });
    setUploadType();
});

function setImage(photoURI) {
    $("#photoURI").val(photoURI);
    $("#imgpre").attr("src", photoURI);
}

//发布商品
function publish() {
    var title = $("#title").val();
    var intro = $("#intro").val();
    var photo = $("#photoURI").val();
    var content = $("#content").val();
    var price = $("#price").val();

    if(checkValid()) {
        $.ajax({
            url: "../api/v1/seller/publish",
            dataType: "json",
            type: "POST",
            async: false,
            data: {
                title: title,
                intro: intro,
                photo: photo,
                content: content,
                price: price
            },
            error: function (error) {
                console.log(error.responseText);
            },
            success: function (e) {
                //发布成功
                if (e.code === 0) {
                    location.href = "../";
                    console.log(e.msg);
                } else {
                }
            }
        });
    } else {
        console.log("非法表单!");
    }

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
    if(file === undefined) {
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