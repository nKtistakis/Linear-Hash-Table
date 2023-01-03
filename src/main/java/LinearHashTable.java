import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinearHashTable<K, V> implements Dictionary<K,V> {

    private static final int DEFAULT_SIZE = 17;

    private Entry<K, V> array[];
    private int size;


    @SafeVarargs
    static <E> E[] newArray(int length, E... array){
        return Arrays.copyOf(array, length);
    }

    public LinearHashTable(int m){
        this.size=0;
        array=newArray(m);
        for(int i=0; i<=m-1 ; i++){
            array[i]=new EntryImpl<>();
        }
    }

    public LinearHashTable(){
        this(DEFAULT_SIZE);
    }

    @Override
    public void put(K key, V value) {

        if (insert(key,value) == null) System.out.println("Could not insert value" + value);
        rehash();

    }


    private V insert(K key, V value){

        if (key==null || value == null)
            throw new IllegalArgumentException("Key or Value cannot be null");

        int index = toHash(key);
        int i = index;
        Entry entry = new EntryImpl(key,value);


        do {
            if (array[i].getKey() == null) {
                array[i] = entry;
                size++;
                return array[i].getValue();
            }

            if (key.equals(array[i].getKey())) {
                array[i] = entry;
                //System.out.println("Replaced key");
                return array[i].getValue();
            }

            i = (i+1) % array.length;

        } while (i != index);

        return null;
    }


    @Override
    public V remove(K key) {

        if (!contains(key)) return null;

        int index = toHash(key);
        int i=0;

        while(!key.equals(array[(index+i) % array.length].getKey())) i++;//FINDS INDEX OF KEY FOR DELETION
        index=(index+i)% array.length;

        Entry<K,V> temp , entry = array[index];

        //System.out.println("Will remove: Key " + array[index].getKey()  + " and Value " + array[index].getValue());
        array[index]=new EntryImpl<>(); //DELETES ENTRY

        i=1;
        int j = (index + i)% array.length;

        while (array[j].getKey()!=null){
            if(toHash(array[j].getKey()) <= index){
                //System.out.println("Shifting: Key " + array[index].getKey()  + " and Value " + array[index].getValue() + " from " + index + " to " + j);
                temp = array[j];
                array[j]=array[index];
                array[index]=temp;
                index=j;
                i=0;
            }
            i++;
            j = (index + i)% array.length;
        }


        size --;
        rehash();
        return entry.getValue();
    }


    public void displayTable(){

        System.out.print("{ ");
        for (int i = 0; i < array.length; i++) {
            System.out.print("[" + array[i].getKey() + " | " + array[i].getValue() + "]");
        }
        System.out.println(" }");
    }

    private void rehash(){

        int newLength=0;

        if(size()>=array.length){//DOUBLE THE ARRAY
            newLength=array.length*2;
            System.out.println("Doubling array");
        }else if(size() <= array.length / 4 && array.length >= DEFAULT_SIZE){//CROP THE ARRAY
            newLength=array.length/2;
            System.out.println("Cropping array");
        }

        if (newLength!=0){
            LinearHashTable<K,V> tempHashTable = new LinearHashTable<>(newLength);
            int i =0;
            for (Entry<K, V> e : array) {//FILL NEW ARRAY
                if (e.getValue() != null && e.getKey() != null) {
                    tempHashTable.insert(e.getKey(), e.getValue());
                    i++;
                }
            }
            this.array=tempHashTable.array;
            this.size=tempHashTable.size;
        }

    }


    @Override
    public V get(K key) {
        int index = toHash(key);
        int i = index;

        do {
            if (key.equals(array[i].getKey())) return array[i].getValue();
            i = (i+1) % array.length;

        } while (i != index);

        return null;
    }

    @Override
    public boolean contains(K key) {
        int index = toHash(key);
        int i = index;

        do {
            if (key.equals(array[i].getKey())) return true;
            i = (i+1) % array.length;

        } while (i != index);

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        this.size=0;
        for(int i=0; i<= array.length; i++){
            array[i] = null;
        }

    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashIterator();
    }

    private int toHash(K key){
        return Math.abs(key.hashCode() % array.length);
    }

    private static class EntryImpl<K, V> implements Dictionary.Entry<K, V> {

        private K key;
        private V value;

        public EntryImpl(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public EntryImpl() {
            this.key = null;
            this.value = null;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }


    }

    private class HashIterator implements Iterator<Entry<K,V>> {

        private Iterator<Entry<K, V>> it;

        public HashIterator() {
            it = ((Dictionary<K, V>) array[0]).iterator();
        }

        @Override
        public boolean hasNext() {
            for(int i=0; i<array.length; i++) {
                i++;
                if (it==null) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return it.next();
        }
    }


}
