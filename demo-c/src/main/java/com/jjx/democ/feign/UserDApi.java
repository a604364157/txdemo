package com.jjx.democ.feign;

import com.jjx.democ.entity.UserD;
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
@FeignClient(name = "demo-d")
@RequestMapping("/d")
public interface UserDApi {

    @GetMapping
    UserD get(@RequestParam Integer keyId);

    @PostMapping
    Boolean save(@RequestBody UserD userD);

    @PutMapping
    Boolean update(@RequestBody UserD userD);
}
