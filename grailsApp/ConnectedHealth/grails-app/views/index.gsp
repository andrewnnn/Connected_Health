<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Connected Health</title>
</head>

<body>
<div id="sectionBox">
    <div class="sectionButton sectionButtonR" onclick="window.location = '/ConnectedHealth/patients'">
        <div>
            <div class="imageInSectionButtons">
                <g:img class="imageInSectionButtonsPic" src="ProfileIcon.png"/>
            </div>
        </div>

        <h1>Patients</h1>

        <hr/>
        <ul>
            <li>Patient Profiles</li>
            <li>Medical Notes</li>
            <li>Journal Entries</li>
            <li>Measurements</li>
        </ul>
    </div>

    <div class="sectionButton sectionButtonB" onclick="window.location = '/ConnectedHealth/questionnaires'">
        <div>
            <div class="imageInSectionButtons">
                <g:img class="imageInSectionButtonsPic" src="QuestionnaireIcon.png"/>
            </div>
        </div>

        <h1>Questionnaires</h1>
        <hr/>
        <ul>
            <li>Create</li>
            <li>Assign</li>
            <li>Manage</li>
        </ul>
    </div>
</div>
</div>
</body>
</html>
