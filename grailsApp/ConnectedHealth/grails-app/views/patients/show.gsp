<%@ page import="views.ViewHelpers" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${patient.firstName + " " + patient.lastName}</title>
</head>

<body>
<div class="patientSection">
    <g:form url="/ConnectedHealth/patients/${patient.id}" method="DELETE">
        <div class="nav" role="navigation">
            <fieldset class="buttons buttonsRed">
                <a href="/ConnectedHealth/patients/" class="list">Patient List</a>
                <a href="/ConnectedHealth/patients/${patient.id}/edit" class="edit">Edit patient profile</a>
                <input type="submit" value="Delete patient profile" class="delete"
                       onclick="return confirm('${message(code: 'use.default', default: 'Are you sure you want to delete this patient profile?')}');"/>
            </fieldset>
        </div>
    </g:form>
    <div class="content scaffold-list">
        <h1>Patient Details</h1>
        <ol class="property-list medicalNote">
            <li class="fieldcontain patientSection">
                <span class="property-label">Name</span>
                <span class="property-value"
                      aria-labelledby="content-label">${patient.firstName + " " + patient.lastName}</span>
            </li>

            <li class="fieldcontain">
                <span class="property-label">ID</span>
                <span class="property-value" aria-labelledby="content-label">${patient.id}</span>
            </li>

            <li class="fieldcontain">
                <span class="property-label">Address</span>
                <span class="property-value" aria-labelledby="patient-label">${patient.homeAddress}</span>
            </li>

            <li class="fieldcontain">
                <span class="property-label">Email</span>
                <span class="property-value" aria-labelledby="patient-label">${patient.contactEmail}</span>
            </li>


            <li class="fieldcontain">
                <span class="property-label">Phone</span>
                <span class="property-value" aria-labelledby="patient-label">${patient.phone}</span>
            </li>
        </ol>

        <br/>
        <div style="margin-left: 5%">
            <g:link uri="/patients/${patient.id}/measurements">
                View Measurements for this Patient
            </g:link>
        </div>
        <br/>

        <h1>Recent journal entries</h1>
        <table class="table table-bordered tablePaddings" style="width: 90%">
            <tbody>
            <g:each in="${recentJournalEntries}">
                <tr>
                    <td style="width: 25%">
                        ${ViewHelpers.formatDate(it.created)}
                    </td>
                    <td style="width: 75%">
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

        <div style="margin-left: 5%">
            <% if (journalEntriesCount > PREVIEW_COUNT) { %>
            <g:link controller="${"JournalEntry"}" action="indexView" params="[patientID: patient.id]">
                View all journal entries (<%=journalEntriesCount%>)
            </g:link>
            <% } %>
        </div>

        <h1>Recent medical notes</h1>

        <input type="button" value="Add new medical note" onclick="window.location='/ConnectedHealth/patients/${patient.id}/medicalnotes/create'" class="create btn btn-danger" style="left: 5%; position: relative" />

        <br/><br/>
        <table class="table table-bordered tablePaddings" style="width: 90%">
            <tbody>
            <g:each in="${recentMedicalNotes}">
                <tr>
                    <td style="width: 25%">
                        ${ViewHelpers.formatDate(it.created)}
                    </td>
                    <td style="width: 75%">
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


        <div style="margin-left: 5%">
            <% if (medicalNotesCount > PREVIEW_COUNT) { %>
            <g:link controller="${"MedicalNote"}" action="indexView" params="[patientID: patient.id]">
                View all medical notes (<%=medicalNotesCount%>)
            </g:link>
            <br/><br/>
            <% } %>
        </div>

    </div>
</div>
</body>
</html>