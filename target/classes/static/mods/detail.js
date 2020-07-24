
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
