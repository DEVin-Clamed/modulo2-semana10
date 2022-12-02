package com.example.caderno.controller;

import com.example.caderno.controller.dto.MateriaRequest;
import com.example.caderno.controller.dto.MateriaResponse;
import com.example.caderno.dataprovider.entity.AnotacaoEntity;
import com.example.caderno.dataprovider.entity.MateriaEntity;
import com.example.caderno.dataprovider.repository.MateriaRepository;
import com.example.caderno.dataprovider.repository.AnotacaoRepository;
import com.example.caderno.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // controller REST, sem telas
@RequestMapping("/materia")
public class MateriaController {

    private final MateriaRepository materiaRepository;
    private final AnotacaoRepository anotacaoRepository;

    public MateriaController(MateriaRepository materiaRepository, AnotacaoRepository anotacaoRepository) {
        this.materiaRepository = materiaRepository;
        this.anotacaoRepository = anotacaoRepository;
    }

    @GetMapping
    public ResponseEntity retornaMaterias(){
        List<MateriaEntity> materiaEntityList = materiaRepository.findAll();
        List<MateriaResponse> materiaResponseList = new ArrayList<>();
        for (MateriaEntity entity :
                materiaEntityList) {
            materiaResponseList.add(new MateriaResponse(entity.getId(),entity.getNome()));
        }
        return ResponseEntity.ok(materiaResponseList);
    }

    @PostMapping
    public ResponseEntity<MateriaResponse> salvaMateria(@RequestBody MateriaRequest request) {
        MateriaEntity entity = materiaRepository.save(new MateriaEntity(request.getNome()));

        return new ResponseEntity<MateriaResponse>(
                new MateriaResponse(entity.getId(), entity.getNome()),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}/notas")
    public ResponseEntity retornaNotasMateria(@PathVariable Long id){

        List<AnotacaoEntity> anotacaoEntities =
                anotacaoRepository.findAnotacaoEntitiesByMateriaEntity_Id(id);


        return ResponseEntity.ok(anotacaoEntities);
    }

    @GetMapping("/{id}")
    public ResponseEntity retornaMateriaPorId(@PathVariable Long id){

        MateriaEntity materiaEntity = materiaRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Materia não encontrar pelo id: " + id));


        return ResponseEntity.ok(materiaEntity);
    }
}
