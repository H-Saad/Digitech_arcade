package com.arcade.springboot.userController;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcade.springboot.model.Users;
import com.arcade.springboot.repositories.QuestionRepository;
import com.arcade.springboot.repositories.UsersRepository;

@RestController
@RequestMapping("user/")
public class SubmitController {
	@Autowired
	UsersRepository ur;
	
	@Autowired
	QuestionRepository qr;
	
	@PostMapping("submit_answer")
	public ResponseEntity<JSONObject> submit_ans(@RequestBody HashMap<String, String> r){
		int usr_id = Integer.parseInt(r.get("user_id"));
		int question_id = Integer.parseInt(r.get("question_id"));
		String ansr = r.get("answer");
		JSONObject ret = new JSONObject();
		
		if(ansr.equals(qr.getReferenceById(question_id).getAnswer())) {
			Users u = ur.getReferenceById(usr_id);
			if(u.getAnswered_questions().contains(question_id)) {
				ScoreHelper sh = new ScoreHelper();
				sh.update_user_points(usr_id, sh.update_question_points(question_id), question_id);
				ret.put("result", "correct");
				return ResponseEntity.status(200).body(ret);
			}
			ret.put("result", "unauthorized");
			return ResponseEntity.status(422).body(ret);
		}
		ret.put("result", "incorrect");
		return ResponseEntity.status(422).body(ret);
	}
}
