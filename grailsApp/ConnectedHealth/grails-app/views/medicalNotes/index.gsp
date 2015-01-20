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
        <li><a href="/ConnectedHealth/patients/${patient.id}/medicalnotes/create" class="create">Add medical note</a></li>
    </ul>
</div>
<div id="list-medicalNotes" class="content scaffold-list" role="main">
    <h1>Medical notes for ${patient.firstName + " " + patient.lastName}</h1>

    <table>
        <thead>
        <tr>
            <th>Note</th>
            <th>Date created</th>
        </tr>
        </thead>
        <tbody>

        <g:each in="${medicalNotes}">
            <tr>
                <td>
                    <span class="property-value"><a href="/ConnectedHealth/patients/${patient.id}/medicalnotes/${it.id}/show">${it.content}</a></span>
                </td>
                <td>
                    ${it.created}
                </td>
            </tr>
        </g:each>

        </tbody>
    </table>

    <fieldset class="buttons">
        <a href="/ConnectedHealth/patients/${patient.id}/show">Back to patient profile</a>
    </fieldset>
</div>
</body>
</html>