<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Connected Health</title>
	</head>
	<body>
        <div>
                <g:form name="a" url="[action:'indexView',controller:'Patient']" style="font-size:50pt;height: 100px; left:20%; margin:1%;  position: relative">
                    <input type="submit" value="Patient Index" style="height:100%;width:60%;font-size:50pt;color: darkgreen; background-color: lawngreen" />
                </g:form>
                <g:form name="b" url="[action:'indexView',controller:'Questionnaire']" style="font-size:50pt;height: 100px; left:20%; margin:1%;  position: relative">
                    <input type="submit" value="Questionnaire" style="height:100%;width:60%;font-size:50pt; color: darkgreen; background-color: lawngreen" />
                </g:form>
        </div>
	</body>
</html>
