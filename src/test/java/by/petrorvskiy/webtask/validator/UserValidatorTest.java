package by.petrorvskiy.webtask.validator;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertEquals;


class UserValidatorTest {

    private static Object[][] userEmailValues(){
        return new Object[][] {
                {"arnild@gm",true},
                {"3123.",false},
                {"123@23 ",true},
                {"gnom@gmail.com",true},
                {"#$!@1",false},
                {"ign11111@",true},
                {"00@interpol.com",true},
                {"1@interpol.com",false}
        };
    }

    @ParameterizedTest
    @MethodSource("userEmailValues")
    void DateValidator(String email,boolean expectedResult) {
        boolean actual = UserValidator.isValidEmail(email);
        assertEquals(actual,expectedResult);
    }
    @ParameterizedTest
    @ValueSource(strings = {"arnild@gm","3123.",
                            "123@23 ","gnom@gmail.com",
                            "ign11111@}","#$!@1",
                            "1@interpol.com","00@interpol.com",
                            "------>}","#@gmail.com",})
    void isValidEmail(String email) {
        Assertions.assertTrue(UserValidator.isValidEmail(email) && !email.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345","..)",
            "+@#$%&! ","ПАСсвор",
            "iя руский}","#$!@1",
            "1@interpol.com","___>",
            "------>}","helloWorld",})
    void isValidPassword(String password) {
        Assertions.assertTrue(UserValidator.isValidPassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Arnold","Арнольд",
            "22221 ","gnom@gmail.com",
            "igno<>!","?????????()",
            "Hermes","Gnom%@",
            "<------@>>}","#@gmail.com",})
    void isValidName(String name) {
        Assertions.assertFalse(UserValidator.isValidPassword(name) | name.isEmpty());
    }
}