package cn.tedu.dao;

import org.apache.ibatis.annotations.Select;

public interface UserDao {
    /**
     * @Select 定义了myBatis执行hello方法处理查询语句
     * myBatis会自动利用JDBC执行SQL语句 select ‘Hello MyBatis!’
     * 自动的将返回值作为hello() 方法的返回值
     * 如果执行 hello() 方法 返回了‘ Hello MyBatis!’ 就说明MyBatis能将接口方法和
     * SQL自动绑定 自动执行JDBC
     * 自动将SQL结果作为方法的返回值
     * @return  SQL 语句的结果
     */

    @Select("select 'Hello MyBatis!'")
    String hello();



}
