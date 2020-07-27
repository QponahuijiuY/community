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
                    swal("收藏成功，收藏消息通知会发送给对方","Collection of success","success",{
                        timer: 5000,
                        buttons: {
                            confirm: true
                        }
                    });
                } else {
                    swal("操作失败","operation failure~","error",{
                        buttons: false,
                        timer: 3000
                    });
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
                    swal("收藏已经取消","Collection to cancel","success",{
                        timer: 5000,
                        buttons: {
                            confirm: true
                        }
                    });
                } else {
                    swal("操作失败","operation failure~","error",{
                        buttons: false,
                        timer: 3000
                    });
                }
            }
        );
    }};
function like(btn,postId) {
    if($(btn).hasClass("unLike"))
    {
        // 点赞
        $.post(
            Context_Path + "/likePost",
            {"postId": postId},
            function (data) {
                data = $.parseJSON(data);
                if (data.code == 0) {
                    // $("#like").load(location.href + " #like")
                    window.location.reload();
                    swal("点赞成功，点赞成功消息会发送给对方","Thumb up success！","success",{
                        timer: 5000,
                        buttons: {
                            confirm: true
                        }
                    });
                } else {
                    // alert(data.msg);
                    swal("操作失败","operation failure~","error",{
                        buttons: false,
                        timer: 3000
                    });
                }
            }
        );
    }else {
        //取消收藏
        $.post(
            Context_Path + "/likePost",
            {"postId": postId},
            function (data) {
                data = $.parseJSON(data);
                if (data.code == 0) {
                    // $("#like").load(location.href + " #like")
                    window.location.reload();
                    swal("点赞已取消","Thumb up to cancel！","success",{
                        timer: 5000,
                        buttons: {
                            confirm: true
                        }
                    });
                } else {
                    // alert(data.msg);
                    swal("操作失败","operation failure~","error",{
                        buttons: false,
                        timer: 3000
                    });
                }
            }
        );
    }};
function delete1(btn, id) {
    $.post(
        Context_Path + "/comment/delete/" + id,
        {"id" : id},
        function (data) {
            data = $.parseJSON(data);
            if (data.code == 0){
                window.location.reload();
                swal("删除成功","Delete success! ","success",{
                    timer: 5000,
                    buttons: {
                        confirm: true
                    }
                });
            } else{
                swal("操作失败","operation failure~","error",{
                    buttons: false,
                    timer: 3000});
            }
        }
    )};
function submit1() {
    if(1){
        swal("评论成功,评论消息通知会发送给对方","Comment success!","success",{
            timer: 5000,
            buttons: {
                confirm: true,
            }
        });
    }
}
function likeComment(btn,id) {
    if($(btn).hasClass("darkgray"))
    {
        $.post(
            Context_Path + "/likeComment",
            {"commentId": id},
            function (data) {
                data = $.parseJSON(data);
                if (data.code == 0) {

                    window.location.reload();
                    swal("点赞成功,点赞消息通知会发送给对方","Thumb up success！","success",{
                        timer: 3000,

                    });
                } else {
                    // alert(data.msg);
                    swal("操作失败,请确认是否已经登陆，或联系网站管理员","operation failure~","error",{
                        buttons: false,
                        timer: 3000
                    });
                }
            }
        );
    }else {
        //取消收藏
        $.post(
            Context_Path + "/likeComment",
            {"commentId": id},
            function (data) {
                data = $.parseJSON(data);
                if (data.code == 0) {
                    // $("#like").load(location.href + " #like")
                    window.location.reload();
                    swal("点赞已取消","Thumb up to cancel！","success",{
                        timer: 3000,

                    });
                } else {
                    // alert(data.msg);
                    swal("操作失败","operation failure~",{
                        buttons: false,
                        timer: 3000,
                    });
                }
            }
        );
    }};

function deve() {
    if (1){
        swal("Sorry! 该功能正在努力开发中~","being developed!","error",{
            timer: 5000,
            buttons: {
                confirm: true,
            }
        });
    }
}
function deveqq() {
    if (1){
        swal("Sorry! QQ登陆功能正在努力开发中~","being developed!","error",{
            timer: 5000,
            buttons: {
                confirm: true,
            }
        });
    }
}
function devewb() {
    if (1){
        swal("Sorry! 微博登陆功能正在努力开发中","being developed!","error",{
            timer: 5000,
            buttons: {
                confirm: true,
            }
        });
    }
}

