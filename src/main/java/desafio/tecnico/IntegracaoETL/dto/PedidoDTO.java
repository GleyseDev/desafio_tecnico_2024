package desafio.tecnico.IntegracaoETL.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class PedidoDTO {
    @JsonProperty("order_id")
    private String idPedido;
    @JsonProperty("total")
    private BigDecimal total;
    @JsonProperty("date")
    private LocalDate dataCompra;
    @JsonProperty("products")
    private List<ItemPedidoDTO> itens;

    public PedidoDTO() {
        this.itens = new ArrayList<>();
    }

    public BigDecimal getTotal() {
        BigDecimal totalPedido = BigDecimal.ZERO;
        if (itens != null) {
            for (ItemPedidoDTO item : itens) {
                totalPedido = totalPedido.add(item.getValor());
            }
        }
        return totalPedido;
    }
}
