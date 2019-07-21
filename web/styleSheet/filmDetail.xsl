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
    <xsl:template match="ns1:Film">
        
        <div class="banner" style="background-image: url('{ns1:imageURL}')">
            <!--            <img src="{ns1:imageURL}" alt="banner"/>-->
        </div>
        <div class="container">
            <div class="film-info">
                <div class="image-container">
                    <img src="{ns1:imageURL}" alt="image" />
                </div>
                <div class="info-container">
                    <h1>     
                        <xsl:value-of select="ns1:filmName" /> ( <xsl:value-of select="ns1:time" />)<br/>
                        <br/>
                        <xsl:value-of select="ns1:engName" /> 
                    </h1>
                    <table>
                        <tr>
                            <td>
                                Thời Lượng: 
                            </td>
                            <td>
                                <xsl:value-of select="ns1:duration" /> Phút
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Giới hạn độ tuổi: 
                            </td>
                            <td>
                                <xsl:value-of select="ns1:limitAge" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Thể loại: 
                            </td>
                            <td id="kinds">
                                <xsl:for-each select="ns1:kindOfFilms">
                                    <span>   
                                        <xsl:value-of select="text()" />
                                    </span>
                                </xsl:for-each>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Đạo diễn: 
                            </td>
                            <td id="directors">
                                <xsl:for-each select="ns1:directors">
                                    <span>   
                                        <xsl:value-of select="text()" />
                                    </span>
                                </xsl:for-each>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Diễn viên: 
                            </td>
                            <td id="actors">
                                <xsl:for-each select="ns1:actors">
                                    <span>   
                                        <xsl:value-of select="text()" />
                                    </span>
                                </xsl:for-each>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </xsl:template>
    <xsl:template match="ns2:FilmEvalute">
    </xsl:template>
</xsl:stylesheet>
