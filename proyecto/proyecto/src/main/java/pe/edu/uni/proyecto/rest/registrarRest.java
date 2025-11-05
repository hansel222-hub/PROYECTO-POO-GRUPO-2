package pe.edu.uni.proyecto.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.uni.proyecto.dto.nuevoProductoDto;
import pe.edu.uni.proyecto.service.procesoService;

import java.sql.SQLException;

@RestController
@RequestMapping("/TiendaDb/api/Procesos")
public class registrarRest {
    @Autowired
    private procesoService service;
    @PostMapping("/Registrar/Producto")
    public ResponseEntity<?> registrar(@RequestBody nuevoProductoDto bean){
        try {
            return ResponseEntity.ok(service.registrarProducto(bean));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR: " +  e.getMessage() );

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un problema al intentar matricular ,Contacte a soporte.");
        }
    }
}
