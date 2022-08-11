package com.c2_g4_sfh_main.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.c2_g4_sfh_main.entity.User;
import com.c2_g4_sfh_main.entity.WishList;
import com.c2_g4_sfh_main.repository.IWishListRepository;
import com.c2_g4_sfh_main.repository.WishListCustomRepository;

@Service
@Transactional
public class WishListServiceImpl {

	private final IWishListRepository wishListRepository;

	@Autowired
	private WishListCustomRepository wishListCustomRepository;

	public WishListServiceImpl(IWishListRepository wishListRepository) {
		this.wishListRepository = wishListRepository;
	}

	public WishList createWishlist(WishList wishList) {
		return wishListRepository.save(wishList);
	}

	public List<WishList> readWishList(Long userId) {
		return null;
	}

	public Boolean deleteWishlist(User user, String productId) {
		return wishListCustomRepository.deleteWishlist(user, productId);
	}

	public Page<WishList> findByBuyerEmail(Long id, PageRequest request) {
		return wishListRepository.findAllByUserId(id, request);
	}
}
