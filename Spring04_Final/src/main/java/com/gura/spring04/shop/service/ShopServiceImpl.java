package com.gura.spring04.shop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.shop.dao.OrderDao;
import com.gura.spring04.shop.dao.ShopDao;
import com.gura.spring04.shop.dto.ShopDto;

@Service
public class ShopServiceImpl implements ShopService{
	
	@Autowired private ShopDao shopDao;
	@Autowired private OrderDao orderDao;
	
	@Override
	public void getList(ModelAndView mView) {
		//상품 목록
		List<ShopDto> list=shopDao.getList();
		//ModelAndView  객체에 list 라는 키값으로 담는다.
		mView.addObject("list", list);
	}

	@Override
	public void buy(HttpServletRequest request, ModelAndView mView) {
		// TODO Auto-generated method stub
		
	}

}
