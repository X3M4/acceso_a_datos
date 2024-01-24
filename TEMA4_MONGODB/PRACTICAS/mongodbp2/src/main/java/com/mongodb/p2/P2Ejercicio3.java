package com.mongodb.p2;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

public class P2Ejercicio3{

    /*
     * Ejercicio 3: Elige un personaje de la colección “characters” y extrae toda su información.
     * A continuación, cambia en el documento de la aplicación (no en la base de datos) el nombre
del personaje por el tuyo en cualquier clave donde aparezca el nombre del personaje elegido.
Guarda tu personaje como un nuevo personaje en la base de datos y muéstralo con una
captura de pantalla de Compass.
    */

    public static void main(String[] args){
        String uri = "mongodb://localhost:27017";

        // Iniciamos en cliente Mongo
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            // Se obtiene la base de datos Marvel
            MongoDatabase db = mongoClient.getDatabase("Marvel");

            // Obtenemos la colección events
            MongoCollection<Document> collection = db.getCollection("characters");
            
            //Recupero el personaje Avalanche
            Document personaje = collection.find(eq("name", "Avalanche")).first();
            System.out.println(personaje.toString());
            //Cambio el nombre de Avalanche por el mío
            personaje.put("name", "Chema Fernández");
            //Cambio _id para que no se duplique
            personaje.put("_id", 1);
            //Añado el personaje nuevo con mi nombre
            collection.insertOne(personaje);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
