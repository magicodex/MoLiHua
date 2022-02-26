(function() {

  $('form').bootstrapValidator({
    fields: {
        journalTitle: {
            validators: {
                notEmpty: {
                  message: '请输入标题'
                },
                stringLength: {
                  min: 1,
                  max: 20,
                  message: '标题长度必须在 1 到 20 之间'
                }
            }
        },
        journalContent: {
            validators: {
                stringLength: {
                  min: 0,
                  max: 50,
                  message: '内容长度必须在 0 到 50 之间'
                }
            }
        }
    }
  });
}());