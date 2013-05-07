package com.kvc.joy.plugin.monitor.jdbc;

import com.kvc.joy.commons.lang.string.StringTool;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * 
 * @author 唐玮琳
 * @time 2012-12-15 下午7:53:54
 */
public class SqlMonitorFilter extends Filter<ILoggingEvent> {

	private String costTime;
	private String filterTime;
	
	@Override
	public FilterReply decide(ILoggingEvent event) {
		System.out.println("time: "+filterTime);
		if(StringTool.isBlank(filterTime)) {
			return FilterReply.DENY;
		}
		Long time = Long.valueOf(filterTime);
		if(time > 0) {
			
		}
		
		if (event.getMessage() != null && event.getMessage().contains("sample")) {
		      return FilterReply.ACCEPT;
		    } else {
		      return FilterReply.DENY;
		    }
	}
	
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.filterTime = time;
	}

}
