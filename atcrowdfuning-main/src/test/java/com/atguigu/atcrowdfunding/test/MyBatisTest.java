package com.atguigu.atcrowdfunding.test;

import com.atguigu.atcrowdfuning.bean.TAdmin;
import com.atguigu.atcrowdfuning.bean.TAdminExample;
import com.atguigu.atcrowdfuning.mapper.TAdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author erdong
 * @create 2019-09-09 9:22
 */
// 使用Spring单元测试驱动普通的测试方法
@RunWith(value = SpringJUnit4ClassRunner.class)
// 加载Spring配置文件
@ContextConfiguration(locations = {
        "classpath:spring/spring-bean.xml",
        "classpath:spring/spring-mybatis.xml",
        "classpath:spring/spring-tx.xml"
})
public class MyBatisTest {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TAdminMapper tAdminMapper;

    @Test
    public void test() {
        logger.debug("开始测试，时间：{}", System.currentTimeMillis());
        TAdminExample tAdminExample = new TAdminExample();
        TAdminExample.Criteria zhangsan = tAdminExample.createCriteria().andLoginacctEqualTo("zhangsan").andUserpswdEqualTo("123456");
        System.out.println(zhangsan);
        logger.info("即将查询数据库");
        List<TAdmin> list = tAdminMapper.selectByExample(tAdminExample);
        logger.warn("查询数据库完毕,结果：{} , 结束时间：{}", list, System.currentTimeMillis());
        logger.error("hehe");

    }

    @Test
    public void testPasswordEncoder(){
        CharSequence rawPassword = "123456";

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode(rawPassword);
        boolean isMatch = encoder.matches(rawPassword, encodePasswd);
        System.out.println("encodePasswd:" + encodePasswd);
        System.out.println(isMatch);
    }

}
