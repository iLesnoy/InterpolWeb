package by.petrorvskiy.webtask.validator;


import jdk.jfr.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class DateValidatorTest {

    @Name("ParameterizedDateTest")
    @ParameterizedTest
    @MethodSource("dateValues")
    void DateValidator(String date,boolean expectedResult) {
        boolean actual = DateValidator.applicationDateValidator(LocalDate.parse(date));
        assertEquals(actual,expectedResult);
    }

    private static Object[][] dateValues(){
            return new Object[][] {
                    {"08-12-2001",true},
                    {"08-12-2001",false},
                    {"2005.19.01",true},
                    {"2005-03-02",true},
                    {"0000.00.00",false},
                    {"0000-00-00",true},
                    {"0012.12.20",false},
                    {"#@%^^&_+=\",}",false}
            };
    }

    @ParameterizedTest
    @ValueSource(strings = {"01.02.2001",
            "2005.19.01","08-12-2001",
            "2005-03-02","#0000.00.00",
            "0000-00-00","0012.12.20",
            "------}","#@%^^&_+=",})
    void applicationDateValidator(String date) {
       assertTrue(DateValidator.applicationDateValidator(LocalDate.parse(date)));
    }

    @Test
    @ValueSource(strings = {"0000.00.00","..)",
            "0001-01.2001 ","asse.eq.12",
            "02.01.2001}","#$!@1",
            "9000,20,10","___>",})
    void wrongDate(String date){
        assertFalse(DateValidator.applicationDateValidator(LocalDate.parse(date)));
    }



}