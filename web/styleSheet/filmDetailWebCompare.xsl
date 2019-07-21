<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : filmDetail.xsl
    Created on : July 12, 2019, 3:07 PM
    Author     : nhatc
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:ns1="http://www.film.com"
                xmlns:ns2="http://www.filmEvalute.com"
                xmlns:ns4="http://www.filmEvalutes.com"
                xmlns:ns3="http://www.website.com">
    <xsl:output method="html"/>

    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>
    <!--    <xsl:template match="ns4:ListFilmEvalute">
        <xsl:apply-templates/>
    </xsl:template>-->
    <!--    <xsl:template match="ns1:Film">
    </xsl:template>-->
    <xsl:template match="ns4:ListFilmEvalute">
        <div class="container">
            <div class="web-info">
                <xsl:for-each select="ns2:FilmEvalute">
                    <div class="web-container" >  
                        <h3 style="display: flex; justify-content: space-between;">      
                            <xsl:value-of select="ns3:Website/ns3:websiteName" />
                            
                            <div>
                                <label>Lượt xem: </label>
                                <span class="badge">   
                                    <xsl:value-of select="ns2:numberOfView" />
                                </span>
                            </div>
                        </h3>
                        <div>
                            đánh giá:   <span class="badge">
                                <xsl:value-of select="ns2:rated" />
                            </span>
                            <button style="margin-left: 5px;" onclick="goWebFilm('{ns2:url}')" class="btn">Xem Film Ở Web Này</button>
                        </div>
                        <br/>
                        <div>
                            <xsl:value-of select="ns2:description" />
                        </div>
                
                    </div>
                   
                </xsl:for-each>
            </div>
        </div>
    </xsl:template>
</xsl:stylesheet>
