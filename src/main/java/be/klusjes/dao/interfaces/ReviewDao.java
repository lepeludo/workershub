package be.klusjes.dao.interfaces;

import java.util.List;

import be.klusjes.entities.Reviews;

public interface ReviewDao {
	
	
	void create(Reviews review);
	List <Reviews>  readByWorkerId (Long workerId);
	List< Reviews>  readByCustomerId (Long customerId);
	Reviews read(Long id);
	void update(Reviews review);
	List<Reviews> findUnreadReportedReviews();
	void deleteReview(Long id);
}
