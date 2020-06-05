/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.tiendatecnologica.dominio;

/**
 *
 * @author abarrime
 */
public class Vendedor {

    private Producto producto;

    public Vendedor() {

    }

    public Vendedor(Producto producto){
        this.producto = producto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public void generarGarantia() {

    }

}
