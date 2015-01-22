<%@ page import="views.ViewHelpers" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Journal ID ${journalentry.id}</title>
</head>

<body>


<div class="content scaffold-list" role="main">
    <h1>Journal ID ${journalentry.id}</h1>
    <ol>
        <li>
            <span>Content</span>
            <span>${journalentry.content}</span>
        </li>
        <li>
            <span>Belongs to Patient Name</span>
            <span><span class="property-value"><g:link controller="${"Patient"}" action="showView" params="[patientID: patient.id]">${patient.firstName + " " + patient.lastName}</g:link></span></span>
        </li>
        <li>
            <span>Created At</span>
            <span>${ViewHelpers.formatDate(journalentry.created)}</span>
        </li>
        <li>
            <span>Last Modified At</span>
            <span>${ViewHelpers.formatDate(journalentry.updated)}</span>
        </li>
    </ol>

</div>

</body>
</html>