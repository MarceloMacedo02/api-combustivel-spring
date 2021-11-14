package br.com.zonesoftware.apicombustivel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import br.com.zonesoftware.apicombustivel.dto.UsuarioDTO;
import br.com.zonesoftware.apicombustivel.dto.VeiculoSampleDTO;
import br.com.zonesoftware.apicombustivel.model.Usuario;
import br.com.zonesoftware.apicombustivel.model.Veiculo;
import br.com.zonesoftware.apicombustivel.repository.VeiculoRepository;
import br.com.zonesoftware.apicombustivel.service.integration.RequestService;

import java.util.List;

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

        if (entity.getImei() != null
                && !entity.getImei().isEmpty()
                && entity.getChip() != null
                && !entity.getChip().isEmpty()) {

            var device = Veiculo.Device.builder()
                    .name(entity.getDescricao())
                    .uniqueId(entity.getImei())
                    .phone(entity.getChip())
                    .disabled("false")
                    .build();

            if (entity.getId() == null) {
                var created = requestService.request("devices", HttpMethod.POST, Veiculo.Device.class, device).getBody();
                entity.setDeviceId(created.getId());
            } else {
                device.setId(entity.getDeviceId());
                requestService.request("devices/" + device.getId(), HttpMethod.PUT, Veiculo.Device.class, device);
            }

        }

        return super.save(entity);
    }

    @Override
    public Veiculo insert(Veiculo entity) {
        if (entity.getImei() != null
                && !entity.getImei().isEmpty()
                && entity.getChip() != null
                && !entity.getChip().isEmpty()) {

            var device = Veiculo.Device.builder()
                    .name(entity.getDescricao())
                    .uniqueId(entity.getImei())
                    .phone(entity.getChip())
                    .disabled("false")
                    .build();
        }
    	return super.insert(entity);
    }
    @Override
    public Veiculo update(Long id, Veiculo entity) {
    	 if (entity.getImei() != null
                 && !entity.getImei().isEmpty()
                 && entity.getChip() != null
                 && !entity.getChip().isEmpty()) {

             var device = Veiculo.Device.builder()
                     .name(entity.getDescricao())
                     .uniqueId(entity.getImei())
                     .phone(entity.getChip())
                     .disabled("false")
                     .build();
         }
    	return super.update(id, entity);
    }
	@Override
	public Page<VeiculoSampleDTO> findAllPaged(String value, Pageable pageable) {
		Veiculo u = new Veiculo();
		u.setPlaca(value);
		Page<Veiculo> pages = findAllPaged("nome", u, pageable);
		Page<VeiculoSampleDTO> baseDtos = pages.map(x -> new VeiculoSampleDTO(x));
		return baseDtos;
	}
	public Page<VeiculoSampleDTO> findAllPagedStatus(String value,String ativo, Pageable pageable) {
		Veiculo u = new Veiculo();
		u.setAtivo(ativo);
		u.setPlaca(value);
		Page<Veiculo> pages = findAllPaged("nome", u, pageable);
		Page<VeiculoSampleDTO> baseDtos = pages.map(x -> new VeiculoSampleDTO(x));
		return baseDtos;
	}
}
