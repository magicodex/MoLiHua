(function () {
  'use strict'

  $(".journal-record").click(function() {
    // alert($(this).children(".journal-content").val());

    var content = $(this).children(".journal-content").val();

    $("#modalDialog .modal-body").text(content);
    $("#modalDialog").modal();
  });
}())
