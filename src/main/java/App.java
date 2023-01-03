import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

    public static void main(String args[]){

        LinearHashTable hashTable = new LinearHashTable(19);

        List<Integer> values = new ArrayList();
        Random rand = new Random();
        for(int i=0;i<=18;i++){
            int entry = rand.nextInt(20);
            values.add(entry);
            hashTable.put(entry,entry+100);
        }

        for (int i = 0; i <= 18; i++) {
            System.out.println(hashTable.get(values.get(i)));
        }

        hashTable.displayTable();

        System.out.println();
    }




}
