package org.til.serializable;

import java.io.*;

public class SerializeMain {

    static String fileName = "src/main/java/org/til/serializable/service.ser";

    public static void main(String[] args) {
        serialization(fileName);
        deserialization(fileName);
    }

    private static void deserialization(String fileName) {
        try{
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Service deserializedService = (Service) ois.readObject();
            System.out.println(deserializedService);
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private static void serialization(String fileName) {
        Service service = new Service(1,"gray", "1q2w3e4r!", 30);


        try{
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(service);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
