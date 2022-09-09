package com.zoolatech.validator;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommonPasswordCheckerTest {
    CommonPasswordChecker commonPasswordChecker;

    @BeforeEach
    void initializeTest(){
        commonPasswordChecker = new CommonPasswordChecker();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234567", "qwerty", "abcdef", "putinhuilo"})
    void isPasswordCommon(String password){
        assertTrue(commonPasswordChecker.checkCommonPassword(password));
    }

    @Test
    void isNotCommonPassword(){
        assertFalse(commonPasswordChecker.checkCommonPassword("P@ison28Touch"));
    }

}
