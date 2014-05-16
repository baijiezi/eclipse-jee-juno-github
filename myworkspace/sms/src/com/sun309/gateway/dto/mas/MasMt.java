package com.sun309.gateway.dto.mas;

import java.sql.Timestamp;

public class MasMt
{

	private Long autoSn;

	private Long smId;

	private Long srcId;

	private String mobiles;

	private String content;

	private Byte isWap;

	private String url;

	private Timestamp sendTime;

	private Byte smType;

	private Integer msgFmt;

	private Byte tpPid;

	private Byte tpUdhi;

	private String feeTerminalId;

	private String feeType;

	private String feeCode;

	private Integer feeUserType;

	public Long getAutoSn()
	{
		return autoSn;
	}

	public void setAutoSn(Long autoSn)
	{
		this.autoSn = autoSn;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getFeeCode()
	{
		return feeCode;
	}

	public void setFeeCode(String feeCode)
	{
		this.feeCode = feeCode;
	}

	public String getFeeTerminalId()
	{
		return feeTerminalId;
	}

	public void setFeeTerminalId(String feeTerminalId)
	{
		this.feeTerminalId = feeTerminalId;
	}

	public String getFeeType()
	{
		return feeType;
	}

	public void setFeeType(String feeType)
	{
		this.feeType = feeType;
	}

	public Integer getFeeUserType()
	{
		return feeUserType;
	}

	public void setFeeUserType(Integer feeUserType)
	{
		this.feeUserType = feeUserType;
	}

	public Byte getIsWap()
	{
		return isWap;
	}

	public void setIsWap(Byte isWap)
	{
		this.isWap = isWap;
	}

	public String getMobiles()
	{
		return mobiles;
	}

	public void setMobiles(String mobiles)
	{
		this.mobiles = mobiles;
	}

	public Integer getMsgFmt()
	{
		return msgFmt;
	}

	public void setMsgFmt(Integer msgFmt)
	{
		this.msgFmt = msgFmt;
	}


	public Timestamp getSendTime()
	{
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime)
	{
		this.sendTime = sendTime;
	}

	public Long getSmId()
	{
		return smId;
	}

	public void setSmId(Long smId)
	{
		this.smId = smId;
	}

	public Byte getSmType()
	{
		return smType;
	}

	public void setSmType(Byte smType)
	{
		this.smType = smType;
	}

	public Long getSrcId()
	{
		return srcId;
	}

	public void setSrcId(Long srcId)
	{
		this.srcId = srcId;
	}

	public Byte getTpPid()
	{
		return tpPid;
	}

	public void setTpPid(Byte tpPid)
	{
		this.tpPid = tpPid;
	}

	public Byte getTpUdhi()
	{
		return tpUdhi;
	}

	public void setTpUdhi(Byte tpUdhi)
	{
		this.tpUdhi = tpUdhi;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}
	
	
}
