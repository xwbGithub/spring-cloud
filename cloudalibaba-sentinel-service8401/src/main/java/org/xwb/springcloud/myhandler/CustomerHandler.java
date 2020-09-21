package org.xwb.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.xwb.springcloud.entities.CommonResult;

/**
 * 客户自定义的handlerException异常类
 */
public class CustomerHandler {
    public static CommonResult handlerException1(BlockException blockException) {
        return new CommonResult(4444, "按照客户自定义， global handlerException-------------1");
    }

    public static CommonResult handlerException2(BlockException blockException) {
        return new CommonResult(4444, "按照客户自定义， global handlerException-------------2");
    }
}
