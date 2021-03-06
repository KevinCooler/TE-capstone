package com.techelevator.model.JDBCDAOs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.DAOs.ReviewDAO;
import com.techelevator.model.Objects.Review;

@Component
public class JDBCReviewDAO implements ReviewDAO{
	
	private JdbcTemplate temp;
	
	@Autowired
	public JDBCReviewDAO(DataSource dataSource) {
		this.temp = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Review> getReviewList(long coachId) {
		List<Review> reviews = new ArrayList<Review>();
		String sqlStatement = "SELECT * FROM coach_reviews WHERE coach_id=? "
				+ "ORDER BY create_date DESC;";
		
		SqlRowSet results = temp.queryForRowSet(sqlStatement, coachId);
		
		while(results.next())
			reviews.add(mapRowToReview(results));

		return reviews;
	}

	private Review mapRowToReview(SqlRowSet results) {
		Review review = new Review();
		
		review.setId(results.getLong("id"));
		review.setCoachId(results.getLong("coach_id"));
		review.setClientId(results.getLong("client_id"));
		review.setRating(results.getInt("rating"));
		review.setReviewText(results.getString("review_text"));
		LocalDateTime time = results.getTimestamp("create_date").toLocalDateTime();
		review.setCreateDate(time);
		results.getTimestamp("edit_date");
		if(!results.wasNull()) {
			time = results.getTimestamp("edit_date").toLocalDateTime();
			review.setEditDate(time);
		}
		
		return review;
	}

	@Override
	public long addReview(long coachId, long clientId, int review, String reviewText) {
		String sqlStatement = "INSERT INTO coach_reviews "
				+ "(coach_id, client_id, review_text, rating, create_date) "
				+ "VALUES(?, ?, ?, ?, NOW()) returning id;";
		
		return temp.queryForObject(sqlStatement, Long.class, coachId, clientId, reviewText, review);
	}
	
	@Override
	public void editReview(long reviewId, long clientId, int rating, String reviewText) {
		String sqlStatement = "UPDATE coach_reviews "
				+ "SET review_text = ?,  rating = ?, edit_date = NOW() "
				+ "WHERE id = ? AND client_id = ?;";
		
		temp.update(sqlStatement, reviewText, rating, reviewId, clientId);
	}

	@Override
	public void removeReview(long reviewId) {
		String sqlStatement = "DELETE FROM coach_reviews WHERE id=?;";
		
		temp.update(sqlStatement, reviewId);
	}

	@Override
	public void removeReviewsByCoachId(long coachId) {
		String sqlStatement = "DELETE FROM coach_reviews WHERE coach_id=?;";
		
		temp.update(sqlStatement, coachId);
	}
}