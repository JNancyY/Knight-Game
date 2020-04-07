package ui;

import model.Hero;

import javax.swing.*;

public class Dialogue {

    //EFFECT: display opening dialogue and use user's choice to decide on path
    public Dialogue() {
        String choice = "";
        Hero.userName = JOptionPane.showInputDialog("Hero, what is your name?");
        JOptionPane.showMessageDialog(null,
                "Please help us defeat the evil overlord, " + Hero.userName + "!");
        choice = JOptionPane.showInputDialog("Will you accept this quest? (yes or no)");
//        System.out.println("Hero, what is your name?");
//        Hero.userName = input.next();
//        System.out.println("Please help us defeat the evil overlord, " + Hero.userName + "!");
//        System.out.println("Will you accept this quest? (yes or no)");
//        choice = input.next();

        if (!choice.equals("yes")) {
            do {
                JOptionPane.showMessageDialog(null,
                        "Please reconsider " + Hero.userName + ", you are our only hope!");
                choice = JOptionPane.showInputDialog("Will you accept this quest? (yes or no)");
//                System.out.println("Please reconsider " + Hero.userName + ", you are our only hope!");
//                System.out.println("Will you accept this quest? (yes or no) ");
//                choice = input.next();

            } while (!choice.equals("yes"));
        }

        JOptionPane.showMessageDialog(null,
                "Thank you " + Hero.userName + ", we are forever grateful to you! ");
        JOptionPane.showMessageDialog(null,
                "Start Fight");
//        System.out.println("Thank you " + Hero.userName + ", we are forever grateful to you! ");
//        System.out.println("Start Fight");
    }
}
