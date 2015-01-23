<%@ page import="views.ViewHelpers" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Journal ID ${journalentry.id}</title>
</head>

<body>


<div class="content scaffold-list" role="main">
    <h1>Journal ID ${journalentry.id}</h1>
    <ol class="property-list">
        <li class="fieldcontain">
            <span class="property-label">Content</span>
            <span class="property-value">${journalentry.content}</span>
        </li>
        <li class="fieldcontain">
            <span class="property-label">Belongs to Patient Name</span>
            <span class="property-value"><g:link controller="${"Patient"}" action="showView" params="[patientID: patient.id]">${patient.firstName + " " + patient.lastName}</g:link></span>
        </li>
        <li class="fieldcontain">
            <span class="property-label">Created At</span>
            <span class="property-value">${ViewHelpers.formatDate(journalentry.created)}</span>
        </li>
        <li class="fieldcontain">
            <span class="property-label">Last Modified At</span>
            <span class="property-value">${ViewHelpers.formatDate(journalentry.updated)}</span>
        </li>
    </ol>

</div>

</body>
</html>