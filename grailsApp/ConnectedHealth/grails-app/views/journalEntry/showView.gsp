<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Journal ID ${journalentry.id}</title>
</head>

<body>


<div class="content scaffold-list" role="main">
    <h1>Journal ID ${journalentry.id}</h1>
    <table>
        <tbody>
        <tr>
            <th>content</th>
            <td>${journalentry.content}</td>
        </tr>
        <tr>
            <th>Belongs to Patient Name</th>
            <td><span class="property-value"><g:link controller="${"Patient"}" action="showView" params="[patientID: patient.id]">${patient.firstName + " " + patient.lastName}</g:link></span></td>
        </tr>
        <tr>
            <th>Created At</th>
            <td>${journalentry.created.toString().substring(0,12)}</td>
        </tr>
        <tr>
            <th>Last Modified At</th>
            <td>${journalentry.updated.toString().substring(0,12)}</td>
        </tr>
        </tbody>
    </table>

</div>

</body>
</html>