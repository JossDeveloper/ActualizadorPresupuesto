package pe.com.claro.eai.ws.actualizapresupuesto;

import javax.annotation.PostConstruct;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import pe.com.claro.eai.ws.actualizapresupuesto.service.ActualizaPresupuestoService;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6hudson-86
 * Generated source version: 2.2
 */
@WebService( portName = "ActualizaPresupuestoSB11", serviceName = "ActualizaPresupuesto", targetNamespace = "http://claro.com.pe/eai/ws/actualizapresupuesto", wsdlLocation = "/wsdls/ActualizaPresupuestoWS.wsdl", endpointInterface = "pe.com.claro.eai.ws.actualizapresupuesto.ActualizaPresupuestoWSPortType" )
@BindingType( "http://schemas.xmlsoap.org/wsdl/soap/http" )
public class ActualizaPresupuesto_ActualizaPresupuestoSB11Impl implements ActualizaPresupuestoWSPortType{
    
    public ActualizaPresupuesto_ActualizaPresupuestoSB11Impl(){}
    
    @Autowired
    ActualizaPresupuestoService service;
    
    @PostConstruct
    public void init(){
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    
    public ActualizarPresupuestoResponse actualizarPresupuesto( ActualizarPresupuestoRequest paremetersRequest ){
        return service.actualizarPresupuesto( paremetersRequest );
    }
}
