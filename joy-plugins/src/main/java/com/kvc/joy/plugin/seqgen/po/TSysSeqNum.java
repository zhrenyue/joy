package com.kvc.joy.plugin.seqgen.po;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kvc.joy.commons.bean.IEntity;

/**
 * 序列号信息对象模型
 * 
 * @author <b>唐玮琳</b>
 */
@Entity
@Table(name = "T_SYS_SEQ_NUM")
public class TSysSeqNum implements IEntity<String> {

	/** 当前周期开始时间格式 */
	public static final String TIME_DB_FMT_STR = "yyyyMMddHHmmss";

	private String id;
	private String seqName; // 序列号名称
	private long curSeq; // 当前序列号(不包含前缀、后缀)
	private String curPeriodStartTime; // 当前周期开始时间
	private TSysSeqNumRule seqRule; // 序列号生成规则
	private boolean active; // 有效性
	private String prefixParams; // 前缀模板的参数值串，以逗号分隔
	private String suffixParams; // 后缀模板的参数值串，以逗号分隔

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getPrefixParams() {
		return prefixParams;
	}

	public void setPrefixParams(String prefixParams) {
		this.prefixParams = prefixParams;
	}

	public String getSuffixParams() {
		return suffixParams;
	}

	public void setSuffixParams(String suffixParams) {
		this.suffixParams = suffixParams;
	}

	@Column(length=64)
	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	@Column(length=20)
	public long getCurSeq() {
		return curSeq;
	}

	public void setCurSeq(long curSeq) {
		this.curSeq = curSeq;
	}

	@Column(length = 14)
	public String getCurPeriodStartTime() {
		return curPeriodStartTime;
	}

	public void setCurPeriodStartTime(String curPeriodStartTime) {
		this.curPeriodStartTime = curPeriodStartTime;
	}

	@Column(nullable = false)
	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "RULE_ID") 
	public TSysSeqNumRule getSeqRule() {
		return seqRule;
	}

	public void setSeqRule(TSysSeqNumRule seqRule) {
		this.seqRule = seqRule;
	}

}
