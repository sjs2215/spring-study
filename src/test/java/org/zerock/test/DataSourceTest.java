package org.zerock.test;

import java.sql.Connection;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class DataSourceTest {
	
	//@Inject 애노테이션 처리된 DataSource는 스프링이 생성해서 '주입'해주므로 개발자가 객체 생성 혹은 다른 작업 하지 않아도됨.
	@Inject
	private DataSource ds;
	
	
	@Test
	public void testConnection() throws Exception{
		
		try(Connection con = ds.getConnection()) {
			
			System.out.println(con);
			 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
