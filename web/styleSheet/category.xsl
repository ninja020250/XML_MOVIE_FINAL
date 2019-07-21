<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : filmDetail.xsl
    Created on : July 12, 2019, 3:07 PM
    Author     : nhatc
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:ns1="http://www.film.com">
    <xsl:output method="html"/>

    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>
   
    <xsl:template match="ns1:Category">
        <div>
                  Thể loại: <select id="filter" onChange="filterData()">
            <xsl:for-each select="ns1:type">
                <option value="{text()}">   
                    <xsl:value-of select="text()" />
                </option>
            </xsl:for-each>
        </select>
        </div>
       
    </xsl:template>
   
</xsl:stylesheet>
