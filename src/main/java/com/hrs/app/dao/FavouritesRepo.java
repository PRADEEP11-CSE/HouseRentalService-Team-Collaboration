package com.hrs.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrs.app.model.Favourite;


@Repository
public interface FavouritesRepo extends JpaRepository<Favourite, Long> {


	@Query( value = "select * from favourites where user_id = :id", nativeQuery = true)
	List<Favourite> findUserFavs(@Param("id") Long id);

}
