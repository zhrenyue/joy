package com.kvc.joy.core.init.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.commons.support.ICommand;
import com.kvc.joy.core.init.service.IJoyInitializer;
import com.kvc.joy.core.init.service.ISystemInitService;
import com.kvc.joy.core.spring.utils.SpringBeanUtils;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-15 下午11:37:01
 */
public class JoyInitializer implements IJoyInitializer {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void beforeContextInit(ICommand command) {
		logger.info("beforeContextInit...");
		if (command != null) {
			command.execute();
		}
		logger.info("done beforeContextInit");
	}

	@Override
	public void initContext(ICommand command) {
		logger.info("initContext...");
		if (command != null) {
			command.execute();
		}
		logger.info("done initContext");
	}

	@Override
	public void afterContextInit(ICommand command) {
		logger.info("afterContextInit...");
		if (command != null) {
			command.execute();
		}

		ISystemInitService compStartupService = (ISystemInitService) SpringBeanUtils.getBean("joyPluginsInitializer");
		compStartupService.init();

		ISystemInitService sysInitService = (ISystemInitService) SpringBeanUtils.getBean("systemInitializer");
		sysInitService.init();

		logger.info("done afterContextInit");
	}

	@Override
	public void onContextDestroyed(ICommand command) {
		logger.info("onContextDestroyed...");
		if (command != null) {
			command.execute();
		}
		logger.info("done onContextDestroyed");
	}

}
