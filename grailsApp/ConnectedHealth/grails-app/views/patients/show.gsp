<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${patient.firstName + " " + patient.lastName}</title>
</head>

<body>
    <g:form url="[resource:patient, action:'delete']" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${patient}"><g:message code="default.button.edit.label.useDefault" default="Edit patient profile" /></g:link>
            <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label.useDefault', default: 'Delete patient profile')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message.patient', default: 'Are you sure you want to delete this patient profile?')}');" />
        </fieldset>
    </g:form>

<div class="content scaffold-list" role="main">
    <h1>Patient</h1>
    <table>
        <tbody>
        <tr>
            <th>Name</th>
            <td>${patient.firstName + " " + patient.lastName}</td>
        </tr>
        <tr>
            <th>Patient ID</th>
            <td>${patient.id}</td>
        </tr>
        <tr>
            <th>Address</th>
            <td>${patient.homeAddress}</td>
        </tr>
        <tr>
            <th>Email</th>
            <td>${patient.contactEmail}</td>
        </tr>
        <tr>
            <th>Phone</th>
            <td>${patient.phone}</td>
        </tr>
        </tbody>
    </table>

    <h1>Recent journal entries</h1>
    <table>
        <tbody>
        <g:each in="${recentJournalEntries}">
            <tr>
                <td>
                    ${it.created.toString().substring(0,DATE_CHARS)}
                </td>
                <td>
                    <span class="property-value"><g:link controller="${"JournalEntry"}" action="show" id="${it.id}">${it.content}</g:link></span>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
    <% if (journalEntriesCount > PREVIEW_COUNT) { %>
        <g:link controller="${"JournalEntry"}" action="indexView" params="[patientID: patient.id]">
            View all journal entries (<%= journalEntriesCount %>)
        </g:link>
    <% } %>

    <h1>Recent medical notes</h1>
    <table>
        <tbody>
        <g:each in="${recentMedicalNotes}">
            <tr>
                <td>
                    ${it.created.toString().substring(0,DATE_CHARS)}
                </td>
                <td>
                    <span class="property-value"><g:link controller="${"MedicalNote"}" action="show" id="${it.id}">${it.content}</g:link></span>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
    <% if (medicalNotesCount > PREVIEW_COUNT) { %>
        <g:link controller="${"MedicalNote"}" action="indexView" params="[patientID: patient.id]">
            View all medical notes (<%= medicalNotesCount %>)
        </g:link>
        <br /><br />
    <% } %>
    <div class="nav" role="navigation">
        <ul>
            <li>
                <g:link controller="${"MedicalNote"}" action="newView" params="[patientID: patient.id]" class="create">
                    Add new medical note
                </g:link>
            </li>
        </ul>
    </div>

</div>

</body>
</html>