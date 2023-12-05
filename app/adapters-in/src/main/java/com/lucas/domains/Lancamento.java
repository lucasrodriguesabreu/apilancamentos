package com.lucas.domains;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "lancamentos")
@Entity(name = "lancamentos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date vencimento;
    private Double valor;
    private String descricao;
    private Boolean pago;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}