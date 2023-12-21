package tema3.practicas;

import org.neodatis.odb.ClassRepresentation;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class NeoDatisDB_LOTR_Management {
    public static void main(String[] args) {
        ODB odb = ODBFactory.open("practica_LOTR"); 
        creaIndex(odb);
        odb.close();
        
    }

    public static void creaIndex(ODB odb){
        if(!odb.getClassRepresentation(Book.class).existIndex("name-index")){
            String[] fieldIndex = {"id"};
            ClassRepresentation representationClase = odb.getClassRepresentation(Book.class);
            representationClase.addUniqueIndexOn("name-index", fieldIndex, true);
        }

        if(!odb.getClassRepresentation(Chapter.class).existIndex("name-index")){
            String[] fieldIndex = {"id"};
            ClassRepresentation representationClase = odb.getClassRepresentation(Chapter.class);
            representationClase.addUniqueIndexOn("name-index", fieldIndex, true);
        }

        if(!odb.getClassRepresentation(Character.class).existIndex("name-index")){
            String[] fieldIndex = {"id"};
            ClassRepresentation representationClase = odb.getClassRepresentation(Character.class);
            representationClase.addUniqueIndexOn("name-index", fieldIndex, true);
        }

        if(!odb.getClassRepresentation(Movie.class).existIndex("name-index")){
            String[] fieldIndex = {"id"};
            ClassRepresentation representationClase = odb.getClassRepresentation(Movie.class);
            representationClase.addUniqueIndexOn("name-index", fieldIndex, true);
        }

        if(!odb.getClassRepresentation(Realm.class).existIndex("name-index")){
            String[] fieldIndex = {"id"};
            ClassRepresentation representationClase = odb.getClassRepresentation(Realm.class);
            representationClase.addUniqueIndexOn("name-index", fieldIndex, true);
        }

        if(!odb.getClassRepresentation(Dialog.class).existIndex("name-index")){
            String[] fieldIndex = {"id"};
            ClassRepresentation representationClase = odb.getClassRepresentation(Dialog.class);
            representationClase.addUniqueIndexOn("name-index", fieldIndex, true);
        }

    }

    
}
