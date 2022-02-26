(function () {
  'use strict'

  // 点击表格行弹出模态框显示日记内容
  $(".journal-record").click(function() {
    // 获取日记内容
    var content = $(this).children(".journal-content").val();

    // 显示日记内容
    $("#modalDialog .modal-body").text(content);
    $("#modalDialog").modal();
  });
}())
