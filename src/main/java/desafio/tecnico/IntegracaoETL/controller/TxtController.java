package desafio.tecnico.IntegracaoETL.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import desafio.tecnico.IntegracaoETL.dto.UsuarioDTO;
import desafio.tecnico.IntegracaoETL.service.ConversorService;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TxtController {

    @PostMapping("/processar-arquivo")
    public ResponseEntity<List<UsuarioDTO>> processarArquivo(@RequestPart("file") MultipartFile arquivo)
            throws IOException {
        Map<String, UsuarioDTO> usuariosMap = ConversorService.ConverterArquivoEmJson(arquivo);

        return ResponseEntity.ok(new ArrayList<>(usuariosMap.values()));
    }

}
