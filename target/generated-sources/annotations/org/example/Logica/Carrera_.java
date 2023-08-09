package org.example.Logica;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.example.Logica.Materia;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-08-04T18:09:03", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Carrera.class)
public class Carrera_ { 

    public static volatile ListAttribute<Carrera, Materia> listaMaterias;
    public static volatile SingularAttribute<Carrera, Integer> id;
    public static volatile SingularAttribute<Carrera, String> nombre;

}