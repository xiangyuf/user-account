package com.xiangyu.account.controller;

import com.xiangyu.account.dao.UserDao;
import com.xiangyu.account.model.User;
import com.xiangyu.account.utility.ResponseConstants;
import com.xiangyu.account.utility.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/user")
@ResponseBody
public class UserController {
    @Autowired
    private UserDao userDao;

    @PostMapping(value = "/add", produces = {"application/json"})
    public ResponseResult addUser(
            @RequestParam(value = "user_id") long userId,
            @RequestParam(value = "coins") long coins
    ) {
        if (coins < 0 || userId < 0) {
            return ResponseResult.buildError(ResponseConstants.PARAM_NUMBER_ERROR);
        }

        userDao.save(
                User.builder().userId(userId).coins(coins).build()
        );

        return ResponseResult.buildSuccess();
    }

    @GetMapping(value = "/coins/{id}", produces = {"application/json"})
    public ResponseResult getUser(@PathVariable long id) {
        if (id < 0) {
            return ResponseResult.buildError(ResponseConstants.PARAM_NUMBER_ERROR);
        }

        User user = userDao.findOne(id);

        if (user != null) {
            return ResponseResult.buildSuccess(user.getCoins());
        } else {
            return ResponseResult.buildError(ResponseConstants.INVALID_USER_ID_ERROR);
        }

    }

    @PostMapping(value = "/transaction/transfer", produces = {"application/json"})
    public ResponseResult transfer(
            @RequestParam(value = "from_user_id") long fromUserId,
            @RequestParam(value = "to_user_id") long toUserId,
            @RequestParam(value = "coins") long coins
    ) {
        if (coins < 0 || fromUserId < 0 || toUserId < 0) {
            return ResponseResult.buildError(ResponseConstants.PARAM_NUMBER_ERROR);
        }

        if(fromUserId == toUserId) {
            return ResponseResult.buildError(ResponseConstants.NOT_SAME_USER_ID_ERROR);
        }

        User fromUser = userDao.findOne(fromUserId);
        User toUser = userDao.findOne(toUserId);

        if (fromUser == null || toUser == null) {
            return ResponseResult.buildError(ResponseConstants.INVALID_USER_ID_ERROR);
        }

        if (fromUser.getCoins() < coins) {
            return ResponseResult.buildError(ResponseConstants.NO_ENOUGH_BALANCE_ERROR);
        }

        transfer(fromUser, toUser, coins);
        return ResponseResult.buildSuccess();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    private void transfer(User fromUser, User toUser, long coins) {
        long fromCoins = fromUser.getCoins();
        long toCoins = toUser.getCoins();

        fromUser.setCoins(fromCoins - coins);
        toUser.setCoins(toCoins + coins);

        userDao.save(fromUser);
        userDao.save(toUser);
    }
}
