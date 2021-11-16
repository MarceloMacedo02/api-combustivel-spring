package br.com.zonesoftware.apicombustivel.service;

import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.zonesoftware.apicombustivel.auth.UserSS;
import br.com.zonesoftware.apicombustivel.excptions.DatabaseException;
import br.com.zonesoftware.apicombustivel.excptions.ResourceNotFoundException;
import br.com.zonesoftware.apicombustivel.model.BaseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
/**
 * @param <T>
 * @author Daniel
 */

public abstract class AbstractService<T extends BaseEntity> {

	protected abstract JpaRepository getRepository();

//	public abstract Class getClasseDTO();

	@SuppressWarnings("unchecked")
	public Class<T> getClasse() {
		Class<T> classe = null;
		try {
			Class<T> class1 = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];

			classe = class1;
		} catch (Exception e) {
		}
		return classe;
	}

//	public D toDTO(T obj) {
//		D dto = null;
//		try {
//			dto = (D) getClasseDTO().getDeclaredConstructor().newInstance();
//		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
//				| NoSuchMethodException | SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		BeanUtils.copyProperties(obj, dto);
//		return dto;
//	}

	@Transactional
	public T update(Long id, T obj) {

		try {
			T thisobj = (T) getRepository().findById(id).get();
			BeanUtils.copyProperties(obj, thisobj, getNullPropertyNames(obj));
			obj = (T) getRepository().save(obj);
			return (obj);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	@Transactional
	public T insert(T obj) {
		obj = (T) getRepository().save(obj);
		return (obj);
	}

	public T save(T entity) {
		return (T) getRepository().save(entity);
	}

	@Transactional(readOnly = true)
	public List<T> findAll() {
		List<T> lista = getRepository().findAll();
		return lista;// .stream().map(x -> toDTO(x)).collect(Collectors.toList());
	}

	public void delete(Long id) {
		try {
			getRepository().deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	public T delete(T entity) {
		getRepository().delete(entity);
		return entity;
	}
	public abstract Page<?> findAllPaged(String value, Pageable pageable);
	
	@Transactional(readOnly = true)
	public Page<T> findAllPaged(String field,T obj, Pageable pageable) {
	  

		ExampleMatcher matcher = ExampleMatcher
				.matchingAll()
				.withMatcher(field, match -> match
						.contains()
						.ignoreCase())
				.withIgnoreNullValues() // ignore unset
				.withIgnoreCase() // properties when // finding
				.withIgnorePaths("id"); // ignore primitives as they default to 0

		Example<T> example = Example.of(obj, matcher);
		Page<T> pages = getRepository().findAll(example, pageable);

		// Page<D> baseDtos = p.map(x -> toDTO((T) x));

		return pages;
	}

	public Optional<T> findById(Long id) {
		return getRepository().findById(id);
	}

	public long count() {
		return getRepository().count();
	}

	// list null
	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
}