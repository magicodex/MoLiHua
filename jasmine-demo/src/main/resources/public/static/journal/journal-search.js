(function () {
  'use strict'

  $(".journal-record").click(function() {
    var content = $(this).children(".journal-content").val();

    $("#modalDialog .modal-body").text(content);
    $("#modalDialog").modal();
  });
}())
