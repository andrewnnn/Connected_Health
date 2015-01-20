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

    <g:form url="/ConnectedHealth/patients/${patient.id}/medicalnotes/create" method="POST">

    <fieldset class="form">
        <div class="fieldcontain  required">
            <label for="content">
                Content
                <span class="required-indicator">*</span>
            </label>
            <g:textArea name="content" value="" id="content" rows="5" cols="40"/>
        </div>
        <g:hiddenField name="patientID" value="${patient.id}" />
    </fieldset>

    <fieldset class="buttons">
        <input type="submit" name="create" class="save" value="Create" id="create">
    </fieldset>
    </g:form>
</div>

</body>
</html>