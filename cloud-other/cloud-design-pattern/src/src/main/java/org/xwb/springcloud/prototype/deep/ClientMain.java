package org.xwb.springcloud.prototype.deep;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class ClientMain {
    public static void main(String[] args) throws CloneNotSupportedException {
        DeepProtoType p = new DeepProtoType();
        p.name = "张三";
        p.deepCloneableTarget = new DeepCloneableTarget("大牛", "大牛的类");
        //方式1 完成深拷贝
        DeepProtoType p2=(DeepProtoType) p.clone();
        log.info("深拷贝1================");
        log.info("p.name={},p.deepCloneableTarget={}",p.name,p.deepCloneableTarget.hashCode());
        log.info("p2.name={},p2.deepCloneableTarget={}",p2.name,p2.deepCloneableTarget.hashCode());

        //方式2、深拷贝
        DeepProtoType p3 = (DeepProtoType)p.deepClone();

        log.info("深拷贝2================");
        log.info("p.name={},p.deepCloneableTarget={}",p.name,p.deepCloneableTarget.hashCode());
        log.info("p3.name={},p3.deepCloneableTarget={}",p3.name,p3.deepCloneableTarget.hashCode());


    }
}
