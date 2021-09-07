package com.services;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class WsEmpresa {
  @PostMapping
  public String cadastrar(@RequestBody String json) {
    // JSONObject jsonObject
    System.out.println(json);
    return json;
    
  }
}
