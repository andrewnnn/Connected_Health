<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Edit medical note for patient</title>
</head>

<body>
<div class="patientSection">
    <fieldset class="buttons">
        <a href="/ConnectedHealth/patients/${patient.id}/show">Patient profile</a>
        <a href="/ConnectedHealth/patients/${patient.id}/medicalnotes">Patient medical notes</a>
    </fieldset>

    <div id="create-medicalNote" class="content scaffold-create" role="main">
        <h1>Edit medical note for ${patient.firstName + " " + patient.lastName}</h1>

        <g:render template="/medicalNotes/form" locals="${[patient: patient, medicalNote: medicalNote]}" />
    </div>
    </div>
</body>
</html>