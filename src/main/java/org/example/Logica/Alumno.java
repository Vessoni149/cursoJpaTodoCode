/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author agust
 */
//Con Entity marcamos que la clase será una tabla.
@Entity
public class Alumno implements Serializable {
    //Las annotations ID y GeneratedValue deben ir juntas arriba de la variable que creemos como id para que se lean como un Id en una tabla. Particularmente el segundo tiene como parametro una estrategia de generacion de ID para que a medida que insertemos valores en la tabla, el id se comporte como le indiquemos, en este caso AUTO hace que cuando insertemos una fila a la tabla el id se genere automaticamente
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    //Basic no es obligatoria, es para campos comunes de la tabla. Si no ponemos la anotacion, JPA ya sabe por defecto que estos son campos extra.
    @Basic
    private String nombre;
    private String apellido;
    @OneToOne
    private Carrera carr;
    //Temporal es una anotacion para identificar datos de tipo Date que iran en la tabla.
    //TemporalType refiere al formato que tendra la fecha, en este caso DATE refiere a solo fecha (sin incluir hora)
    @Temporal(TemporalType.DATE) 
    private Date fechaNac;

    public Alumno(int id, String nombre, String apellido,
            Date fechaNac,Carrera carr) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carr = carr;
        this.fechaNac = fechaNac;
    }

    
    
    public Alumno(){}
     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Carrera getCarr() {
        return carr;
    }

    public void setCarr(Carrera carr) {
        this.carr = carr;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", nombre=" + nombre + ", apellido=" +
                apellido + ", carr=" + carr + ", fechaNac=" + fechaNac + '}';
    }
    
    

   
     
}
