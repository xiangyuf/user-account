package com.xiangyu.account;

import com.xiangyu.account.controller.OpsController;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class OpsControllerTest {

    private OpsController opsController = new OpsController();

    @Test
    public void testOpsController() throws Exception {
        Assert.assertNotNull(opsController.getJstack());
    }
}
