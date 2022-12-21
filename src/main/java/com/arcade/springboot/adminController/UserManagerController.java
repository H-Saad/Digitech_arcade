package com.arcade.springboot.adminController;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcade.springboot.model.Users;
import com.arcade.springboot.repositories.UsersRepository;

@RestController
@RequestMapping("admin/")
public class UserManagerController {
	@Autowired
	UsersRepository ur;
	
	@PostMapping("/add_user")
	public ResponseEntity<JSONObject> adduser(@RequestBody HashMap<String, String> req){
		String email = req.get("email");
		JSONObject ret = new JSONObject();
		try {
			ur.findAllByEmail(email);
		}
		catch(NoSuchElementException e) {
			ret.put("result", "Already exists");
			return ResponseEntity.status(401).body(ret);
		}
		Users temp = new Users();
		temp.setFullname(req.get("fullname"));
		temp.setMajor(req.get("major"));
		temp.setPhone(req.get("phone"));
		temp.setEmail(req.get("email"));
		temp.setPassword(req.get("password"));
		ur.save(temp);
		ret.put("result", "success");
		return ResponseEntity.status(200).body(ret);
	}
	
	@PostMapping("/edit_user")
	public ResponseEntity<JSONObject> edituser(@RequestBody HashMap<String, String> req){
		Users u;
		JSONObject ret = new JSONObject();
		try {
			u = ur.findById(Integer.parseInt(req.get("id"))).get();
		}
		catch(NoSuchElementException e){
			ret.put("result", "user not found");
			return ResponseEntity.status(404).body(ret);
		}
		u.setFullname(req.get("fullname"));
		u.setMajor(req.get("major"));
		u.setPhone(req.get("phone"));
		u.setEmail(req.get("email"));
		u.setPassword(req.get("password"));
		u.setPoints(Integer.parseInt(req.get("points")));
		u.setBanned(Boolean.parseBoolean(req.get("banned")));
		ret.put("result", "success");
		return ResponseEntity.status(200).body(ret);
	}
	
	
	@DeleteMapping("/remove_user/{user_id}")
	public ResponseEntity<JSONObject> removeuser(@PathVariable("user_id") int user_id){
		JSONObject ret = new JSONObject();
		try {
			ur.findById(user_id);
		}
		catch(NoSuchElementException e) {
			ret.put("result", "user not found");
			return ResponseEntity.status(404).body(ret);
		}
		ur.deleteById(user_id);
		ret.put("result", "success");
		return ResponseEntity.status(200).body(ret);
	}
}
