package pe.edu.uni.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class nuevoProductoDto {
    //variables
    private int idUsuario;
    private String nombProducto;
    private String nombCategoria;
    private double precio;
    private int stock;
    //variables de salida:
    private int idCategoria;
    private String codigo;

}
