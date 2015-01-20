<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Medical notes for ${patient.firstName + " " + patient.lastName}</title>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="/ConnectedHealth/">Home</a></li>
        <li><a href="/ConnectedHealth/patients/${patient.id}/medicalnotes/" class="create">Add medical note</a></li>
    </ul>
</div>
<div id="list-medicalNotes" class="content scaffold-list" role="main">
    <h1>Medical notes for ${patient.firstName + " " + patient.lastName}</h1>

    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Address</th>
            <th>Email</th>
            <th>Phone</th>
        </tr>
        </thead>
        <tbody>

        <g:each in="${medicalNotes}">
            <tr>
                <td>
                    <span class="property-value"><g:link controller="Patient" action="showView" params="[patientID: it.id]">${it.lastName + ", " + it.firstName}</g:link></span>
                </td>
                <td>
                    ${it.id}
                </td>
                <td>
                    ${it.id}
                </td>
                <td>
                    ${it.id}
                </td>
            </tr>
        </g:each>

        </tbody>
    </table>
</div>
</body>
</html>