package com.klystopad.xyzbank.repository;

/**
 * Created by Kirill Listopad.
 */
import com.klystopad.xyzbank.domain.MsgEntity;
import com.klystopad.xyzbank.domain.TransactionType;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.ResultsExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.*;

@Repository
public class MsgRepository {

    private static final String TABLE = "test";
    private static final String FAMILY = "cf";

    @Autowired
    private HbaseTemplate hbaseTemplate;

    public List<MsgEntity> findAllByMinTimestamp(Long minTimestamp) {
        Assert.notNull(minTimestamp);

        Scan scan = new Scan();
        scan.setMaxVersions();

        try {
            scan.setTimeRange(minTimestamp, Long.MAX_VALUE);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

        return hbaseTemplate.find(TABLE, scan, new ResultsExtractor<List<MsgEntity>>() {
            @Override
            public List<MsgEntity> extractData(ResultScanner results) throws Exception {
                List<MsgEntity> msgEntities =new ArrayList<>();
                Result res;
                while ((res = results.next()) != null) {
                    msgEntities.addAll(rowMapper(res));
                }
                return msgEntities;
            }
        });
    }

    private List<MsgEntity> rowMapper(Result result) {
        List<MsgEntity> msgEntities = new ArrayList<>();
        Map<Long, MsgEntity> timestampMsgEntityMap = new HashMap<>();

        TransactionType transactionType = TransactionType.valueOf(Bytes.toString(result.getRow()));
        NavigableMap<byte[], NavigableMap<Long, byte[]>> qualifierTimestampValueMap = result.getMap().get(Bytes.toBytes(FAMILY));

        for (Map.Entry<byte[], NavigableMap<Long, byte[]>> qualifierEntry : qualifierTimestampValueMap.entrySet()) {
            String column = Bytes.toString(qualifierEntry.getKey());
            NavigableMap<Long, byte[]> timestampValueMap = qualifierEntry.getValue();
            addAllValues(transactionType, column, timestampMsgEntityMap, timestampValueMap);
        }

        msgEntities.addAll(timestampMsgEntityMap.values());
        return msgEntities;
    }

    private void addAllValues(TransactionType transactionType, String column, Map<Long, MsgEntity> timestampMsgEntityMap, NavigableMap<Long, byte[]> timestampValueMap) {
        for (Map.Entry<Long, byte[]> entry : timestampValueMap.entrySet()) {
            Long timestamp = entry.getKey();
            MsgEntity msgEntity = timestampMsgEntityMap.get(timestamp);

            if (msgEntity == null) {
                msgEntity = new MsgEntity();
                msgEntity.setTransactionType(transactionType);
                msgEntity.setTimestamp(timestamp);
                timestampMsgEntityMap.put(timestamp, msgEntity);
            }
            msgEntity.getData().put(column, Bytes.toString(entry.getValue()));
        }
    }
}
