package com.atguigu.atcrowdfunding.exception;

/**
 * 自定义异常（新增失败异常  saveAdmin方法）
 * @author erdong
 * @create 2019-09-15 19:45
 */
public class TAdminAcctException extends RuntimeException {
    public TAdminAcctException(String message) {
        super(message);// 将异常信息传给父类，以后通过当前异常的对象调用.getMessage()方法时可以获取到msg信息
    }
}
