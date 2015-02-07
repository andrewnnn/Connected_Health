<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Questionnaire Submissions</title>
</head>

<body>
<div class="questionnaireSection">
<div class="nav" role="navigation">
    <fieldset class="buttons buttonsBlue">
        <a href="/ConnectedHealth/patients/${patient.id}/show" class="list">Back to patient profile</a>
    </fieldset>
</div>
<div id="list-questionnaire" class="content scaffold-list" role="main">
    <h1>Questionnaire Submission List</h1>

    <table  class="table table-bordered tablePaddings" style="width: 90%">
        <thead>
        <tr>
            <th>Questionnaire</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>

        <g:each in="${submissions}">
            <tr>
                <td>
                    <span class="property-value">
                        <a href="/ConnectedHealth/patients/${patient.id}/submissions/${it.id}/show">${it.questionnaire.name}</a>
                    </span>
                </td>
                <td>
                    ${views.ViewHelpers.formatDate(it.created)}
                </td>
            </tr>
        </g:each>

        </tbody>
    </table>
</div>
    </div>
</body>
</html>