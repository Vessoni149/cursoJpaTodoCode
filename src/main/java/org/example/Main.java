package org.example;

import static java.awt.SystemColor.control;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.example.Logica.Alumno;
import org.example.Logica.Carrera;
import org.example.Logica.Controladora;
import org.example.Logica.Materia;
import org.example.Persistencia.ControladoraPersistencia;

/** JPA es un ORM (Object Relational Mapping) que tiene como ojetivo lograr la persistencia de datos entre una aplicacion desarrollada en java y una DB. Intermediario que comunica la app java con la DB.
 * es una técnica o enfoque utilizado para mapear objetos en una aplicación Java a tablas en una base de datos relacional. Proporciona una abstracción de alto nivel que permite a los desarrolladores interactuar con la base de datos utilizando objetos y métodos familiares en lugar de escribir consultas SQL directamente.
 * JPA utiliza por debajo JDBC, donde sí hay que escribir las consultas sql a mano en el codigo java.
 *Un ORM en Java facilita la persistencia de datos, lo que significa que puede guardar y recuperar objetos Java de manera transparente en la base de datos sin tener que preocuparse por la manipulación directa de las consultas SQL. El ORM se encarga de traducir automáticamente las operaciones de base de datos en consultas SQL y de asignar los resultados de las consultas a los objetos Java correspondientes.
 *
 * Para relacionar el codigo java con la bd se usan annotations. Entre ellas hay una que traduce las clases de java en tablas o entidades.
 *
 * JPA tiene proveedores, por ej Hibernate, Eclipselink, Toplink, etc.*/
public class Main {
    public static void main(String[] args) {
        //ControladoraPersistencia controlPersis = new ControladoraPersistencia(); //Esta sola linea crea las tablas segun las entidades que hayamos creado.
        
        
        Controladora control = new Controladora();  //Esta instancia de Controladora nos permitira usasr todos los metodos de todas las entidades.
        
        //1) Crear lista de materias:
        ArrayList<Materia> listaMaterias = new ArrayList<Materia>();
        
        //Creo una carrera
        Carrera carr = new Carrera(1,"Filosofia",listaMaterias);
        
        //Guardo la carrera en la DB.
        control.crearCarrera(carr);
        
        
        //Creo las materias:
        Materia mat1 = new Materia(1, "Historia de la Filosofia", "1er Cuatrimestre", carr);
        Materia mat2 = new Materia(2, "Filosofia Estoica", "1er Cuatrimestre", carr);
        Materia mat3 = new Materia(3, "Materialismo Historico", "1er Cuatrimestre", carr);
        
        //Guardo las materias en la DB:
        control.crearMateria(mat1);
        control.crearMateria(mat2);
        control.crearMateria(mat3);
        
        //Agrego a la LISTA de materias las creadas previamente:
        listaMaterias.add(mat1);
        listaMaterias.add(mat2);
        listaMaterias.add(mat3);
        
        //a la carrera le agrego la lista de materias con las materias ya cargadas.
        carr.setListaMaterias(listaMaterias);
        
        //Realizo la modificacion anterior pero en la DB.
        control.editarCarrera(carr);
        
        //Creo alumnos
        Alumno alu = new Alumno(15, "Jorge", "Suspenso", new Date(),carr);
        Alumno alu2 = new Alumno(16, "Martin", "Martinez", new Date(),carr);
        Alumno alu3 = new Alumno(17, "Ramiro", "Ramirez", new Date(),carr);
        
        //Guardo los alumnos en la DB.
        control.crearAlumno(alu);
        control.crearAlumno(alu2); 
        control.crearAlumno(alu3);
        
        //control.eliminarAlumno(15);
        
        //alu.setNombre("Agustin");
        //alu.setApellido("Agustinez");
        //control.editarAlumno(alu);
        
        //Leer 1 alumno:
      // Alumno alumn = control.traerAlumno(15);
        //System.out.println("El alumno traido es: " + alumn.toString());
        
        //System.out.println("------------------------------------");
        
        //Leer varios alumnos:
       // List<Alumno> listaAlumnos = control.traerListaAlumnos();
        //for(Alumno al : listaAlumnos){
        //    System.out.println("El alumno es: " + al.toString());
    //}
        
        // System.out.println("------------------------------------");
         //System.out.println("El alumno:   " + alu.getNombre() + " " + alu.getApellido() + ", cursa la carrera: " + carr1.getNombre());
        
       
   
    }
}