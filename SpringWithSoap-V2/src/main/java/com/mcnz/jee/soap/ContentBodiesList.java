//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.12.07 at 12:01:20 AM EET 
//


package com.mcnz.jee.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContentBodiesList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContentBodiesList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ContentBodyInfo" type="{http://soap.jee.mcnz.com/}ContentBodyInfo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContentBodiesList", propOrder = {
    "contentBodyInfo"
})
public class ContentBodiesList {

    @XmlElement(name = "ContentBodyInfo", required = true)
    protected ContentBodyInfo contentBodyInfo;

    /**
     * Gets the value of the contentBodyInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ContentBodyInfo }
     *     
     */
    public ContentBodyInfo getContentBodyInfo() {
        return contentBodyInfo;
    }

    /**
     * Sets the value of the contentBodyInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContentBodyInfo }
     *     
     */
    public void setContentBodyInfo(ContentBodyInfo value) {
        this.contentBodyInfo = value;
    }

}
