package com.worldcup.bolaoworldcup.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.worldcup.bolaoworldcup.model.BetUserGameKey;
import com.worldcup.bolaoworldcup.model.BetUserGamePoints;
import com.worldcup.bolaoworldcup.view.UserPointsView;


@CrossOrigin("http://localhost:4200")
public interface BetUserGamePointsRepository extends JpaRepository<BetUserGamePoints,BetUserGameKey> {


	List<BetUserGamePoints> findAllByIdUserId(Long userId);
	List<BetUserGamePoints> findAllByIdGameId(Long userId);
	
	
	public static final String SUM_POINT_BY_USER = "select  SUM(points), us.id ,us.name from bet_user_game_points bet left join usuarios us on bet.user_id = us.id group by us.id";

	@Query(value = SUM_POINT_BY_USER, nativeQuery = true)
	public List<Object[]> sumPointAllUser();
}
