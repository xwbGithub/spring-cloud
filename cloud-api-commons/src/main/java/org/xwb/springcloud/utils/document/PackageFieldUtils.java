package org.xwb.springcloud.utils.document;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Administrator
 * @description 创建工具类获取值返回生成的结果
 */
@Slf4j
public class PackageFieldUtils {
    /**
     * 塞入公共参数方法
     *
     * @param docFieldList 文档集合
     * @param logo         发货企业logo
     * @param sendUrl      发货企业电子公章
     * @param receiveUrl   收货企业电子公章
     */
    public static void setCommon(List<DocField> docFieldList, String logo, String sendUrl,
                                 String receiveUrl) {
        docFieldList.add(new DocField("logo", logo, "2", 3));
        //承运单位（签章）
        docFieldList.add(new DocField("sendUrl", sendUrl, "2", 2));
        //收货单位（签章）
        docFieldList.add(new DocField("receiveUrl", receiveUrl, "2", 2));
        //发货单位
        docFieldList.add(new DocField("sendEnterpriseName", "33333333333", "1", 2));
        //收货单位
        docFieldList.add(new DocField("receiveEnterpriseName", "5555555555555", "1", 2));
        //货品大类
        docFieldList.add(new DocField("goodsClassName", "5555555555", "1", 2));
        //货品名称
        docFieldList.add(new DocField("goodsName", "666666666", "1", 2));
        //车牌号码
        docFieldList.add(new DocField("vehicleNumber", "666666666", "1", 2));
        //司机电话
        docFieldList.add(new DocField("driverPhone", "666666666", "1", 2));
        //司机姓名
        docFieldList.add(new DocField("driverName", "222222222", "1", 2));
    }
}
