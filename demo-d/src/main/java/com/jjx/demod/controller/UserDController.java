package com.jjx.demod.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.jjx.demod.entity.UserD;
import com.jjx.demod.service.IUserDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jiangjx
 * @since 2019-11-16
 */
@RestController
@RequestMapping("/d")
public class UserDController {

    @Autowired
    private IUserDService userDService;

    @GetMapping
    public UserD get(@RequestParam Integer keyId) {
        Wrapper<UserD> param = Wrappers.<UserD>lambdaQuery().eq(UserD::getKeyId, keyId);
        return userDService.getOne(param);
    }

    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    @PostMapping
    public Boolean save(@RequestBody UserD userD) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);
        return userDService.save(userD);
    }

    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    @PutMapping
    public Boolean update(@RequestBody UserD userD) {
        return userDService.updateById(userD);
    }

}

