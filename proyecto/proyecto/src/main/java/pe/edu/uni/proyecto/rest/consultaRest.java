package pe.edu.uni.proyecto.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.uni.proyecto.dto.nuevoProductoDto;
import pe.edu.uni.proyecto.service.consultaService;

import java.util.List;

@RestController
@RequestMapping("/TiendaDb/api/productos")
public class consultaRest {
    @Autowired
    private consultaService service;
    @GetMapping("/Inventario/Disponible")
    public ResponseEntity<List<nuevoProductoDto>> listarConStock() {
        List<nuevoProductoDto> lista = service.listarConStock();
        return ResponseEntity.ok(lista);
    }
}
