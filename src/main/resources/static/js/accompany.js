'use strict';

let accompanyIndex = {
    init: function () {
        $("#accompany-btn-save").on("click", () => {
            this.accompanySave();
        });
    },


    accompanySave: function () {
            let boardId = $("#boardId").val();
            console.log(boardId);
            $.ajax({
                type: "POST",
                url: `/board/{boardId}/accompany`,
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            }).done(function (res) {
                alert("동행신청이 완료되었습니다..");
                location.href = `/`;
            }).fail(function (err) {
                alert(JSON.stringify(err));
            });
        },

}
accompanyIndex.init();