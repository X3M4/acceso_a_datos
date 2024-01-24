package com.mongodb.p2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class pP2Ejercicio4 {
    
    /*
     * Obtén los personajes que tengan más de 50 cómics y hayan sido modificados
     * posteriormente al año 2000, así como los 20 primeros comics que haya. De los cómics saca
     * solo el título y la descripción.
     */
    public static void main(String[] args){

        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            // Se obtiene la base de datos Marvel
            MongoDatabase db = mongoClient.getDatabase("Marvel");

            // Obtenemos la colección events
            MongoCollection<Document> collection = db.getCollection("characters");
            
            List<Document> consulta = collection.find().into(new ArrayList<Document>());

            for(Document doc: consulta){
                String nombre = doc.getString("name");
                List<String> lista = getNumberComics(doc);
                

                if(lista != null){
                    lista = lista.subList(0, Math.min(lista.size(), 20));
                    System.out.println(nombre);
                    for(String s: lista){
                        System.out.println("\t-----" + s);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     * En esta función devuelve True si de cada documento que recibe
     * el personaje del documento sale en más de 50 comics
     */
    public static List<String> getNumberComics(Document doc){
        Document comics = doc.get("comics", Document.class);
        int numero = comics.getInteger("available", 0);
       
        String modificacion = doc.getString("modified");
        
        String[] modificaciones = modificacion.split("-");
        int year = Integer.parseInt(modificaciones[0]);

        if(numero > 50 && year > 2000) {

            return getComicList(doc.getString("name"));
        }
        else return null;
    }

    /*
     * LO HAGO DE ESTA MANERA PORQUE A LA DESCRIPCIÓN SOLAMENTE SE PUEDE ACCEDER DESDE LA COLECCIÓN COMICS
     * DESDE CHARACTER SOLAMENTE ESTÁ EL NOMBRE DEL COMIC.
     */
    public static List<String> getComicList(String comics){
        List<String> listaTitulosDesc = new ArrayList<String>();

        //Conexión con la colección comics
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            // Se obtiene la base de datos Marvel
            MongoDatabase db = mongoClient.getDatabase("Marvel");

            // Obtenemos la colección comics
            MongoCollection<Document> collection = db.getCollection("comics");
            
            //Obtenemos todos los elementos y los recorremos
            List<Document> consulta = collection.find().into(new ArrayList<Document>());
            for(Document doc: consulta){

                //Obtenemos la rama characters de comics y metemos en una lista los items
                Document personajes = doc.get("characters", Document.class);
                List<Document> listaPersonajesComic = personajes.getList("items", Document.class);

                //Se recorre la lista de items y si el nombre del personaje en el item coincide con el dado a la función
                for(Document item: listaPersonajesComic){
                    String nombrePersonajeComic = item.getString("name");

                    //Se mete en la lista de String el nombre y la descripción
                    if(nombrePersonajeComic.equals(comics)){
                        listaTitulosDesc.add(doc.getString("title").toUpperCase() + "-->" + doc.getString("description")+"\n");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaTitulosDesc;

    }
}
