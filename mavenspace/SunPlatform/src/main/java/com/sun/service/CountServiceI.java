package com.sun.service;

import java.util.List;

import com.sun.entity.CountEntity;

public interface CountServiceI {
	List<CountEntity> getBooking(CountEntity ce);
	List<CountEntity> getAutoPay(CountEntity ce);
	List<CountEntity> getBookingTerminal(CountEntity ce);
	List<CountEntity> getAutoPayTerminal(CountEntity ce);
}
