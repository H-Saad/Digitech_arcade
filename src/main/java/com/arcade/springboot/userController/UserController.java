package com.arcade.springboot.userController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcade.springboot.model.Users;
import com.arcade.springboot.repositories.UsersRepository;

@RestController
@RequestMapping("user/")
public class UserController {
	
	@Autowired
	UsersRepository ur;
	
	@RequestMapping("getuser/{user_id}")
	public ResponseEntity<JSONObject> getUser(@PathVariable int user_id){
		Users u = ur.getReferenceById(user_id);
		JSONObject ret = new JSONObject();
		if(u != null) {
			ret.put("id", u.getId());
			ret.put("fullname", u.getFullname());
			ret.put("major", u.getMajor());
			ret.put("points", u.getPoints());
			return ResponseEntity.status(200).body(ret);
		}
		return ResponseEntity.status(404).body(ret);
	}
	
	@RequestMapping("get_scoreboard")
	public ResponseEntity<List<JSONObject>> getScoreboard(){
		List<Users> li = ur.findAll();
		ArrayList<HashMap<String,String>> arrMap = new ArrayList<HashMap<String,String>>();
		if(li != null) {
			for(Users u:li) {
				HashMap<String,String> temp = new HashMap<String,String>();
				temp.put("id", ""+u.getId());
				temp.put("fullname", u.getFullname());
				temp.put("points", ""+u.getPoints());
				arrMap.add(temp);
			}
			List<JSONObject> ret = new ArrayList<JSONObject>();
			
			for(HashMap<String,String> data : arrMap) {
				JSONObject obj = new JSONObject(data);
				ret.add(obj);
			}
			
			return ResponseEntity.status(200).body(ret);
		}
		return ResponseEntity.status(404).body(null);
	}
}
