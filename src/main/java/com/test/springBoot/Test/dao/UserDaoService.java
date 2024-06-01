package com.test.springBoot.Test.dao;


import com.test.springBoot.Test.entity.User;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<com.test.springBoot.Test.entity.User> users = new ArrayList<>();

    private static Integer userCount = 0;


    static {
        users.add(new User(++userCount, "Admin", LocalDate.now().minusYears(20)));
        users.add(new User(++userCount, "Eugene", LocalDate.now().minusYears(26)));
        users.add(new User(++userCount, "James", LocalDate.now().minusYears(28)));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(@RequestBody User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public User findById(int id){

        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }

    public void deleteUserById(int id) {

        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
