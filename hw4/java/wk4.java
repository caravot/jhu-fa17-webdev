// Carrie Ravotta, cravott1@jhu.edu
// Fall 2017
// JHU EN.605.481 Principles of Enterprise Web Dev
// Homework #4

import java.util.*;

public class wk4 {
    public static void main(String[] args) {
        // Create 2 Destroyers
        Destroyer destroyerOne = new Destroyer();
        destroyerOne.setName("Destroyer 1");
        destroyerOne.setSpeed(50);
        destroyerOne.setLength(340);
        destroyerOne.setNumberMissile(12);

        Destroyer destroyerTwo = new Destroyer();
        destroyerTwo.setName("Destroyer 2");
        destroyerTwo.setSpeed(20);
        destroyerTwo.setLength(420);
        destroyerTwo.setNumberMissile(22);

        // Create 2 Submarines
        Submarine submarineOne = new Submarine();
        submarineOne.setName("Submarine 1");
        submarineOne.setSpeed(65);
        submarineOne.setLength(320);
        submarineOne.setNumberTorpedos("4");

        Submarine submarineTwo = new Submarine();
        submarineTwo.setName("Submarine 2");
        submarineTwo.setSpeed(65);
        submarineTwo.setLength(130);
        submarineTwo.setNumberTorpedos(1);

        // Create 2 P3s
        PS3 ps3One = new PS3();
        ps3One.setName("PS3 1");
        ps3One.setNumberEngines(4);
        ps3One.setSpeed("110");
        ps3One.setAltitude(2545);
        ps3One.setLength(85);

        PS3 ps3Two = new PS3();
        ps3Two.setName("PS3 2");
        ps3Two.setNumberEngines(2);
        ps3Two.setSpeed("85");
        ps3Two.setAltitude(1575);
        ps3Two.setLength(52);

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
        System.out.println(
                "\n************************************\n" +
                "\tPrint out the list of Contacts" +
                "\n************************************\n"
        );
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println(contactList.get(i).toString());
        }

        // Note: None of your methods should let the user set the number of items to a negative number.

        // You can choose what the constructors set these parameters to in your classes
        // (they can be defaults or you can provide them). Typically, you try to exercise your
        // code as much as possible in a test case so try a bit of each.

        // Choose what values you wish to assign to the test cases.

        // Exercise exception handling case for the submarine by setting the number of torpedoes in one of your
        // submarine classes to the string "Foo".
        System.out.println(
                "\n************************************\n" +
                "\tTesting Error Handling" +
                "\n************************************\n"
        );
        System.out.println("Test 1: Set Destroyer->numberMissile to 'Foo'");
        destroyerOne.setNumberMissile("Foo");
        System.out.println();
        System.out.println("Test 2: Set Destroyer->numberMissile to -3");
        destroyerOne.setNumberMissile(-3);
        System.out.println();
        System.out.println("Test 3: Set Destroyer->numberMissile to 3");
        destroyerOne.setNumberMissile(3);
        System.out.println();

        System.out.println("Test 4: Set Submarine->numberTorpedos to 'Foo'");
        submarineOne.setNumberTorpedos("Foo");
        System.out.println();
        System.out.println("Test 5: Set Submarine->numberTorpedos to -3");
        submarineOne.setNumberTorpedos(-3);
        System.out.println();
        System.out.println("Test 6: Set Submarine->setNumberTorpedos to 3");
        submarineOne.setNumberTorpedos(3);
        System.out.println();

        System.out.println("Test 7: Set Destroyer->speed to -300");
        destroyerOne.setSpeed(-30);
        System.out.println();
        System.out.println("Test 8: Set Destroyer->speed to 'Foo'");
        destroyerOne.setSpeed("Foo");
        System.out.println();
        System.out.println("Test 9: Set Destroyer->speed to 3");
        destroyerOne.setSpeed(3);
        System.out.println();

        System.out.println("Test 10: Set PS3->setNumberEngines to -300");
        ps3One.setNumberEngines(-30);
        System.out.println();
        System.out.println("Test 11: Set PS3->setNumberEngines to 3");
        ps3One.setNumberEngines(3);
        System.out.println();
    }
}
