package mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {
	
	private static SqlSessionFactory factory;
	
	private MyBatisUtils(){
		
	}
	
	static {
		Reader reader = null;
		
		try {
			reader = Resources.getResourceAsReader("/mybatis/config.xml");
		}catch(IOException e){
			e.printStackTrace();
		}
		factory = new SqlSessionFactoryBuilder().build(reader);
	}
	
	public static SqlSessionFactory getSqlSessionFactory(){
		return factory;
	}

}
