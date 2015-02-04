<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${patient.firstName + " " + patient.lastName}</title>
</head>

<body>
<div class="nav" role="navigation">
    <fieldset class="buttons buttonsRed">
        <a href="/ConnectedHealth/patients/${patient.id}/show" class="list">Back to patient profile</a>
    </fieldset>
</div>
<div class="patientSection">
<div class="content scaffold-list" role="main">

    <h1>${patient.firstName + " " + patient.lastName}'s Journal Entries</h1>
    <table class="table table-bordered tablePaddings" style="width: 90%">
        <tbody>
        <g:each in="${JournalEntries}">
            <tr>
                <td style="width: 25%">
                    ${it.created.toString().substring(0,16)}
                </td>
                <td>
                    <span class="property-value"><g:link controller="${"JournalEntry"}" action="showView" params="[patientID: patient.id, journalEntryID:it.id]">${it.content}</g:link></span>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

</div>
    </div>
</body>
</html>