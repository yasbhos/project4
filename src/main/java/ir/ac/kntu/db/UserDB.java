package ir.ac.kntu.db;

import ir.ac.kntu.model.User;

import java.io.*;
import java.util.ArrayList;

public class UserDB {
    private ArrayList<User> users;

    public UserDB() {
        loadUsersInfo();
    }

    public void addUser(User user) {
        users.add(user);
        saveUsersInfo();
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        return users.stream().filter(user -> user.getUsername().equals(username) &&
                user.getPassword().equals(password)).findFirst().orElse(null);
    }

    private void loadUsersInfo() {
        ArrayList<User> users = new ArrayList<>();
        File file = new File("users.info");
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {
            while (true) {
                try {
                    User user = (User) inputStream.readObject();
                    users.add(user);
                } catch (EOFException e) {
                    break;
                } catch (Exception e) {
                    System.out.println("Problem with some of the records in the users data file");
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("No previous data for users has been saved.");
        }

        this.users = users;
    }

    private void saveUsersInfo() {
        File file = new File("users.info");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {
            for (User user : users) {
                try {
                    outputStream.writeObject(user);
                } catch (IOException e) {
                    System.out.println("(UserDB::saveUsersInfo): An error occurred while trying to save info");
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("(UserDB::saveUsersInfo): An error occurred while trying to save info");
            System.out.println(e.getMessage());
        }
    }
}
