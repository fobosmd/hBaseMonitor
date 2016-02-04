package com.klystopad.xyzbank.domain;

import java.util.Comparator;

/**
 * Created by Kirill Listopad.
 */
public class MsgEntityTimestampComparator implements Comparator<MsgEntity> {
    @Override
    public int compare(MsgEntity o1, MsgEntity o2) {
        return o1.getTimestamp().compareTo(o2.getTimestamp());
    }
}
