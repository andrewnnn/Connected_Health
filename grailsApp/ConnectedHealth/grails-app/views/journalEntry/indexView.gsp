<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${patient.firstName + " " + patient.lastName}</title>
</head>

<body>
<div class="content scaffold-list" role="main">

    <h1>${patient.firstName + " " + patient.lastName}'s Journal Entries</h1>
    <table>
        <tbody>
        <g:each in="${JournalEntries}">
            <tr>
                <td>
                    ${it.created.toString().substring(0,16)}
                </td>
                <td>
                    <span class="property-value"><g:link controller="${"JournalEntry"}" action="show" id="${it.id}">${it.content}</g:link></span>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

</div>
</body>
</html>