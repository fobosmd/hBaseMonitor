# hBaseMonitor

Installing

1) install hBase 1.1.3.
Installation guide: http://hbase.apache.org/0.94/book/quickstart.html

make sure you have only one entry of Ip address 127.0.0.1 in "hosts" file

hbase-site.xml sample: (change in config only "hbase.rootdir" and hbase.zookeeper.property.dataDir)

```xml
<configuration>
  <property>
    <name>hbase.zookeeper.quorum</name>
    <value>localhost</value>
  </property>
  <property>
    <name>hbase.zookeeper.property.clientPort</name>
    <value>2181</value>
  </property>

  <property>
    <name>hbase.rootdir</name>
    <value>file:///home/md/opt/hbase/hbase-1.1.3/tmp/hbase</value>
  </property>
  <property>
    <name>hbase.zookeeper.property.dataDir</name>
    <value>/home/md/opt/hbase/hbase-1.1.3/tmp/zookeeper</value>
  </property>
</configuration>
```

2) execute in "./bin/hbase shell"
create 'test', 'cf'
alter 'test', NAME => 'cf', VERSIONS => 100

3) run class that populate DB with data com.klystopad.xyzbank.MsgProducerApp2
4) run monitoring App com.klystopad.xyzbank.MsgMonitorApp
5) open in browser http://localhost:8080/index