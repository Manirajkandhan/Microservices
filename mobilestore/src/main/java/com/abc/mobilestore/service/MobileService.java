package com.abc.mobilestore.service;

import java.util.List;

import com.abc.mobilestore.entity.Mobile;

public interface MobileService {
	
	Mobile saveMobile(Mobile mobile);
	
	Mobile getMobileById(int mobileId);
	
	List<Mobile> getAllMobiles();

	Mobile updateMobile(Mobile mobile);
	
	void deleteMobile(int mobileId);
}
