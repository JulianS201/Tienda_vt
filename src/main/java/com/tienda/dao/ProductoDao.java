package com.tienda.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ProductoDao extends JpaRepository<Producto, Long> {

    //Consulta Ampliada, método que recupera los productos que están en un rango de precios
    public List<Producto> findByPrecioBetweenOrderByPrecioAsc(
            double precioInf, double precioSup
    );

//Consulta JPQL, método que recupera los productos que están en un rango de precios
    @Query(value = "SELECT a "
            + "FROM Producto a "
            + "WHERE a.precio "
            + "BETWEEN :precioInf AND :precioSup "
            + "ORDER BY a.precio ASC")
    public List<Producto> consultaJPQL(
            @Param("precioInf") double precioInf,
            @Param("precioSup") double precioSup
    );

//Consulta SQL, método que recupera los productos que están en un rango de precios
    @Query(nativeQuery = true,
            value = "SELECT * "
            + "FROM producto a "
            + "WHERE a.precio "
            + "BETWEEN :precioInf AND :precioSup "
            + "ORDER BY a.precio ASC")
    public List<Producto> consultaSQL(
            @Param("precioInf") double precioInf,
            @Param("precioSup") double precioSup);

}
