package com.example.centerserver.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("user-service")
public interface UserClient {

}
