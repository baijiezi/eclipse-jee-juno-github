package edu.shao.springHibernate.dao.impl;

import edu.shao.springHibernate.dao.IUserDao;
import edu.shao.springHibernate.po.User;

public class UserDaoImpl extends AbstractHibernateDao<User> implements IUserDao {

    public UserDaoImpl() {
        super(User.class);
    }

}