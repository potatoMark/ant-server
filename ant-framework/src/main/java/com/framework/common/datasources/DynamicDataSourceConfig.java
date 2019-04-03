package com.framework.common.datasources;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源
 */
@Configuration
public class DynamicDataSourceConfig {


	/**
	 * 分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	@Bean
	@ConfigurationProperties("spring.datasource.druid.master")
	public DataSource master() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties("spring.datasource.druid.slave")
	public DataSource slave() {
		return DruidDataSourceBuilder.create().build();
	}

//	如果还有其他数据源可在此自定义配置(注意druid不是支持所有jdbc驱动，比如PI实时数据库，遇到此问题需要单独写逻辑对应)
//	@Bean
//	@ConfigurationProperties("spring.datasource.druid.slave")
//	public DataSource middle() {
//		return DruidDataSourceBuilder.create().build();
//	}


	@Bean
	@Primary
	public DynamicDataSource dataSource(DataSource master, DataSource slave) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DataSourceNames._MASTER_, master);
		targetDataSources.put(DataSourceNames._MIDDLE_, slave);
		return new DynamicDataSource(master, targetDataSources);
	}

	@Bean("sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource(master(),slave()));
		MybatisConfiguration configuration = new MybatisConfiguration();
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setCacheEnabled(false);
		sqlSessionFactory.setConfiguration(configuration);
		sqlSessionFactory.setPlugins(new Interceptor[]{ paginationInterceptor() });
		return sqlSessionFactory.getObject();
	}

}
