/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author desig
 */
public class Almacen {
    private int idAlmacen;
    private String nombre;
    private String direccion;
    private String  fechaAlta;
    private String usuarioAlta;
    private String fechaBaja;
    private String usuarioBaja;
    private String fechaModificacion;
    private String usuarioModificacion;
    private String claveEstado;

    /**
     * @return the idAlmacen
     */
    public Almacen() {
    }

    public Almacen(int idAlmacen, String nombre, String direccion, String fechaAlta, String usuarioAlta, String fechaBaja, String usuarioBaja, String fechaModificacion, String usuarioModificacion, String claveEstado) {
        this.idAlmacen = idAlmacen;
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
    
    public int getIdAlmacen() {
        return idAlmacen;
    }

    /**
     * @param idAlmacen the idAlmacen to set
     */
    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the fechaAlta
     */
    public String getFechaAlta() {
        return fechaAlta;
    }

    /**
     * @param fechaAlta the fechaAlta to set
     */
    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * @return the usuarioAlta
     */
    public String getUsuarioAlta() {
        return usuarioAlta;
    }

    /**
     * @param usuarioAlta the usuarioAlta to set
     */
    public void setUsuarioAlta(String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    /**
     * @return the fechaBaja
     */
    public String getFechaBaja() {
        return fechaBaja;
    }

    /**
     * @param fechaBaja the fechaBaja to set
     */
    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * @return the usuarioBaja
     */
    public String getUsuarioBaja() {
        return usuarioBaja;
    }

    /**
     * @param usuarioBaja the usuarioBaja to set
     */
    public void setUsuarioBaja(String usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    /**
     * @return the fechaModificacion
     */
    public String getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion the fechaModificacion to set
     */
    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * @return the usuarioModificacion
     */
    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    /**
     * @param usuarioModificacion the usuarioModificacion to set
     */
    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    /**
     * @return the claveEstado
     */
    public String getClaveEstado() {
        return claveEstado;
    }

    /**
     * @param claveEstado the claveEstado to set
     */
    public void setClaveEstado(String claveEstado) {
        this.claveEstado = claveEstado;
    }
    
}
