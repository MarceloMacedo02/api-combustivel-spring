package br.com.zonesoftware.apicombustivel.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zonesoftware.apicombustivel.model.BaseEntity;
import br.com.zonesoftware.apicombustivel.service.AbstractService;
import io.swagger.annotations.ApiOperation;

public abstract class AbstractController<T extends BaseEntity> {

    /**
     * Usado para recupear o DAO associado as Sub Classes
     * de {@link AbstractController}
     *
     * @return
     */
    protected abstract AbstractService getService();

    /**
     * @param entity
     * @return
     */
    @ApiOperation(value="Salvar Entidade")
    @PostMapping
    public ResponseEntity salvar(@RequestBody
                                 @Valid T entity,
                                 BindingResult errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(getFieldValidationErrors(errors));
        }

        return new ResponseEntity(getService().save(entity), entity.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);

    }
    @PostMapping("/insert")
    @ApiOperation(value="Inserir Entidade")
	public ResponseEntity<Long> insert( @RequestBody    T obj) {
    	obj = ( T) getService().insert(obj);
		java.net.URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).body(obj.getId());
	}
    
    /**
     * @param id
     * @param entity
     * @return
     */
    @PutMapping(value = "/update/{id}")
    @ApiOperation(value="Atualizar Entidade")
	public ResponseEntity<T> update(@PathVariable Long id, 
			@RequestBody @Valid  T entity) {
    	entity = (@Valid T) getService().update(id, entity);
		return ResponseEntity.ok().body(entity);
	}
//    /**
//     * @param id
//     * @return
//     */
//    @DeleteMapping(value = "/{id}")
//	public ResponseEntity<Void> delete(@PathVariable Long id) {
//		getService().delete(id);
//		return ResponseEntity.noContent().build();
//	}
    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value="Excluir Entidade")
    public ResponseEntity excluir(@PathVariable("id") Long id) {

        T entity = (T) getService().findById(id).orElse(null);

        if (entity == null) {
            return ResponseEntity.notFound().build();
        }

        try{
//            entity = (T) getService().delete(entity);
        	getService().delete(id);
        }catch (org.springframework.dao.DataIntegrityViolationException ex){
            return ResponseEntity.badRequest().body(getGlobalError("Ops! Esse registro está sendo utilizado em outro cadastro"));
        }

        return ResponseEntity.ok(entity);

    }

    /**
     * Lista os objetos da entidade alvo
     *
     * @return
     */
    @GetMapping
    @ApiOperation(value="Listar todas entidades")
    public ResponseEntity<List<T>> listar() {
        return ResponseEntity.ok(getService().findAll());
    }
	@GetMapping("/pageable")
	public ResponseEntity<Page<?>> findAllPaged(@RequestParam(value = "value",
	defaultValue = "") String value,
			Pageable pageable) {
		Page<T> list = getService().findAllPaged( value.trim(), pageable);
		return ResponseEntity.ok().body(list);
	}
    /**
     * Buscar por id
     *
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value="encontrar entidade")
    public ResponseEntity<T> findById(@PathVariable("id") Long id) {
        T entity = (T) getService().findById(id).orElse(null);

        if (entity == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok((T) entity);
    }

    @GetMapping("/count")
    public long count(){
        return getService().count();
    }

    public Map<String, String> getFieldValidationErrors(Errors errors) {
        return errors.getFieldErrors()
                .stream()
                .collect(Collectors.toMap(e -> e.getField(), e -> e.getDefaultMessage()));
    }

    public Map<String, String> getGlobalError(String message) {
        return Map.of("error", message);
    }

}
