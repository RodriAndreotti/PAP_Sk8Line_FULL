/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sk8line.venda.dao;

import br.com.sk8line.common.dao.DAO;
import br.com.sk8line.venda.model.Venda;
import java.util.List;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author leonardo.lima
 */
public class VendaDAO extends DAO {

    private static VendaDAO instance;

    private VendaDAO() {
    }

    public static VendaDAO getInstance() {
        if (instance == null || !(instance instanceof VendaDAO)) {
            instance = new VendaDAO();
        }

        return instance;
    }

    /**
     * Salva a venda
     *
     * @param venda
     * @return
     */
    public Venda salvar(Venda venda) {

        if (venda.getId() == 0) {
            this.getEntityManager().persist(venda);
        } else {
            if (!this.getEntityManager().contains(venda)) {
                if (this.getEntityManager().find(Venda.class, venda.getId()) == null) {
                    throw new NotFoundException("Venda não encontrado");
                }
            }
            this.getEntityManager().merge(venda);
        }

        return venda;
    }

    /**
     * Lista todas as vendas
     *
     * @return
     */
    public List<Venda> listar() {
        return this.getEntityManager().createQuery("SELECT v FROM venda v", Venda.class).getResultList();
    }

    /**
     * Obtém uma venda pelo ID
     *
     * @param id
     * @return
     */
    public Venda getById(int id) {
        Venda venda = this.getEntityManager().find(Venda.class, id);
        if (venda == null) {
            throw new NotFoundException("Venda não encontrada");
        }

        return venda;
    }

    public void apagar(Venda venda) {
        this.getEntityManager().remove(venda);
    }
}
