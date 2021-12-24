package com.xwb.springcloud.annotation.json;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("all")
public class JSON {
    public static String toJSONString(Object object) {
        StringBuffer json = new StringBuffer();
        try {
            //1、得到object的Class对象
            Class<?> clazz = object.getClass();
            //2、得到class里面的所有属性
            Field[] fields = clazz.getDeclaredFields();
            //3、循环遍历所有属性病打破属性的访问权限
            int index = 0;
            //得到属性长度
            int length = fields.length;
            json.append("{");
            for (Field field : fields) {
                index++;
                //判断属性上是否有忽略的注解
                if (field.isAnnotationPresent(JsonIgnore.class)) {
                    continue;
                }
                //私有变量可读
                field.setAccessible(true);
                //4得到属性名作为json的key
                String name = field.getName();
                //5、得到属性值作为json的value
                Object value = field.get(object);
                //判断属性上是否有更改名字的注解
                if (field.isAnnotationPresent(JsonProperty.class)) {
                    //有的话取出注解上的名字
                    JsonProperty annotation = field.getAnnotation(JsonProperty.class);
                    name = annotation.value();
                }
                json.append("\"" + name + "\"");
                if (value instanceof String) {
                    json.append(":\"" + value.toString() + "\"");
                } else if (value instanceof Date) {
                    //此处如果是日期的话，则判断是否有日起格式化的注解
                    Date date = (Date) value;
                    if (field.isAnnotationPresent(JsonFormat.class)) {
                        JsonFormat format = field.getAnnotation(JsonFormat.class);
                        json.append(":\"" + parseDateToStr(date, format.pattern()) + "\"");
                    } else {
                        json.append(":\"" + date.getTime() + "\"");
                    }
                } else {
                    json.append(":" + value.toString());
                }
                if (index != length) {
                    json.append(",");
                }
            }
            json.append("}");
            return json.toString();
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
        return null;
    }

    public static String parseDateToStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
