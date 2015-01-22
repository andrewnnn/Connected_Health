<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Measurement Types</title>
</head>

<body>
<div class="content scaffold-list" role="main">
    <h1>${patient.firstName + " " + patient.lastName}'s measurements</h1>
    <table>
        <tr>
            <th>
                Type
            </th>
            <th>
                Description
            </th>
        </tr>
        <tbody>
            <g:each in="${measurementTypes}">
                <tr>
                    <td>
                        <span class="property-value"><g:link controller="${"WithingsApi"}" action="stepsView" params="[patientID: patient.id]">${it.name}</g:link></span>
                    </td>
                    <td>
                        ${it.description}
                    </td>
                </tr>
            </g:each>
        </tbody>
    </table>


</div>
</body>
</html>