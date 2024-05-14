package desafio.tecnico.IntegracaoETL.dto;

import java.math.BigDecimal;

public class ProdutoDTO {
    private String idProduto;
    private BigDecimal valor;

    // Getters e Setters
    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
