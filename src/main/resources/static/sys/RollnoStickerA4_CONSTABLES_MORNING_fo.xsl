<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="2.0" xmlns:fn="http://www.w3.org/2005/xpath-functions"
	xmlns:saxon="http://icl.com/saxon" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	extension-element-prefixes="saxon" xmlns:svg="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
	<xsl:template match="/">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master master-name="simple"
					page-height="29.8cm" page-width="21.2cm" margin-top="0cm"
					margin-bottom="0cm" margin-left="0cm" margin-right="0cm">
					<fo:region-before region-name="before-first" extent="0cm"  margin-top="0cm"/>
					<fo:region-body margin-top="0.7cm" margin-bottom="0cm" />
					<fo:region-after region-name="after-last" extent="0cm" />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="simple">
				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<!-- here we are calling the Template that do all the data display 
							process -->
						<xsl:call-template name="root" />
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
		<xsl:template name="root">
		<fo:table width="19.7cm" table-layout="fixed">
				<fo:table-column column-width="7cm" />
				<fo:table-column column-width="7cm" />
				<fo:table-column column-width="7cm" />
			<fo:table-body>
				<xsl:for-each select="//row">
						<fo:table-row >
							<fo:table-cell  display-align="center"  height="2.4cm" text-align="center" padding-top="0.0pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name1"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno1"/>
											</fo:block>
								</fo:table-cell>
						
						<fo:table-cell  display-align="center"  height="2.4cm" text-align="center" padding-top="0.0pt">
						<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name13"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno13"/>
											</fo:block>
								</fo:table-cell>
						<fo:table-cell  display-align="center"  height="2.4cm" text-align="center" padding-top="0.0pt">
						<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name25"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="24px">
												<xsl:value-of select="rollno25"/>
											</fo:block>
						</fo:table-cell>
						</fo:table-row>
						
						<fo:table-row >
							<fo:table-cell  display-align="center"  height="2.4cm" text-align="center" padding-top="0.1pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name2"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno2"/>
											</fo:block>
								</fo:table-cell>
						
						<fo:table-cell  display-align="center"  height="2.4cm" text-align="center" padding-top="0.1pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name14"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno14"/>
											</fo:block>
								</fo:table-cell>
						<fo:table-cell  display-align="center"  height="2.4cm" text-align="center" padding-top="0.1pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name26"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="24px">
												<xsl:value-of select="rollno26"/>
											</fo:block>
						</fo:table-cell>
						</fo:table-row>
						
						<fo:table-row >
							<fo:table-cell  display-align="center"  height="2.4cm" text-align="center" padding-top="0.3pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name3"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno3"/>
											</fo:block>
								</fo:table-cell>
						
						<fo:table-cell  display-align="center"  height="2.4cm" text-align="center" padding-top="0.3pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name15"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno15"/>
											</fo:block>
								</fo:table-cell>
						<fo:table-cell  display-align="center"  height="2.4cm" text-align="center" padding-top="0.3pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name27"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="24px">
												<xsl:value-of select="rollno27"/>
											</fo:block>
						</fo:table-cell>
						</fo:table-row>
						
						<fo:table-row >
							<fo:table-cell  display-align="center"  height="2.4cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name4"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno4"/>
											</fo:block>
								</fo:table-cell>
						
						<fo:table-cell  display-align="center"  height="2.4cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name16"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno16"/>
											</fo:block>
								</fo:table-cell>
						<fo:table-cell  display-align="center"  height="2.4cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name28"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="24px">
												<xsl:value-of select="rollno28"/>
											</fo:block>
						</fo:table-cell>
						</fo:table-row>
						
						<fo:table-row >
							<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name5"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno5"/>
											</fo:block>
								</fo:table-cell>
						
						<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name17"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno17"/>
											</fo:block>
								</fo:table-cell>
						<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name29"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="24px">
												<xsl:value-of select="rollno29"/>
											</fo:block>
						</fo:table-cell>
						</fo:table-row>
						
						<fo:table-row >
							<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name6"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno6"/>
											</fo:block>
								</fo:table-cell>
						
						<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name18"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno18"/>
											</fo:block>
								</fo:table-cell>
						<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name30"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="24px">
												<xsl:value-of select="rollno30"/>
											</fo:block>
						</fo:table-cell>
						</fo:table-row>
						
						<fo:table-row >
							<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name7"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno7"/>
											</fo:block>
								</fo:table-cell>
						
						<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name19"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno19"/>
											</fo:block>
								</fo:table-cell>
						<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name31"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="24px">
												<xsl:value-of select="rollno31"/>
											</fo:block>
						</fo:table-cell>
						</fo:table-row>
						
						<fo:table-row >
							<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name8"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno8"/>
											</fo:block>
								</fo:table-cell>
						
						<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name20"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno20"/>
											</fo:block>
								</fo:table-cell>
						<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name32"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="24px">
												<xsl:value-of select="rollno32"/>
											</fo:block>
						</fo:table-cell>
						</fo:table-row>
						
						<fo:table-row >
							<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name9"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno9"/>
											</fo:block>
								</fo:table-cell>
						
						<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name21"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno21"/>
											</fo:block>
								</fo:table-cell>
						<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="0.9pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name33"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="24px">
												<xsl:value-of select="rollno33"/>
											</fo:block>
						</fo:table-cell>
						</fo:table-row>
						
						<fo:table-row >
							<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="1.3pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name10"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno10"/>
											</fo:block>
								</fo:table-cell>
						
						<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="1.3pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name22"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno22"/>
											</fo:block>
								</fo:table-cell>
						<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="1.3pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name34"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="24px">
												<xsl:value-of select="rollno34"/>
											</fo:block>
						</fo:table-cell>
						</fo:table-row>
						
						<fo:table-row >
							<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="1.3pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name11"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno11"/>
											</fo:block>
								</fo:table-cell>
						
						<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="1.3pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name23"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno23"/>
											</fo:block>
								</fo:table-cell>
						<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="1.3pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name35"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="24px">
												<xsl:value-of select="rollno35"/>
											</fo:block>
						</fo:table-cell>
						</fo:table-row>
						
						<fo:table-row >
							<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="1.3pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name12"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno12"/>
											</fo:block>
								</fo:table-cell>
						
						<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="1.3pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name24"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px" >
												<xsl:value-of select="rollno24"/>
											</fo:block>
								</fo:table-cell>
						<fo:table-cell  display-align="center"  height="2.5cm" text-align="center" padding-top="1.3pt">
											<fo:block  font-family="Helvetica, Arial, sans" line-height="19pt" font-size="9px" font-weight="bold" text-decoration="underline">
												<xsl:value-of select="name36"/>
											</fo:block>
											<fo:block  font-family="Helvetica, Arial, sans" line-height="15pt" font-size="22px">
												<xsl:value-of select="rollno36"/>
											</fo:block>
						</fo:table-cell>
						</fo:table-row>
						
				</xsl:for-each>
			</fo:table-body>
			
		</fo:table>
		</xsl:template>
</xsl:transform>
