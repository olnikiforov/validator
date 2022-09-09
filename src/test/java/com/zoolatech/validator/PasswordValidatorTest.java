package com.zoolatech.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordValidatorTest {


    CommonPasswordChecker commonPasswordChecker;
    PasswordValidator passwordValidator;

    @BeforeEach
    void initializeTests(){
        commonPasswordChecker = new CommonPasswordChecker();
        passwordValidator = new PasswordValidator(commonPasswordChecker);
    }

    @Test
    void passwordShouldNotBeNull(){
        assertFalse(passwordValidator.checkPasswordNull(null));
        assertTrue(passwordValidator.checkPasswordNull("1234567"));
    }

    @Test
    void passwordLengthShouldBeMoreThan8(){
        assertFalse(passwordValidator.checkPasswordLength("1234"));
    }

    @Test
    void passwordShouldBeLessThan25(){
        assertFalse(passwordValidator.checkPasswordLength("12341234123412341234123412341234"));
    }

    @Test
    void passwordShouldBeInRange8to25(){
        assertTrue(passwordValidator.checkPasswordLength("123456789"));
    }

    @Test
    void passwordShouldNotContainSameNumbers(){
        assertFalse(passwordValidator.checkPasswordSameNumber("11111111"));
        assertTrue(passwordValidator.checkPasswordSameNumber("12345678"));
    }

    @Test
    void passwordShouldContainDifferentCases(){
        assertFalse(passwordValidator.checkPasswordUpperLowerCase("poisontouch"));
        assertFalse(passwordValidator.checkPasswordUpperLowerCase("POISONTOUCH"));
        assertTrue(passwordValidator.checkPasswordUpperLowerCase("PoisonTouch"));
    }

    @Test
    void passwordShouldContainNumber(){
        assertFalse(passwordValidator.checkPasswordHasNumber("PoisonTouch"));
        assertTrue(passwordValidator.checkPasswordHasNumber("Poison1Touch"));
    }

    @Test
    void passwordShouldContainSpecialCharacter(){
        assertFalse(passwordValidator.checkPasswordSpecialCharacter("PoisonTouch"));
        assertTrue(passwordValidator.checkPasswordSpecialCharacter("Poison@Touch"));
    }

    @Test
    void passwordShouldNotContainContinuousNumbers(){
        assertFalse(passwordValidator.checkPasswordContinuousNumbers("12345678"));
        assertTrue(passwordValidator.checkPasswordContinuousNumbers("127564321"));
    }

    @Test
    void passwordMustPassAllChecks(){
        assertTrue(passwordValidator.checkPassword("P@ison28Touch"));
    }
}
