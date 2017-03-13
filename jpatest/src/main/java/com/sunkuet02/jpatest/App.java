package com.sunkuet02.jpatest;

import com.sunkuet02.jpatest.dao.UserDao;
import com.sunkuet02.jpatest.dao.UserDaoImpl;
import com.sunkuet02.jpatest.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by sun on 3/13/17.
 */
public class App {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();

        userDao.addUser(new User("tamim"));
        userDao.addUser(new User("shibli"));

        List<User> users = userDao.findAllUser();
        for(User user : users) {
            System.out.println(user.toString());
        }

        User user = userDao.findUserByName("sun");
        if(user == null) {
            System.out.println("***************** NO DATA");
        }
        else {
            System.out.println(user.toString());
        }
    }
}
