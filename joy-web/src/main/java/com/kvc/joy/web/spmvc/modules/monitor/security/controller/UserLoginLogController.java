package com.kvc.joy.web.spmvc.modules.monitor.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kvc.joy.plugin.security.login.model.po.TLoginLog;
import com.kvc.joy.web.spmvc.core.BaseController;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年11月30日 下午11:29:09
 */
@Controller
@RequestMapping("/userLoginLog")
public class UserLoginLogController extends BaseController<TLoginLog> {

	@Override
	protected String getCurrentViewName() {
		return "joy/core/monitor/security/userLoginLog";
	}

}