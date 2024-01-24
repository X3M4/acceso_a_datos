package com.mongodb.p2;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/*
 * Obtén el nombre de todos los personajes que tengan “man” en su nombre, al final, en mayúscula o minúscula, y el total de éstos.
 */
public class P2Ejercicio5 {

    public static void main(String[] args) {

        String uri = "mongodb://localhost:27017";
        int cantidad = 0;

        // Iniciamos en cliente Mongo
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            // Se obtiene la base de datos Marvel
            MongoDatabase db = mongoClient.getDatabase("Marvel");

            // Obtenemos la colección events
            MongoCollection<Document> collection = db.getCollection("characters");
            List<Document> consulta = collection.find().into(new ArrayList<Document>());
            
            for(Document doc : consulta){
                
                String nombre = doc.getString("name");
                String[] primeraParte = nombre.split("\\(");
                String primerNombre = primeraParte[0].trim();

                if(primerNombre.endsWith("man") || primerNombre.endsWith("Man") ||
                    primerNombre.endsWith("-Man")) {
                    System.out.println(nombre);
                    cantidad++;
                }   
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(String.format("En total %d personajes contienen man o Man al final del nombre", cantidad));
    }
}

