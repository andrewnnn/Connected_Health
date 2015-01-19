<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Questionnaires</title>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="/ConnectedHealth/">Home</a></li>
        <li><a href="/ConnectedHealth/questionnaire/create" class="create">New Questionnaire</a></li>
    </ul>
</div>
<div id="list-questionnaire" class="content scaffold-list" role="main">
    <h1>Questionnaire List</h1>

    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>

        <g:each in="${questionnaires}">
            <tr>
                <td>
                    <span class="property-value"><g:link controller="${"Questionnaire"}" action="showView" params="[questionnaireID: it.id]">${it.name}</g:link></span>
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