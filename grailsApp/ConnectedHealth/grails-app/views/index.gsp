<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Connected Health</title>
	</head>
	<body>
        <div id = "sectionBox">
            <div class="sectionButton sectionButtonR well" onclick="window.location='/ConnectedHealth/patients'">
                <div>
                    <h1>Patients</h1>
                    <div class="imageInSectionButtons">
                        <g:img width="200" src="ProfileIcon.png"/>
                    </div>
                    <p>
                        Patient Profiles<br/>
                        Medical Notes<br/>
                        Journal Entries<br/>
                        Measurements<br/>
                    </p>
                </div>
            </div>

            <div class="sectionButton sectionButtonB well" onclick="window.location='/ConnectedHealth/questionnaires'">
                <div>
                    <h1>Questionnaires</h1>
                    <div class="imageInSectionButtons">
                        <g:img width="200" src="QuestionnaireIcon.png"/>
                    </div>
                    <p>
                        Create<br/>
                        Assign<br/>
                        Manage<br/>
                    </p>
                </div>
            </div>
        </div>
	</body>
</html>
