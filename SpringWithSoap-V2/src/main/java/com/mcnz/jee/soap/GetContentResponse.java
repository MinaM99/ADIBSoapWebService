//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.12.04 at 03:23:22 PM EET 
//


package com.mcnz.jee.soap;

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
 *         &lt;element name="ContentURLsList" type="{http://soap.jee.mcnz.com/}ContentURLsList"/&gt;
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
    "contentURLsList"
})
@XmlRootElement(name = "GetContentResponse")
public class GetContentResponse {

    @XmlElement(name = "ContentURLsList", required = true)
    protected ContentURLsList contentURLsList;

    /**
     * Gets the value of the contentURLsList property.
     * 
     * @return
     *     possible object is
     *     {@link ContentURLsList }
     *     
     */
    public ContentURLsList getContentURLsList() {
        return contentURLsList;
    }

    /**
     * Sets the value of the contentURLsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContentURLsList }
     *     
     */
    public void setContentURLsList(ContentURLsList value) {
        this.contentURLsList = value;
    }

}
