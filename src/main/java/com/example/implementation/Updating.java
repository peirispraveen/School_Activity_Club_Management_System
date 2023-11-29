package com.example.implementation;

import javafx.event.ActionEvent;

import java.io.IOException;

public abstract class Updating {
    // abstract class to use in both profile and members
    public abstract void getList();

    public abstract Club getDetails(ActionEvent e) throws IOException;
}
