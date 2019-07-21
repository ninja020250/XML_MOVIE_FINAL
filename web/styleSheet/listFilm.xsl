<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : listFilm.xsl
    Created on : July 10, 2019, 4:31 PM
    Author     : nhatc
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:ns1="http://www.film.com"
                xmlns:ns2="http://www.films.com">
    <xsl:output method="html"  indent="yes"/>
    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>
    <xsl:template match="ns2:listFilm">
        <h1 style="padding-top: 10vh; padding-left: 2vw">Phim khuyến khích xem, Top film:</h1>
        <section class="film-container" >
            <xsl:for-each select="ns1:Film">
                <form action="FilmDetailServlet" onclick="goFilmDetail('{ns1:filmID}')">
                    <div class="film-container">
                        <div class="image-container">
                            <img src="{ns1:imageURL}"/>   
                        </div>
                        <div class="film-title">
                            <xsl:value-of select="ns1:filmName" />
                        </div>
                    </div>
                </form>
            </xsl:for-each>
        </section>
    </xsl:template>
</xsl:stylesheet>
