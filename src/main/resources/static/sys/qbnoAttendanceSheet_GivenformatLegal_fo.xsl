<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="2.0" xmlns:fn="http://www.w3.org/2005/xpath-functions"
	xmlns:saxon="http://icl.com/saxon" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	extension-element-prefixes="saxon" xmlns:svg="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
	<xsl:template match="/">
		<fo:root>
			<!-- page-height="35.56cm" -->
			<fo:layout-master-set>
				<fo:simple-page-master master-name="simple"
					page-height="35cm" page-width="21.59cm" margin-top="0.5cm"
					margin-bottom="0cm" margin-left="1.5cm" margin-right="0.7cm">
					<fo:region-before region-name="before-first" extent="2.5cm"  margin-top="0.5cm"/>
					<fo:region-body margin-top="1.4cm" margin-bottom="3cm" />
					<fo:region-after region-name="after-last" extent="3cm" />
				</fo:simple-page-master>
			</fo:layout-master-set>
			 <xsl:variable name="SheetNumber">
    <xsl:value-of select="//pageno"/>
   </xsl:variable>
   
			<fo:page-sequence master-reference="simple" initial-page-number="{$SheetNumber}">
				<fo:static-content flow-name="before-first"> 
				<xsl:call-template name="header" />
				</fo:static-content>
				
				
				<fo:static-content flow-name="after-last">
					<fo:block white-space-treatment="preserve" font-family="sans-serif"
						font-size="7px">
						
						<fo:table table-layout="fixed" width="21.5cm" >
						    <fo:table-column column-width="2.1cm"/>
						    <fo:table-column column-width="2.1cm"/>
							<fo:table-column column-width="5.9cm"/>
							<fo:table-column column-width="7.9cm"/>
							<fo:table-body>
							<fo:table-row>
									<fo:table-cell   display-align="before" >
														<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="10px" >
															<fo:instream-foreign-object>
															<svg:svg  width="275" height="20">
															<svg:text x="0" y="10" font-size="8px">Total Present</svg:text>
  															<svg:rect x="50" y="0" width="20" height="20" style="fill:white;stroke:black;stroke-width:0.5;" />
															</svg:svg>
															</fo:instream-foreign-object>
														 </fo:block> 
									</fo:table-cell>
													
									<fo:table-cell   display-align="before" >
														<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="10px" >
															<fo:instream-foreign-object>
															<svg:svg  width="275" height="20">
															<svg:text x="0" y="10" font-size="8px">Total Absent</svg:text>
  															<svg:rect x="50" y="0" width="20" height="20" style="fill:white;stroke:black;stroke-width:0.5;" />
															</svg:svg>
															</fo:instream-foreign-object>
														 </fo:block> 
									 </fo:table-cell>				
									<fo:table-cell>
									 <fo:block   font-size="8pt" font-family="Helvetica, Arial, sans" line-height="45pt" text-align="left" padding-bottom="1pt">
										<fo:leader leader-length="5mm"/>	Invigilator's Name :_________________________
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
									 <fo:block   font-size="8pt" font-family="Helvetica, Arial, sans" line-height="45pt" text-align="left" padding-bottom="1pt">
										 Invigilator's Signature:____________________________
										</fo:block>
									</fo:table-cell>
									
									<!-- <fo:table-cell>
									 <fo:block   font-size="8pt" font-family="Helvetica, Arial, sans" line-height="18pt" text-align="right" padding-bottom="1pt">
										Sheet No: <fo:leader leader-length="5mm"/><fo:inline  font-size="10pt"><fo:page-number/></fo:inline>
										</fo:block>
									</fo:table-cell> -->
									<!-- <fo:table-cell   text-align="center"> border="solid 0.2pt black"
									 <fo:block   font-family="Helvetica, Arial, sans" line-height="22pt" text-align="center" padding-bottom="1pt">
										<fo:page-number/>
										</fo:block>
									</fo:table-cell> -->
								</fo:table-row>
								
								<fo:table-row>
									<fo:table-cell>
									 <fo:block   font-size="8pt" font-family="Helvetica, Arial, sans" line-height="18pt" text-align="left" padding-bottom="1pt">
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
									 <fo:block   font-size="8pt" font-family="Helvetica, Arial, sans" line-height="18pt" text-align="left" padding-bottom="1pt">
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
									 <fo:block   font-size="10pt" font-family="Helvetica, Arial, sans" line-height="18pt" text-align="right" padding-bottom="1pt">
										</fo:block>
									</fo:table-cell>
									<fo:table-cell  text-align="center">
									 <fo:block   font-size="10pt" font-family="Helvetica, Arial, sans" line-height="18pt" text-align="center" padding-bottom="1pt">
										Sheet No: <fo:leader leader-length="5mm"/><fo:inline  font-size="10pt"><!-- <xsl:value-of select="//pageno"/> --> 
										<fo:page-number/></fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>	
						</fo:table>
						
						<fo:leader rule-thickness="1px" leader-length="100%"
							leader-pattern="rule" color="#6D7B8D" />
						<fo:table table-layout="fixed" width="18cm">
							<fo:table-column />
							<fo:table-column />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell>
										<fo:block>
											All Rights Reserved 2021 BPSSC.
										</fo:block>
									</fo:table-cell>
									<!-- <fo:table-cell>
										<fo:block text-align="right">
											Page 
											<fo:page-number /> of <fo:page-number-citation ref-id="TheVeryLastPage"/>
										</fo:block>
									</fo:table-cell> -->
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:static-content>

				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<!-- here we are calling the Template that do all the data display 
							process -->
						<xsl:call-template name="root" />
					</fo:block>
					<fo:block  id="TheVeryLastPage"></fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
		<xsl:template name="header">
			<fo:table table-layout="fixed" width="19cm">
							<fo:table-column column-width="1.5cm"/>
							<fo:table-column column-width="17.5cm"/>
							<fo:table-body>
							<fo:table-row>
								<fo:table-cell display-align="center" text-align="center"  height="1.2cm" >
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="10px">
												<fo:external-graphic height="1.2cm" width="1.2cm" src="../Bihar-Police-constable-recruitment-1.jpg">
							               	</fo:external-graphic>
											</fo:block>
								</fo:table-cell>
								<fo:table-cell   display-align="center"  height="0.6cm" text-align="left">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="10px">
											<fo:inline margin-top="1cm"><fo:external-graphic height="0.7cm" width="14cm" src="../ESI_MAINS_1stShift.jpg">
							               	</fo:external-graphic></fo:inline> 
											<fo:inline text-align="end"> <fo:leader leader-length="8mm"/>Page 
												<!-- <fo:page-number /> of <fo:page-number-citation ref-id="TheVeryLastPage"/> -->
												<fo:page-number />  of <xsl:value-of select="//maxpageno"/>
												<!-- <xsl:value-of select="//pageno"/>  of <xsl:value-of select="//maxpageno"/>  -->
												</fo:inline>
											</fo:block>
											<fo:block  font-size="8pt" font-family="Helvetica, Arial, sans" line-height="18pt" text-align="left" padding-bottom="1pt">
												Centre : <xsl:value-of select="//center_code"/>  <fo:leader leader-length="2mm"/>  <fo:inline font-size="8pt" > <xsl:value-of select="//center_name"/> </fo:inline> 
											 </fo:block> 
											 <!-- <fo:block  font-size="9pt" font-family="Helvetica, Arial, sans" line-height="18pt" text-align="left" padding-bottom="1pt">
												Centre : <xsl:value-of select="//center_code"/>  <fo:leader leader-length="2mm"/>   <xsl:value-of select="//center_name"/> 
											 </fo:block>  -->
								</fo:table-cell>
							</fo:table-row>
							
							
							<!-- 
								<fo:table-row>
										<fo:table-cell>
										 <fo:block  font-size="10pt" font-family="Helvetica, Arial, sans" line-height="18pt" text-align="left" padding-bottom="1pt"	>
											<fo:external-graphic height="0.7cm" width="6cm" src="../Untitled.jpg"> At
							               	</fo:external-graphic><fo:inline margin-top="20pt" padding-top="10pt">
											 <fo:leader leader-length="10cm"/>Page</fo:inline> 
										</fo:block>
										</fo:table-cell>
				                  </fo:table-row>
			                 </fo:table-cell>
			                 
								<fo:table-row>
									<fo:table-cell>
									<fo:block  font-size="10pt" font-family="Helvetica, Arial, sans" line-height="18pt" text-align="left" padding-bottom="1pt">
										Center : 2012  D.A.V. Public School, Maniyadda, Lakhisarai Road, Jamui
										 </fo:block> 
										
									</fo:table-cell>
								</fo:table-row> -->
							</fo:table-body>	
						</fo:table>		
						
			<!-- <fo:block  font-size="10pt" font-family="Helvetica, Arial, sans" line-height="18pt" text-align="left" padding-bottom="1pt">
				Center Code:12  D.A.V. Public School, Maniyadda, Lakhisarai Road, Jamui 
				<fo:instream-foreign-object>
					<svg:svg  width="275" height="15">
					<svg:rect x="5" y="0" width="120" height="40" style="fill:white;stroke:black;stroke-width:0.5;" />
					<svg:text x="6" y="38" font-size="6px" font-family="Arial">Sheet No</svg:text>
					</svg:svg>
				</fo:instream-foreign-object>
			</fo:block> -->
		</xsl:template>
		<xsl:template name="root">
		<fo:table width="19cm" table-layout="fixed" >
			<!-- <fo:table-column column-width="1cm" /> -->
			<fo:table-column column-width="4cm" />
			<fo:table-column column-width="15cm" />
			<!-- <fo:table-column column-width="5.0cm" /> -->
			<fo:table-header>
				<fo:table-row>
					<!-- <fo:table-cell  border="0.25pt solid" display-align="center"  height="0.6cm" text-align="center">
								<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="10px" wrap-option="wrap">
									S.No
								</fo:block>
					</fo:table-cell> -->
					<fo:table-cell   border="0.25pt solid" display-align="center"  height="0.6cm" text-align="center">
								<fo:block font-family="Helvetica, Arial, sans" line-height="15pt" font-size="10px" wrap-option="wrap">
									Candidate Photo
								</fo:block>
					</fo:table-cell>
					<fo:table-cell  border="0.25pt solid" display-align="center"  height="0.6cm" text-align="center">
								<fo:block font-family="Helvetica, Arial, sans" line-height="15pt" font-size="10px" wrap-option="wrap" >
									Candidate Details
								</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-header>
			<fo:table-body>
				<xsl:for-each select="//row">
					<fo:table-row >
						   <fo:table-cell  border="0.25pt solid"    text-align="center">
								<fo:block font-family="Helvetica, Arial, sans" line-height="15pt" font-size="10px" >
									<fo:external-graphic height="4.0cm" width="3.5cm">
               					          <xsl:attribute name="src">
               					          	<xsl:value-of select="canphoto"/>
               					          </xsl:attribute>
               					    </fo:external-graphic>
								</fo:block>
						   </fo:table-cell>
						   
						   <fo:table-cell  border="0.25pt solid" display-align="center"  height="4cm" padding-left="2px" text-align="center">
								<fo:block font-family="Helvetica, Arial, sans" line-height="15pt" >
							<fo:table width="14.5cm" table-layout="fixed">
			                 <fo:table-column column-width="14.5cm" />
									<fo:table-body>
										<fo:table-row>
													<fo:table-cell>
														<fo:table width="14.5cm" table-layout="fixed">
															<fo:table-column column-width="14.5cm" />
															<!-- <fo:table-column column-width="5.5cm" /> -->
															<fo:table-body>
																<fo:table-row>
																<fo:table-cell>
																	<fo:table width="14cm" table-layout="fixed">
																		<fo:table-column column-width="4.5cm" />
																		<fo:table-column column-width="10.5cm" />
																			<fo:table-body>
																				<fo:table-row>
																				<fo:table-cell>
																					<fo:block  font-family="Helvetica, Arial, sans" line-height="16pt" font-size="10px" text-align="left">
																						<fo:instream-foreign-object>
																							<svg:svg  width="210" height="20">
																							<svg:text x="0" y="10" font-size="6px">Question Booklet No:</svg:text>
								  															<svg:rect x="58" y="0" width="19" height="19" style="fill:None;stroke:black;stroke-width:0.5;" />
								  															<svg:rect x="77" y="0" width="19" height="19" style="fill:None;stroke:black;stroke-width:0.5;" />
								  															<svg:rect x="96" y="0" width="19" height="19" style="fill:None;stroke:black;stroke-width:0.5;" />
								  															<svg:rect x="115" y="0" width="19" height="19" style="fill:None;stroke:black;stroke-width:0.5;" />
								  															<svg:rect x="134" y="0" width="19" height="19" style="fill:None;stroke:black;stroke-width:0.5;" />
								  															<svg:rect x="153" y="0" width="19" height="19" style="fill:None;stroke:black;stroke-width:0.5;" />
																							<svg:rect x="172" y="0" width="19" height="19" style="fill:None;stroke:black;stroke-width:0.5;" />
																							<svg:rect x="191" y="0" width="19" height="19" style="fill:None;stroke:black;stroke-width:0.5;" />								  															
																							</svg:svg>
																						</fo:instream-foreign-object> 
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell>
																					<fo:block  font-family="Helvetica, Arial, sans" line-height="16pt" font-size="10px" text-align="left" padding-top="0.2cm">
																						<fo:leader leader-length="60mm"/><fo:inline margin-top="10pt" font-weight="bold" font-size="7px"> Roll No: <fo:leader leader-length="4mm"/> <fo:inline font-weight="bold" font-size="12px"><xsl:value-of select="rollno"/> </fo:inline>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																				
																					<!-- <fo:table-cell>
																						<fo:block  font-family="Helvetica, Arial, sans" line-height="8pt" font-size="6px" text-align="center">
																							Question Booklet No
																							<fo:instream-foreign-object>
																								<svg:svg  width="140" height="18">
									  															<svg:rect x="15" y="0" width="15" height="18" style="fill:None;stroke:black;stroke-width:0.5;" />
									  															<svg:rect x="30" y="0" width="15" height="18" style="fill:None;stroke:black;stroke-width:0.5;" />
									  															<svg:rect x="45" y="0" width="15" height="18" style="fill:None;stroke:black;stroke-width:0.5;" />
									  															<svg:rect x="60" y="0" width="15" height="18" style="fill:None;stroke:black;stroke-width:0.5;" />
									  															<svg:rect x="75" y="0" width="15" height="18" style="fill:None;stroke:black;stroke-width:0.5;" />
									  															<svg:rect x="90" y="0" width="15" height="18" style="fill:None;stroke:black;stroke-width:0.5;" />
									  															<svg:rect x="105" y="0" width="15" height="18" style="fill:None;stroke:black;stroke-width:0.5;" />
									  															<svg:rect x="120" y="0" width="15" height="18" style="fill:None;stroke:black;stroke-width:0.5;" />
																								</svg:svg>
																							</fo:instream-foreign-object> 
																						</fo:block>
																					</fo:table-cell>
																					<fo:table-cell>
																						<fo:block  font-family="Helvetica, Arial, sans" line-height="16pt" font-size="10px" text-align="center">
																							<fo:leader leader-length="5mm"/><fo:inline margin-top="10pt" font-size="7px" font-weight="bold"> Roll No: <fo:leader leader-length="5mm"/> <fo:inline font-weight="bold" font-size="12px"><xsl:value-of select="rollno"/> </fo:inline>
																							</fo:inline>
																						</fo:block>
																					</fo:table-cell> -->
																				</fo:table-row>
																			</fo:table-body>
																	</fo:table>
																</fo:table-cell>
																</fo:table-row>
															</fo:table-body>
														</fo:table>
													</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell   display-align="before" background-color="#FFFFFF"  padding-top="0.2px" text-align="start">
												<fo:block  font-family="Helvetica, Arial, sans" line-height="13pt" font-size="6px" wrap-option="wrap">
													<fo:inline font-weight="bold">	Name : </fo:inline > <fo:inline font-size="6px"> <xsl:value-of select="name"/> </fo:inline>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>	
										<fo:table-row>
											<fo:table-cell   display-align="before" background-color="#FFFFFF"  padding-top="0.2px" text-align="start">
												<fo:table width="13.5cm" table-layout="fixed">
															<fo:table-column column-width="6.5cm" />
															<fo:table-column column-width="6.5cm" />
															<fo:table-body>
																<fo:table-row>
																<fo:table-cell>
																	<fo:block  font-family="Helvetica, Arial, sans" line-height="13pt" font-size="6px" wrap-option="wrap">
																		<fo:inline font-weight="bold">F/H : </fo:inline><fo:inline font-size="6px"><xsl:value-of select="fname"/> </fo:inline>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell>
																	<fo:block  font-family="Helvetica, Arial, sans" line-height="13pt" font-size="6px" wrap-option="wrap">
																		 <fo:inline font-size="6px" font-weight="bold">	ID Card : </fo:inline> <fo:inline font-size="6px"> <xsl:value-of select="idno"/> </fo:inline>
																	</fo:block>
																</fo:table-cell>
																</fo:table-row>
															</fo:table-body>
													</fo:table>
											</fo:table-cell>
										</fo:table-row>	
										<fo:table-row>
											<fo:table-cell   display-align="before" background-color="#FFFFFF"  padding-top="0.2px" text-align="start">
												<fo:table width="14.5cm" table-layout="fixed">
															<fo:table-column column-width="6.5cm" />
															<fo:table-column column-width="7.5cm" />
															<fo:table-body>
																<fo:table-row>
																<fo:table-cell number-columns-spanned="2">
																	<fo:block  font-family="Helvetica, Arial, sans" line-height="13pt" font-size="6px" wrap-option="wrap">
																		<fo:inline font-weight="bold">Identification Mark  : </fo:inline><fo:inline font-size="5px"><xsl:value-of select="idmark"/> </fo:inline>
																	</fo:block>
																</fo:table-cell>
																<!--<fo:table-cell>
																
																  <xsl:if test="phc_disability ne 'NA'">
																  <xsl:if test="string-length(phc_disability)  &gt; 41">
																	<fo:block  font-family="Helvetica, Arial, sans" line-height="13pt" font-size="6px" wrap-option="wrap">
																		 <fo:inline font-size="6px" font-weight="bold">	Disability : </fo:inline> <fo:inline font-size="5px"> <xsl:value-of select="phc_disability"/> </fo:inline>
																	</fo:block>
																  </xsl:if>	
																   <xsl:if test="string-length(phc_disability)  &lt; 41">
																	<fo:block  font-family="Helvetica, Arial, sans" line-height="13pt" font-size="6px" wrap-option="wrap">
																		 <fo:inline font-size="6px" font-weight="bold">	Disability : </fo:inline> <fo:inline font-size="6px"> <xsl:value-of select="phc_disability"/> </fo:inline>
																	</fo:block>
																  </xsl:if>	
																</xsl:if> 	
																</fo:table-cell> -->
																</fo:table-row>
															</fo:table-body>
													</fo:table>
											</fo:table-cell>
										</fo:table-row>	
											<!-- <fo:table-row>
											<fo:table-cell   display-align="before" background-color="#FFFFFF"  padding-top="0.2px" text-align="start">
												<fo:block  font-family="Helvetica, Arial, sans" line-height="13pt" font-size="7px" wrap-option="wrap">
													<fo:inline  font-weight="bold" font-size="6px">Identification Mark : </fo:inline><fo:inline font-size="6px"><xsl:value-of select="idmark"/></fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell>
																	<fo:block  font-family="Helvetica, Arial, sans" line-height="13pt" font-size="6px" wrap-option="wrap">
																		 <fo:inline font-size="6px" font-weight="bold">	Disability : </fo:inline> <fo:inline font-size="6px"> Visual Impeirment </fo:inline>
																	</fo:block>
																</fo:table-cell>
										</fo:table-row> -->
										<fo:table-row>
											<fo:table-cell   display-align="before" background-color="#FFFFFF"  padding-top="0.2px" text-align="start">
												<fo:block>
														</fo:block>
														<fo:block>
														     <fo:leader leader-length="10mm"/>
														    <fo:inline>
														    <fo:external-graphic height="0.9cm" width="5.25cm">
							               					          <xsl:attribute name="src">
							               					          	<xsl:value-of select="canesign"/>
							               					          </xsl:attribute>
							               					    </fo:external-graphic>
														    </fo:inline>
														    <fo:leader leader-length="25mm"/>
														    <fo:inline >
														    <fo:external-graphic height="0.9cm" width="5.25cm">
							               					          <xsl:attribute name="src">
							               					          	<xsl:value-of select="canhsign"/>
							               					          </xsl:attribute>
							               					    </fo:external-graphic>
														    </fo:inline>
														</fo:block>
											</fo:table-cell>
										</fo:table-row>	
										<fo:table-row >
											<fo:table-cell  display-align="before"  padding-top="0.2px" text-align="start">
												      <fo:block>
															<fo:instream-foreign-object>
															<svg:svg  width="470" height="42">
																<svg:rect x="5" y="0" width="202" height="34" style="fill:None;stroke:black;stroke-width:0.5;" />
	  															<svg:text x="6" y="41" font-size="6px" font-family="Arial">Sign in English</svg:text>
	  															<svg:rect x="214" y="0" width="202" height="34" style="fill:None;stroke:black;stroke-width:0.5;" />
																<svg:text x="216" y="41" font-size="6px" font-family="Arial">Sign in Hindi</svg:text>
																</svg:svg>
															</fo:instream-foreign-object>
														</fo:block>
											</fo:table-cell>
										</fo:table-row>	
									</fo:table-body>
									</fo:table>
							  </fo:block>		
						   </fo:table-cell>
					</fo:table-row>
				</xsl:for-each>
			</fo:table-body>
		</fo:table>
		</xsl:template>
</xsl:transform>
