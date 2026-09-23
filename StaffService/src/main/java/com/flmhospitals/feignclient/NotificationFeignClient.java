package com.flmhospitals.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.flmhospitals.config.FeignClientConfig;
import com.flmhospitals.dto.EmailRequestDto;

@FeignClient(name = "notification-service", configuration = FeignClientConfig.class)
public interface NotificationFeignClient {
    @PostMapping("/api/notifications/email")
    String sendEmail(@RequestBody EmailRequestDto request);
}
