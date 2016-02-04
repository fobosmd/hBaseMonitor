package com.klystopad.xyzbank;

/**
 * Created by Kirill Listopad.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Map;

public class MsgProducerApp2 {

    private static final String FAMILY = "cf";
    private static final String TABLE = "test";
    private static final int SLEEP_INTERVAL = 2000;

    public static void main(String[] args) {
        Configuration conf = HBaseConfiguration.create();

        conf.set("hbase.zookeeper.quorum", "localhost");
        conf.set("hbase.zookeeper.property.clientPort", "2181");

        RowGenerator rowGenerator = new RowGenerator();
        Connection con = null;
        Table table = null;

        try {
            con = ConnectionFactory.createConnection(conf);
            table = con.getTable(TableName.valueOf(TABLE));

            while (true) {
                Map<String, String> map = rowGenerator.generateRow();
                String rowId = rowGenerator.getTransactionType().toString();
                insertRow(table, rowId, map);
                System.out.println(String.format("<<< rowId: %s was inserted", rowId));
                Thread.sleep(SLEEP_INTERVAL);
            }

        } catch (InterruptedException e) {
            System.out.println("Was interrupted");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (table != null) {
                try {
                    table.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void insertRow(Table table, String rowId, Map<String, String> map) throws IOException {
        Put put = new Put(Bytes.toBytes(rowId));

        for (Map.Entry<String, String> entry : map.entrySet()) {
            put.addColumn(Bytes.toBytes(FAMILY), Bytes.toBytes(entry.getKey()), Bytes.toBytes(entry.getValue()));
        }
        table.put(put);
    }
}
