<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Add choice</title>
</head>

<body>
<div class="questionnaireSection">
    <div class="nav" role="navigation">
        <fieldset class="buttons buttonsBlue">
            <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/show" class="list">Back to Questionnaire</a>
            <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${question.id}/show"
               class="list">View question</a>
        </fieldset>
    </div>

    <div id="create-question" class="content scaffold-create" role="main">
        <h1>Add choice</h1>

        <g:render template="/choices/form" locals="${[questionnaire: questionnaire, question: question]}"/>
    </div>
</div>
</body>
</html>