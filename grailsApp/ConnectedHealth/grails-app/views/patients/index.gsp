<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Patients</title>
</head>

<body>
<div class="patientSection">
<div class="nav" role="navigation">
    <fieldset class="buttons buttonsRed">
        <a href="/ConnectedHealth/patients/create" class="create">Create new patient profile</a>
       </fieldset>
</div>
<div id="list-patient" class="content scaffold-list tablePaddings" role="main">
    <h1 style="margin-left:0px">Patients</h1>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Name</th>
            <th>Address</th>
            <th>Email</th>
            <th>Phone</th>
        </tr>
        </thead>
        <tbody>

        <g:each in="${patients}">
            <tr>
                <td>
                    <span class="property-value">
                        <g:link controller="Patient" action="showView" params="[patientID: it.id]">${it.lastName + ", " + it.firstName}</g:link>
                    </span>
                </td>
                <td>
                    ${it.homeAddress}
                </td>
                <td>
                    ${it.contactEmail}
                </td>
                <td>
                    ${it.phone}
                </td>
            </tr>
        </g:each>

        </tbody>
    </table>
</div>
 </div>
</body>
</html>