
(function () {
  'use strict'

  feather.replace();

  // 点击菜单事件
  $(".home-menu-item > a").click(function(event) {
    event.preventDefault();

    var link = $(this).attr("href");
    $("main > iframe").attr("src", link);
  });
}())
