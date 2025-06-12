package com.devtaskpro;

import com.devtaskpro.view.CommandLineInterface;

public class DevTaskPro {
    public static void main(String[] args) {
        System.out.println("Welcome to DevTask Pro!");
        System.out.println("A professional task management system for developers.");
        
        CommandLineInterface cli = new CommandLineInterface();
        cli.start();
    }
} 