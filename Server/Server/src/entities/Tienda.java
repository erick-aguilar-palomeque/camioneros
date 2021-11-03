/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author titof
 */
public class Tienda {
    private int idTienda;
    private String nombre;
    private String direccion;
    private String fechaAlta;
    private String usuarioAlta;
    private String fechaBaja;
    private String usuarioBaja;
    private String fechaModificacion;
    private String usuarioModificacion;
    private String claveEstado;

    public Tienda() {
    }

    public Tienda(int idTienda, String nombre, String direccion, String fechaAlta, String usuarioAlta, String fechaBaja, String usuarioBaja, String fechaModificacion, String usuarioModificacion, String claveEstado) {
        this.idTienda = idTienda;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
        this.usuarioAlta = usuarioAlta;
        this.fechaBaja = fechaBaja;
        this.usuarioBaja = usuarioBaja;
        this.fechaModificacion = fechaModificacion;
        this.usuarioModificacion = usuarioModificacion;
        this.claveEstado = claveEstado;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getUsuarioBaja() {
        return usuarioBaja;
    }

    public void setUsuarioBaja(String usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public String getClaveEstado() {
        return claveEstado;
    }

    public void setClaveEstado(String claveEstado) {
        this.claveEstado = claveEstado;
    }
    
    
}
