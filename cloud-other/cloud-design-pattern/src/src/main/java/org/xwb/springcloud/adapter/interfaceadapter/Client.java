package org.xwb.springcloud.adapter.interfaceadapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @description
 */
@Slf4j
public class Client {
    public static void main(String[] args) {
        AbstractAdapter adapter= new AbstractAdapter(){
            @Override
            public void method1() {
                log.info("使用了method1方法");
            }
        };
        adapter.method1();
    }
}
