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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TransactionHeader" type="{http://hspd12.hhs.gov/federated/enrollment/v1}TransactionHeaderType"/&gt;
 *         &lt;element name="CardHolderPI" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PersonIdType"/&gt;
 *         &lt;element name="PIVReactivationReason" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PIVReactivationReasonCodeType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "transactionHeader",
    "cardHolderPI",
    "pivReactivationReason"
})
@XmlRootElement(name = "PIVReactivationRequest")
public class PIVReactivationRequest {

    @XmlElement(name = "TransactionHeader", required = true)
    protected TransactionHeaderType transactionHeader;
    @XmlElement(name = "CardHolderPI", required = true)
    protected String cardHolderPI;
    @XmlElement(name = "PIVReactivationReason", required = true)
    @XmlSchemaType(name = "string")
    protected PIVReactivationReasonCodeType pivReactivationReason;

    /**
     * Gets the value of the transactionHeader property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionHeaderType }
     *     
     */
    public TransactionHeaderType getTransactionHeader() {
        return transactionHeader;
    }

    /**
     * Sets the value of the transactionHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionHeaderType }
     *     
     */
    public void setTransactionHeader(TransactionHeaderType value) {
        this.transactionHeader = value;
    }

    /**
     * Gets the value of the cardHolderPI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardHolderPI() {
        return cardHolderPI;
    }

    /**
     * Sets the value of the cardHolderPI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardHolderPI(String value) {
        this.cardHolderPI = value;
    }

    /**
     * Gets the value of the pivReactivationReason property.
     * 
     * @return
     *     possible object is
     *     {@link PIVReactivationReasonCodeType }
     *     
     */
    public PIVReactivationReasonCodeType getPIVReactivationReason() {
        return pivReactivationReason;
    }

    /**
     * Sets the value of the pivReactivationReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link PIVReactivationReasonCodeType }
     *     
     */
    public void setPIVReactivationReason(PIVReactivationReasonCodeType value) {
        this.pivReactivationReason = value;
    }

}