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

    <table>
        <thead>
        <tr>
            <th>Question</th>
            <th>Choices</th>
        </tr>
        </thead>
        <tbody>

        <g:each in="${questionnaire.questions}">
            <tr>
                <td>
                    <span class="property-value"><g:link controller="${"Question"}" action="showView" params="[questionID: it.id]">${it.content}</g:link></span>
                </td>
                <td>
                    <% if (it.choices.size() == 0) { %>
                        N/A
                    <% } else { %>
                        <ol>
                        <g:each in="${it.choices}">
                            <li>
                                <span class="property-value"><g:link controller="${"Choice"}" action="showView" params="[choiceID: it.id]">${it.content}</g:link></span>
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