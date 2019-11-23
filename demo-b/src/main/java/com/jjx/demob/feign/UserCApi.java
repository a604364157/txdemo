package com.jjx.demob.feign;

import com.jjx.demob.entity.UserC;
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
@FeignClient(name = "demo-c")
@RequestMapping("/c")
public interface UserCApi {

    @GetMapping
    UserC get(@RequestParam Integer keyId);

    @PostMapping
    Boolean save(@RequestBody UserC userC);

    @PutMapping
    Boolean update(@RequestBody UserC userC);
}
