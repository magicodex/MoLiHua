(function () {
  'use strict'

  feather.replace();

  $(".journal-record").click(function() {
    alert($(this).children(".journal-content").val());
  });
}())
