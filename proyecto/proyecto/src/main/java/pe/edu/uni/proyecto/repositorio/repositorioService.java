package pe.edu.uni.proyecto.repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.edu.uni.proyecto.dto.nuevoProductoDto;

import java.util.List;

@Repository
public class repositorioService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<nuevoProductoDto> listarProductosConStock(){
        String sql = """
            SELECT id_usuario, nombre AS nombProducto, codigo, stock_actual AS stock
            FROM PRODUCTO
            WHERE stock_actual > 0
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            nuevoProductoDto dto = new nuevoProductoDto();
            dto.setIdUsuario(rs.getInt("id_usuario"));
            dto.setNombProducto(rs.getString("nombProducto"));
            dto.setCodigo(rs.getString("codigo"));
            dto.setStock(rs.getInt("stock"));
            return dto;
        });
    }
}
