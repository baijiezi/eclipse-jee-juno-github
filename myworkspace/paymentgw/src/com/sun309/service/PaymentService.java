package com.sun309.service;

import java.util.ArrayList;
import com.sun309.dto.Payment;

public interface PaymentService
{
	public boolean insert(Payment payment);
	public ArrayList<Payment> findPaymentByCondition(String condition);
	public boolean updateStatus(String id, Integer status, boolean autoIncrementTryTime);
	public boolean moveToLog(String paymentId);
}
