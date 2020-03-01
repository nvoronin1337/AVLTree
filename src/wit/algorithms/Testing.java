package wit.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class Testing {

    public static void main(String[] args) {
        AVLTree <String> nameTree = new AVLTree <>();

        nameTree.insert("Ann");
        nameTree.insert("Ben");
        nameTree.insert("Bob");
        nameTree.insert("Charles");
        nameTree.insert("Daniel");
        nameTree.insert("David");
        nameTree.insert("Ethan");
        nameTree.insert("Frank");
        nameTree.insert("George");
        nameTree.insert("Harry");


        System.out.println("height: " + nameTree.getHeight());
        System.out.println("real height: " + nameTree.getRealHeight());

        Iterator <String> level = nameTree.getLevelOrderIterator();

        while (level.hasNext())
            System.out.print(level.next() + " ");
        System.out.println();


        Integer [] numbers = {5, 6, 8, 3, 2, 4, 7};
        AVLTree <Integer> numTree = new AVLTree <>();

        for (Integer i: numbers)
            numTree.insert(i);

        Iterator <Integer> levelInt = numTree.getLevelOrderIterator();
        while (levelInt.hasNext())
            System.out.print(levelInt.next() + " ");
        System.out.println();
        try {
            AVLTree <String> capitals = new AVLTree <>();
            File capNames = new File ("StateCapitals.txt");

            Scanner read = new Scanner (capNames);
            while (read.hasNext())
                capitals.insert(read.nextLine());
            read.close();

            Iterator <String> capIn = capitals.getInorderIterator();
            while (capIn.hasNext())
                System.out.println (capIn.next());


            System.out.println ("Heights are " + capitals.getHeight()
                    + "," + capitals.getRealHeight()) ;

            // for bonus
            //capitals.delete("Lincoln");
            //capitals.delete("Juneau");
            //System.out.println ("++++++++++++++++++++++++++++++++++++++");
            //capIn = capitals.getInorderIterator();
            //while (capIn.hasNext())
            //System.out.println (capIn.next());

        }

        catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

}