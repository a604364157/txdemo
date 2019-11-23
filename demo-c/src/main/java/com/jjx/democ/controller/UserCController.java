package com.jjx.democ.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.core.DTXLocalContext;
import com.jjx.democ.entity.UserC;
import com.jjx.democ.entity.UserD;
import com.jjx.democ.feign.UserDApi;
import com.jjx.democ.service.IUserCService;
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
@RequestMapping("/c")
public class UserCController {

    @Autowired
    private IUserCService userCService;
    @Autowired
    private UserDApi userDApi;

    @GetMapping
    public UserC get(@RequestParam Integer keyId) {
        Wrapper<UserC> param = Wrappers.<UserC>lambdaQuery().eq(UserC::getKeyId, keyId);
        return userCService.getOne(param);
    }

    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    @PostMapping
    public Boolean save(@RequestBody UserC userC) {
        System.out.println(DTXLocalContext.getOrNew().getGroupId());
        userCService.save(userC);
        UserD userD = new UserD();
        BeanUtils.copyProperties(userC, userD);
        userDApi.save(userD);
        return Boolean.TRUE;
    }

    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    @PutMapping
    public Boolean update(@RequestBody UserC userC) {
        userCService.updateById(userC);
        UserD userD = new UserD();
        BeanUtils.copyProperties(userC, userD);
        userDApi.update(userD);
        return Boolean.TRUE;
    }

}

