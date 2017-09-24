package com.xiangyu.account.controller;

import com.xiangyu.account.utility.Utility;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ops")
@ResponseBody
public class OpsController {

    @GetMapping(value = "/jstack")
    public String getJstack() throws Exception{
        return Utility.acquireJstack();
    }
}
