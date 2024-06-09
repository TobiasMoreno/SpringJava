package ar.edu.utn.frc.tup.lciii.utils.validation.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Validator;
import org.passay.*;

import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword,String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        PasswordValidator passwordValidator = new PasswordValidator(Arrays.asList(

                new LengthRule(8 , 30),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 3, false),
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 3, false),
                new IllegalSequenceRule(EnglishSequenceData.USQwerty, 3, false),
                new WhitespaceRule()));

        RuleResult result = passwordValidator.validate(new PasswordData(password));
         if(result.isValid()){
             return true;
         }

         constraintValidatorContext.disableDefaultConstraintViolation();
         constraintValidatorContext.buildConstraintViolationWithTemplate(
                 String.join("," , passwordValidator.getMessages(result))).addConstraintViolation();
         return false;
    }
}
