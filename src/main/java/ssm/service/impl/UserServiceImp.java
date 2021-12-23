package ssm.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.dao.UserMapper;
import ssm.entity.User;
import ssm.service.UserService;


@Service("UserService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper mapper;

    @Override
    public void print() {
        System.out.println(mapper);
        mapper.login();
        System.out.println(userService);
        System.out.println("running------ ");
    }

    @Override
    public User login(String account, String password) {

        return null;
    }

    public static void main(String[] args) {
        UserService userService = new UserServiceImp();
        userService.print();
    }
}
