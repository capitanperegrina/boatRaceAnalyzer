package com.capitanperegrina.boatraceanalyzer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class IconsNamingService.
 */
@Component
public class IconsNamingService {

    /**
     * The Constant BALIZA_VERDE.
     */
    public static final Integer BALIZA_VERDE = 1;

    /**
     * The Constant BALIZA_ROJA.
     */
    public static final Integer BALIZA_ROJA = 2;

    /**
     * The Constant BALIZA_VERDE_ROJA.
     */
    public static final Integer BALIZA_VERDE_ROJA = 3;

    /**
     * The Constant BALIZA_ROJA_VERDE.
     */
    public static final Integer BALIZA_ROJA_VERDE = 4;

    /**
     * The Constant BALIZA_AMARILLA.
     */
    public static final Integer BALIZA_AMARILLA = 5;

    /**
     * The Constant CARDINAL_N.
     */
    public static final Integer CARDINAL_N = 6;

    /**
     * The Constant CARDINAL_E.
     */
    public static final Integer CARDINAL_E = 7;

    /**
     * The Constant CARDINAL_S.
     */
    public static final Integer CARDINAL_S = 8;

    /**
     * The Constant CARDINAL_W.
     */
    public static final Integer CARDINAL_W = 9;

    /**
     * The Constant BALIZA_PELIGRO_AISLADO.
     */
    public static final Integer BALIZA_PELIGRO_AISLADO = 10;

    /**
     * The Constant BALIZA_MARCA_ESPECIAL.
     */
    public static final Integer BALIZA_MARCA_ESPECIAL = 11;

    /**
     * The Constant BALIZA_AGUAS_NAVEGABLES.
     */
    public static final Integer BALIZA_AGUAS_NAVEGABLES = 12;

    /**
     * The Constant BOYA_AZUL.
     */
    public static final Integer BOYA_AZUL = 13;

    /**
     * The Constant BOYA_VERDE.
     */
    public static final Integer BOYA_VERDE = 14;

    /**
     * The Constant BOYA_ROJA.
     */
    public static final Integer BOYA_ROJA = 15;

    /**
     * The Constant BOYA_AMARILLA.
     */
    public static final Integer BOYA_AMARILLA = 16;

    /**
     * The Constant BOYA_NARANJA.
     */
    public static final Integer BOYA_NARANJA = 17;

    /**
     * The Constant COMITE.
     */
    public static final Integer COMITE = 18;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(IconsNamingService.class);

    /**
     * The icons base 64.
     */
    private final Map<Integer, String> iconsBase64 = new HashMap<>();

    /**
     * Inits the.
     */
    @PostConstruct
    public void init() {
        LOGGER.info("Cargando iconos...");
        final String folder = "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\img\\";
        for (int i = 1; i <= 18; i++) {
            this.iconsBase64.put(i, ExtendedFileUtils.image2base64(folder + String.format("%03d", i) + ".png"));
        }
        LOGGER.info("Cargando iconos hecho!");
    }

    /**
     * Gets the icon.
     *
     * @param i the i
     * @return the icon
     */
    public String getIcon(final Integer i) {
        return this.iconsBase64.get(i);
    }
}
