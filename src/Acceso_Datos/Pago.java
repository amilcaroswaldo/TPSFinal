/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acceso_Datos;

import java.util.Date;

/**
 *
 * @author Amilcar
 */
public class Pago {
    int idPago;
    Date fechaPago;

    public Pago() {
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    @Override
    public String toString() {
        return "Pago{" + "idPago=" + idPago + ", fechaPago=" + fechaPago + ", total=" + total + '}';
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    double total;

    public Pago(int idPago, Date fechaPago, double total) {
        this.idPago = idPago;
        this.fechaPago = fechaPago;
        this.total = total;
    }
}
