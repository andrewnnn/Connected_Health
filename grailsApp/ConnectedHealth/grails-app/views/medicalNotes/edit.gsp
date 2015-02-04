<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Edit medical note for patient</title>
</head>

<body>
<div class="patientSection">
    <div class="nav" role="navigation">
        <fieldset class="buttons buttonsRed">
            <a href="/ConnectedHealth/patients/${patient.id}/show" class="list">Patient profile</a>
            <a href="/ConnectedHealth/patients/${patient.id}/medicalnotes" class="list">Patient medical notes</a>
        </fieldset>
    </div>

    <div id="create-medicalNote" class="content scaffold-create" role="main">
        <h1>Edit medical note for ${patient.firstName + " " + patient.lastName}</h1>

        <g:render template="/medicalNotes/form" locals="${[patient: patient, medicalNote: medicalNote]}" />
    </div>
    </div>
</body>
</html>