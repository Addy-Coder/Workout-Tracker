package com.workout.tracker.service.validation.aspect;

import com.workout.tracker.exception.APIException;
import com.workout.tracker.service.validation.ValidateInput;
import com.workout.tracker.service.validation.Validator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class ValidationAspect {

    @Autowired
    private ApplicationContext applicationContext;

    @Pointcut("@annotation(validateInput)")
    public void validatePointcut(ValidateInput validateInput) {
    }

    @Around("@annotation(validateInput)")
    public Object validate(ProceedingJoinPoint proceedingJoinPoint, ValidateInput validateInput) throws Throwable {
        Class<?> validatorClass = validateInput.validator();
        Validator<Object> validator = (Validator<Object>) applicationContext.getBean(validatorClass);

        Object[] args = proceedingJoinPoint.getArgs();
        if (args.length == 1) {
            validator.validate(args[0]);
        } else {
            throw new APIException(
                    "Internal Server error",
                    "Method should have a single argument encapsulating all inputs",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return proceedingJoinPoint.proceed();
    }
}
