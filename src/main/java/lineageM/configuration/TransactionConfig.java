package lineageM.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement// annotation기반 트랜잭션 활성화
@Configuration
public class TransactionConfig {
	@Autowired
	private ApplicationContext applicationContext; //어플리케이션이 동작되는 동안 필요한 클래스 모아두는 곳 (데이터 누적)
	@Autowired
	DataSource dataSource;//굳이 셋팅하지 않아도 properties 데이터를 읽어온다.
	
	@Bean
	public PlatformTransactionManager transactionManager() throws Exception{
		System.out.println("dataSource: "+dataSource);
		return new DataSourceTransactionManager(dataSource);
	}
	
	/*Mybatis 사용시 설정*/
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
	    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	    factoryBean.setDataSource( dataSource );
	    factoryBean.setVfs(SpringBootVFS.class);
	    // mapper설정 ->resource부분에 매퍼 저장
	    // namespace 일치시켜야 함
	    Resource[] mapperLocations= 
	    		applicationContext.getResources("classpath:/mapper/**/mapper-*.xml"); //resource를 가져올 수 있도록
	    // /mapper/**/ : mapper폴더 아래 모든 폴더를 지칭
	    // mapper-*.xml :  mapper-로 시작하고 확장자가 xml인 모든 파일을 지칭
	    factoryBean.setMapperLocations(mapperLocations);
	    return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		System.out.println("sqlSessionFactory: "+sqlSessionFactory());
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	
	
}
