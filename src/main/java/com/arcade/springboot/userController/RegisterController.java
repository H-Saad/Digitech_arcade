package com.arcade.springboot.userController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcade.springboot.model.Allowed_to_reg;
import com.arcade.springboot.model.Users;
import com.arcade.springboot.repositories.Allowed_to_regRepository;
import com.arcade.springboot.repositories.UsersRepository;

@RestController
@RequestMapping("user/")
public class RegisterController {
	
	@Autowired
	Allowed_to_regRepository atr;
	
	@Autowired
	UsersRepository ur;
	
	@PostMapping("register")
	public ResponseEntity<JSONObject> register(@RequestBody HashMap<String, String> r){
		boolean found = false;
		JSONObject ret = new JSONObject();
		List<Allowed_to_reg> arr = atr.findAll();
		for(Allowed_to_reg a : arr) {
			if(a.getEmail().equals(r.get("email"))) {
				found = true;
				break;
			}
		}
		if(found) {
			Users u = new Users();
			u.setFullname(r.get("fullname"));
			u.setMajor(r.get("major"));
			u.setPhone(r.get("phone"));
			u.setEmail(r.get("email"));
			u.setPassword(r.get("password"));
			ur.save(u);
			ret.put("result", "success");
			return ResponseEntity.status(200).body(ret);
		}else {
			ret.put("result", "unauthorized");
			return ResponseEntity.status(401).body(ret);
		}
	}
}
