package com.example.caderno.controller;

import com.example.caderno.controller.dto.*;
import com.example.caderno.dataprovider.entity.AnotacaoEntity;
import com.example.caderno.dataprovider.entity.MateriaEntity;
import com.example.caderno.dataprovider.entity.TagEntity;
import com.example.caderno.dataprovider.repository.MateriaRepository;
import com.example.caderno.dataprovider.repository.AnotacaoRepository;
import com.example.caderno.dataprovider.repository.TagRepository;
import com.example.caderno.exception.NotFoundException;
import com.example.caderno.exception.ServerSideException;
import com.example.caderno.padroes.DefaultResponse;
import com.example.caderno.service.AnotacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// locahost:8080/nota
@RestController
@RequestMapping("/notas")
public class AnotacaoController {

    private final AnotacaoRepository anotacaoRepository;
    private final AnotacaoService anotacaoService;
    private final MateriaRepository materiaRepository;
    private final TagRepository tagRepository;

    public AnotacaoController(AnotacaoRepository repository, AnotacaoService anotacaoService, MateriaRepository materiaRepository, TagRepository tagRepository) {
        this.anotacaoRepository = repository;
        this.anotacaoService = anotacaoService;
        this.materiaRepository = materiaRepository;
        this.tagRepository = tagRepository;
    }

    // GET /notas
    @GetMapping
    public ResponseEntity<List<AnotacaoResponse>> encontraNotas() {
        List<AnotacaoResponse> responseList = anotacaoService.encontraTodasNotas();

        return ResponseEntity.ok(responseList);
//        return new ResponseEntity(responseList,HttpStatus.OK);
    }

    // POST /notas/nota
    @PostMapping("/nota")
    public ResponseEntity<DefaultResponse> salvarNota(@Valid @RequestBody AnotacaoRequest anotacaoRequest) { // corpo de requisição
        AnotacaoResponse response = anotacaoService.salvarNovaNota(anotacaoRequest);

        return new ResponseEntity<>( //reposta padrão DefaultResponse
                new DefaultResponse<AnotacaoResponse>(
                        HttpStatus.CREATED.value(), // valor int do status Created
                        response
                ),
                HttpStatus.CREATED
        );

    }

    // GET /notas/nota?id=2 -> id = 2
    @GetMapping("/nota")
//    public ResponseEntity<AnotacaoResponse> encontrarNotaPorId(@RequestParam("id") Long id) {
    // @Valida @NotNull torna o parametro obrigatorio
//    required = false torna o parametro opcional
    public ResponseEntity<AnotacaoResponse> encontrarNotaPorId(@RequestParam(value = "id", required = false) Long id) {
        AnotacaoEntity entity = anotacaoRepository.findById(id).get();

        return new ResponseEntity<AnotacaoResponse>(
                new AnotacaoResponse(
                        entity.getId(),
                        entity.getTitulo(),
                        entity.getTexto(),
                        new MateriaResponse(
                                entity.getMateriaEntity().getId(),
                                entity.getMateriaEntity().getNome()
                        )),
                HttpStatus.OK
        );
    }

    // método de atualização de um Objeto
    // endpoint /nota/{id}
    // endpoint /nota/1 -> se refere a nota com id 1
    @PutMapping("/nota/{id}")
    public ResponseEntity<AnotacaoResponse> atualizarNotaPorId(
            @PathVariable Long id,
            @RequestBody AnotacaoRequest request
    ) {
        AnotacaoEntity entity = anotacaoRepository.findById(id).get(); // acho a nota de id 1
        entity.setTitulo(request.getTitulo()); // atualizo o valor do titulo da nota 1
        entity.setTexto(request.getTexto()); // atualizo o valor do texto da nota 1
        anotacaoRepository.save(entity); // salvo a atualização da nota 1

        return new ResponseEntity<AnotacaoResponse>(
                // resposta rest, do tipo NotaRespose,
                // e o body da resposta é um objeto NotaRespose
//                new NotaResponse(entity.getTitulo(), entity.getNota()),

                AnotacaoResponse.builder() // permite preencher os atributos do objeto sem ter que usar um construtor
                        .titulo(entity.getTitulo())
                        .texto(entity.getTexto())
                        .build(),

                HttpStatus.OK // status ok
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarNotaPorId(
            @PathVariable Long id
    ) {
        anotacaoRepository.deleteById(id); // deleta a nota de id {id}
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/tag")
    public ResponseEntity<TagResponse> adicionarTagPorNotaId(
            @PathVariable Long id,
            @RequestBody TagRequest request
    ) {
        AnotacaoEntity notaEntity = anotacaoRepository.findById(id).get(); // acho a nota de id

        TagEntity tagEntity = tagRepository.save(
                new TagEntity(request.getTag(), notaEntity) // temos que salvar tanto a tag quanto o objeto NotaEntity
        ); // Salvar uma TagEntity com os dados da requisição
        return ResponseEntity.ok(
                new TagResponse(
                        tagEntity.getTag(), tagEntity.getNotaEntity().getId()
                ));
    }

    @GetMapping("/{id}/tag")
    public ResponseEntity procurarTagPorNotaId(
            @PathVariable Long id
    ) {
        AnotacaoEntity notaEntity = anotacaoRepository.findById(id).get(); // acho a nota de id

        List<TagEntity> tagEntity = tagRepository.findAll();

        return ResponseEntity.ok(
                tagEntity);
    }

    // tagEntity - id, tag, (notaEntity - id, titulo, nota, dataCriacao)


    @GetMapping("/materia")
    public ResponseEntity procurarNotaNomeMateria(
            @RequestParam("nomeMateria") String nomeMateria
    ) {
        List<AnotacaoEntity> notaEntity = anotacaoRepository.findAllByMateriaNome(nomeMateria);

        return ResponseEntity.ok(
                notaEntity);
    }
}
