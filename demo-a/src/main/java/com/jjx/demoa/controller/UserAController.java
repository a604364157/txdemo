package com.jjx.demoa.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.jjx.demoa.entity.UserA;
import com.jjx.demoa.entity.UserB;
import com.jjx.demoa.feign.UserBApi;
import com.jjx.demoa.service.IUserAService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jiangjx
 * @since 2019-11-16
 */
@RestController
@RequestMapping("/a")
public class UserAController {

    @Autowired
    private IUserAService userAService;
    @Autowired
    private UserBApi userBApi;

    @GetMapping
    public UserA get(@RequestParam Integer keyId) {
        Wrapper<UserA> param = Wrappers.<UserA>lambdaQuery().eq(UserA::getKeyId, keyId);
        return userAService.getOne(param);
    }

    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    @PostMapping
    public Boolean save(@RequestBody UserA userA) {
        userAService.save(userA);
        UserB userB = new UserB();
        BeanUtils.copyProperties(userA, userB);
        userBApi.save(userB);
        return Boolean.TRUE;
    }

    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    @PutMapping
    public Boolean update(@RequestBody UserA userA) {
        userAService.updateById(userA);
        UserB userB = new UserB();
        BeanUtils.copyProperties(userA, userB);
        userBApi.update(userB);
        return Boolean.TRUE;
    }

}

