<html>
<head>
    <meta name="layout" content="main"/>
    <title>Choice</title>
</head>

<body>
<g:form url="[resource:question, action:'delete']" method="DELETE">
    <fieldset class="buttons">
        <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${question.id}/edit" class="edit">Edit question</a>
        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label.useDefault', default: 'Delete question')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message.patient', default: 'Are you sure you want to delete this question?')}');" />
    </fieldset>
</g:form>
<h1>${answerFormat}</h1>

<div id="show-question" class="content scaffold-show" role="main">
    <h1>${choice.content}</h1>

    <ol class="property-list question">
        <li class="fieldcontain">
            <span id="question-label" class="property-label">Question</span>
            <span class="property-value" aria-labelledby="question-label">${question.content}</span>
        </li>
    </ol>

    <fieldset class="buttons">
        <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/show">Back to ${questionnaire.name}</a>
    </fieldset>
</div>

</body>
</html>