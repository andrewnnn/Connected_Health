<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Add medical note for patient</title>
</head>

<body>
<g:form url="[resource:patient, action:'delete']" method="DELETE">
    <fieldset class="buttons">
        <g:link class="edit" action="edit" resource="${patient}"><g:message code="default.button.edit.label.useDefault" default="Edit patient profile" /></g:link>
        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label.useDefault', default: 'Delete patient profile')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message.patient', default: 'Are you sure you want to delete this patient profile?')}');" />
    </fieldset>
</g:form>

<div id="create-medicalNote" class="content scaffold-create" role="main">
    <h1>Add medical note for ${patient.firstName + " " + patient.lastName}</h1>

    <g:render template="/medicalNotes/form" locals="${[patient: patient, medicalNote: medicalNote]}" />
</div>

</body>
</html>