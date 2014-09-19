package be.klusjes.service;

import java.util.List;

import be.klusjes.entities.Reviews;

public interface ReviewService {

	void create(Reviews review);
	 List<Reviews> readByWorkerId (Long workerId);
	 List<Reviews> readByCustomerId (Long CustomerId);
	Reviews read(Long idReview);
	void update(Reviews review);
	List<Reviews> findUnreadReportedReviews();
	void deleteReview(Long id);
}
