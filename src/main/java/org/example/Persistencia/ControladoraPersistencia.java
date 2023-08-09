/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Persistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.example.Logica.Alumno;
import org.example.Logica.Carrera;
import org.example.Logica.Materia;
import org.example.Persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author agust
 */
//Esta clase controlara cada una de las instancias de las jpaControllers que tengamos, en este caso solo una.
public class ControladoraPersistencia {
    AlumnoJpaController aluJpa = new AlumnoJpaController();

    public void crearAlumno(Alumno alu) {
       aluJpa.create(alu);
    }
    
    public void eliminarAlumno(int id){
        try {
            aluJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
    
    public void editarAlumno(Alumno alu){
        try {
            aluJpa.edit(alu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
    
    public Alumno traerAlumno(int id){
        return aluJpa.findAlumno(id);
    }
    
    public List<Alumno> traerListaAlumnos(){
        List<Alumno> listita = aluJpa.findAlumnoEntities();
        return listita;
    }
    
    
    CarreraJpaController carrJpa = new CarreraJpaController();
    
    public void crearCarrera(Carrera carr){
        carrJpa.create(carr);
    }
     public void eliminarCarrera(int id){
        try {
            carrJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
     }
     public void editarCarrera(Carrera carr){
        try {
            carrJpa.edit(carr);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
     }
      public Carrera traerCarrera(int id){
        return carrJpa.findCarrera(id);
    }
       public List<Carrera> traerListaCarreras(){
        List<Carrera> listaCarreras = carrJpa.findCarreraEntities();
        return listaCarreras;
    }
       
     MateriaJpaController matJpa = new MateriaJpaController();
       
    public void crearMateria(Materia mat){
        matJpa.create(mat);
    }
     public void eliminarMateria(int id){
        try {
            matJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
     }
     public void editarMateria(Materia mat){
        try {
            matJpa.edit(mat);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
     }
      public Materia traerMateria(int id){
        return matJpa.findMateria(id);
    }
       public ArrayList<Materia> traerListaMaterias(){
            List<Materia> listaMats = matJpa.findMateriaEntities();
            ArrayList<Materia> listaMaterias = new ArrayList<Materia>(listaMats);
            return listaMaterias;
    }
       
       
} 
