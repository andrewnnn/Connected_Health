<%@ page import="views.ViewHelpers" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Medical note for ${patient.firstName + " " + patient.lastName}</title>
</head>

<body>
<g:form url="[resource:patient, action:'delete']" method="DELETE">
    <fieldset class="buttons">
        <a href="/ConnectedHealth/patients/${patient.id}/medicalnotes/${medicalNote.id}/edit" class="edit">Edit note</a>
        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label.useDefault', default: 'Delete note')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message.patient', default: 'Are you sure you want to delete this note?')}');" />
    </fieldset>
</g:form>

<div id="show-medicalNote" class="content scaffold-show" role="main">
    <h1>Medical Note</h1>

    <ol class="property-list medicalNote">
        <li class="fieldcontain">
            <span id="content-label" class="property-label">Content</span>
            <span class="property-value" aria-labelledby="content-label">${medicalNote.content}</span>
        </li>

        <li class="fieldcontain">
            <span id="created-label" class="property-label">Created</span>
            <span class="property-value" aria-labelledby="content-label">${ViewHelpers.formatDate(medicalNote.created)}</span>
        </li>

        <li class="fieldcontain">
            <span id="patient-label" class="property-label">Patient</span>
            <span class="property-value" aria-labelledby="patient-label"><a href="/ConnectedHealth/patients/${patient.id}/show">${patient.firstName + " " + patient.lastName}</a></span>
        </li>
    </ol>

    <fieldset class="buttons">
        <a href="/ConnectedHealth/patients/${patient.id}/medicalnotes">View all notes</a>
    </fieldset>
</div>

</body>
</html>