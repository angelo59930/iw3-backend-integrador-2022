package iua.kaf.Backend.integration.Cli1.model;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import iua.kaf.Backend.model.business.ICamionBusiness;
import iua.kaf.Backend.model.business.IChoferBusiness;
import iua.kaf.Backend.model.business.IClienteBusiness;
import iua.kaf.Backend.model.business.IProductoBusiness;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.util.JsonUtiles;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdenCli1JsonDeserializer extends StdDeserializer<OrdenCli1> {

    private static final long serialVersionUID = -2316126911378870652L;

    protected OrdenCli1JsonDeserializer(Class<?> vc) {
        super(vc);
    }

    private ICamionBusiness camionBusiness;
    private IChoferBusiness choferBusiness;
    private IClienteBusiness clienteBusiness;
    private IProductoBusiness productoBusiness;

    public OrdenCli1JsonDeserializer(Class<?> vc, ICamionBusiness camionBusiness, IChoferBusiness choferBusiness,
            IClienteBusiness clienteBusiness, IProductoBusiness productoBusiness) {
        super(vc);

        this.camionBusiness = camionBusiness;
        this.choferBusiness = choferBusiness;
        this.clienteBusiness = clienteBusiness;
        this.productoBusiness = productoBusiness;
    }

    public OrdenCli1 deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        OrdenCli1 r = new OrdenCli1();
        JsonNode node = jp.getCodec().readTree(jp);

        // Este es nuestro codigo externo que vamos pasar entre distintas entidades
        String codigoExterno = JsonUtiles.getString(node,
                "orden_codigoExterno,codigoExterno_orden,codigoExterno".split(","),
                System.currentTimeMillis() + "");

        String numeroOrden = JsonUtiles.getString(node, "orden_numeroOrden,numeroOrden_orden,numeroOrden".split(","), null);

        Long parseNumOrden = Long.parseLong(numeroOrden);

        r.setNumeroOrden(parseNumOrden);

        String patente = JsonUtiles.getString(node, "orden_patente,patente_orden,patente".split(","), null);
        if (patente != null) {
            try {
                r.setCamion(camionBusiness.load(patente));
            } catch (NotFoundException | BusinessException e) {

            }
        }

        String producto = JsonUtiles.getString(node, "orden_producto,producto_orden,producto".split(","), null);
        if (producto != null) {
            try {
                r.setProducto(productoBusiness.load(producto));
            } catch (NotFoundException | BusinessException e) {

            }
        }

        String dniChofer = JsonUtiles.getString(node, "orden_dniChofer,dniChofer_orden,dniChofer".split(","), null);

        long dni = Long.parseLong(dniChofer);

        if (dni != 0) {
            try {
                r.setChofer(choferBusiness.load(dni));
            } catch (NotFoundException | BusinessException e) {

            }
        }

        String nombreCliente = JsonUtiles.getString(node,
                "orden_nombreCliente,nombreCliente_orden,nombreCliente,cliente".split(","), null);
        if (nombreCliente != null) {
            try {
                r.setCliente(clienteBusiness.load(nombreCliente));
            } catch (NotFoundException | BusinessException e) {

            }
        }

        String fechaCargaPrevista = JsonUtiles.getString(node,
                "orden_fechaCargaPrevista,fechaCargaPrevista_orden,fechaCargaPrevista".split(","), null);
        Date date1 = null;

        try {
            date1 = new SimpleDateFormat("dd-MM-yyyy").parse(fechaCargaPrevista);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        r.setFechaCargaPrevista(date1);

        String preset = JsonUtiles.getString(node, "preset_orden,orden_preset,preset".split(","), null);

        double parsePreset = Double.parseDouble(preset);

        r.setPreset(parsePreset);

        r.setCodeCli1(codigoExterno);

        return r;
    }

}