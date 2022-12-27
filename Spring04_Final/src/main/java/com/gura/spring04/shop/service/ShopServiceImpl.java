package com.gura.spring04.shop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.shop.dao.OrderDao;
import com.gura.spring04.shop.dao.ShopDao;
import com.gura.spring04.shop.dto.OrderDto;
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
		//구입자의 아이디
		String id=(String)request.getSession().getAttribute("id");
		//1. 파라미터로 전달되는 구입할 상품 번호
		int num=Integer.parseInt(request.getParameter("num"));
		//2. 상품의 가격을 얻어온다.
		int price=shopDao.getPrice(num);
		//3. 상품의 가격만큼 계좌 잔액을 줄인다.
		ShopDto dto=new ShopDto();
		dto.setId(id);
		dto.setPrice(price);
		shopDao.minusMoney(dto);
		//4. 가격의 10% 를 포인트로 적립한다.
		shopDao.plusPoint(dto);
		//5. 재고의 갯수를 1 줄인다.
		shopDao.minusCount(num);
		//6. 주문 테이블(배송) 에 정보를 추가 한다. 
		OrderDto dto2=new OrderDto();
		dto2.setId(id); //누가
		dto2.setCode(num); //어떤 상품을 
		//클라이언트가 입력한 배송 주소라고 가정 
		String addr="서울시 강남구 삼원타워";
		dto2.setAddr(addr);//어디로 배송할지
		orderDao.addOrder(dto2);
	}

}












