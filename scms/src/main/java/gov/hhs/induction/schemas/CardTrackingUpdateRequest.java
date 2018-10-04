//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.03 at 10:07:04 PM EDT 
//


package gov.hhs.induction.schemas;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &lt;element name="CardBarCodeData" maxOccurs="unbounded"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="256"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="PackageTrackingID"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="128"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CardReceivedLocation" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CardHandlerPI" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PersonIdType"/&gt;
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
    "cardBarCodeData",
    "packageTrackingID",
    "cardReceivedLocation",
    "cardHandlerPI"
})
@XmlRootElement(name = "CardTrackingUpdateRequest")
public class CardTrackingUpdateRequest {

    @XmlElement(name = "TransactionHeader", required = true)
    protected TransactionHeaderType transactionHeader;
    @XmlElement(name = "CardBarCodeData", required = true)
    protected List<String> cardBarCodeData;
    @XmlElement(name = "PackageTrackingID", required = true)
    protected String packageTrackingID;
    @XmlElement(name = "CardReceivedLocation", required = true)
    protected String cardReceivedLocation;
    @XmlElement(name = "CardHandlerPI", required = true)
    protected String cardHandlerPI;

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
     * Gets the value of the cardBarCodeData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cardBarCodeData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCardBarCodeData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCardBarCodeData() {
        if (cardBarCodeData == null) {
            cardBarCodeData = new ArrayList<String>();
        }
        return this.cardBarCodeData;
    }

    /**
     * Gets the value of the packageTrackingID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageTrackingID() {
        return packageTrackingID;
    }

    /**
     * Sets the value of the packageTrackingID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageTrackingID(String value) {
        this.packageTrackingID = value;
    }

    /**
     * Gets the value of the cardReceivedLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardReceivedLocation() {
        return cardReceivedLocation;
    }

    /**
     * Sets the value of the cardReceivedLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardReceivedLocation(String value) {
        this.cardReceivedLocation = value;
    }

    /**
     * Gets the value of the cardHandlerPI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardHandlerPI() {
        return cardHandlerPI;
    }

    /**
     * Sets the value of the cardHandlerPI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardHandlerPI(String value) {
        this.cardHandlerPI = value;
    }

}
