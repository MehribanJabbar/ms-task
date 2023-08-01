package az.ingress.ms.common;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, String> hash = new HashMap<>();
        hash.put(101, "MeriJabbar");
        hash.put(102, "Ilkin Aliyev");
        hash.put(103, "Sahira Mammad");
        hash.put(104, "Ilkin Aliyev");
        hash.put(105, "Tural asad");
        hash.put(null, "Anar asad");
        hash.put(null, "Nariman asad");
        hash.put(null, "Sakina asad");
        System.out.println("Hashmap :");
        for (Map.Entry m: hash.entrySet()){
            System.out.println(m.getKey() + " " + m.getValue());
        }


        Hashtable<Integer, String> hashTable = new Hashtable<Integer, String>();
        hashTable.put(101, "Meri");
        hashTable.put(102, "Meri");
        hashTable.put(103, "Meri");
        hashTable.put(104, "Meri");
        hashTable.put(105, "Meri");
        hashTable.put(106, "Meri");
        hashTable.put(107, "Meri");
        System.out.println("HashSet");
        for (Map.Entry m: hashTable.entrySet()){
            System.out.println(m.getKey() + " " + m.getValue());
        }

    }
}
