
import cn.ssm.entity.Account;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import groovy.util.logging.Log4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.xmlgraphics.io.Resource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ssm.dao.UserMapper;
import ssm.service.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test1 {

    @Autowired
    private UserService userService;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void test1() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/ssm");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
        DruidPooledConnection druidPooledConnection = druidDataSource.getConnection();
        System.out.println(druidPooledConnection);
    }

    @Test
    public void test2() throws ClassNotFoundException {
        Account account = new Account();
        Class class1 = account.getClass();

        Class class2 = Account.class;

        Class class3 = Class.forName("entity.Account");
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        System.out.println();
    }

    @Test
    public void tes2() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("spring-mybatis.xml");
        System.out.println(inputStream);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        System.out.println(mapper);
    }

    @Test
    public void test3() {
        System.out.println(3.0/0);
        int []a = new int[5];
        int []b = new int[4];
//        b = Arrays.
    }

    public double getNum(float d) {
        return 3.0f;
    }
}
