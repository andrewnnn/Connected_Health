<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Add choice</title>
</head>

<body>
<fieldset class="buttons">
    <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/show">Back to ${questionnaire.name}</a>
    <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${question.id}">Back to ${question.content}</a>
</fieldset>

<div id="create-question" class="content scaffold-create" role="main">
    <h1>Add choice</h1>

    <g:render template="/choices/form" locals="${[questionnaire: questionnaire, question: question]}" />
</div>
</body>
</html>