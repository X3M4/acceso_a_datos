package com.mongodb.p2;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.*;

public class P2Ejercicio2 {

    /*
     * Ejercicio 2: Obtén el título de todos los eventos (“events”) junto con el
     * número total de éstos.
     */
    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";

        // Iniciamos en cliente Mongo
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            // Se obtiene la base de datos Marvel
            MongoDatabase db = mongoClient.getDatabase("Marvel");

            // Obtenemos la colección events
            MongoCollection<Document> collection = db.getCollection("events");
            
            List<Document> consulta = collection.find().into(new ArrayList<Document>());

            for(Document document : consulta){
                System.out.println(document.getString("title"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
