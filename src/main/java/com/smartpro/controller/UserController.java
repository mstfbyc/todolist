package com.smartpro.controller;

import com.smartpro.util.ApiPaths;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@SecurityRequirement(name = "todolist")
public class UserController {
}
