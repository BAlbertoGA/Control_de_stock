package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.Producto;

public class ProductoDAO {
	
	final private Connection con;
	
	public ProductoDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Producto producto) {
        
        try{
            

            PreparedStatement statement;
            statement = con.prepareStatement(
                    "INSERT INTO PRODUCTO "
                        + "(nombre, descripcion, cantidad, categoria_id)"
                        + " VALUES (?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
            
            try (statement){
            	statement.setString(1, producto.getNombre());
            	statement.setString(2, producto.getDescripcion());
            	statement.setInt(3, producto.getCantidad());
            	statement.setInt(4, producto.getCategoriaID());
            	
            	statement.execute();
            	
            	final ResultSet resultSet = statement.getGeneratedKeys();
            	
            	try (resultSet) {
            		while (resultSet.next()) {
            			producto.setId(resultSet.getInt(1));
            			
            			System.out.println(String.format("Fue insertado el producto: %s", producto));
            		}
            	}
            }
        } catch (SQLException e) {
        	throw new RuntimeException(e);
        }
	}
	

	public List<Producto> listar() {
		List<Producto> resultado = new ArrayList<>();
        
        ConnectionFactory factory = new ConnectionFactory();
        final Connection con = factory.recuperaConexion();
        
        try(con) {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
            
            try(statement) {
                statement.execute();
        
                final ResultSet resultSet = statement.getResultSet();
                
                try(resultSet) {
                    while (resultSet.next()) {
                        Producto fila = new Producto(resultSet.getInt("ID"),
                        		resultSet.getString("nombre"),
                        		resultSet.getString("descripcion"),
                        		resultSet.getInt("cantidad"));
            
                        resultado.add(fila);
                    }
                }
            }
    
            return resultado;
        } catch (SQLException e) {
        	throw new RuntimeException(e);
        }
	}

	public List<Producto> listar(Categoria categoria) {
		List<Producto> resultado = new ArrayList<>();

        try {
            String sql = "SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD "
            + " FROM PRODUCTO WHERE CATEGORIA_ID = ?";
            System.out.println(sql);
            
            final PreparedStatement statement = con.prepareStatement(
                    sql);
    
            try (statement) {
                statement.setInt(1, categoria.getId());
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Producto(
                                resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("DESCRIPCION"),
                                resultSet.getInt("CANTIDAD")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
    
    
    
    
}
