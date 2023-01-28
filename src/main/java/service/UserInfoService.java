package service;

import model.UserModel;
import repository.UsersRepository;

public class UserInfoService {
    UsersRepository usersRepository = new UsersRepository();

    public UserModel getUserInfo(String email){
        return usersRepository.getUserInfoByEmail(email);
    }
}
