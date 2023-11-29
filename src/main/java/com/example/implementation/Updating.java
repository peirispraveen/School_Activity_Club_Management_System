package com.example.implementation;

import javafx.event.ActionEvent;

import java.io.IOException;

public abstract class Updating {
    public abstract void getList();

    public abstract Club getDetails(ActionEvent e) throws IOException;
}
