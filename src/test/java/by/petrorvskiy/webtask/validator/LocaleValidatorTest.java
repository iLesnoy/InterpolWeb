package by.petrorvskiy.webtask.validator;

import jdk.jfr.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class LocaleValidatorTest {

    @Name("LocaleTest")
    @ParameterizedTest
    @MethodSource("createLocale")
    void isLocaleExist(String locale) {
        boolean condition = LocaleValidator.isLocaleExist(locale);
        assertTrue(condition,locale + " doesn't exist in this application");
    }

    private static Object[][] createLocale() {
        String[][] locale = {{"ru_RU"},
                {"en_US"}};
        return locale;
    }

    @Name("localeExistWithNullConditionTest")
    @Test
    public void localeExistWithNullCondition() {
        String data = null;
        boolean condition = LocaleValidator.isLocaleExist(data);
        assertFalse(condition);
    }

    @Test
    public void localeExistWithFalseCondition() {
        String data = "se_WEB";
        boolean condition = LocaleValidator.isLocaleExist(data);
        assertFalse(condition);
    }
}