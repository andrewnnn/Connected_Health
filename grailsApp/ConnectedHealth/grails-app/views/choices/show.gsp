<html>
<head>
    <meta name="layout" content="main"/>
    <title>Choice</title>
</head>

<body>
<g:form url="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${question.id}/choices/${choice.id}/" method="DELETE">
    <fieldset class="buttons">
        <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${question.id}/choices/${choice.id}/edit" class="edit">Edit choice</a>
        <input type="submit" value="Delete choice" class="delete" onclick="return confirm('${message(code: 'use.default', default: 'Are you sure you want to delete this patient profile?')}');"/>
    </fieldset>
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

<fieldset class="buttons">
    <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${question.id}/show">Back to Question View</a>
</fieldset>
</div>

</body>
</html>