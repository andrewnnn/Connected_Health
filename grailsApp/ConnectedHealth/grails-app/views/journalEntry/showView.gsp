<%@ page import="views.ViewHelpers" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Journal ID ${journalentry.id}</title>
</head>

<body>
<div class="nav" role="navigation">
    <fieldset class="buttons buttonsRed">
        <a href="/ConnectedHealth/patients/${patient.id}/journal" class="create">View all Journals</a>
        <a href="/ConnectedHealth/patients/${patient.id}/show" class="list">Back to patient profile</a>
    </fieldset>
</div>
<div class="patientSection">

<div class="content scaffold-list" role="main">
    <h1>Journal ID ${journalentry.id}</h1>
    <ol class="property-list">
        <li class="fieldcontain">
            <span class="property-label">Patient</span>
            <span class="property-value"><g:link controller="${"Patient"}" action="showView" params="[patientID: patient.id]">${patient.firstName + " " + patient.lastName}</g:link></span>
        </li>
        <li class="fieldcontain">
            <span class="property-label">Created</span>
            <span class="property-value">${ViewHelpers.formatDate(journalentry.created)}</span>
        </li>
        <li class="fieldcontain">
            <span class="property-label">Updated</span>
            <span class="property-value">${ViewHelpers.formatDate(journalentry.updated)}</span>
        </li>
        <li class="fieldcontain" style="border:2px dotted lightpink;border-radius: 10px ">
            <p style="margin:10px">${journalentry.content}</p>
        </li>
    </ol>

</div>
</div>
</body>

</html>