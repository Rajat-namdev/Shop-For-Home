package com.c2_g4_sfh_main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c2_g4_sfh_main.entity.WishList;

@Repository
public interface IWishListRepository extends JpaRepository<WishList, Integer> {

	Page<WishList> findAllByUserId(Long id, Pageable pageable);

}
