<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Medical note for ${patient.firstName + " " + patient.lastName}</title>
</head>

<body>
<g:form url="[resource:patient, action:'delete']" method="DELETE">
    <fieldset class="buttons">
        <g:link class="edit" action="edit" resource="${patient}"><g:message code="default.button.edit.label.useDefault" default="Edit patient profile" /></g:link>
        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label.useDefault', default: 'Delete patient profile')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message.patient', default: 'Are you sure you want to delete this patient profile?')}');" />
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

            <span class="property-value" aria-labelledby="created-label">${medicalNote.created}</span>

        </li>



        <li class="fieldcontain">
            <span id="patient-label" class="property-label">Patient</span>

            <span class="property-value" aria-labelledby="patient-label"><a href="/ConnectedHealth/patients/${patient.id}/show">${patient.firstName + " " + patient.lastName}</a></span>

        </li>


    </ol>
    <form action="/ConnectedHealth/medicalNote/delete/4" method="post" ><input type="hidden" name="_method" value="DELETE" id="_method" />
        <fieldset class="buttons">
            <a href="/ConnectedHealth/medicalNote/edit/4" class="edit">Edit</a>
            <input type="submit" name="_action_delete" value="Delete" class="delete" onclick="return confirm(&#39;Are you sure?&#39;);" />
        </fieldset>
    </form>
</div>

</body>
</html>