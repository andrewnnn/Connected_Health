(function(){var h=window.cfx,a=window.sfx;h.motif="glow";var g=a["gauge.templates"];if(void 0!=g){g.glowDashBorder='<T><T.R><s K="plotMargin">targetChart</s></T.R><C><B F="{B F}" S="{B S}" ST="{B ST}"><g M="1"><g.RD><RD H="Auto" MinHeight="20"/><RD H="*"/></g.RD><TextBlock M="8" Text="{B Title}" VerticalAlignment="Center" HorizontalAlignment="Center" Foreground="{Binding Class=DashboardTitle.fill}" FontFamily="{Binding Class=DashboardTitle.font-family}" FontSize="11" FontWeight="Normal"/><C g.R="1" N="targetChart" M="8,0,8,8"/></g></B></C></T>';
g.glowRadialDashBorder='<T><T.R><Th K="borderFactor">0.03</Th></T.R><V VW="100" VH="100"><C><E W="100" H="100" F="{B S}"/><E C.Left="3" C.Top="3" W="94" H="94" F="{B F}"/></C></V></T>';g.glowRadialIndicator='<T><C><r M="-1.5" F="{Binding Class=Glow.fill}" A="0.3" ><r.BE><BBE R="2"/></r.BE></r><r F="{B F}"/></C></T>';g.glowRadialCap="RadialCapPlain";g.glowRadialGlare="<T/>";g.glowRadialFiller='<T><C><P D="{B G}" F="{Binding Class=Glow.fill}" A="0.3" ><P.BE><BBE R="2" OffsetY="2" OffsetX="2"/></P.BE></P><P D="{B G}" F="{Binding Class=Glow.fill}" A="0.3" ><P.BE><BBE R="2" OffsetY="-2" OffsetX="-2"/></P.BE></P><P D="{B G}" F="{B F}"/></C></T>';
g.glowLinearDashBorder="<T/>";g.glowLinearGlare="<T/>";g.glowLinearFiller='<T><C><r M="-1.5" F="{Binding Class=Glow.fill}" A="0.3" ><r.BE><BBE R="2"/></r.BE></r><r F="{B F}"/></C></T>';g.glowLinearBar='<T><C M="-6"><B F="{B F}" S="{N}" CR="2"/><B F="{N}" ST="2" S="#131616" StartCorner="3" Segments="2" CR="2"/><B F="{N}" ST="3" S="#282A2B" StartCorner="1" Segments="2" CR="2"/></C></T>';var k=new h.gauge.Palette;k.setColors(["#DBDBD9","#252729","#DBDBD9","#252729","#EE7323","#B3571B","#EE7323","#B3571B",
"#666666",null,"#666666","#767778","#767778",null,"#FF0B00","#C00900",null,"#EBF928","#B1BB1E",null,"#49EA22","#37B01A",null,"#DBDBD9","#DBDBD9","#929394","#929394","#A0A0A0","#666666","#FF0B00","#EBF928","#49EA22","#666666","#FFFFFF","#666666"]);h.gauge.Palette.setDefaultPalette(k)}a=a["vector.templates"];void 0!=a&&(a["DashboardTitle.fill"]="0,#929394",a["DashboardTitle.font-family"]="1,Arial",a["Glow.fill"]="0,#E0E0E0",a["AxisY_Text.fill"]="0,#666666",a.glowBorder='<T><T.R><s K="plotMargin">targetChart</s></T.R><C><B F="{B F}" S="{B S}" ST="{B ST}"><g M="1"><g.RD><RD H="Auto" MinHeight="20"/><RD H="*"/></g.RD><TextBlock M="8" Text="{B Title}" VerticalAlignment="Center" HorizontalAlignment="Center" Foreground="{Binding Class=DashboardTitle.fill}" FontFamily="{Binding Class=DashboardTitle.font-family}" FontSize="11" FontWeight="Normal"/><C g.R="1" N="targetChart" M="8,0,8,8"/></g></B></C></T>',
a.glowLine='<T><T.R><D K="cfxDefStrokeThickness">3</D><mc K="multConverter"/></T.R><C><Pl P="{B P}" S="{Binding Class=Glow.fill}" A="0.3" ST="{B StrokeThickness, Converter={S multConverter},ConverterParameter=1.6}" ><Pl.BE><BBE R="2"/></Pl.BE></Pl><Pl P="{B P}" S="{B S}" ST="{B ST}"/></C></T>',a.glowCurve='<T><C><P D="{B G}" S="{Binding Class=Glow.fill}" A="0.5" ST="{B ST}" O="true"><P.BE><BBE R="3"/></P.BE></P><P D="{B G}" S="{B S}" ST="{B ST}" O="true"/></C></T>',a.glowArea='<T><T.R><D K="cfxDefStrokeThickness">3</D></T.R><C><Po P="{B P}" F="{B F}" S="{B S}" ST="{B ST}"/><Pl C.Top="-2.5" P="{B PT}" S="{Binding Class=Glow.fill}" A="0.5" ST="{B ST}" ><Pl.BE><BBE R="2"/></Pl.BE></Pl></C></T>',
a.glowRose='<T><T.R><D K="cfxDefStrokeThickness">3</D></T.R><C><P D="{B G}" F="{B F}" S="{B S}" ST="{B ST}"/><P D="{B G}" S="{Binding Class=Glow.fill}" A="0.3" ST="4" ><P.BE><BBE R="2"/></P.BE></P></C></T>',a.glowCurveArea='<T><C><P D="{B G}" S="{Binding Class=Glow.fill}" A="0.3" ST="{B ST}"><P.BE><BBE R="3"/></P.BE></P><P D="{B G}" S="{B S}" F="{B F}" ST="{B ST}"/></C></T>',a.glowBar='<T><C><B M="-2.5" W="{B Width}" H="{B Height}" rT="{B Transform}" A="0.3" F="{Binding Class=Glow.fill}"  ><B.BE><BBE R="2"/></B.BE></B><B W="{B Width}" H="{B Height}" rT="{B Transform}" A="{B Opacity}" F="{B F}" S="{B S}" ST="{B ST}"></B></C></T>',
a.glowGantt=a.glowBar,a.glowEqualizer='<T><T.R><T K="off"><B A="{B Opacity}" F="{B F}"/></T></T.R><C><B M="-1.5" W="{B Width}" H="{B Height}" rT="{B Transform}" A="0.3" F="{Binding Class=Glow.fill}"  ><B.BE><BBE R="1"/></B.BE></B><B A="{B Opacity}" F="{B F}"/></C></T>',a.glowDoughnut='<T><T.R><Th K="cfxBackgroundMargin">3</Th><T K="cfxBackgroundFull"><P D="{B G}" F="{Binding Class=Glow.fill}" A="0.3" ><P.BE><BBE R="2"/></P.BE></P></T></T.R><P D="{B G}" F="{B F}" S="{B S}" ST="{B ST}"/></T>',a.glowPie=
a.glowDoughnut,a.glowBubble='<T><C><E M="-2.5" F="{Binding Class=Glow.fill}" A="0.3" ><E.BE><BBE R="2"/></E.BE></E><E F="{B F}" S="{B S}" ST="{B ST}"/></C></T>',a.glowTreeMap=a.glowBar,a.glowFunnel='<T><C><Po P="{B P}" F="{Binding Class=Glow.fill}" A="0.5" ><Po.BE><BBE R="4"/></Po.BE></Po><Po P="{B P}" F="{B F}" S="{B S}"/></C></T>',a.glowPyramid=a.glowFunnel,a.glowHeatMap='<T><C><B M="-2.5" W="{B Width}" H="{B Height}" rT="{B Transform}" F="{Binding Class=Glow.fill}" A="0.3" ><B.BE><BBE R="2"/></B.BE></B><B M="2" W="{B Width}" H="{B Height}" rT="{B Transform}" A="{B Opacity}" F="{B F}" S="{B S}" ST="{B ST}"></B></C></T>',
a.glowRadar=a.glowLine,a.glowHighLow=a.glowLine,a.glowOverlayBubble=a.glowBubble,a.glowSparklineLine=a.glowLine,a.glowSparklineBar=a.glowBar,a.glowSparklineArea=a.glowArea,a.glowSparklineCurve=a.glowCurve,a.glowSparklineCurveArea=a.glowCurveArea,a.glowBullet='<T><T.R><T K="templateLine"><Line X1="{B X1}" X2="{B X2}" Y1="{B Y1}" Y2="{B Y2}" S="{B S}" ST="3"/></T></T.R><C><r M="-2.5" F="{Binding Class=Glow.fill}" A="0.3" ><r.BE><BBE R="2"/></r.BE></r><r F="{B F}" S="{B S}"/></C></T>',a.glowDensity=
'<T xmlns:x="a"><T.R><T K="background"><B M="-1" F="{Binding Class=Glow.fill}" A="0.3"><B.BE><BBE R="2"/></B.BE></B></T></T.R><B F="{B F}" S="{B S}"/></T>',a.glowMarker1='<T><C><r M="-1.5" F="{Binding Class=Glow.fill}" A="0.3" ><r.BE><BBE R="1"/></r.BE></r><r F="{B F}"/></C></T>',a.glowMarker2='<T><C><E M="-1.5" F="{Binding Class=Glow.fill}" A="0.3"><E.BE><BBE R="1"/></E.BE></E><E F="{B F}"/></C></T>',a.glowMarker3='<T><T.R><D K="cfxOSW">1</D></T.R><V VW="25" VH="25"><C><P D="M12,0L24,24L0,24Z" F="{Binding Class=Glow.fill}" A="0.3"><P.BE><BBE R="2"/></P.BE></P><P D="M12,4L20,20L4,20Z" F="{B F}"/></C></V></T>',
a.glowMarker4='<T><T.R><D K="cfxOSW">1</D></T.R><V VW="25" VH="25"><C><P D="M24,12L12,24L0,12L12,0Z" F="{Binding Class=Glow.fill}" A="0.3"><P.BE><BBE R="2"/></P.BE></P><P D="M20,12L12,20L4,12L12,4Z" F="{B F}"/></C></V></T>',a.glowMarker5='<T><C><E M="-1.5" F="{Binding Class=Glow.fill}" A="0.3"><E.BE><BBE R="1"/></E.BE></E><E F="{B F}"/><E><E.F><R><G C="#55FFFFFF" O="0"/><G C="#55333333" O="1"/></R></E.F></E></C></T>',a.glowMarker6='<T><T.R><D K="cfxOSW">1</D></T.R><V VW="25" VH="25"><C><P D="M24,10L0,10L0,14L24,14Z" F="{Binding Class=Glow.fill}" A="0.3"><P.BE><BBE R="2"/></P.BE></P><P D="M22,11L2,11L2,13L22,13Z" F="{B F}"/></C></V></T>',
a.glowMarker7='<T><T.R><D K="cfxOSW">1</D></T.R><V VW="25" VH="25"><C><P D="M14,24L14,0L10,0L10,24Z" F="{Binding Class=Glow.fill}" A="0.3" ><P.BE><BBE R="2"/></P.BE></P><P D="M13,22L13,2L11,2L11,22Z" F="{B F}"/></C></V></T>',a.glowMarker8='<T><T.R><D K="cfxOSW">1</D></T.R><V VW="25" VH="25"><C><P D="M24,10L14,10L14,0L10,0L10,10L0,10L0,14L10,14L10,24L14,24L14,14L24,14Z" F="{Binding Class=Glow.fill}" A="0.3"><P.BE><BBE R="2"/></P.BE></P><P D="M22,11L13,11L13,2L11,2L11,11L2,11L2,13L11,13L11,22L13,22L13,13L22,13Z" F="{B F}"/></C></V></T>',
a.glowMarker9='<T><T.R><D K="cfxOSW">1</D></T.R><V VW="25" VH="25"><C><P D="M12,24L24,0L0,0L12,24" F="{Binding Class=Glow.fill}" A="0.3"><P.BE><BBE R="2"/></P.BE></P><P D="M12,20L20,4L4,4L12,20" F="{B F}"/></C></V></T>',a.glowMarker10='<T><T.R><D K="cfxOSW">1</D></T.R><V VW="25" VH="25"><C><P D="M0,0L2,0L12,10L22,0L24,0L24,2L14,12L24,22L24,24L22,24L12,14L2,24L0,24L0,22L10,12L0,2Z" F="{Binding Class=Glow.fill}" A="0.3"><P.BE><BBE R="2"/></P.BE></P><P D="M1,1L2,1L12,11L22,1L23,1L23,2L13,12L23,22L23,23L22,23L12,13L2,23L1,23L1,22L11,12L1,2L1,1M2,2L22,22Z" F="{B F}"/></C></V></T>',
a=new h.Palette,a.setColors("#EE7323 #3083FD #F51EF5 #49EA22 #EBF928 #FF0B00 #31BEF3 #B75BE0 #FC8D07 #F7A7FE #FDFE86 #D20000 #FE9900 #8B698A #CCCD67 #66C226 #252729 #252729 #00000000 #dbdbd9 #dbdbd9 #f5f5ee #3083FD #404447 #666666 #666666 #00000000 #0296b1 #00000000 #666666 #00000000 #252729 #666666 #404447 #dbdbd9 #EE7323 #252729 #dbdbd9 #252729 #666666 #FFFFFF #666666".split(" ")),h.Chart.setDefaultPalette(a));var f=function(){};h.motifs.glow=f;f.getStyleInfo=function(e){var a="";if(e!==void 0){e=
e[0];e!==void 0&&(a=e[0])}e={isGroup:false};e.name=a;e.isSingle=false;e.isBullet=false;e.sections=[];if(a!=void 0){a=a.toUpperCase();if(a.indexOf("SINGLE")>=0){e.isSingle=true;e.name=""}if(a.indexOf("GROUP")>=0){e.isGroup=true;e.name="";e.name=""}if(a.indexOf("BULLET")>=0){e.isBullet=true;e.name=""}if(a.indexOf("SECTIONS")>=0){var b,d;b=a.indexOf("SECTIONS");d=a.indexOf(":",b);if(d>0){b=d;d=a.indexOf("-",b);a=d>=0?a.substring(b+1,d):a.substring(b+1,a.length);e.sections=a.split(",",100)}e.name=""}}return e};
f.global=function(a){a.setFont("8pt Arial");a.getMainScale().getTickmarks().getMedium().setVisible(false)};f.radial=function(a,c){f.global(a);a.getDashboardBorder().setInsideGap(0.1);var b=f.getStyleInfo(c);if(b.name!=null){a.setStyle(b.name);if(b.name=="progress"){var b=a.getMainScale(),d=a.getMainIndicator();b.getBar().setVisible(true);d.setTemplate(g.glowRadialFiller)}}};f.linear=function(a,c){f.global(a);var b=a.getMainScale(),d=b.getBar(),i=b.getMainIndicator();d.setVisible(false);i.setSize(0.2);
i.setPosition(0.35);d=f.getStyleInfo(c);if(d.isGroup){a.getBorder().setTemplate("<T/>");a.getDashboardBorder().setTemplate("<T/>")}if(d.isBullet){b.setThickness(0.9);b.setPosition(0);i.setSize(0.25);i.setPosition(0.375);i.setTitle("Current");i=new h.gauge.Marker;i.setSize(0.4);i.setPosition(0.5);i.setTitle("Target");i.setTemplate("MarkerThinRectangle");b.getIndicators().add(i);a.getDefaultAttributes().setSectionThickness(0.5);a.getDefaultAttributes().setSectionPosition(0.25)}if(d.sections.length>
0){for(var i=0,g,j=0;j<d.sections.length;j++){g=d.sections[j];b=new h.gauge.ScaleSection;b.setFrom(i);b.setTo(g);a.getMainScale().getSections().add(b);i=g}a.getMainScale().setMax(g)}};f.vert=function(a,c){f.linear(a,c)};f.horz=function(a,c){f.linear(a,c)};f.map=function(a){a.setShowAdditionalLayers(false);var c=new h.maps.MapLayer;c.setPath("@main");var b=c.getShadow();b.setXOffset(3);b.setYOffset(3);b.setColor("#4DE0E0E0");b.setBlur(3);a.getLayers().add(c)};f.heatmap=function(a){a=a.getGradientStops();
a.getItem(0).setColor("#EE7323");a.getItem(1).setColor("#3083FD")};f.equalizer=function(a){var c=new h.equalizer.EqualizerItem;c.setColor("#49EA22");c.setCount(2);a.getTopItems().add(c);c=new h.equalizer.EqualizerItem;c.setColor("#EBF928");c.setCount(1);a.getTopItems().add(c);a.setOffColor("#33DBDBD9")};f.trend=function(a,c){var b="";if(c!==void 0){var d=c[0];d!==void 0&&(b=d[0])}if(b!=void 0){if(b.toUpperCase().indexOf("SINGLE")>=0){a.getDelta().setVisible(false);a.getPercentChange().setVisible(false);
a.getIndicator().setVisible(false)}b.toUpperCase().indexOf("GROUP")>=0&&a.getBorder().setTemplate("<T/>")}};f.chart=function(a,c){var b="";if(c!==void 0){var d=c[0];d!==void 0&&(b=d[0])}if(b!=void 0){b=b.toUpperCase();b=="GROUP"&&a.getBorder().setTemplate("<T/>")}b=a.getAxisY().getGrids();b.getMajor().setVisible(false);b.getMajor().setTickMark(h.TickMark.None);b.getMinor().setVisible(false);b.getMinor().setTickMark(h.TickMark.None);b=a.getAxisY2().getGrids();b.getMajor().setVisible(false);b.getMajor().setTickMark(h.TickMark.None);
b.getMinor().setVisible(false);b.getMinor().setTickMark(h.TickMark.None);a.setForeColor("#80FFFFFF");a.getAllSeries().setMarkerStyle(h.MarkerStyle.Filled);a.getAxisX().getLine().setWidth(1)}})();