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
 *         &lt;element name="ResultCode"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Success"/&gt;
 *               &lt;enumeration value="NotFound"/&gt;
 *               &lt;enumeration value="Reject-InvalidData"/&gt;
 *               &lt;enumeration value="Reject-Duplicate"/&gt;
 *               &lt;enumeration value="Reject-MissingData"/&gt;
 *               &lt;enumeration value="Failed"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="EffectiveIdentityProofLevel" type="{http://hspd12.hhs.gov/scms/idProofing/v1}IdentityProofingLevelType" minOccurs="0"/&gt;
 *         &lt;element name="AssignedPI" type="{http://hspd12.hhs.gov/scms/idProofing/v1}HHSIdType" minOccurs="0"/&gt;
 *         &lt;element name="AssignedUserName" type="{http://hspd12.hhs.gov/scms/idProofing/v1}UserNameType" minOccurs="0"/&gt;
 *         &lt;element name="ResultMessage" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="256"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="FailureDetailMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "resultCode",
    "effectiveIdentityProofLevel",
    "assignedPI",
    "assignedUserName",
    "resultMessage",
    "failureDetailMessage"
})
@XmlRootElement(name = "SetPersonDataResponse", namespace = "http://hspd12.hhs.gov/scms/idProofing/v1")
public class SetPersonDataResponse {

    @XmlElement(name = "ResultCode", namespace = "http://hspd12.hhs.gov/scms/idProofing/v1", required = true)
    protected String resultCode;
    @XmlElement(name = "EffectiveIdentityProofLevel", namespace = "http://hspd12.hhs.gov/scms/idProofing/v1")
    protected Integer effectiveIdentityProofLevel;
    @XmlElement(name = "AssignedPI", namespace = "http://hspd12.hhs.gov/scms/idProofing/v1")
    protected String assignedPI;
    @XmlElement(name = "AssignedUserName", namespace = "http://hspd12.hhs.gov/scms/idProofing/v1")
    protected String assignedUserName;
    @XmlElement(name = "ResultMessage", namespace = "http://hspd12.hhs.gov/scms/idProofing/v1")
    protected String resultMessage;
    @XmlElement(name = "FailureDetailMessage", namespace = "http://hspd12.hhs.gov/scms/idProofing/v1")
    protected String failureDetailMessage;

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultCode(String value) {
        this.resultCode = value;
    }

    /**
     * Gets the value of the effectiveIdentityProofLevel property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEffectiveIdentityProofLevel() {
        return effectiveIdentityProofLevel;
    }

    /**
     * Sets the value of the effectiveIdentityProofLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEffectiveIdentityProofLevel(Integer value) {
        this.effectiveIdentityProofLevel = value;
    }

    /**
     * Gets the value of the assignedPI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssignedPI() {
        return assignedPI;
    }

    /**
     * Sets the value of the assignedPI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssignedPI(String value) {
        this.assignedPI = value;
    }

    /**
     * Gets the value of the assignedUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssignedUserName() {
        return assignedUserName;
    }

    /**
     * Sets the value of the assignedUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssignedUserName(String value) {
        this.assignedUserName = value;
    }

    /**
     * Gets the value of the resultMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * Sets the value of the resultMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultMessage(String value) {
        this.resultMessage = value;
    }

    /**
     * Gets the value of the failureDetailMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFailureDetailMessage() {
        return failureDetailMessage;
    }

    /**
     * Sets the value of the failureDetailMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFailureDetailMessage(String value) {
        this.failureDetailMessage = value;
    }

}
