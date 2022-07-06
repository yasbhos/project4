package ir.ac.kntu.db;

import ir.ac.kntu.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class UserDB {
    private ArrayList<User> users;

    public UserDB() {
        loadUsersInfo();
        users.sort(Comparator.comparing(User::getHighScore));
    }

    public void addUser(User user) {
        users.add(user);
        saveUsersInfo();
    }

    public int getSize() {
        return users.size();
    }

    public User getUserByIndex(int index) {
        return users.get(index);
    }

    public User getUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
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
