package com.sun309.gateway.impl;

import com.sun309.gateway.dao.MasMtDao;
import com.sun309.gateway.dto.mas.MasMt;
import com.sun309.gateway.jdbc.MasDao;

public class MasMtDaoImpl extends MasDao implements MasMtDao
{
//	private LogService log = LogFactory.create(LogFactory.SEND_DATA, MasMtDaoImpl.class);
	public MasMt insert(String mobile, String content, long smId, long srcId, String interfaceName, Integer needCallBack)
	{
		MasMt masMt = new MasMt();
		masMt.setContent(content);
		masMt.setFeeCode(" ");
		masMt.setFeeTerminalId(" ");
		masMt.setFeeType(" ");
		masMt.setFeeUserType(0);
		masMt.setIsWap((byte) 0);
		masMt.setMobiles(mobile);
		masMt.setMsgFmt(0);
		masMt.setSmId(new Long(smId));
		masMt.setSmType((byte) 0);
		masMt.setSrcId((new Long(srcId)));
		masMt.setSendTime(null);
		masMt.setTpPid((byte) 0);
		masMt.setTpUdhi((byte) 0);
		masMt.setUrl(" ");
		StringBuffer sql = new StringBuffer();
		sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("api_mt_");
		sql.append(interfaceName);
		sql.append("(");
		sql.append("SM_ID, "); // 回执ID
		sql.append("SRC_ID, "); // 回复ID
		sql.append("MOBILES, ");
		sql.append("CONTENT, ");
		sql.append("IS_WAP, ");
		sql.append("URL, ");
		sql.append("SEND_TIME, ");
		sql.append("SM_TYPE, ");
		sql.append("MSG_FMT, ");
		sql.append("TP_PID, ");
		sql.append("TP_UDHI, ");
		sql.append("FEE_TERMINAL_ID, ");
		sql.append("FEE_TYPE, ");
		sql.append("FEE_CODE, ");
		sql.append("FEE_USER_TYPE");
		sql.append(")");
		sql.append(" VALUES ");
		sql.append("(");
		sql.append("'");
		sql.append(masMt.getSmId());
		sql.append("', ");
		sql.append("'");
		
		if(needCallBack!=null && needCallBack.intValue() == 1)
		{
			sql.append(masMt.getSrcId());
		}
		else
		{
			sql.append(0);
		}
		
		sql.append("', ");
		sql.append("'");
		sql.append(masMt.getMobiles());
		sql.append("', ");
		sql.append("'");
		sql.append(masMt.getContent());
		sql.append("', ");
		sql.append("'");
		sql.append(masMt.getIsWap());
		sql.append("', ");
		sql.append("'");
		sql.append(masMt.getUrl());
		sql.append("', ");
		sql.append(masMt.getSendTime());
		sql.append(", ");
		sql.append("'");
		sql.append(masMt.getSmType());
		sql.append("', ");
		sql.append("'");
		sql.append(masMt.getMsgFmt());
		sql.append("', ");
		sql.append("'");
		sql.append(masMt.getTpPid());
		sql.append("', ");
		sql.append("'");
		sql.append(masMt.getTpUdhi());
		sql.append("', ");
		sql.append("'");
		sql.append(masMt.getFeeTerminalId());
		sql.append("', ");
		sql.append("'");
		sql.append(masMt.getFeeType());
		sql.append("', ");
		sql.append("'");
		sql.append(masMt.getFeeCode());
		sql.append("', ");
		sql.append("'");
		sql.append(masMt.getFeeUserType());
		sql.append("' ");
		sql.append(")");
		long id = this.insert(sql.toString());
		masMt.setAutoSn(id);
		return masMt;
	}
}
