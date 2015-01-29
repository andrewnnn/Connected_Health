<html>
<head>
    <meta name="layout" content="main"/>
    <title>Choice</title>
</head>

<body>
<div class="questionnaireSection">
<g:form url="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${question.id}/choices/${choice.id}/" method="DELETE">

    <div class="nav" role="navigation">
        <fieldset class="buttons buttonsBlue">
            <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/show" class="list">Back to Questionnaire</a>
            <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${question.id}/show"
               class="list">View question</a>
            <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${question.id}/choices/${choice.id}/edit" class="edit">Edit choice</a>
            <input type="submit" value="Delete choice" class="delete"
                   onclick="return confirm('${message(code: 'useDefault', default: 'Are you sure you want to delete this choice?')}');"/>
    </fieldset>
</div>
</g:form>
<h1>${answerFormat}</h1>

<div id="show-question" class="content scaffold-show" role="main">
<h1>${choice.content}</h1>

<ol class="property-list question">
    <li class="fieldcontain">
        <span id="question-label" class="property-label">Choice</span>
        <span class="property-value" aria-labelledby="question-label"><p>${choice.content}</p></span>
    </li>
</ol>

</div>
</div>
</body>
</html>