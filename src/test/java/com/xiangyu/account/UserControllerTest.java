package com.xiangyu.account;

import com.xiangyu.account.controller.UserController;
import com.xiangyu.account.dao.UserDao;
import com.xiangyu.account.model.User;
import com.xiangyu.account.utility.ResponseConstants;
import com.xiangyu.account.utility.ResponseResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({User.class})
public class UserControllerTest {
    @InjectMocks
    private UserController userController = new UserController();

    @Mock
    private UserDao userDao;

    private User user1 = null;
    private User user2 = null;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        user1 = User.builder().userId(10L).coins(150L).build();
        user2 = User.builder().userId(11L).coins(250L).build();

        Mockito.when(userDao.save(user1)).thenReturn(user1);
        Mockito.when(userDao.save(user2)).thenReturn(user2);

        Mockito.when(userDao.findOne(10L)).thenReturn(user1);
        Mockito.when(userDao.findOne(11L)).thenReturn(user2);
    }

    @Test
    public void testCreateUser() {
        ResponseResult responseResult = userController.addUser(10L, 150L);
        Assert.assertEquals(responseResult.getMessage(), ResponseConstants.SUCCESS_MESSAGE);

        responseResult = userController.addUser(10L, -150L);
        Assert.assertEquals(responseResult.getMessage(), ResponseConstants.PARAM_NUMBER_ERROR);

        responseResult = userController.addUser(-10L, 150L);
        Assert.assertEquals(responseResult.getMessage(), ResponseConstants.PARAM_NUMBER_ERROR);

        responseResult = userController.addUser(-10L, -150L);
        Assert.assertEquals(responseResult.getMessage(), ResponseConstants.PARAM_NUMBER_ERROR);
    }

    @Test
    public void testGetUser() {
        ResponseResult responseResult = userController.getUser(10L);

        Assert.assertEquals(responseResult.getMessage(), ResponseConstants.SUCCESS_MESSAGE);
        Assert.assertEquals(responseResult.getData(), 150L);

        responseResult = userController.getUser(20L);
        Assert.assertEquals(responseResult.getMessage(), ResponseConstants.INVALID_USER_ID_ERROR);

        responseResult = userController.getUser(-20L);
        Assert.assertEquals(responseResult.getMessage(), ResponseConstants.PARAM_NUMBER_ERROR);
    }

    @Test
    public void testTransactionTransfer() {
        ResponseResult responseResult = userController.transfer(10L, 11L, 100);
        Assert.assertEquals(responseResult.getMessage(), ResponseConstants.SUCCESS_MESSAGE);

        responseResult = userController.transfer(10L, 11L, 300);
        Assert.assertEquals(responseResult.getMessage(), ResponseConstants.NO_ENOUGH_BALANCE_ERROR);

        responseResult = userController.transfer(15L, 11L, 100);
        Assert.assertEquals(responseResult.getMessage(), ResponseConstants.INVALID_USER_ID_ERROR);

        responseResult = userController.transfer(10L, 15L, 100);
        Assert.assertEquals(responseResult.getMessage(), ResponseConstants.INVALID_USER_ID_ERROR);

        responseResult = userController.transfer(-10L, 11L, 100);
        Assert.assertEquals(responseResult.getMessage(), ResponseConstants.PARAM_NUMBER_ERROR);

        responseResult = userController.transfer(10L, -11L, 100);
        Assert.assertEquals(responseResult.getMessage(), ResponseConstants.PARAM_NUMBER_ERROR);

        responseResult = userController.transfer(10L, 11L, -100);
        Assert.assertEquals(responseResult.getMessage(), ResponseConstants.PARAM_NUMBER_ERROR);
    }
}
