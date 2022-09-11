package com.zoolatech.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordValidatorTest {


    CommonPasswordChecker commonPasswordChecker;
    PasswordValidator passwordValidator;

    @BeforeEach
    void initializeTests(){
        commonPasswordChecker = Mockito.mock(CommonPasswordChecker.class);
        passwordValidator = new PasswordValidator(commonPasswordChecker);
    }

    @Test
    void passwordShouldNotBeNull(){
        assertTrue(passwordValidator.checkPasswordNull("1234567"));
    }

    @Test
    void passwordShouldFailWhenPaswordIsNull(){
        assertFalse(passwordValidator.checkPasswordNull(null));
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
    void shouldFailValidationWhenPasswordContainSameNumbers(){
        assertFalse(passwordValidator.checkPasswordSameNumber("11111111"));
    }
    @Test
    void passwordShouldNotContainSameNumbers(){
        assertTrue(passwordValidator.checkPasswordSameNumber("12345678"));
    }

    @Test
    void passwordShouldContainDifferentCases(){
        assertTrue(passwordValidator.checkPasswordUpperLowerCase("PoisonTouch"));
    }

    @Test
    void shouldFailValidationWhenPasswordInUpperCase(){
        assertFalse(passwordValidator.checkPasswordUpperLowerCase("POISONTOUCH"));
    }

    @Test
    void shouldFailValidationWhenPasswordInLowerCase(){
        assertFalse(passwordValidator.checkPasswordUpperLowerCase("poisontouch"));
    }

    @Test
    void shouldFailValidaionWhenPasswordDoNotContainNumber(){
        assertFalse(passwordValidator.checkPasswordHasNumber("PoisonTouch"));
    }

    @Test
    void passwordShouldContainNumber(){
        assertTrue(passwordValidator.checkPasswordHasNumber("Poison1Touch"));
    }

    @Test
    void shouldFailValidationWhenPasswordDoNotContainSpecialNumber(){
        assertFalse(passwordValidator.checkPasswordSpecialCharacter("PoisonTouch"));
    }

    @Test
    void passwordShouldContainSpecialCharacter(){
        assertTrue(passwordValidator.checkPasswordSpecialCharacter("Poison@Touch"));
    }

    @Test
    void shouldFailValidationWhenPasswordContainConinousNumbers(){
        assertFalse(passwordValidator.checkPasswordContinuousNumbers("12345678"));
    }

    @Test
    void passwordShouldNotContainContinuousNumbers(){
        assertTrue(passwordValidator.checkPasswordContinuousNumbers("127564321"));
    }

    @Test
    void passwordMustPassAllChecks(){
        assertTrue(passwordValidator.checkPassword("P@ison28Touch"));
    }
}
