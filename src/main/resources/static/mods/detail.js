
function collection(btn,postId) {
    if($(btn).hasClass("unCollection"))
    {
        // 收藏
        $.post(
            Context_Path + "/collection",
            {"postId": postId},
            function (data) {
                data = $.parseJSON(data);
                if (data.code == 0) {
                    // $("#collection").load(location.href + " #collection")
                    window.location.reload();
                    $(btn).text(data)
                } else {
                    alert(data.msg);
                }
            }
        );
    }else {
        //取消收藏
        $.post(
            Context_Path + "/collection",
            {"postId": postId},
            function (data) {
                data = $.parseJSON(data);
                if (data.code == 0) {
                    // $("#collection").load(location.href + " #collection")
                    window.location.reload();
                } else {
                    alert(data.msg);
                }
            }
        );
    }};




function like(btn,postId) {
    if($(btn).hasClass("unLike"))
    {
        // 点赞
        $.post(
            Context_Path + "/like",
            {"postId": postId},
            function (data) {
                data = $.parseJSON(data);
                if (data.code == 0) {
                    // $("#like").load(location.href + " #like")
                    window.location.reload();
                } else {
                    alert(data.msg);
                }
            }
        );
    }else {
        //取消收藏
        $.post(
            Context_Path + "/like",
            {"postId": postId},
            function (data) {
                data = $.parseJSON(data);
                if (data.code == 0) {
                    // $("#like").load(location.href + " #like")
                    window.location.reload();
                } else {
                    alert(data.msg);
                }
            }
        );
    }};
