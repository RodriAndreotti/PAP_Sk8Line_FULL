
package br.com.sk8line.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class Endereco {

    @Id
    @Column(name="id_endereco")
    private int id;

    private String logradouro;

    private int numero;

    private String complemento;

    private String bairro;

    private String cidade;

    @OneToOne
    @JoinTable(name="estado", joinColumns = {@JoinColumn(name="id")})
    private Estado estado;

    private String cep;

    private int tipo;

    public void salvarEndereco() {

    }

}
