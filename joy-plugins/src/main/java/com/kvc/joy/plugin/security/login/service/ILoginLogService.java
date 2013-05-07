package com.kvc.joy.plugin.security.login.service;

import com.kvc.joy.plugin.security.login.model.po.TLoginLog;
import com.kvc.joy.plugin.security.login.support.vo.LoginVo;

/**
 * 登陆日志服务接口
 * 
 * @author 唐玮琳
 * @time 2012-6-17 下午9:02:45
 */
public interface ILoginLogService {

	/**
	 * 登陆时记录日志
	 * 
	 * @param loginVo 登陆VO
	 * @return 登陆日志PO
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月1日 下午5:23:51
	 */
	TLoginLog logOnLogin(LoginVo loginVo);
	
	/**
	 * 统计某帐号一段时间内密码错误次数
	 * 
	 * @param account 用户帐号
	 * @param fromTime 统计时间起
	 * @param toTime 统计时间止
	 * @return 密码错误次数
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年9月30日 上午12:20:20
	 */
	long statPasswordErrorCount(String account, String fromTime, String toTime);
	
	/**
	 * 是否需要用户输入验证码
	 * 
	 * @param account 用户帐号
	 * @return true: 需要验证码, false: 不需要验证码
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年9月30日 上午12:22:41
	 */
	boolean shouldCaptchaRequire(String account);
	
	/**
	 * 取得当前日志的前一条登陆成功的日志
	 * 
	 * @param curLogId 当前日志id
	 * @return 前一条登陆成功的日志
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月15日 上午10:48:58
	 */
	TLoginLog getPreLoginSuccessLog(String curLogId);
}
