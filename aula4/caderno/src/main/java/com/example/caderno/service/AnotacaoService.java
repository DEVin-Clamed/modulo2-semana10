package com.example.caderno.service;

import com.example.caderno.controller.dto.AnotacaoRequest;
import com.example.caderno.controller.dto.AnotacaoResponse;
import com.example.caderno.controller.dto.MateriaResponse;
import com.example.caderno.dataprovider.entity.AnotacaoEntity;
import com.example.caderno.dataprovider.entity.MateriaEntity;
import com.example.caderno.dataprovider.repository.AnotacaoRepository;
import com.example.caderno.dataprovider.repository.MateriaRepository;
import com.example.caderno.dataprovider.repository.TagRepository;
import com.example.caderno.exception.NotFoundException;
import com.example.caderno.exception.ServerSideException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnotacaoService {

    private final AnotacaoRepository anotacaoRepository;
    private final MateriaRepository materiaRepository;
    private final TagRepository tagRepository;

    public AnotacaoService(AnotacaoRepository anotacaoRepository, MateriaRepository materiaRepository, TagRepository tagRepository) {
        this.anotacaoRepository = anotacaoRepository;
        this.materiaRepository = materiaRepository;
        this.tagRepository = tagRepository;
    }

    public List<AnotacaoResponse> encontraTodasNotas() {
        List<AnotacaoEntity> entityList = anotacaoRepository.findAll();

        List<AnotacaoResponse> responseList = new ArrayList<>();

        for (AnotacaoEntity entity : entityList) { // transforma AnotacaoEntity em AnotacaoResponse
            responseList.add(
                    new AnotacaoResponse(
                            entity.getId(),
                            entity.getTitulo(),
                            entity.getTexto(),
                            new MateriaResponse(
                                    entity.getMateriaEntity().getId(),
                                    entity.getMateriaEntity().getNome()
                            ))
            );
        }

        return responseList;
    }

    public AnotacaoResponse salvarNovaNota(AnotacaoRequest anotacaoRequest) {
        try {
            MateriaEntity materiaEntity = materiaRepository.findById(anotacaoRequest.getIdMateria()) // procura a matéria pelo id da requisição
                    .orElseThrow(() -> new NotFoundException("Materia nao encontrada")); // caso a materia não exista o programa irá gerar um erro NotFoundException

//        if (materiaEntity == null){
//            throw new NotFoundException("Materia nao encontrada");
//        }

            AnotacaoEntity entity = anotacaoRepository.save(new AnotacaoEntity(anotacaoRequest.getTitulo()
                    , anotacaoRequest.getTexto()
                    , materiaEntity
            ));
            return new AnotacaoResponse(
                    entity.getId(),
                    entity.getTitulo(),
                    entity.getTexto(),
                    new MateriaResponse(
                            entity.getMateriaEntity().getId(),
                            entity.getMateriaEntity().getNome()));
        }catch (NotFoundException e){
            throw e; // passa adiante o erro NotFoundException
        }catch (Exception e){
            throw new ServerSideException("Erro ao salvar nota, mensagem localizada:" + e.getLocalizedMessage());
            //Erro que é passado caso tenhamos qualquer Exception(que não seja NotFoundException) no programa acima
        }
    }
}
