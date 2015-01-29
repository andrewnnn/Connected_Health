<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Questionnaires</title>
</head>

<body>
<div class="questionnaireSection">
<div class="nav" role="navigation">
    <fieldset class="buttons buttonsBlue">
        <a href="/ConnectedHealth/questionnaires/create" class="create">New Questionnaire</a>
    </fieldset>
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
    </div>
</body>
</html>