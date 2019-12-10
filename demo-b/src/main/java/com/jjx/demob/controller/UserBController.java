package com.jjx.demob.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.jjx.demob.entity.UserB;
import com.jjx.demob.entity.UserC;
import com.jjx.demob.feign.UserCApi;
import com.jjx.demob.service.IUserBService;
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
@RequestMapping("/b")
public class UserBController {

    @Autowired
    private IUserBService userBService;
    @Autowired
    private UserCApi userCApi;

    @GetMapping
    public UserB get(@RequestParam Integer keyId) {
        Wrapper<UserB> param = Wrappers.<UserB>lambdaQuery().eq(UserB::getKeyId, keyId);
        return userBService.getOne(param);
    }

    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    @PostMapping
    public Boolean save(@RequestBody UserB userB) {
        userBService.save(userB);
        UserC userC = new UserC();
        BeanUtils.copyProperties(userB, userC);
        userCApi.save(userC);
        return Boolean.TRUE;
    }

    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    @PutMapping
    public Boolean update(@RequestBody UserB userB) {
        userBService.updateById(userB);
        UserC userC = new UserC();
        BeanUtils.copyProperties(userB, userC);
        userCApi.update(userC);
        return Boolean.TRUE;
    }

}

