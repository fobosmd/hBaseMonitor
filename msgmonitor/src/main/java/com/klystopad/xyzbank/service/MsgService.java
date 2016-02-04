package com.klystopad.xyzbank.service;

import com.klystopad.xyzbank.domain.MsgEntity;

import java.util.List;

/**
 * Created by Kirill Listopad.
 */
public interface MsgService {

    List<MsgEntity> getAllMsgsFormTimestamp(Long timestamp);
}
