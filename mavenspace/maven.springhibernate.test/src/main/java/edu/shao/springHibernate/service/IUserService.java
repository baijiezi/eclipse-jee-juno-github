package edu.shao.springHibernate.service;

public interface IUserService {
    void saveUser();
    void saveUserThrowException() throws Exception;
    void findUsers();
}