(function () {
  'use strict'

  $(".journal-record").click(function() {
    alert($(this).children(".journal-content").val());
  });
}())
