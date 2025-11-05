package pe.edu.uni.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.proyecto.dto.nuevoProductoDto;
import pe.edu.uni.proyecto.repositorio.repositorioService;
import java.util.List;

@Service
public class consultaService {
    @Autowired
    private repositorioService repo;

    public List<nuevoProductoDto> listarConStock() {
        return repo.listarProductosConStock();
    }
}
