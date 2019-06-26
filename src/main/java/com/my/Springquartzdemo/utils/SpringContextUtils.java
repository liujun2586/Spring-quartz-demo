/**
 * Copyright 2018 智野 http://www.zhiye.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.my.Springquartzdemo.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Spring Context 工具类
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
	public static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextUtils.applicationContext = applicationContext;
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
	public static  <T> T  getBean(Class<T> tClass) {
		return applicationContext.getBean(tClass);
	}

	public static <T> T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}

	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	public static boolean isSingleton(String name) {
		return applicationContext.isSingleton(name);
	}

	public static Class<? extends Object> getType(String name) {
		return applicationContext.getType(name);
	}


	// 国际化使用
	public static String getMessage(String key) {
		return applicationContext.getMessage(key, null, Locale.getDefault());
	}
	/// 获取当前环境
	public static String getActiveProfile() {
		return applicationContext.getEnvironment().getActiveProfiles()[0];
	}

}