package com.summer.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
//Druid是Java语言中最好的数据库连接池，并且能够提供强大的监控和扩展功能。

/**
 * @description:Druid监控数据源
 */
//然后启动项目后访问 http://127.0.0.1:8080/tmall/druid/index.html 即可查看数据源及SQL统计等。
@Configuration //@Configuration标注在类上，相当于把该类作为spring的xml配置文件中的，作用为：配置spring容器(应用上下文)
@PropertySource(value = "classpath:application-dev.yml") //加载指定的属性文件（*.properties）到 Spring 的 Environment 中
public class DruidConfiguration {

	@Bean //@Bean 用在方法上，告诉Spring容器，你可以从下面这个方法中拿到一个Bean
	@ConfigurationProperties(prefix = "spring.datasource") //@Configuration批量注入配置文件中的属性
	public DataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		return druidDataSource;
	}

	/**
	 * 注册一个StatViewServlet
	 * @return
	 */
	@Bean
	//配置Servlet
	public ServletRegistrationBean druidStatViewServlet(){
		//org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");

		//添加初始化参数：initParams
		//白名单：
		servletRegistrationBean.addInitParameter("allow","127.0.0.1");
		//IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
		servletRegistrationBean.addInitParameter("deny","");
		//登录查看信息的账号密码.
		servletRegistrationBean.addInitParameter("loginUsername","admin");
		servletRegistrationBean.addInitParameter("loginPassword","123456");
		//是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable","false");
		return servletRegistrationBean;
	}

	/**
	 * 注册一个：filterRegistrationBean
	 * @return
	 */
	@Bean
	//配置filter
	public FilterRegistrationBean druidStatFilter(){

		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

		//添加过滤规则.
		filterRegistrationBean.addUrlPatterns("/*");

		//添加不需要忽略的格式信息.
		filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
}
