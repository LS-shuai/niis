package com.tools.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

/**
 * 理财人信息实体类
 * 
 * @author liushuai
 *
 */
public class InvestorMatchDetail {

	private Integer investorapplyid;// 理财工单ID
	private Long matchdetailid;// 匹配明细表工单ID
	private String channelinvestorappid;// 理财端工单ID
	private Integer loaneeapplyid; // 借款工单号
	private BigDecimal matchamount; // 匹配金额
	private String mobile; // 手机号
	private String realname; // 真实姓名
	private String idcardno; // 身份证号

	public Integer getInvestorapplyid() {
		return investorapplyid;
	}

	public void setInvestorapplyid(Integer investorapplyid) {
		this.investorapplyid = investorapplyid;
	}

	public Long getMatchdetailid() {
		return matchdetailid;
	}

	public void setMatchdetailid(Long long1) {
		this.matchdetailid = long1;
	}

	public String getChannelinvestorappid() {
		return channelinvestorappid;
	}

	public void setChannelinvestorappid(String string) {
		this.channelinvestorappid = string;
	}

	public Integer getLoaneeapplyid() {
		return loaneeapplyid;
	}

	public void setLoaneeapplyid(Integer loaneeapplyid) {
		this.loaneeapplyid = loaneeapplyid;
	}

	public BigDecimal getMatchamount() {
		return matchamount;
	}

	public void setMatchamount(BigDecimal matchamount) {
		this.matchamount = matchamount;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((investorapplyid == null) ? 0 : investorapplyid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvestorMatchDetail other = (InvestorMatchDetail) obj;
		if (investorapplyid == null) {
			if (other.investorapplyid != null)
				return false;
		} else if (!investorapplyid.equals(other.investorapplyid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return loaneeapplyid + "|" + matchamount + "|" + (mobile == null ? "" : mobile) + "|"
				+ (realname == null ? "" : realname) + "|" + (idcardno == null ? "" : idcardno)+"|" + (channelinvestorappid== null ? "" :channelinvestorappid) + "|" + matchdetailid+ "\n";
	}
}
