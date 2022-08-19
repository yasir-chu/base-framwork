package com.chuyx.service.impl;

import com.chuyx.service.TestService;
import io.lyh.dtp.core.DtpExecutor;
import io.lyh.dtp.core.DtpRegistry;
import org.springframework.stereotype.Service;


/**
 * @author chuyx
 * @date 2021/5/4 16:46
 */
@Service
public class TestServiceImpl implements TestService {



    @Override
    public void testThread() {
        DtpExecutor dtpExecutor = DtpRegistry.getExecutor("demo1-executor");
        dtpExecutor.execute(() -> System.out.println("test"));
    }
}
