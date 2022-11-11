package iua.kaf.Backend.integration.Cli1.model;

import iua.kaf.Backend.model.Orden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "OrdenCli1")
@PrimaryKeyJoinColumn(name = "id_orden")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrdenCli1 extends Orden {

    private static final long serialVersionUID = -8955239799454818328L;

    @Column(nullable = false , unique = true)
    private String codeCli1;


}
