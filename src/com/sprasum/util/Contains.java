package com.sprasum.util;

import com.sprasum.domain.User;

import java.util.ArrayList;

public class Contains {

    ArrayList<User> allUser = new ArrayList<>();

    public boolean contains(User userInput) {
        for (int i = 0; i < allUser.size(); i++) {
            User rightUser = allUser.get(i);
            if (rightUser.getUsername().equals(userInput.getUsername()) && rightUser.getPassword().equals(userInput.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
