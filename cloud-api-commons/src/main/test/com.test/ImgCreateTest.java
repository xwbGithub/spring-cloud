package com.test;

import org.junit.Test;
import org.xwb.springcloud.utils.document.DocField;
import org.xwb.springcloud.utils.document.ElectBillUtils;
import org.xwb.springcloud.utils.document.PackageFieldUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description
 */
public class ImgCreateTest {
    /**
     * 提货单
     */
    @Test
    public void testPickUp() {
        List<DocField> docFieldList = new ArrayList<DocField>();
        //接单时间
        docFieldList.add(new DocField("time", ElectBillUtils.getTime(new Date(), null), "1", 1));
        //票号
        docFieldList.add(new DocField("planNumber", "234234234", "1", 1));
        //单位
        docFieldList.add(new DocField("unit", "吨", "1", 1));
        String url = "https://img-home.csdnimg.cn/images/20201124032511.png";
        PackageFieldUtils.setCommon(docFieldList, url, url, url);
        String common = ElectBillUtils.createCommon(ElectBillUtils.PICKUP, docFieldList);
        System.out.println("file:" + common);
    }
}
