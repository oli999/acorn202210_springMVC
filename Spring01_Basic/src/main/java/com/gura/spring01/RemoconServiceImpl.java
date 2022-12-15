package com.gura.spring01;

import org.springframework.stereotype.Service;

@Service
public class RemoconServiceImpl implements RemoconService{

	@Override
	public void up() {
		System.out.println("체널을 올려요!");
	}

	@Override
	public void down() {
		System.out.println("체널을 내려요!");
	}

}
