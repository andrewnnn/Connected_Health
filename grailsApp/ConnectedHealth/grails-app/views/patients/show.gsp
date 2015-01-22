<%@ page import="views.ViewHelpers" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${patient.firstName + " " + patient.lastName}</title>
</head>

<body>
    <g:form url="/ConnectedHealth/patients/${patient.id}" method="DELETE">
        <fieldset class="buttons">
            <a href="/ConnectedHealth/patients/${patient.id}/edit" class="edit">Edit patient profile</a>
            <input type="submit" value="Delete patient profile" class="delete" onclick="return confirm('${message(code: 'use.default', default: 'Are you sure you want to delete this patient profile?')}');"/>
        </fieldset>
    </g:form>

    <div class="content scaffold-list">
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
                        ${ViewHelpers.formatDate(it.created)}
                    </td>
                    <td>
                        <span class="property-value">
                            <a href="/ConnectedHealth/patients/${patient.id}/journal/${it.id}">
                                ${ViewHelpers.previewString(it.content)}
                            </a>
                        </span>
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
                        ${ViewHelpers.formatDate(it.created)}
                    </td>
                    <td>
                        <span class="property-value">
                            <a href="/ConnectedHealth/patients/${patient.id}/medicalnotes/${it.id}/show">
                                ${ViewHelpers.previewString(it.content)}
                            </a>
                        </span>
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
        <div class="nav">
            <ul>
                <li>
                    <span class="property-value">
                        <a href="/ConnectedHealth/patients/${patient.id}/medicalnotes/create" class="create">Add new medical note</a>
                    </span>
                </li>
                <li>
                    <span class="property-value">
                        <a href="/ConnectedHealth/patients">View patient list</a>
                    </span>
                </li>
            </ul>
        </div>

    </div>

</body>
</html>