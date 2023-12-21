package tema3.practicas;

import org.neodatis.odb.ClassRepresentation;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class NeoDatisDB_LOTR_Management {
    public static void main(String[] args) {
        ODB odb = ODBFactory.open("practica_LOTR"); 
        creIndex(odb);   
    }

    public static  void creIndex(ODB odb){
        if(!odb.getClassRepresentation(Book.class).existIndex("name-index")){
            String[] fieldIndex = {"id"};
            ClassRepresentation representationClase = odb.getClassRepresentation(Book.class);
            representationClase.addUniqueIndexOn("name-index", fieldIndex, true);
        }
    }
}
