package pe.com.claro.eai.ws.actualizapresupuesto.dto;

import java.io.Serializable;

public class DatosTransferencia implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String idAgrupadorPpto;
    private String idPresupuesto;
    private String numeroReserva;
    private String proveedorSAP;
    private String numeroPep;
    private String numeroSot;
    private String documentoPpto;
    private String mensajeSap;
    
    public String getIdAgrupadorPpto(){
        return idAgrupadorPpto;
    }
    public void setIdAgrupadorPpto( String idAgrupadorPpto ){
        this.idAgrupadorPpto = idAgrupadorPpto;
    }
    public String getIdPresupuesto(){
        return idPresupuesto;
    }
    public void setIdPresupuesto( String idPresupuesto ){
        this.idPresupuesto = idPresupuesto;
    }
    public String getNumeroReserva(){
        return numeroReserva;
    }
    public void setNumeroReserva( String numeroReserva ){
        this.numeroReserva = numeroReserva;
    }
    public String getProveedorSAP(){
        return proveedorSAP;
    }
    public void setProveedorSAP( String proveedorSAP ){
        this.proveedorSAP = proveedorSAP;
    }    
    public String getNumeroPep(){
        return numeroPep;
    }
    public void setNumeroPep( String numeroPep ){
        this.numeroPep = numeroPep;
    }
    public String getNumeroSot(){
        return numeroSot;
    }
    public void setNumeroSot( String numeroSot ){
        this.numeroSot = numeroSot;
    }
    public String getDocumentoPpto(){
        return documentoPpto;
    }
    public void setDocumentoPpto( String documentoPpto ){
        this.documentoPpto = documentoPpto;
    }
    public String getMensajeSap(){
        return mensajeSap;
    }    
    public void setMensajeSap( String mensajeSap ){
        this.mensajeSap = mensajeSap;
    }
    
}
