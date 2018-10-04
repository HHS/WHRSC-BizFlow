//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.03 at 10:07:04 PM EDT 
//


package gov.hhs.induction.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         		This type represents a range of PIV Person Identifier
 *         		(PI) values.  The range is assumed to be in ascending
 *         		numeric order.
 *         	
 * 
 * <p>Java class for PersonIdRangeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PersonIdRangeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FirstPI" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PersonIdType"/&gt;
 *         &lt;element name="LastPI" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PersonIdType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonIdRangeType", propOrder = {
    "firstPI",
    "lastPI"
})
public class PersonIdRangeType {

    @XmlElement(name = "FirstPI", required = true)
    protected String firstPI;
    @XmlElement(name = "LastPI", required = true)
    protected String lastPI;

    /**
     * Gets the value of the firstPI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstPI() {
        return firstPI;
    }

    /**
     * Sets the value of the firstPI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstPI(String value) {
        this.firstPI = value;
    }

    /**
     * Gets the value of the lastPI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastPI() {
        return lastPI;
    }

    /**
     * Sets the value of the lastPI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastPI(String value) {
        this.lastPI = value;
    }

}
