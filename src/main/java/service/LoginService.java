package service;

import repository.UsersRepository;

public class LoginService {
    public boolean checkLogin(String account, String password){
        UsersRepository usersRepository = new UsersRepository();

        return usersRepository.countAccountByEmailAndPassword(account,password);
    }
}
