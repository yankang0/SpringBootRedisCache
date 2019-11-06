package com.baizhi.dao;

import com.baizhi.entity.User;

import java.util.List;

public interface UserDAO {
    /*
    * 查询所有
    * */
    public List<User> selectAll();
    /*
    * 通过ID查询
    * */
    public User selectByID(String id);
    /*
    * 删除
    * */
    public void delete(String id);
}
