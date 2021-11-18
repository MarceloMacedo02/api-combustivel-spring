package br.com.zonesoftware.apicombustivel.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import br.com.zonesoftware.apicombustivel.dto.VeiculoSampleDTO;
import br.com.zonesoftware.apicombustivel.excptions.DatabaseException;
import br.com.zonesoftware.apicombustivel.excptions.ResourceNotFoundException;
import br.com.zonesoftware.apicombustivel.excptions.ViolationException;
import br.com.zonesoftware.apicombustivel.model.Veiculo;
import br.com.zonesoftware.apicombustivel.repository.VeiculoRepository;
import br.com.zonesoftware.apicombustivel.service.integration.RequestService;

@Service
public class VeiculoService extends AbstractService<Veiculo> {

	@Autowired
	private VeiculoRepository repository;

	@Autowired
	private RequestService requestService;

	@Override
	public VeiculoRepository getRepository() {
		return repository;
	}

	public List<Veiculo> findByPlacaContainingIgnoreCase(String placa) {
		return getRepository().findByPlacaContainingIgnoreCase(placa);
	}

	@Override
	public Veiculo save(Veiculo entity) {
		try {
			if (entity.getImei() != null && !entity.getImei().isEmpty() && entity.getChip() != null
					&& !entity.getChip().isEmpty()) {

				var device = Veiculo.Device.builder().name(entity.getDescricao()).uniqueId(entity.getImei())
						.phone(entity.getChip()).disabled("false").build();

				if (entity.getId() == null) {
					var created = requestService.request("devices", HttpMethod.POST, Veiculo.Device.class, device)
							.getBody();
					entity.setDeviceId(created.getId());
				} else {
					device.setId(entity.getDeviceId());
					requestService.request("devices/" + device.getId(), HttpMethod.PUT, Veiculo.Device.class, device);
				}

			}

			return super.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + entity.getId());
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Id not found " + entity.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("DviceId inválido");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("DviceId inválido");
		} catch (ConstraintViolationException e) {
			throw new ViolationException("Chip(" + "" + entity.getChip() + ") já existe", e.getSQLException());
		}

	}

	@Override
	public Veiculo insert(Veiculo entity) {
		try {
			if (entity.getImei() != null && !entity.getImei().isEmpty() && entity.getChip() != null
					&& !entity.getChip().isEmpty()) {

				var device = Veiculo.Device.builder().name(entity.getDescricao()).uniqueId(entity.getImei())
						.phone(entity.getChip()).disabled("false").build();
			}
			return super.insert(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + entity.getId());
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Id not found " + entity.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("DviceId inválido");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		} catch (DatabaseException e) {
			throw new DatabaseException(e.getMessage());
		} catch (ConstraintViolationException e) {
			throw new ViolationException("Chip(" + "" + entity.getChip() + ") já existe", e.getSQLException());
		}

	}

	@Override
	public Veiculo update(Long id, Veiculo entity) {
		try {
			if (entity.getImei() != null && !entity.getImei().isEmpty() && entity.getChip() != null
					&& !entity.getChip().isEmpty()) {

				var device = Veiculo.Device.builder().name(entity.getDescricao()).uniqueId(entity.getImei())
						.phone(entity.getChip()).disabled("false").build();
			}
			return super.update(id, entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + entity.getId());
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Id not found " + entity.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("DviceId inválido");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		} catch (DatabaseException e) {
			throw new DatabaseException(e.getMessage());
		} catch (ConstraintViolationException e) {
			throw new ViolationException("Chip(" + "" + entity.getChip() + ") já existe", e.getSQLException());
		}

	}

	@Override
	public Page<VeiculoSampleDTO> findAllPaged(String value, Pageable pageable) {
		Veiculo u = new Veiculo();
		u.setPlaca(value);
		Page<Veiculo> pages = findAllPaged("placa", u, pageable);
		Page<VeiculoSampleDTO> baseDtos = pages.map(x -> new VeiculoSampleDTO(x));
		return baseDtos;
	}

	public Page<VeiculoSampleDTO> findAllPagedStatus(String value, String ativo, Pageable pageable) {
		Veiculo u = new Veiculo();
//		u.setAtivo(ativo);
//		u.setPlaca(value);
		Page<Veiculo> pages = repository.findByPlacaContainingIgnoreCaseAndAtivo(value, ativo, pageable);
		Page<VeiculoSampleDTO> baseDtos = pages.map(x -> new VeiculoSampleDTO(x));
		return baseDtos;
	}
}
