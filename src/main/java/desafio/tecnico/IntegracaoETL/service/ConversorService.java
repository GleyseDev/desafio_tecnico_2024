package desafio.tecnico.IntegracaoETL.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import desafio.tecnico.IntegracaoETL.dto.ItemPedidoDTO;
import desafio.tecnico.IntegracaoETL.dto.PedidoDTO;
import desafio.tecnico.IntegracaoETL.dto.ProdutoDTO;
import desafio.tecnico.IntegracaoETL.dto.UsuarioDTO;

public class ConversorService {

    public static Map<String, UsuarioDTO> ConverterArquivoEmJson(MultipartFile arquivo) throws IOException {

        Map<String, UsuarioDTO> usuariosMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(arquivo.getInputStream()))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                /*
                 * Verifica se a linha possui comprimento suficiente, se não possuir ignora a
                 * linha que esta incorreta.
                 */
                if (linha.length() >= 95) {
                    String idUsuario = linha.substring(0, 10).trim();
                    /*
                     * Verifica se o ja existe dados de pedido do usuario
                     */
                    UsuarioDTO usuarioDTO = usuariosMap.get(idUsuario);
                    if (usuarioDTO == null) {
                        usuarioDTO = new UsuarioDTO();
                        usuarioDTO.setIdUsuario(idUsuario);
                        usuarioDTO.setNome(linha.substring(10, 55).trim());
                        usuariosMap.put(idUsuario, usuarioDTO);
                    }

                    String idPedido = linha.substring(55, 65).trim();
                    /*
                     * Verifica se o usuario já possui um pedido com o mesmo id e inclui o produto
                     */
                    PedidoDTO pedidoDTO = usuarioDTO.getPedidoPorId(idPedido);
                    if (pedidoDTO == null) {
                        pedidoDTO = new PedidoDTO();
                        pedidoDTO.setIdPedido(idPedido);

                        String dataCompraString = linha.substring(87, 95).trim();
                        LocalDate dataCompra = LocalDate.parse(dataCompraString,
                                DateTimeFormatter.ofPattern("yyyyMMdd"));
                        pedidoDTO.setDataCompra(dataCompra);

                        usuarioDTO.addPedido(pedidoDTO);
                    }

                    ProdutoDTO produtoDTO = new ProdutoDTO();
                    produtoDTO.setIdProduto(linha.substring(65, 75).trim());
                    produtoDTO.setValor(new BigDecimal(linha.substring(75, 87).trim()));

                    ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
                    itemPedidoDTO.setIdProduto(produtoDTO.getIdProduto());
                    itemPedidoDTO.setValor(produtoDTO.getValor());

                    pedidoDTO.getItens().add(itemPedidoDTO);
                }
            }
        }
        return usuariosMap;
    }
}
