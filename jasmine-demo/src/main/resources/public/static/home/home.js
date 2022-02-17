
(function () {
  'use strict'

  // 初始主区域大小
  $("main").css("height", $(document).height() - 48);

  // 调整主区域大小
  $(window).resize(function() {
    $("main").css("height", $(document).height() - 48);
  });
}())
