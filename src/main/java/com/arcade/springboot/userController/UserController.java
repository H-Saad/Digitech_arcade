package com.arcade.springboot.userController;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/")
public class UserController {
	@RequestMapping("getuser/{user_id}")
	public ResponseEntity<JSONObject> getUser(@PathVariable int user_id){
		
	}
}
