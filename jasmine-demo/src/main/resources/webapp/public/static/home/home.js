
(function () {
  
  // feather图标
  feather.replace();

  // 点击菜单加载内嵌页面
  $(".home-menu-item > a").click(function(event) {
    event.preventDefault();

    var link = $(this).attr("href");
    $("main > iframe").attr("src", link);
  });
}());
