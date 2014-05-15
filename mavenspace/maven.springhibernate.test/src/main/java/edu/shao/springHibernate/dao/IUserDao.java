package edu.shao.springHibernate.dao;

import java.util.List;

import edu.shao.springHibernate.po.User;

public interface IUserDao {
    public void save(User user);
    public List<User> query(String sql,Object[] args);
}