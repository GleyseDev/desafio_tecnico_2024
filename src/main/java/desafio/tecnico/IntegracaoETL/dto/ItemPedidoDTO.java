package desafio.tecnico.IntegracaoETL.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ItemPedidoDTO {
    private String idProduto;
    private BigDecimal valor;
}
