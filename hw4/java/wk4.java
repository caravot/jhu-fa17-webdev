// Carrie Ravotta, cravott1@jhu.edu
// Fall 2017
// JHU EN.605.481 Principles of Enterprise Web Dev
// Homework #4

import java.util.*;

public class wk4 {
    public static void main(String[] args) {
        // Create 2 Destroyers
        Destroyer destroyerOne = new Destroyer();
        destroyerOne.setNumberMissile(12);
        destroyerOne.setName("Destroyer 1");

        Destroyer destroyerTwo = new Destroyer();

        // Create 2 Submarines
        Submarine submarineOne = new Submarine();
        Submarine submarineTwo = new Submarine();

        // Create 2 P3s
        PS3 ps3One = new PS3();
        PS3 ps3Two = new PS3();

        // Make a collection of Destroyers (you select the type of Collection)
        ArrayList<Destroyer> destroyerList = new ArrayList<Destroyer>();
        destroyerList.add(destroyerOne);
        destroyerList.add(destroyerTwo);

        // Make a collection of Submarines (you select the type)
        ArrayList<Submarine> submarineList = new ArrayList<Submarine>();
        submarineList.add(submarineOne);
        submarineList.add(submarineTwo);

        // Make a collection that holds all Ships
        ArrayList<Ship> shipList = new ArrayList<Ship>();
        shipList.add(destroyerOne);
        shipList.add(destroyerTwo);
        shipList.add(submarineOne);
        shipList.add(submarineTwo);

        // Make a collection that holds all Contacts
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        contactList.add(destroyerOne);
        contactList.add(destroyerTwo);
        contactList.add(submarineOne);
        contactList.add(submarineTwo);
        contactList.add(ps3One);
        contactList.add(ps3Two);

        // Print out the list of Contacts to System.out.println().
        // You should override the toString() method
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println(contactList.get(i).toString());
        }

        // Note: None of your methods should let the user set the number of items to a negative number.

        // You can choose what the constructors set these parameters to in your classes
        // (they can be defaults or you can provide them). Typically, you try to exercise your
        // code as much as possible in a test case so try a bit of each.

        // Choose what values you wish to assign to the test cases.

        // Exercise execption handling case for the submarine by setting the number of torpedoes in one of your
        // submarine classes to the string "Foo".
    }
}
