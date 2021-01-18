package org.xwb.springcloud.lb.impl;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import org.xwb.springcloud.lb.LoadBalance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalance {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            //2147483647=Integer.MAX_VAlUE
            next = current >= 2147483647 ? 0 : current + 1;
            //current期望值，next操作值,如果不满足，当前对象atomicInteger自旋取反，知道满足期望值
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("****第几次访问，次数 next:" + next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
