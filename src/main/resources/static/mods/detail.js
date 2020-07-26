
function delete1(btn, id) {
    $.post(
        Context_Path + "/comment/delete/" + id,
        {"id" : id},
        function (data) {
            data = $.parseJSON(data);
            if (data.code == 0){
                swal("删除成功","删除成功","success",
                    {
                        buttons: false,
                        timer: 3000,
                    });
                window.location.reload();
            } else{
                swal("删除失败",
                    {buttons: false,
                        timer: 3000,});
            }
        }
    )};

function submit1() {
    if(1){
        swal("评论成功","评论成功","success",{
            buttons: false,
                timer: 4000,
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
                    swal("点赞成功","点赞成功","success",{
                        buttons: false,
                        timer: 3000,
                    });
                } else {
                    // alert(data.msg);
                    swal("操作失败","操作失败",{
                        buttons: false,
                        timer: 3000,
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
                    swal("点赞已取消","点赞已取消","success",{
                        buttons: false,
                        timer: 3000,
                    });
                } else {
                    // alert(data.msg);
                    swal("操作失败","操作失败",{
                        buttons: false,
                        timer: 3000,
                    });
                }
            }
        );
    }};

