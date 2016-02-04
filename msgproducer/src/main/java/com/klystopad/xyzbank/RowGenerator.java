package com.klystopad.xyzbank;

import com.klystopad.xyzbank.domain.TransactionType;

import java.util.*;

/**
 * Created by Kirill Listopad.
 */
public class RowGenerator {

    private static final String COLUMN_PREFIX = "col";
    private static final int MAX_CELL_VALUE = 100;

    private List<String> columnNames = new ArrayList<>();
    private Random random = new Random();


    public RowGenerator() {
        initColumnNames();
    }

    private void initColumnNames(){
        for (int i = 0; i < 100; i++) {
            columnNames.add(COLUMN_PREFIX + i);
        }
    }

    public TransactionType getTransactionType(){
        int index = random.nextInt(TransactionType.values().length);
        return TransactionType.values()[index];
    }

    public Map<String, String> generateRow(){
        Map<String, String> map = new HashMap<>();

        for (String colName : columnNames){
            if (random.nextBoolean()){
                map.put(colName, String.valueOf(random.nextInt(MAX_CELL_VALUE)));
            }
        }
        return map;
    }
}
