package com.klystopad.xyzbank.conf;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

/**
 * Created by Kirill Listopad.
 */

@Configuration
public class Conf {

    @Bean
    public HbaseTemplate hbaseTemplate(){
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "localhost");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        return new HbaseTemplate(conf);
    }
}
