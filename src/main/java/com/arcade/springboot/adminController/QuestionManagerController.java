package com.arcade.springboot.adminController;

import java.util.ArrayList;
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

import com.arcade.springboot.model.Question;
import com.arcade.springboot.repositories.QuestionRepository;

@RestController
@RequestMapping("admin/")
public class QuestionManagerController {
	@Autowired
	QuestionRepository qr;
	
	@PostMapping("/add_question")
	ResponseEntity<JSONObject> addquestion(@RequestBody HashMap<String,String> req){
		Question q = new Question();
		q.setTitle(req.get("title"));
		q.setBody(req.get("body"));
		q.setType(req.get("type"));
		ArrayList<String> al = new ArrayList<String>();
		if(!(req.get("type").equals("text"))) {
			String str = req.get("choices");
			String[] parts = str.split(";;");
			for(String part : parts) {
				al.add(part);
			}
		}
		q.setChoices(al);
		q.setAnswer(req.get("answer"));
		q.setOriginal_pts(Integer.parseInt(req.get("original_pts")));
		q.setPoints(q.getOriginal_pts());
		q.setMin_pts(Integer.parseInt(req.get("min_pts")));
		q.setDec_value(Integer.parseInt(req.get("dec_value")));
		
		JSONObject ret = new JSONObject();
		ret.put("result", "success");
		return ResponseEntity.status(200).body(ret);
	}
	
	@PostMapping("/edit_question")
	ResponseEntity<JSONObject> editquestion(@RequestBody HashMap<String,String> req){
		JSONObject ret = new JSONObject();
		Question temp;
		try {
			temp = qr.findById(Integer.parseInt(req.get("id"))).get();
		}
		catch(NoSuchElementException e) {
			ret.put("result", "question not found");
			return ResponseEntity.status(404).body(ret);
		}
		temp.setTitle(req.get("title"));
		temp.setBody(req.get("body"));
		temp.setType(req.get("type"));
		ArrayList<String> al = new ArrayList<String>();
		if(!(req.get("type").equals("text"))) {
			String str = req.get("choices");
			String[] parts = str.split(";;");
			for(String part : parts) {
				al.add(part);
			}
		}
		temp.setChoices(al);
		temp.setAnswer(req.get("answer"));
		temp.setOriginal_pts(Integer.parseInt(req.get("original_pts")));
		temp.setPoints(temp.getOriginal_pts());
		temp.setMin_pts(Integer.parseInt(req.get("min_pts")));
		temp.setDec_value(Integer.parseInt(req.get("dec_value")));
		
		ret.put("result", "success");
		qr.save(temp);
		
		return ResponseEntity.status(200).body(ret);
	}
	
	@DeleteMapping("/delete_question/{question_id}")
	public ResponseEntity<JSONObject> deletequestion(@PathVariable("question_id") int question_id){
		JSONObject ret = new JSONObject();

		if(qr.findById(question_id).isEmpty()) {
			ret.put("result", "question not found");
			return ResponseEntity.status(404).body(ret);
		}
		qr.deleteById(question_id);
		ret.put("result", "success");
		return ResponseEntity.status(200).body(ret);
	}
	
	@RequestMapping("/archive_question/{question_id}")
	public ResponseEntity<JSONObject> archivequestion(@PathVariable("question_id") int question_id){
		JSONObject ret = new JSONObject();
		if(qr.findById(question_id).isEmpty()) {
			ret.put("result", "question not found");
			return ResponseEntity.status(404).body(ret);
		}
		Question q = qr.findById(question_id).get();
		q.setArchived(true);
		ret.put("result", "success");
		return ResponseEntity.status(200).body(ret);
	}
	
	@RequestMapping("/unarchive_question/{question_id}")
	public ResponseEntity<JSONObject> unarchivequestion(@PathVariable("question_id") int question_id){
		JSONObject ret = new JSONObject();
		if(qr.findById(question_id).isEmpty()) {
			ret.put("result", "question not found");
			return ResponseEntity.status(404).body(ret);
		}
		Question q = qr.findById(question_id).get();
		q.setArchived(false);
		ret.put("result", "success");
		return ResponseEntity.status(200).body(ret);
	}
}
