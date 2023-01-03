import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class CropingTableTest {
    private static final int TABLESIZE = 10;
    private static final int VALUEStoPUT = 60;
    private static final int VALUEStoRemove = 59;

    @Test
    public void testArrayQueue() {

        LinearHashTable<Integer,Integer> hashTable = new LinearHashTable(TABLESIZE);


        Random rng = new Random();
        List<Integer> values = new ArrayList<>();

        for (int i = 0; i < VALUEStoPUT; i++) {
            int entry = rng.nextInt(130);
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
        int i = 0;
        while (i<VALUEStoRemove && i<values.size()){

            found=0;
            for(Integer c : values){
                if (hashTable.get(c) != null) found++;// CHECK IF ALL KEYS CAN BE ACCESSED
            } assertTrue(found==hashTable.size());


            if(hashTable.remove(values.get(i))!=null){//IF KEY EXISTED, CHECKING IF REMOVES GOES AS PLANNED
                sizeCounter--;
                assertTrue(hashTable.size()==sizeCounter);
            }

            i++;
        }
        System.out.println("\nTable after removing " + VALUEStoRemove + " elements:");
        hashTable.displayTable();

        System.out.println("\nTest finished successfully!");
    }
}
