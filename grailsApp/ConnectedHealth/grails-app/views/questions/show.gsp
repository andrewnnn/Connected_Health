<%@ page import="views.ViewHelpers" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Question</title>
</head>

<body>
<g:form url="[resource:question, action:'delete']" method="DELETE">
    <fieldset class="buttons">
        <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${question.id}/edit" class="edit">Edit question</a>
        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label.useDefault', default: 'Delete question')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message.patient', default: 'Are you sure you want to delete this question?')}');" />
    </fieldset>
</g:form>

<div id="show-question" class="content scaffold-show" role="main">
    <h1>${question.content}</h1>

    <ol class="property-list question">
        <li class="fieldcontain">
            <span id="answerFormat-label" class="property-label">Answer Format</span>
            <span class="property-value" aria-labelledby="answerFormat-label">${ViewHelpers.answerFormatString(question.answerFormat)}</span>
        </li>

        <% if (question.answerFormat == 0 || question.answerFormat == 1) { %>
            <li class="fieldcontain">
                <span id="choices-label" class="property-label">Choices</span>
                <g:each in="${question.choices}">
                    <span class="property-value" aria-labelledby="choices-label">
                        <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${question.id}/choices/${it.id}/show">${it.content}</a>
                    </span>
                </g:each>
                <br />
                <span id="choices-label" class="property-label">
                    <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${question.id}/choices/create" class="create">Add&nbsp;choice</a>
                </span>
            </li>
        <% } %>
    </ol>

    <fieldset class="buttons">
        <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/show">Back to ${questionnaire.name}</a>
    </fieldset>
</div>

</body>
</html>