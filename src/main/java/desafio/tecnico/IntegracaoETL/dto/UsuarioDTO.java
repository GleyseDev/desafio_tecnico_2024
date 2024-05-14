package desafio.tecnico.IntegracaoETL.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class UsuarioDTO {
    @JsonProperty("user_id")
    private String idUsuario;
    private String nome;
    @JsonProperty("orders")
    private List<PedidoDTO> pedidos;

    public UsuarioDTO() {
        this.pedidos = new ArrayList<>();
    }

    public void addPedido(PedidoDTO pedido) {
        if (pedidos == null) {
            pedidos = new ArrayList<>();
        }
        pedidos.add(pedido);
    }

    public PedidoDTO getPedidoPorId(String idPedido) {
        if (pedidos != null) {
            for (PedidoDTO pedido : pedidos) {
                if (pedido.getIdPedido().equals(idPedido)) {
                    return pedido;
                }
            }
        }
        return null;
    }
}
