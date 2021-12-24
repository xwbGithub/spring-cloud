package com.xwb.springcloud.test;

import com.xwb.springcloud.annotation.check.CheckClass;
import com.xwb.springcloud.annotation.check.CheckField;
import com.xwb.springcloud.annotation.check.CheckMethod;
import com.xwb.springcloud.annotation.check.CheckParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestAnnotation {
    public static void main(String[] args) {
        //解析注解
        Class<Person> clazz = Person.class;
        boolean al = clazz.isAnnotationPresent(CheckClass.class);
        System.out.println("================类的注解====================");
        System.out.println("是否有CheckClass注解：" + al);
        CheckClass checkClass = clazz.getAnnotation(CheckClass.class);
        System.out.println("value值：{" + checkClass.value() + "} msg值：{" + checkClass.msg() + "}");
        System.out.println("\n================属性的注解====================");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean annotationPresent = field.isAnnotationPresent(CheckField.class);
            CheckField fieldAnnotation = field.getAnnotation(CheckField.class);
            System.out.println("是否存在该注解CheckField：" + annotationPresent + "属性上的值{" + fieldAnnotation.value() + "}");
        }
        System.out.println("\n================方法的注解====================");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            boolean annotationPresent = method.isAnnotationPresent(CheckMethod.class);
            CheckMethod methodAnnotation = method.getAnnotation(CheckMethod.class);
            System.out.println("是否存在该注解CheckMethod：" + annotationPresent + "属性上的值{" + methodAnnotation.value() + "}");
            System.out.println("\n================方法上的参数的注解====================");
            //取出方法上的参数
            if (method.getParameterCount() > 0) {
                //取出所有参数， [为什么是二位数字---因为一个方法有可能有多个参数，一个参数有可能有多个注解]
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                for (Annotation[] parameterAnnotation : parameterAnnotations) {
                    for (Annotation annotation : parameterAnnotation) {
                        if (annotation instanceof CheckParam) {
                            CheckParam checkParamValue = (CheckParam) annotation;
                            System.out.println("当前方法上参数的注解{" + checkParamValue.value() + "}");
                        }
                    }
                }
            }
        }
    }
}

@CheckClass(value = "我是类注解上的的value", msg = "我是类上面的msg")
class Person {
    @CheckField("我是属性注解上的value值")
    private String name;

    @CheckMethod("我是方法上的注解value值")
    public void eat(@CheckParam("我是参数注解上的value值") String food, @CheckParam("我是参数注解上的value值") String name) {
        System.out.println(food + "===" + name);
    }

}
