package com.kevin.simulate.unit.moduler.user.controller;

import com.kevin.simulate.unit.moduler.user.service.UserService;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kevin
 */
@RestController
@RequestMapping("/api/user/")
public class UserController {

  @Resource
  private UserService userService;

  @GetMapping("/sayHello")
  public ResponseEntity<String> sayHello() {
    String word = userService.sayHello();
    return new ResponseEntity(word, HttpStatus.OK);
  }


}
