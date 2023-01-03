## Linear-Hash-Table
A linear hash table implementation developed in Java.

# Description
This linear hash table was developed to store hashed data for easy and fast access, as the value correlates to the hash value of its key. Even though the table needs a given size to initialize, in the case of the size exceeds, the table will automaticly double its size, to be able to store the new _Entries_ given. In contrast, if the _Entries_ of the table are one fourth of the table's total size, the table's size will subduplicate, in order to be memory efficient.

Upon creation, the Linear Hash Table will be filled with null _Entry_ objects.


# Supported methods
- Insert to table: **void put(K key, V value)**
Accepting 2 non-nullable values, of which the _key_ variable will get its hashed value, matched to an index on the table in order for the _value_ variable to get stored there. If the index is already occupied the value will be stored in the next available index, following a circular path. After the successfull insertion, the table will rehash and check if it needs to alter its size.

- Remove from table: **V remove(K key)**
The search starts from the expected index of the _key_ and keeps incremating until there is a match. Then a replace of the _Entries_ with a null _Entries_ is perfomed and the deleted value gets returned. In order to not cause an access problem to the table (since it will miss a hashed index value), the table gets rehashed in order the _keys_ hashed values to match their indexes.


# Run Instructions
In order for tests to execute, run:
-cd ../LinearHash

-mvn test (inside ../LinearHash direcotry!)
