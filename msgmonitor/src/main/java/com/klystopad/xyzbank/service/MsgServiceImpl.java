package com.klystopad.xyzbank.service;

import com.klystopad.xyzbank.domain.MsgEntity;
import com.klystopad.xyzbank.domain.MsgEntityTimestampComparator;
import com.klystopad.xyzbank.repository.MsgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Kirill Listopad.
 */

@Component("msgService")
public class MsgServiceImpl implements MsgService {

    @Autowired
    private MsgRepository repository;

    private Comparator<MsgEntity> comparator = new MsgEntityTimestampComparator();

    @Override
    public List<MsgEntity> getAllMsgsFormTimestamp(Long timestamp) {
        Assert.notNull(timestamp);
        List<MsgEntity> entities = repository.findAllByMinTimestamp(timestamp);
        Collections.sort(entities, comparator);
        return entities;
    }
}
