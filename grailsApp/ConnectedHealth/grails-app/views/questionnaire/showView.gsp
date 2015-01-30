<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${questionnaire.name}</title>
</head>

<body>
<div class="questionnaireSection">
<div class="nav" role="navigation">
    <g:form url="/ConnectedHealth/questionnaires/${questionnaire.id}" method="DELETE">
    <fieldset class="buttons buttonsBlue">
        <a href="/ConnectedHealth/questionnaires" class="list">Questionnaire List</a>
        <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/edit" class="edit">Edit</a>
        <input type="submit" value="Delete" class="delete" onclick="return confirm('${message(code: 'useDefault', default: 'Are you sure you want to delete this questionnaire?')}');"/>
    </fieldset>
    </g:form>
</div>

<div id="list-questionnaire" class="content scaffold-list" role="main">
    <h1>${questionnaire.name}</h1>
    <ol class="property-list questionnaire">
        <li class="fieldcontain">
            <span id="patient-label" class="property-label">Questionnaire ID</span>
            <span class="property-value" aria-labelledby="patient-label">${questionnaire.id}</span>
        </li>
        <li class="fieldcontain">
            <span id="patient-label" class="property-label">Description</span>
            <span class="property-value" aria-labelledby="patient-label">${questionnaire.description}</span>
        </li>
    </ol>

    &nbsp;&nbsp;&nbsp;&nbsp;<a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/create" class="create">Add question</a>
    <br /><br />

    <table style="table-layout: fixed">
        <thead>
        <tr>
            <th>Question</th>
            <th>Answer Format</th>
            <th>Choices</th>
        </tr>
        </thead>
        <tbody>

        <g:each in="${questionnaire.questions}">
            <tr>
                <td style="width: 10%">
                    <span class="property-value">
                        <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${it.id}/show" class="show">${it.content}</a>
                    </span>

                </td>
                <td>
                    ${views.ViewHelpers.answerFormatString(it.answerFormat)}
                </td>
                <td style="width: 90%">
                    <% if (it.choices.size() == 0) { %>
                        N/A
                    <% } else { %>
                        <ol>
                        <% questionId = it.id %>
                        <g:each in="${it.choices}">
                            <li>
                                <span class="property-value">
                                    <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${questionId}/choices/${it.id}/show" class="show">${it.content}</a>
                                </span>


                            </li>
                        </g:each>
                        </ol>
                    <% } %>
                </td>
            </tr>
        </g:each>

        </tbody>
    </table>

</div>
    </div>
</body>
</html>