package com.sun309.gateway.thread.type;

import java.util.ArrayList;

import com.sun309.gateway.dao.OperatorDao;
import com.sun309.gateway.dto.Operator;
import com.sun309.gateway.factory.OperatorDaoFactory;

/**
 * Mas = MAS机
 * Sp1 = SP1
 * 
 * @author caiyuerui
 *
 */
public class SendPortMapping
{
	public static String getMapping(String operName)
	{
		OperatorDao o = OperatorDaoFactory.create();
		ArrayList<Operator> list = o.findOperatorByCondition(new StringBuffer("oper_name='").append(operName).append("'").toString());
		return list.get(0).getAccess();
	}
}
