
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Measurement Types</title>
</head>

<body>
<div class="content scaffold-list" role="main">
    <h1>${patient.firstName + " " + patient.lastName} has Measurement Types which listed below</h1>
    <table>
        <tr>
            <th>
                Type
            </th>
            <th>
                Descryption
            </th>
        </tr>
        <tbody>
            <tr>
                <td>
                    <span class="property-value"><g:link controller="${"WithingsApi"}" action="stepsView" params="[patientID: patient.id]">Steps</g:link></span>
                </td>
                <td>
                    Steps measurement
                </td>
            </tr>
        <tr>
            <td>
                <span class="property-value"><g:link controller="${"WithingsApi"}" action="stepsView" params="[patientID: patient.id]">Weight</g:link></span>
            </td>
            <td>
                Weight measurement
            </td>
        </tr>
        <tr>
            <td>
                <span class="property-value"><g:link controller="${"WithingsApi"}" action="stepsView" params="[patientID: patient.id]">Blood Pressure</g:link></span>
            </td>
            <td>
                Blood pressure measurement
            </td>
        </tr>
        </tbody>
    </table>


</div>
</body>
</html>