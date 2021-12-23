package ssm.service;

import ssm.entity.User;

public interface UserService {
    void print();
    User login(String account,String password);
}
