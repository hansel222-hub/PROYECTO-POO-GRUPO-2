package pe.edu.uni.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.uni.proyecto.dto.nuevoProductoDto;

import java.math.BigDecimal;
import java.sql.SQLException;

@Service
public class procesoService {
    @Autowired
    JdbcTemplate jdbctemplate;
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public nuevoProductoDto registrarProducto(nuevoProductoDto bean) throws SQLException {
        //variables
        String codigo;
        double  precio;
        int idCategoria;
        //procesos ,
        validarCategoria(bean.getNombCategoria().toUpperCase());
        validarPrecio(bean.getPrecio());
        validarStock(bean.getStock());
        idCategoria=obtenerIdCategoria(bean.getNombCategoria());
        bean.setIdCategoria(idCategoria);
        codigo=generarCodigo(bean.getIdCategoria(),bean.getNombCategoria());
        bean.setCodigo(codigo);
        registrarNuevoProducto(bean);
        return bean;
    }
    @Transactional(propagation = Propagation.MANDATORY,rollbackFor = Exception.class)
    private void registrarNuevoProducto(nuevoProductoDto bean) {
        //variables
        String sql= """
                insert into PRODUCTO(nombre,codigo,id_categoria,precio,stock_actual,fecha,id_usuario)
                values (?,?,?,?,?,GETDATE(),?)
                """;
        Object[] datos={bean.getNombProducto(),bean.getCodigo(),bean.getIdCategoria(),bean.getPrecio(),bean.getStock(),bean.getIdUsuario()};
        jdbctemplate.update(sql,datos);
    }

    @Transactional(propagation = Propagation.MANDATORY,rollbackFor = Exception.class)
    private void validarStock(int stock)throws SQLException {
        //variables
        if(stock<=0){
            throw new SQLException ("Cantidad Invalido.");
        }
    }
    @Transactional(propagation = Propagation.MANDATORY,rollbackFor = Exception.class)
    private void validarPrecio(double precio) throws SQLException {
        //variables
        if(precio <= 0){
            throw new SQLException ("Precio Invalido.");
        }
    }
    @Transactional(propagation = Propagation.MANDATORY,rollbackFor = Exception.class)
    private String generarCodigo(int idCategoria,String nombCategoria) {
        //variables
        String codigo;
        int cont;
        int cifrascont;
        String sql ="select count(*) cont  from PRODUCTO where id_categoria =?";
        //proceso
        codigo=nombCategoria.substring(0,3).toUpperCase();
        cont =jdbctemplate.queryForObject(sql, Integer.class,idCategoria) + 1;
        cifrascont=String.valueOf(cont).length();
        for(int i=1;i<=5-cifrascont;i++){
            codigo=codigo + "0";
        }
        codigo=codigo+cont;
        return codigo;

    }
    @Transactional(propagation = Propagation.MANDATORY,rollbackFor = Exception.class)
    private int obtenerIdCategoria(String nombCategoria) {
        //Vvariables
        int cont=0;
        String sql=" select id_categoria  from CATEGORIA where nombre=?";
        //proceso
        cont=jdbctemplate.queryForObject(sql, Integer.class,nombCategoria);
        return cont;
    }
    @Transactional(propagation = Propagation.MANDATORY,rollbackFor = Exception.class)
    private void validarCategoria(String nombCategoria) throws SQLException {
        //Vvariables
        int cont=0;
        String sql=" select COUNT(*) cont from CATEGORIA where nombre=?";
        //proceso
       cont=jdbctemplate.queryForObject(sql, Integer.class,nombCategoria);
       if(cont==0){
           throw new SQLException("Categoria no existente .");
       }
    }
}
