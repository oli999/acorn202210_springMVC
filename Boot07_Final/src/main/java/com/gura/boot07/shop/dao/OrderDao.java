package com.gura.boot07.shop.dao;

import com.gura.boot07.shop.dto.OrderDto;

public interface OrderDao {
	//배송 정보를 추가 하는 메소드
	public void addOrder(OrderDto dto);
}
