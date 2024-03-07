package com.bizlog.rms.feignClient;

import com.bizlog.rms.dto.CreationDTO;
import com.bizlog.rms.dto.NotifyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "org-api", url = "http://localhost:9081", path = "/api")
public interface NotifyCalls {
    @PostMapping("/email/registration")
    String postOrg(@RequestBody NotifyDTO notifyDTO);

}