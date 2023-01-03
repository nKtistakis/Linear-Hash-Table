
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LinearHashTest {

    private static final int TABLESIZE = 15;
    private static final int VALUEStoPUT = 13;
    private static final int VALUEStoRemove = 8;

    @Test
    public void testArrayQueue() {

        LinearHashTable<Integer,Integer> hashTable = new LinearHashTable(TABLESIZE);


        Random rng = new Random();
        List<Integer> values = new ArrayList<>();

        for (int i = 0; i < VALUEStoPUT; i++) {
            int entry = rng.nextInt(30);
            if (!hashTable.contains(entry)) values.add(entry); //So values do not contain doubles
                hashTable.put(entry,entry+100);
        }

        System.out.println("Table after inserting " + VALUEStoPUT + " elements:");
        hashTable.displayTable();
        for ( Integer i : values){
            assertTrue(hashTable.get(i) == i+100); //CHECK IF KEY == ITS VALUE
        }


        int found;
        int sizeCounter = hashTable.size();
        for (int i = 0; i < VALUEStoRemove; i++) {
            found=0;
            for(Integer c : values){
                if (hashTable.get(c) != null) found++;// CHECK IF ALL KEYS CAN BE ACCESSED
            } assertTrue(found==hashTable.size());


            if(hashTable.remove(values.get(i))!=null){//IF KEY EXISTED, CHECKING IF REMOVES GOES AS PLANNED
                sizeCounter--;
                assertTrue(hashTable.size()==sizeCounter);
            }
        }
        System.out.println("\nTable after removing " + VALUEStoRemove + " elements:");
        hashTable.displayTable();

        System.out.println("\nTest finished successfully!");
    }
}
