
(function () {
  'use strict'

  // 初始主区域大小
  $("main").css("height", $(document).height() - 48);

  // 调整主区域大小
  $(window).resize(function() {
    $("main").css("height", $(document).height() - 48);
  });

  // 点击菜单事件
  $(".menu-item").click(function(event) {
    event.preventDefault();

    var link = $(this).attr("href");
    $("main > iframe").attr("src", link);
  });
}())
