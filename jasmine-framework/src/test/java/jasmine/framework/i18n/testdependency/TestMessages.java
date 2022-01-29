package jasmine.framework.i18n.testdependency;

import jasmine.core.i18n.DeclareI18N;

/**
 * @author mh.z
 */
public interface TestMessages {

    @DeclareI18N("message 1")
    String MESSAGE_1 = "message1";

    @DeclareI18N("message 2")
    String MESSAGE_2 = "message2";
}
