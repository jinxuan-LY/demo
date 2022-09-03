package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author yuanqiang.liao
 * @description TODO
 * @createTime 2022-09-03 18:46:00
 */

@Service
public class HelloWorld {
    @Value("${sharding.batch.size:200}")
    private int shardingBatchSize;


    @Value("${total.export.size:200}")
    private int totalExportSize;


    public void PrintHello() {
        System.out.println("shardingBatchSize: " + shardingBatchSize);
        System.out.println("totalExportSize: " + totalExportSize);
    }
}
