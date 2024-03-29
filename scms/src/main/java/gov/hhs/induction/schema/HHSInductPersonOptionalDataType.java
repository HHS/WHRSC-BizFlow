//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.01.09 at 11:07:08 PM EST 
//


package gov.hhs.induction.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         		This Type defines the optional data items which may be 
 *         		included in an Induct Person request.
 *         	
 * 
 * <p>Java class for HHSInductPersonOptionalDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HHSInductPersonOptionalDataType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Prefix" type="{http://hspd12.hhs.gov/federated/enrollment/v1}NamePrefixType" minOccurs="0"/&gt;
 *         &lt;element name="MiddleName" type="{http://hspd12.hhs.gov/federated/enrollment/v1}MiddleNameType" minOccurs="0"/&gt;
 *         &lt;element name="GenerationQualifier" type="{http://hspd12.hhs.gov/federated/enrollment/v1}NameGenerationQualifierType" minOccurs="0"/&gt;
 *         &lt;element name="NameSuffix" type="{http://hspd12.hhs.gov/federated/enrollment/v1}NameSuffixType" minOccurs="0"/&gt;
 *         &lt;element name="CityOfBirth" type="{http://hspd12.hhs.gov/federated/enrollment/v1}CityOfBirthType" minOccurs="0"/&gt;
 *         &lt;element name="StateOfBirth" type="{http://hspd12.hhs.gov/federated/enrollment/v1}StateCodeType" minOccurs="0"/&gt;
 *         &lt;element name="CountryOfBirth" type="{http://hspd12.hhs.gov/federated/enrollment/v1}CountryCodeType" minOccurs="0"/&gt;
 *         &lt;element name="VisaNumber" type="{http://hspd12.hhs.gov/federated/enrollment/v1}VisaNumberType" minOccurs="0"/&gt;
 *         &lt;element name="ARN" type="{http://hspd12.hhs.gov/federated/enrollment/v1}AlienRegistrationNumberType" minOccurs="0"/&gt;
 *         &lt;element name="ForeignID" type="{http://hspd12.hhs.gov/federated/enrollment/v1}ForeignNationalIdInfoType" minOccurs="0"/&gt;
 *         &lt;element name="PositionTitle" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PositionTitleType" minOccurs="0"/&gt;
 *         &lt;element name="AlternateEmailAddress" type="{http://hspd12.hhs.gov/federated/enrollment/v1}EmailAddressType" minOccurs="0"/&gt;
 *         &lt;element name="WorkPhone" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="30"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="WorkPhoneExtension" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="8"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CostCenter" type="{http://hspd12.hhs.gov/federated/enrollment/v1}CostCenterType" minOccurs="0"/&gt;
 *         &lt;element name="SubAgency" type="{http://hspd12.hhs.gov/federated/enrollment/v1}SubAgencyType" minOccurs="0"/&gt;
 *         &lt;element name="FundsCertification" type="{http://hspd12.hhs.gov/federated/enrollment/v1}FundsCertificationType" minOccurs="0"/&gt;
 *         &lt;element name="SponsorshipStatus" type="{http://hspd12.hhs.gov/federated/enrollment/v1}StatusCodeType" minOccurs="0"/&gt;
 *         &lt;element name="SponsorPI" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PersonIdType" minOccurs="0"/&gt;
 *         &lt;element name="SponsorDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="InductorPI" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PersonIdType" minOccurs="0"/&gt;
 *         &lt;element name="InductorEmail" type="{http://hspd12.hhs.gov/federated/enrollment/v1}EmailAddressType" minOccurs="0"/&gt;
 *         &lt;element name="SACAdjudicationStatus" type="{http://hspd12.hhs.gov/federated/enrollment/v1}AdjudicationStatusCodeType" minOccurs="0"/&gt;
 *         &lt;element name="SACAdjudicatorPI" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PersonIdType" minOccurs="0"/&gt;
 *         &lt;element name="SACAdjudicationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="BkinvestAdjudicationStatus" type="{http://hspd12.hhs.gov/federated/enrollment/v1}AdjudicationStatusCodeType" minOccurs="0"/&gt;
 *         &lt;element name="BkinvestType" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PersonInvestigationCodeType" minOccurs="0"/&gt;
 *         &lt;element name="BkinvestAdjudicatorPI" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PersonIdType" minOccurs="0"/&gt;
 *         &lt;element name="BkinvestAdjudicationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="BadgeExpDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="PropertyPassCodes" type="{http://hspd12.hhs.gov/federated/enrollment/v1}PropertyPassCodeType" maxOccurs="2" minOccurs="0"/&gt;
 *         &lt;element name="SecAccessCodes" type="{http://hspd12.hhs.gov/federated/enrollment/v1}SecurityAccessCodeType" minOccurs="0"/&gt;
 *         &lt;element name="SOI" type="{http://hspd12.hhs.gov/federated/enrollment/v1}SOIType" minOccurs="0"/&gt;
 *         &lt;element name="SON" type="{http://hspd12.hhs.gov/federated/enrollment/v1}SONType" minOccurs="0"/&gt;
 *         &lt;element name="EnrollmentType" type="{http://hspd12.hhs.gov/federated/enrollment/v1}EnrollmentTypeCodeType" minOccurs="0"/&gt;
 *         &lt;element name="PivProcessFlowCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="1"/&gt;
 *               &lt;maxLength value="20"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="EarliestCardIssueDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="AlternateUID" type="{http://hspd12.hhs.gov/federated/enrollment/v1}ApplicantAlternateUIDType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="OPAC" type="{http://hspd12.hhs.gov/federated/enrollment/v1}OPACType" minOccurs="0"/&gt;
 *         &lt;element name="BkInvestigateOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="CardTypeCode" type="{http://hspd12.hhs.gov/federated/enrollment/v1}CardTypeCodeType" minOccurs="0"/&gt;
 *         &lt;element name="CardAssociation" type="{http://hspd12.hhs.gov/federated/enrollment/v1}CardAssociationType" minOccurs="0"/&gt;
 *         &lt;element name="AutoProduceCard" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="ObligatingDocumentNumber" type="{http://hspd12.hhs.gov/federated/enrollment/v1}ObligatingDocumentNumberType" minOccurs="0"/&gt;
 *         &lt;element name="CopsAgency" type="{http://hspd12.hhs.gov/federated/enrollment/v1}CopsAgencyType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HHSInductPersonOptionalDataType", propOrder = {
    "prefix",
    "middleName",
    "generationQualifier",
    "nameSuffix",
    "cityOfBirth",
    "stateOfBirth",
    "countryOfBirth",
    "visaNumber",
    "arn",
    "foreignID",
    "positionTitle",
    "alternateEmailAddress",
    "workPhone",
    "workPhoneExtension",
    "costCenter",
    "subAgency",
    "fundsCertification",
    "sponsorshipStatus",
    "sponsorPI",
    "sponsorDate",
    "inductorPI",
    "inductorEmail",
    "sacAdjudicationStatus",
    "sacAdjudicatorPI",
    "sacAdjudicationDate",
    "bkinvestAdjudicationStatus",
    "bkinvestType",
    "bkinvestAdjudicatorPI",
    "bkinvestAdjudicationDate",
    "badgeExpDate",
    "propertyPassCodes",
    "secAccessCodes",
    "soi",
    "son",
    "enrollmentType",
    "pivProcessFlowCode",
    "earliestCardIssueDate",
    "alternateUID",
    "opac",
    "bkInvestigateOnly",
    "cardTypeCode",
    "cardAssociation",
    "autoProduceCard",
    "obligatingDocumentNumber",
    "copsAgency"
})
public class HHSInductPersonOptionalDataType {

    @XmlElement(name = "Prefix")
    @XmlSchemaType(name = "string")
    protected NamePrefixType prefix;
    @XmlElement(name = "MiddleName")
    protected String middleName;
    @XmlElement(name = "GenerationQualifier")
    @XmlSchemaType(name = "string")
    protected NameGenerationQualifierType generationQualifier;
    @XmlElement(name = "NameSuffix")
    protected String nameSuffix;
    @XmlElement(name = "CityOfBirth")
    protected String cityOfBirth;
    @XmlElement(name = "StateOfBirth")
    @XmlSchemaType(name = "string")
    protected StateCodeType stateOfBirth;
    @XmlElement(name = "CountryOfBirth")
    @XmlSchemaType(name = "string")
    protected CountryCodeType countryOfBirth;
    @XmlElement(name = "VisaNumber")
    protected String visaNumber;
    @XmlElement(name = "ARN")
    protected String arn;
    @XmlElement(name = "ForeignID")
    protected ForeignNationalIdInfoType foreignID;
    @XmlElement(name = "PositionTitle")
    protected String positionTitle;
    @XmlElement(name = "AlternateEmailAddress")
    protected String alternateEmailAddress;
    @XmlElement(name = "WorkPhone")
    protected String workPhone;
    @XmlElement(name = "WorkPhoneExtension")
    protected String workPhoneExtension;
    @XmlElement(name = "CostCenter")
    protected String costCenter;
    @XmlElement(name = "SubAgency")
    protected String subAgency;
    @XmlElement(name = "FundsCertification")
    protected String fundsCertification;
    @XmlElement(name = "SponsorshipStatus")
    @XmlSchemaType(name = "string")
    protected StatusCodeType sponsorshipStatus;
    @XmlElement(name = "SponsorPI")
    protected String sponsorPI;
    @XmlElement(name = "SponsorDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar sponsorDate;
    @XmlElement(name = "InductorPI")
    protected String inductorPI;
    @XmlElement(name = "InductorEmail")
    protected String inductorEmail;
    @XmlElement(name = "SACAdjudicationStatus")
    @XmlSchemaType(name = "string")
    protected AdjudicationStatusCodeType sacAdjudicationStatus;
    @XmlElement(name = "SACAdjudicatorPI")
    protected String sacAdjudicatorPI;
    @XmlElement(name = "SACAdjudicationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar sacAdjudicationDate;
    @XmlElement(name = "BkinvestAdjudicationStatus")
    @XmlSchemaType(name = "string")
    protected AdjudicationStatusCodeType bkinvestAdjudicationStatus;
    @XmlElement(name = "BkinvestType")
    @XmlSchemaType(name = "string")
    protected PersonInvestigationCodeType bkinvestType;
    @XmlElement(name = "BkinvestAdjudicatorPI")
    protected String bkinvestAdjudicatorPI;
    @XmlElement(name = "BkinvestAdjudicationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar bkinvestAdjudicationDate;
    @XmlElement(name = "BadgeExpDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar badgeExpDate;
    @XmlElement(name = "PropertyPassCodes")
    @XmlSchemaType(name = "string")
    protected List<PropertyPassCodeType> propertyPassCodes;
    @XmlElement(name = "SecAccessCodes")
    @XmlSchemaType(name = "string")
    protected SecurityAccessCodeType secAccessCodes;
    @XmlElement(name = "SOI")
    protected String soi;
    @XmlElement(name = "SON")
    protected String son;
    @XmlElement(name = "EnrollmentType", defaultValue = "PIV-AutoProduceCard")
    @XmlSchemaType(name = "string")
    protected EnrollmentTypeCodeType enrollmentType;
    @XmlElement(name = "PivProcessFlowCode")
    protected String pivProcessFlowCode;
    @XmlElement(name = "EarliestCardIssueDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar earliestCardIssueDate;
    @XmlElement(name = "AlternateUID")
    protected List<ApplicantAlternateUIDType> alternateUID;
    @XmlElement(name = "OPAC")
    protected String opac;
    @XmlElement(name = "BkInvestigateOnly", defaultValue = "false")
    protected Boolean bkInvestigateOnly;
    @XmlElement(name = "CardTypeCode")
    @XmlSchemaType(name = "string")
    protected CardTypeCodeType cardTypeCode;
    @XmlElement(name = "CardAssociation")
    @XmlSchemaType(name = "string")
    protected CardAssociationType cardAssociation;
    @XmlElement(name = "AutoProduceCard", defaultValue = "true")
    protected Boolean autoProduceCard;
    @XmlElement(name = "ObligatingDocumentNumber")
    protected String obligatingDocumentNumber;
    @XmlElement(name = "CopsAgency")
    protected String copsAgency;

    /**
     * Gets the value of the prefix property.
     * 
     * @return
     *     possible object is
     *     {@link NamePrefixType }
     *     
     */
    public NamePrefixType getPrefix() {
        return prefix;
    }

    /**
     * Sets the value of the prefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link NamePrefixType }
     *     
     */
    public void setPrefix(NamePrefixType value) {
        this.prefix = value;
    }

    /**
     * Gets the value of the middleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * Gets the value of the generationQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link NameGenerationQualifierType }
     *     
     */
    public NameGenerationQualifierType getGenerationQualifier() {
        return generationQualifier;
    }

    /**
     * Sets the value of the generationQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameGenerationQualifierType }
     *     
     */
    public void setGenerationQualifier(NameGenerationQualifierType value) {
        this.generationQualifier = value;
    }

    /**
     * Gets the value of the nameSuffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameSuffix() {
        return nameSuffix;
    }

    /**
     * Sets the value of the nameSuffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameSuffix(String value) {
        this.nameSuffix = value;
    }

    /**
     * Gets the value of the cityOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityOfBirth() {
        return cityOfBirth;
    }

    /**
     * Sets the value of the cityOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityOfBirth(String value) {
        this.cityOfBirth = value;
    }

    /**
     * Gets the value of the stateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link StateCodeType }
     *     
     */
    public StateCodeType getStateOfBirth() {
        return stateOfBirth;
    }

    /**
     * Sets the value of the stateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateCodeType }
     *     
     */
    public void setStateOfBirth(StateCodeType value) {
        this.stateOfBirth = value;
    }

    /**
     * Gets the value of the countryOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link CountryCodeType }
     *     
     */
    public CountryCodeType getCountryOfBirth() {
        return countryOfBirth;
    }

    /**
     * Sets the value of the countryOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryCodeType }
     *     
     */
    public void setCountryOfBirth(CountryCodeType value) {
        this.countryOfBirth = value;
    }

    /**
     * Gets the value of the visaNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisaNumber() {
        return visaNumber;
    }

    /**
     * Sets the value of the visaNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisaNumber(String value) {
        this.visaNumber = value;
    }

    /**
     * Gets the value of the arn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getARN() {
        return arn;
    }

    /**
     * Sets the value of the arn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setARN(String value) {
        this.arn = value;
    }

    /**
     * Gets the value of the foreignID property.
     * 
     * @return
     *     possible object is
     *     {@link ForeignNationalIdInfoType }
     *     
     */
    public ForeignNationalIdInfoType getForeignID() {
        return foreignID;
    }

    /**
     * Sets the value of the foreignID property.
     * 
     * @param value
     *     allowed object is
     *     {@link ForeignNationalIdInfoType }
     *     
     */
    public void setForeignID(ForeignNationalIdInfoType value) {
        this.foreignID = value;
    }

    /**
     * Gets the value of the positionTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPositionTitle() {
        return positionTitle;
    }

    /**
     * Sets the value of the positionTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPositionTitle(String value) {
        this.positionTitle = value;
    }

    /**
     * Gets the value of the alternateEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlternateEmailAddress() {
        return alternateEmailAddress;
    }

    /**
     * Sets the value of the alternateEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlternateEmailAddress(String value) {
        this.alternateEmailAddress = value;
    }

    /**
     * Gets the value of the workPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkPhone() {
        return workPhone;
    }

    /**
     * Sets the value of the workPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkPhone(String value) {
        this.workPhone = value;
    }

    /**
     * Gets the value of the workPhoneExtension property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkPhoneExtension() {
        return workPhoneExtension;
    }

    /**
     * Sets the value of the workPhoneExtension property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkPhoneExtension(String value) {
        this.workPhoneExtension = value;
    }

    /**
     * Gets the value of the costCenter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCostCenter() {
        return costCenter;
    }

    /**
     * Sets the value of the costCenter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCostCenter(String value) {
        this.costCenter = value;
    }

    /**
     * Gets the value of the subAgency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubAgency() {
        return subAgency;
    }

    /**
     * Sets the value of the subAgency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubAgency(String value) {
        this.subAgency = value;
    }

    /**
     * Gets the value of the fundsCertification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundsCertification() {
        return fundsCertification;
    }

    /**
     * Sets the value of the fundsCertification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundsCertification(String value) {
        this.fundsCertification = value;
    }

    /**
     * Gets the value of the sponsorshipStatus property.
     * 
     * @return
     *     possible object is
     *     {@link StatusCodeType }
     *     
     */
    public StatusCodeType getSponsorshipStatus() {
        return sponsorshipStatus;
    }

    /**
     * Sets the value of the sponsorshipStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusCodeType }
     *     
     */
    public void setSponsorshipStatus(StatusCodeType value) {
        this.sponsorshipStatus = value;
    }

    /**
     * Gets the value of the sponsorPI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSponsorPI() {
        return sponsorPI;
    }

    /**
     * Sets the value of the sponsorPI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSponsorPI(String value) {
        this.sponsorPI = value;
    }

    /**
     * Gets the value of the sponsorDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSponsorDate() {
        return sponsorDate;
    }

    /**
     * Sets the value of the sponsorDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSponsorDate(XMLGregorianCalendar value) {
        this.sponsorDate = value;
    }

    /**
     * Gets the value of the inductorPI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInductorPI() {
        return inductorPI;
    }

    /**
     * Sets the value of the inductorPI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInductorPI(String value) {
        this.inductorPI = value;
    }

    /**
     * Gets the value of the inductorEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInductorEmail() {
        return inductorEmail;
    }

    /**
     * Sets the value of the inductorEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInductorEmail(String value) {
        this.inductorEmail = value;
    }

    /**
     * Gets the value of the sacAdjudicationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link AdjudicationStatusCodeType }
     *     
     */
    public AdjudicationStatusCodeType getSACAdjudicationStatus() {
        return sacAdjudicationStatus;
    }

    /**
     * Sets the value of the sacAdjudicationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjudicationStatusCodeType }
     *     
     */
    public void setSACAdjudicationStatus(AdjudicationStatusCodeType value) {
        this.sacAdjudicationStatus = value;
    }

    /**
     * Gets the value of the sacAdjudicatorPI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSACAdjudicatorPI() {
        return sacAdjudicatorPI;
    }

    /**
     * Sets the value of the sacAdjudicatorPI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSACAdjudicatorPI(String value) {
        this.sacAdjudicatorPI = value;
    }

    /**
     * Gets the value of the sacAdjudicationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSACAdjudicationDate() {
        return sacAdjudicationDate;
    }

    /**
     * Sets the value of the sacAdjudicationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSACAdjudicationDate(XMLGregorianCalendar value) {
        this.sacAdjudicationDate = value;
    }

    /**
     * Gets the value of the bkinvestAdjudicationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link AdjudicationStatusCodeType }
     *     
     */
    public AdjudicationStatusCodeType getBkinvestAdjudicationStatus() {
        return bkinvestAdjudicationStatus;
    }

    /**
     * Sets the value of the bkinvestAdjudicationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjudicationStatusCodeType }
     *     
     */
    public void setBkinvestAdjudicationStatus(AdjudicationStatusCodeType value) {
        this.bkinvestAdjudicationStatus = value;
    }

    /**
     * Gets the value of the bkinvestType property.
     * 
     * @return
     *     possible object is
     *     {@link PersonInvestigationCodeType }
     *     
     */
    public PersonInvestigationCodeType getBkinvestType() {
        return bkinvestType;
    }

    /**
     * Sets the value of the bkinvestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonInvestigationCodeType }
     *     
     */
    public void setBkinvestType(PersonInvestigationCodeType value) {
        this.bkinvestType = value;
    }

    /**
     * Gets the value of the bkinvestAdjudicatorPI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBkinvestAdjudicatorPI() {
        return bkinvestAdjudicatorPI;
    }

    /**
     * Sets the value of the bkinvestAdjudicatorPI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBkinvestAdjudicatorPI(String value) {
        this.bkinvestAdjudicatorPI = value;
    }

    /**
     * Gets the value of the bkinvestAdjudicationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBkinvestAdjudicationDate() {
        return bkinvestAdjudicationDate;
    }

    /**
     * Sets the value of the bkinvestAdjudicationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBkinvestAdjudicationDate(XMLGregorianCalendar value) {
        this.bkinvestAdjudicationDate = value;
    }

    /**
     * Gets the value of the badgeExpDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBadgeExpDate() {
        return badgeExpDate;
    }

    /**
     * Sets the value of the badgeExpDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBadgeExpDate(XMLGregorianCalendar value) {
        this.badgeExpDate = value;
    }

    /**
     * Gets the value of the propertyPassCodes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the propertyPassCodes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPropertyPassCodes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PropertyPassCodeType }
     * 
     * 
     */
    public List<PropertyPassCodeType> getPropertyPassCodes() {
        if (propertyPassCodes == null) {
            propertyPassCodes = new ArrayList<PropertyPassCodeType>();
        }
        return this.propertyPassCodes;
    }

    /**
     * Gets the value of the secAccessCodes property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityAccessCodeType }
     *     
     */
    public SecurityAccessCodeType getSecAccessCodes() {
        return secAccessCodes;
    }

    /**
     * Sets the value of the secAccessCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityAccessCodeType }
     *     
     */
    public void setSecAccessCodes(SecurityAccessCodeType value) {
        this.secAccessCodes = value;
    }

    /**
     * Gets the value of the soi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSOI() {
        return soi;
    }

    /**
     * Sets the value of the soi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSOI(String value) {
        this.soi = value;
    }

    /**
     * Gets the value of the son property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSON() {
        return son;
    }

    /**
     * Sets the value of the son property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSON(String value) {
        this.son = value;
    }

    /**
     * Gets the value of the enrollmentType property.
     * 
     * @return
     *     possible object is
     *     {@link EnrollmentTypeCodeType }
     *     
     */
    public EnrollmentTypeCodeType getEnrollmentType() {
        return enrollmentType;
    }

    /**
     * Sets the value of the enrollmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnrollmentTypeCodeType }
     *     
     */
    public void setEnrollmentType(EnrollmentTypeCodeType value) {
        this.enrollmentType = value;
    }

    /**
     * Gets the value of the pivProcessFlowCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPivProcessFlowCode() {
        return pivProcessFlowCode;
    }

    /**
     * Sets the value of the pivProcessFlowCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPivProcessFlowCode(String value) {
        this.pivProcessFlowCode = value;
    }

    /**
     * Gets the value of the earliestCardIssueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEarliestCardIssueDate() {
        return earliestCardIssueDate;
    }

    /**
     * Sets the value of the earliestCardIssueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEarliestCardIssueDate(XMLGregorianCalendar value) {
        this.earliestCardIssueDate = value;
    }

    /**
     * Gets the value of the alternateUID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alternateUID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlternateUID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApplicantAlternateUIDType }
     * 
     * 
     */
    public List<ApplicantAlternateUIDType> getAlternateUID() {
        if (alternateUID == null) {
            alternateUID = new ArrayList<ApplicantAlternateUIDType>();
        }
        return this.alternateUID;
    }

    /**
     * Gets the value of the opac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOPAC() {
        return opac;
    }

    /**
     * Sets the value of the opac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOPAC(String value) {
        this.opac = value;
    }

    /**
     * Gets the value of the bkInvestigateOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBkInvestigateOnly() {
        return bkInvestigateOnly;
    }

    /**
     * Sets the value of the bkInvestigateOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBkInvestigateOnly(Boolean value) {
        this.bkInvestigateOnly = value;
    }

    /**
     * Gets the value of the cardTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link CardTypeCodeType }
     *     
     */
    public CardTypeCodeType getCardTypeCode() {
        return cardTypeCode;
    }

    /**
     * Sets the value of the cardTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardTypeCodeType }
     *     
     */
    public void setCardTypeCode(CardTypeCodeType value) {
        this.cardTypeCode = value;
    }

    /**
     * Gets the value of the cardAssociation property.
     * 
     * @return
     *     possible object is
     *     {@link CardAssociationType }
     *     
     */
    public CardAssociationType getCardAssociation() {
        return cardAssociation;
    }

    /**
     * Sets the value of the cardAssociation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardAssociationType }
     *     
     */
    public void setCardAssociation(CardAssociationType value) {
        this.cardAssociation = value;
    }

    /**
     * Gets the value of the autoProduceCard property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoProduceCard() {
        return autoProduceCard;
    }

    /**
     * Sets the value of the autoProduceCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoProduceCard(Boolean value) {
        this.autoProduceCard = value;
    }

    /**
     * Gets the value of the obligatingDocumentNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObligatingDocumentNumber() {
        return obligatingDocumentNumber;
    }

    /**
     * Sets the value of the obligatingDocumentNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObligatingDocumentNumber(String value) {
        this.obligatingDocumentNumber = value;
    }

    /**
     * Gets the value of the copsAgency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCopsAgency() {
        return copsAgency;
    }

    /**
     * Sets the value of the copsAgency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCopsAgency(String value) {
        this.copsAgency = value;
    }

}
