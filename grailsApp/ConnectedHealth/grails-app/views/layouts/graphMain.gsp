<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <asset:stylesheet src="application.css"/>
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

        var min = 1000;

        var titleString = "Date Steps";

        var data = [
            { "Date": "Mon", "Steps": 1800 },
            { "Date": "Tue", "Steps": 1760 },
            { "Date": "Wed", "Steps": 1740 },
            { "Date": "Thu", "Steps": 1750 },
            { "Date": "Fri", "Steps": 1810 },
            { "Date": "Sat", "Steps": 1920 },
            { "Date": "Sun", "Steps": 1000 }
        ];
    </script>
    <link rel="stylesheet" type="text/css" href="http://www.jchartfx.com/libs/v7/current/styles/Attributes/jchartfx.attributes.whitespace.css" />
    <link rel="stylesheet" type="text/css" href="http://www.jchartfx.com/libs/v7/current/styles/Palettes/jchartfx.palette.whitespace.css" />


    <title>Steps Graph</title>


    <nav class="navbar navbar-default navbar-fixed-top" style="background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #000000), color-stop(1, #666666));">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" style="color: white">Connected Health</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav" >
                    <li><a href="/ConnectedHealth" style="color: white">Home</a></li>
                    <li><a href="/ConnectedHealth/patients" style="color: white">Patients</a></li>
                    <li><a href="/ConnectedHealth/questionnaires" style="color: white">Questionnaires</a></li>

                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/ConnectedHealth/login" style="color: white">Sign In</a></li>
                    <li><a href="http://www.google.com" style="color: white">Explode</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>


</head>
<body onload="loadChart()">
<div class="nav" role="navigation">
    <fieldset class="buttons buttonsGreen">
        <a href="/ConnectedHealth/patients//measurement" class="list">Back to Measurement List</a>
        <a href="" class="list">Back to patient profile</a>
    </fieldset>
</div>
<div >
        <h2 style="position: absolute; width: 74%; text-align: center">Step Graph</h2>
        <div id="ChartDiv" style="width:100%;height:500px;position: relative"></div>
</div>
    <div class="footer" role="contentinfo"></div>
<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/>

</div>

</body>
</html>
