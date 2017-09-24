package com.xiangyu.account.dao;

import com.xiangyu.account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Long> {

}
