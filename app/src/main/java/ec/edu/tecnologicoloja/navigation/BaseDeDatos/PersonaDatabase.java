package ec.edu.tecnologicoloja.navigation.BaseDeDatos;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ec.edu.tecnologicoloja.navigation.BaseDeDatos.Persona;
import ec.edu.tecnologicoloja.navigation.BaseDeDatos.PersonaDao;

@Database(entities = {Persona.class},version = 1)
public abstract class PersonaDatabase extends RoomDatabase {

    public abstract PersonaDao getPersonaDao();

}

