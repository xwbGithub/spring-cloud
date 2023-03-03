package org.xwb.springcloud.utils.document;

import lombok.Data;
import lombok.ToString;

/**
 * typeOfLocation 如果是图片，则显示在哪个位置
 * 对齐方式：0-7(默认为2) {@link com.aspose.words.HorizontalAlignment}
 * <p>
 * <br/>0 NONE | DEFAULT<br/>1：LEFT<br/>2 CENTER<br/>3 RIGHT<br/>4 INSIDE<br/>5 OUTSIDE<br/>
 *
 * @author Administrator
 */
@Data
@ToString
public class DocField {
    private String field;
    private String value;
    /**
     * 类型 1、字符串2、图片
     */
    private String type;
    /**
     * 如果是图片，则显示在哪个位置
     * 对齐方式：0-7(默认为2) {@link com.aspose.words.HorizontalAlignment}
     * <p>
     * <br/>0 NONE | DEFAULT<br/>1：LEFT<br/>2 CENTER<br/>3 RIGHT<br/>4 INSIDE<br/>5 OUTSIDE<br/>
     */
    private int typeOfLocation = 2;

    /**
     * @param field          字段名称
     * @param value          字段对应的值
     * @param type           1、字符串2、图片
     * @param typeOfLocation 对齐方式：0-7(默认为2) {@link com.aspose.words.HorizontalAlignment}
     *                       <br/>0 NONE | DEFAULT<br/>1：LEFT<br/>2 CENTER<br/>3 RIGHT<br/>4 INSIDE<br/>5 OUTSIDE<br/>
     */
    public DocField(String field, String value, String type, int typeOfLocation) {
        this.field = field;
        this.value = value;
        this.type = type;
        this.typeOfLocation = typeOfLocation;
    }
}
