function read(btn,id) {
    $.post(
        Context_Path + "/message/delete/" + id,
        {"id" : id},
        function (data) {
            data = $.parseJSON(data);
            if (data.code == 0){
                window.location.reload();
                swal("操作成功","operation success！","success",{
                    timer: 5000,
                    buttons: {
                        confirm: true
                    }
                });

            } else{
                swal("操作失败","operation failure~","error",
                    {buttons: false,
                        timer: 3000});
            }
        }
    )};


function readAll1(btn,toId) {
    $.post(
        Context_Path + "/message/deleteAll/" + toId,
        {"toId": toId},
        function (data) {
            data = $.parseJSON(data);
            if (data.code == 0){
                window.location.reload();
                swal("操作成功","operation success！","success",{
                    timer: 5000,
                    buttons: {
                        confirm: true
                    }
                });

            } else{
                swal("操作失败","operation failure~","error",
                    {buttons: false,
                        timer: 3000});
            }
        }
    )
}