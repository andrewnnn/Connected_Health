<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Connected Health"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>
    <g:layoutHead/>


    <script type="text/javascript" src="http://www.jchartfx.com/libs/jQuery/jquery-1.7.1.js">
    </script>
    <script type="text/javascript" src="http://www.jchartfx.com/libs/v7/current/js/jchartfx.system.js">
    </script>
    <script type="text/javascript" src="http://www.jchartfx.com/libs/v7/current/js/jchartfx.coreVector.js">
    </script>
    <script type="text/javascript" src="http://www.jchartfx.com/libs/v7/current/js/jchartfx.advanced.js">
    </script>
    <script type="text/javascript" src="http://www.jchartfx.com/libs/v7/current/js/jchartfx.gauge.js">
    </script>
    <script type="text/javascript" src="http://www.jchartfx.com/libs/v7/current/js/jchartfx.treemap.js">
    </script>
    <script type="text/javascript" src="http://www.jchartfx.com/libs/v7/current/js/jchartfx.sparkline.js">
    </script>
    <script type="text/javascript" src="http://www.jchartfx.com/libs/v7/current/motifs/jchartfx.motif.whitespace.js">
    </script>


    <script type="text/javascript">
        var chart1;
        function loadChart()
        {
            chart1 = new cfx.Chart();
            chart1.getData().setSeries(1);
            chart1.getAxisY().setMin(min);
            chart1.getAxisY().setMax(max);
            var series1 = chart1.getSeries().getItem(0);
            series1.setGallery(cfx.Gallery.Lines);
            chart1.setDataSource(data);
            var divHolder = document.getElementById('ChartDiv');
            chart1.create(divHolder);
            chart1.setTitle(null);
        }

        var max = 2000;

        var min = 1600;

        var titleString = "Date Steps";

        var data = [
            { "Date": "Jan", "Steps": 1800 },
            { "Date": "Feb", "Steps": 1760 },
            { "Date": "Mar", "Steps": 1740 },
            { "Date": "Apr", "Steps": 1750 },
            { "Date": "May", "Steps": 1810 },
            { "Date": "Jun", "Steps": 1920 }
        ];
    </script>
    <link rel="stylesheet" type="text/css" href="http://www.jchartfx.com/libs/v7/current/styles/Attributes/jchartfx.attributes.whitespace.css" />
    <link rel="stylesheet" type="text/css" href="http://www.jchartfx.com/libs/v7/current/styles/Palettes/jchartfx.palette.whitespace.css" />

</head>
<body onload="loadChart()">
<div id="grailsLogo" role="banner"><a href="http://localhost:8080/ConnectedHealth/"><h2>Connected Health</h2></a></div>
<g:layoutBody/>
<div>
<h1>----------------------------------------------> Graph for Steps Measurements</h1>
        <div id="ChartDiv" style="width:100%;height:500px;top:-50px;position: relative"></div>
</div>
    <div class="footer" role="contentinfo"></div>
<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/>

</div>

</body>
</html>
