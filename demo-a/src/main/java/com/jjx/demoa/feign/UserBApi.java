package com.jjx.demoa.feign;

import com.jjx.demoa.entity.UserB;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jiangjx
 */
@FeignClient(name = "demo-b")
@RequestMapping("/b")
public interface UserBApi {

    @GetMapping
    UserB get(@RequestParam Integer keyId);

    @PostMapping
    Boolean save(@RequestBody UserB userB);

    @PutMapping
    Boolean update(@RequestBody UserB userB);
}
