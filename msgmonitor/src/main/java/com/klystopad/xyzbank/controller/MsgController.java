package com.klystopad.xyzbank.controller;

import com.klystopad.xyzbank.domain.MsgEntity;
import com.klystopad.xyzbank.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kirill Listopad.
 */

@Controller
public class MsgController {

    @Autowired
    private MsgService msgService;

    @RequestMapping(value = "msglist", method=RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MsgEntity> msgList(@RequestBody Long timestamp){
        return msgService.getAllMsgsFormTimestamp(timestamp);
    }

    @RequestMapping(value = "index", method=RequestMethod.GET)
    public String index() {
        return "msgList";
    }
}
