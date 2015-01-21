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

<div id="show-medicalNote" class="content scaffold-show" role="main">
    <h1>Question</h1>

    <ol class="property-list medicalNote">
        <li class="fieldcontain">
            <span id="content-label" class="property-label">Content</span>
            <span class="property-value" aria-labelledby="content-label">${question.content}</span>
        </li>

        <li class="fieldcontain">
            <span id="patient-label" class="property-label">Answer Format</span>
            <span class="property-value" aria-labelledby="patient-label">${question.answerFormat}</span>
        </li>
    </ol>

    <fieldset class="buttons">
        <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/show">Back to ${questionnaire.name}</a>
    </fieldset>
</div>

</body>
</html>