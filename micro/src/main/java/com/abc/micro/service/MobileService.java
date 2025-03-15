package com.abc.micro.service;

import java.util.List;

import com.abc.micro.entity.Mobile;

public interface MobileService {
	Mobile saveMobile(Mobile mobile);
	Mobile getMobileById(int mobileId);
	List<Mobile> getAllMobiles();
	Mobile updateMobile(Mobile mobile);
	void deleteMobile(int mobileId);
}

