package com.klystopad.xyzbank.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kirill Listopad.
 */
public class MsgEntity {

    private TransactionType transactionType;
    private Long timestamp;
    private Map<String, String> data = new HashMap<>();

    public MsgEntity() {
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
