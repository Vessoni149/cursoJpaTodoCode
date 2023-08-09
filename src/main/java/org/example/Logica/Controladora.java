/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Logica;

//Esta clase va a tener todos los metodos que vamos a necesitar para despues llamarl a los metodos de la persistencia.

import java.util.ArrayList;
import java.util.List;
import org.example.Persistencia.ControladoraPersistencia;

public class Controladora {
     ControladoraPersistencia controlPersis = new ControladoraPersistencia();
     
     
     //METODOS PARA LA CLASE ALUMNO
     public void crearAlumno(Alumno alu){
      controlPersis.crearAlumno(alu);  
     };
     
     public void eliminarAlumno(int id){
         controlPersis.eliminarAlumno(id);
     }
     
     public void editarAlumno(Alumno alu){
        controlPersis.editarAlumno(alu);
     }
     
     public Alumno traerAlumno(int id){
     return controlPersis.traerAlumno(id);
     }
     
     public List<Alumno> traerListaAlumnos(){
         return controlPersis.traerListaAlumnos();
     }
     
     
     //METODOS PARA LA CLASE CARRERA
     public void crearCarrera(Carrera car){
      controlPersis.crearCarrera(car);  
     };
     
     public void eliminarCarrera(int id){
         controlPersis.eliminarCarrera(id);
     }
     
     public void editarCarrera(Carrera car){
        controlPersis.editarCarrera(car);
     }
     
     public Carrera traerCarrera(int id){
     return controlPersis.traerCarrera(id);
     }
     
     public List<Carrera> traerListaCarreras(){
         return controlPersis.traerListaCarreras();
     }
     
     
     //METODOS PARA LA CLASE MATERIA
      public void crearMateria(Materia mat){
      controlPersis.crearMateria(mat);  
     };
     
     public void eliminarMateria(int id){
         controlPersis.eliminarMateria(id);
     }
     
     public void editarMateria(Materia mat){
        controlPersis.editarMateria(mat);
     }
     
     public Materia traerMateria(int id){
     return controlPersis.traerMateria(id);
     }
     
     public List<Materia> traerListaMaterias(){
         return controlPersis.traerListaMaterias();
     }
}
