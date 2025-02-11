package com.itheima.bigevent.validation;

import com.itheima.bigevent.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/*
自定义注解校验数据类
 */
public class StateValidation implements ConstraintValidator<State,String> {
    /**
     *
     * @param value 将来要校验的数据
     * @param context context in which the constraint is evaluated
     *
     * @return 如果返回false则校验不通过，如果返回true则校验通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //提供校验规则
        if (value == null){
            return false;
        }
        if (value.equals("已发布") || value.equals("草稿")){
            return true;
        }
        return false;
    }
}
