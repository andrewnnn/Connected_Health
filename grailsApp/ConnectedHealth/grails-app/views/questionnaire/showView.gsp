<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${questionnaire.name}</title>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="/ConnectedHealth/">Home</a></li>
        <li><a href="/ConnectedHealth/questionnaires" class="list">Questionnaire List</a></li>
        <li><a href="/ConnectedHealth/questionnaires/create" class="create">New Questionnaire</a></li>
    </ul>
</div>

<div id="list-questionnaire" class="content scaffold-list" role="main">
    <h1>${questionnaire.name}</h1>

    <table>
        <tbody>
        <tr>
            <th>Questionnaire ID</th>
            <td>${questionnaire.id}</td>
        </tr>
        <tr>
            <th>Description</th>
            <td>${questionnaire.description}</td>
        </tr>
        </tbody>
    </table>

    &nbsp;&nbsp;&nbsp;&nbsp;<a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/create" class="create">Add question</a>
    <br /><br />

    <table style="table-layout: fixed">
        <thead>
        <tr>
            <th>Question</th>
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
                    <br />
                    <g:form url="[resource:it, action:'delete']" method="DELETE">
                        <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${it.id}/edit" class="edit">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label.useDefault', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message.patient', default: 'Are you sure you want to delete this question?')}');" />
                        <% if (it.answerFormat == 0 || it.answerFormat == 1) { %>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${it.id}/choices/create" class="create">Add&nbsp;choice</a>
                        <% } %>
                    </g:form>
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
                                    <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${questionId}/choices/${it.id}" class="show">${it.content}</a>
                                </span>
                                &nbsp;&nbsp;&nbsp;&nbsp;Edit&nbsp;&nbsp;&nbsp;&nbsp;Delete
                            </li>
                        </g:each>
                        </ol>
                    <% } %>
                </td>
            </tr>
        </g:each>

        </tbody>
    </table>

    <g:form url="[resource:questionnaire, action:'delete']" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${questionnaire}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message.questionnaire', default: 'Are you sure you want to delete this patient account?')}');" />
        </fieldset>
    </g:form>
</div>
</body>
</html>