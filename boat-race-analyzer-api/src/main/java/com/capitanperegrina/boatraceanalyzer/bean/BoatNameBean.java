package com.capitanperegrina.boatraceanalyzer.bean;

/**
 * The Class BoatNameBean.
 */
public class BoatNameBean {

    /**
     * The boat name.
     */
    private String boatName;

    /**
     * The normalized boat name.
     */
    private String normalizedBoatName;

    /**
     * Instantiates a new boat name bean.
     *
     * @param boatName           the boat name
     * @param normalizedBoatName the normalized boat name
     */
    public BoatNameBean(final String boatName, final String normalizedBoatName) {
        super();
        this.boatName = boatName;
        this.normalizedBoatName = normalizedBoatName;
    }

    /**
     * Gets the boat name.
     *
     * @return the boat name
     */
    public String getBoatName() {
        return this.boatName;
    }

    /**
     * Sets the boat name.
     *
     * @param boatName the new boat name
     */
    public void setBoatName(final String boatName) {
        this.boatName = boatName;
    }

    /**
     * Gets the normalized boat name.
     *
     * @return the normalized boat name
     */
    public String getNormalizedBoatName() {
        return this.normalizedBoatName;
    }

    /**
     * Sets the normalized boat name.
     *
     * @param normalizedBoatName the new normalized boat name
     */
    public void setNormalizedBoatName(final String normalizedBoatName) {
        this.normalizedBoatName = normalizedBoatName;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "BoatNameBean [boatName=" + this.boatName + ", normalizedBoatName=" + this.normalizedBoatName + "]";
    }
}
