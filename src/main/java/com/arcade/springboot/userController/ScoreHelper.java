package com.arcade.springboot.userController;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.arcade.springboot.model.Question;
import com.arcade.springboot.model.Users;
import com.arcade.springboot.repositories.QuestionRepository;
import com.arcade.springboot.repositories.UsersRepository;

public class ScoreHelper {
	
	@Autowired
	private QuestionRepository qr;
	
	@Autowired
	private UsersRepository ur;
	
	public int update_question_points(int q_id) {
		Question q = qr.getReferenceById(q_id);
		if(q.isArchived()) return 0;
		int dec_value = q.getDec_value();
		int min_pts = q.getMin_pts();
		int points = q.getPoints();
		int ret_pts = points;
		if(points > min_pts) {
			if((points - dec_value) < min_pts) {
				points = min_pts;
			}else {
				points = points - dec_value;
			}
		q.setPoints(points);
		qr.save(q);
		}
		return ret_pts;
	}
	
	public void update_user_points(int user_id, int points, int question_id) {
		Users u = ur.getReferenceById(user_id);
		u.setPoints(u.getPoints()+points);
		ArrayList<Integer> a = u.getAnswered_questions();
		a.add(question_id);
		u.setAnswered_questions(a);
		ur.save(u);
	}
}
